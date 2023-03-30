package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("?msg=Exit")) {
                        server.close();
                    }
                    if (str.contains("?msg=Hello")) {
                        out.write("Hello, my friend!".getBytes());
                    } else {
                        String msg = str.substring(str.indexOf("=") + 1);
                        msg = msg.substring(0, msg.indexOf(" "));
                        out.write(msg.getBytes());
                    }
                    for (; str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
            throw new IOException("Unsupported code");
        } catch (IOException e) {
            LOG.error("There's an IOExcepti.", e);
        }
    }
}