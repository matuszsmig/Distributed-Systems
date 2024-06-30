import ray
import time
import random
from ray.exceptions import RayActorError

if ray.is_initialized:
    ray.shutdown()
ray.init(ignore_reinit_error=True)

@ray.remote
class DataNode:
    def __init__(self):
        self.chunks = []
        self.is_alive = True

    def add_chunk(self, value):
        self.chunks.append(value)

    def get_chunks_lenght(self):
        return len(self.chunks)

    def get_chunks(self):
        return self.chunks
    
    def is_node_alive(self):
        return self.is_alive

    def set_node_dead(self):
        self.is_alive = False
    
    def clear_chunk_value(self, index):
        self.chunks[index] = ""

@ray.remote
class NameNode:
    def __init__(self):
        self.artifacts = {}
        self.data_nodes = []
        for _ in range(3):
            data_node_copy = []
            for _ in range(3):
                data_node = DataNode.remote()
                data_node_copy.append(data_node)
            self.data_nodes.append(data_node_copy)

    def add_artifact(self, key, artifact):
        artifact_table = []
        for i in range(0, len(artifact), 3):
            artifact_element = artifact[i:i+3]
            artifact_element_table = []

            for j in range(len(self.data_nodes)):
                random_index = random.randint(0,2)
                self.data_nodes[j][random_index].add_chunk.remote(artifact_element)
                try:
                    if ray.get(self.data_nodes[j][random_index].is_node_alive.remote()):
                        data_node_index = ray.get(self.data_nodes[j][random_index].get_chunks_lenght.remote())
                except RayActorError:
                    self.data_nodes[j][random_index].set_node_dead.remote()
                    print("Actor is dead or not yet created.")
                artifact_element_table.append((self.data_nodes[j][random_index], data_node_index-1))

            artifact_table.append(artifact_element_table)

        self.artifacts[key] = artifact_table

    def remove_artifact(self, key):
        if key in self.artifacts:
            artifact = self.artifacts.get(key, [])
            for i in range(len(artifact)):
                for node_copy in artifact[i]:
                    index = node_copy[1]
                    node = node_copy[0]
                    node.clear_chunk_value.remote(index)
            del self.artifacts[key]
        else: 
            print("This artifact does not exists")

    def get_blocks(self, key):
        artifact_value = ""
        artifact = self.artifacts.get(key, [])
        for i in range(len(artifact)):
            for node_copy in artifact[i]:
                try:
                    if ray.get(node_copy[0].is_node_alive.remote()):
                        index = node_copy[1]
                        node = node_copy[0]
                        artifact_value += ray.get(node.get_chunks.remote())[index]
                        break
                except RayActorError:
                    node_copy[0].set_node_dead.remote()
                    print("Actor is dead or not yet created.")

        return artifact_value
    
    def list_data_nodes(self):
        for i in range(3):
            for j in range(3):
                try:
                    if ray.get(self.data_nodes[i][j].is_node_alive.remote()):
                        print(ray.get(self.data_nodes[i][j].get_chunks.remote()))
                except RayActorError:
                    self.data_nodes[i][j].set_node_dead.remote()
                    print("Actor is dead or not yet created.")     

    def kill_actor(self, index_i, index_j):
        ray.kill(self.data_nodes[index_i][index_j])

name_node = NameNode.remote()

welcome = """
Hello, to my own Hadoop Distributed File System :))

You may try this commands:
add <key> <value> - adds artifact by key with given value
update <key> <value> - updates artifact by key with new given value
delete <key> - deletes artifact with given key
show <key> - shows artifact with given key
list - lists data nodes in name node
kill <index1> <index> - kills actor on given indexes
exit - exits from program
"""
print(welcome)

while True:
    command_input = input(">")
    command_text = command_input.split(" ", 2)
    try:
        if len(command_text) < 1:
            raise ValueError("Inccorect command")
        command = command_text[0]

        if command == 'add':
            if len(command_text) < 3:           
                raise ValueError("Inccorect add command, expect <key> <value>")
            key = command_text[1]
            artifact = command_text[2]
            name_node.add_artifact.remote(key, artifact)
            print("added")

        elif command == 'update':
            if len(command_text) < 3:           
                raise ValueError("Inccorect update command, expect <key> <value>")
            key = command_text[1]
            artifact = command_text[2]
            name_node.remove_artifact.remote(key)
            name_node.add_artifact.remote(key, artifact)
            print("updated")

        elif command == 'delete':
            if len(command_text) < 2:           
                raise ValueError("Inccorect delete command, expect <key>")
            key = command_text[1]
            name_node.remove_artifact.remote(key)
            print("delted " + key)

        elif command == 'show':
            if len(command_text) < 2:           
                raise ValueError("Inccorect show command, expect <key>")
            key = command_text[1]
            print(ray.get(name_node.get_blocks.remote(key)))

        elif command == 'list':
            name_node.list_data_nodes.remote()
            
        elif command == 'kill':
            if len(command_text) != 3 or not command_text[1].isdigit() or not command_text[2].isdigit() or not (0 <= int(command_text[1]) <= 2) or not (0 <= int(command_text[2]) <= 2):           
                raise ValueError("Inccorect kill command, expect <index1>: number <index2>: number")
            index_i = int(command_text[1])
            index_j = int(command_text[2])
            name_node.kill_actor.remote(index_i, index_j)
        elif command == "exit":
            break

        else:
            print("unknowned command")

    except ValueError as error:
        print("Error: ", error)