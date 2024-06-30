import socket
import threading

def is_new_user(data):
    return len(data) == 41

def get_user(message):
    return message.split(" ")[0].strip(":")

def user_connected(connection, users):
    with connection:
        data = connection.recv(1024).decode()

        if is_new_user(data):
            users.append([data, connection])
            print("Zalogowano użytkownika: " + data)
            welcome_message = "Witamy nowego użytkownika!"
            connection.sendall(welcome_message.encode())

            while True:
                try:
                    received_message = connection.recv(1024).decode()
                    if not received_message:
                        break
                    current_user = get_user(received_message)

                    for user, user_connection in users:
                        if user != current_user:
                            user_connection.sendall(received_message.encode())

                except BlockingIOError:
                    continue

def udp_message(server_socket_udp, udp_users, server_ip):
    while True:
        buff, address = server_socket_udp.recvfrom(1024)
        user_message = buff.decode('utf-8')
        current_user = get_user(user_message)
        server_port = address[1]

        if is_new_user(user_message):
            udp_users.append([current_user, server_port])
        else:
            for user, user_port in udp_users:
                if user != current_user:
                    server_socket_udp.sendto(bytes(user_message, "utf-8"), (server_ip, user_port))

def main():
    print('Witamy na serwerze!\n')
    print("-------------------------\n")
    server_port = 9009
    server_ip = "127.0.0.1"
    users = []
    udp_users = []

    server_socket_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket_udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    server_socket_tcp.bind((server_ip, server_port))
    server_socket_udp.bind((server_ip, server_port))
    server_socket_tcp.listen()

    while True:
        connection, address = server_socket_tcp.accept()
        connection.setblocking(False)
        receive_thread_tcp = threading.Thread(target=lambda: user_connected(connection, users))
        receive_thread_udp = threading.Thread(target=lambda: udp_message(server_socket_udp, udp_users, server_ip))
        receive_thread_tcp.start()
        receive_thread_udp.start()
            

if __name__ == "__main__":
    main()
