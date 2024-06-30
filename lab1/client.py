import socket
import threading
import uuid

def send_message(user_nick, client_socket_tcp, client_socket_udp, lock, server_ip, server_port):
    while True:
        terminal_input = str(input())
        if (terminal_input == "U"):
            print("Teraz jesteś w trybie wykorzystującym protokół UDP, spróbuj wysłać ASCII Art'a :)")
            terminal_udp_input = str(input())
            user_message = user_nick + ": " + terminal_udp_input
            with lock:
                client_socket_udp.sendto(bytes(user_message, 'utf-8'), (server_ip, server_port))
        else:
            user_message = user_nick + ": " + terminal_input
            with lock:
                client_socket_tcp.sendall(user_message.encode())

def recive_message_tcp(client_socket_tcp, lock):
    while True:
        received_message = client_socket_tcp.recv(1024).decode()
        with lock:
            print(received_message)

def receive_message_udp(client_socket_udp, lock):
    while True:
        received_message, address = client_socket_udp.recvfrom(1024)
        with lock:
            print("Przesłano z UDP: " + str(received_message))

def connect_to_server_tcp(server_ip, server_port, user_nick):
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((server_ip, server_port))
    client_socket.sendall(user_nick.encode())
    welcome_message = client_socket.recv(1024).decode()
    print(welcome_message + "\n")
    return client_socket

def connect_to_server_udp(server_ip, server_port, user_nick):
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    client_socket.sendto(bytes(user_nick, "utf-8"), (server_ip, server_port))
    return client_socket

def main():
    client_uuid = str(uuid.uuid4())
    server_ip = "127.0.0.1"
    server_port = 9009
    user_nick = "user-" + client_uuid

    lock = threading.Lock()
    client_socket_tcp = connect_to_server_tcp(server_ip, server_port, user_nick)
    client_socket_udp = connect_to_server_udp(server_ip, server_port, user_nick)
   
    send_message_thread = threading.Thread(target=lambda: send_message(user_nick, client_socket_tcp, client_socket_udp, lock, server_ip, server_port))
    receive_message_tcp_thread = threading.Thread(target=lambda: recive_message_tcp(client_socket_tcp, lock))
    receive_message_udp_thread = threading.Thread(target=lambda: receive_message_udp(client_socket_udp, lock))
    send_message_thread.start()
    receive_message_tcp_thread.start()
    receive_message_udp_thread.start()

if __name__ == "__main__":
    main()


"""
Tu mamy ascii art'a do testów :)

             ,
       (`.  : \               __..----..__
        `.`.| |:          _,-':::''' '  `:`-._
          `.:\||       _,':::::'         `::::`-.
            \\`|    _,':::::::'     `:.     `':::`.
             ;` `-''  `::::::.                  `::\
          ,-'      .::'  `:::::.         `::..    `:\
        ,' /_) -.            `::.           `:.     |
      ,'.:     `    `:.        `:.     .::.          \
 __,-'   ___,..-''-.  `:.        `.   /::::.         |
|):'_,--'           `.    `::..       |::::::.      ::\
 `-'                 |`--.:_::::|_____\::::::::.__  ::|
                     |   _/|::::|      \::::::|::/\  :|
                     /:./  |:::/        \__:::):/  \  :\
                   ,'::'  /:::|        ,'::::/_/    `. ``-.__
                  ''''   (//|/\      ,';':,-'         `-.__  `'--..__
                                                           `''---::::'
"""