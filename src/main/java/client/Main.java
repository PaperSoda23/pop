package client;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String ip = "127.0.0.1";
    private static final int port = 3334;

    public static void main(String[] args) {
        try {
            var scanner = new Scanner(System.in);
            var client = new Client(ip, port);
            client.connect(scanner::nextLine, System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
