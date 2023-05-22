import socket
import struct

# host = input("Server IP: ")
# port = input("Port: ")

host = "127.0.0.1"
port = "12345"

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((host, int(port)))
    while True:
        data = s.recv(12)
        if data:
            # '>f' means big endian float from java
            xAxis: float = struct.unpack('>f', data[0:4:])[0]
            yAxis: float = struct.unpack('>f', data[4:8])[0]
            zAxis: float = struct.unpack('>f', data[8:12])[0]
            print(f"x: {xAxis:.2f} [m/s^2]\n"
                  f"y: {yAxis:.2f} [m/s^2]\n"
                  f"z: {zAxis:.2f} [m/s^2]\n")
        else:
            print("Error")
            break
