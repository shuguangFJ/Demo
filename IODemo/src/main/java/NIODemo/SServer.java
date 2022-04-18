package NIODemo;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SServer extends Thread {

    private ServerSocket serverSocket;

    private Executor executor;

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(0);
            executor = Executors.newFixedThreadPool(8);
            while (true) {
                Socket socket = serverSocket.accept();
                ResponseHandler responseHandler = new ResponseHandler(socket);
                executor.execute(responseHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SServer sServer = new SServer();
        sServer.start();

        Socket socket = new Socket(InetAddress.getLocalHost(), sServer.getPort());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedReader.lines().forEach(l -> System.out.println(l));
    }
}

class ResponseHandler extends Thread {

    private Socket socket;

    public ResponseHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("hello world");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
