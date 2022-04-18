package NIODemo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Selector;

public class NIOServer extends Thread {

    @Override
    public void run() {
        try (Selector selector = Selector.open()) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
