package NIODemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.Selector;
import java.nio.charset.Charset;

public class NIO2 extends Thread {

    @Override
    public void run() {
        try (AsynchronousServerSocketChannel listener =
                     AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9876))) {
            listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    listener.accept(null,this);
                    sayHello(result);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHello(AsynchronousSocketChannel result){
        result.write(Charset.defaultCharset().encode("hello !"));
    }

    public static void main(String[] args) throws IOException {
        NIO2 nio2 = new NIO2();
        nio2.start();

//        Socket socket = new Socket(InetAddress.getLocalHost(), 9876);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        bufferedReader.lines().forEach(l-> System.out.println(l));
    }
}
