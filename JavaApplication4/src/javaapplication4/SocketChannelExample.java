package javaapplication4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class SocketChannelExample {

    public static void main(String[] args) throws IOException,
            InterruptedException {
        int port = 5555;
        SocketChannel channel = SocketChannel.open();

        // we open this channel in non blocking mode
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("localhost", port));

        while (!channel.finishConnect()) {
            // System.out.println("still connecting");
        }
        String message = "";
        while (true) {

            message = "";
//            //}
//            // see if any message has been received
//            ByteBuffer bufferA = ByteBuffer.allocate(20);
//            int count = 0;
//
//            while ((count = channel.read(bufferA)) > 0) {
//                // flip the buffer to start reading
//                bufferA.flip();
//                message += Charset.defaultCharset().decode(bufferA);
//
//            }
            message = "hola mundo asdfasdf";
            //if (message.length() > 0) {
            System.out.println(message);
            // write some data into the channel

            if (!message.isEmpty()) {

                CharBuffer buffer = CharBuffer.wrap("hola mundo asdfasdf");
                while (buffer.hasRemaining()) {
                    channel.write(Charset.defaultCharset().encode(buffer));

                }

            }

            break;

        }
        Thread.sleep(5000);
        System.out.println("ahora voy a leer");
        ByteBuffer bufferA = ByteBuffer.allocate(20);
        int count = 0;

        while ((count = channel.read(bufferA)) > 0) {
            // flip the buffer to start reading
            bufferA.flip();
            message += Charset.defaultCharset().decode(bufferA);
            System.out.println(message);
        }

    }
}
