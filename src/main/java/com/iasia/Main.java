package com.iasia;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Start");

        final String host = "127.0.0.1";
        final int port = 25881;

        final String message = "KsHK;HzmGEM;DC" + (char)3 + (char)4;
        final byte[] bytes = message.getBytes();

        final int targetCount = 10_000_000;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected");

            OutputStream outputStream = socket.getOutputStream();

            final int iteration = 100;
            for (int i = 0; i < iteration; i++) {
                final int sendCount = targetCount / iteration;
                for (int j = 0; j < sendCount; j++) {
                    outputStream.write(bytes);
                    outputStream.flush();
                }

                int sentCount = (i + 1) * sendCount;
                System.out.println("Sent: " + sentCount
                        + " (" + (sentCount * 100 / targetCount) + "%)");
            }

            System.out.println("Messages sent successfully.");
        }
    }
}