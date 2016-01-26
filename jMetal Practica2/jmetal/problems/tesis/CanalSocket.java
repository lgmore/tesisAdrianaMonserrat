/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmetal.problems.tesis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmetal.metaheuristics.smpso.SMPSOTesis;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author lgmore
 */
public class CanalSocket {

    int port;
    public static SocketChannel canalSocket;
    static Logger log;

    static {
        PropertyConfigurator.configure("logger.properties");
        log = Logger.getLogger(CanalSocket.class.getName());
        //setDimensionesImagen();

    }

    public CanalSocket(int puerto) {

        try {
            this.port = puerto;
            canalSocket = SocketChannel.open();
            // we open this channel in non blocking mode
            canalSocket.configureBlocking(false);
            canalSocket.connect(new InetSocketAddress("localhost", port));
            while (!canalSocket.finishConnect()) {
                // System.out.println("still connecting");
            }
        } catch (IOException ex) {
            Logger.getLogger(CanalSocket.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }

    }

    public String enviarMensaje(String mensaje) {
        String respuesta = "";
        try {
            
            if (!mensaje.isEmpty()) {
                
                CharBuffer buffer = CharBuffer.wrap(mensaje);
                while (buffer.hasRemaining()) {
                    canalSocket.write(Charset.defaultCharset().encode(buffer));
                    
                }
            }
            log.info("ahora voy a leer");
            ByteBuffer bufferA = ByteBuffer.allocate(50);
            int count = 0;
            //aca tengo que esperar la respuesta
            do {
                //System.out.println();
            } while ((count = canalSocket.read(bufferA)) == 0);
            System.out.println();
//            while ((count = channel.read(bufferA)) > 0) {
            // flip the buffer to start reading
            bufferA.flip();
            respuesta += Charset.defaultCharset().decode(bufferA);
            log.info(respuesta);
            
            
        } catch (IOException ex) {
            Logger.getLogger(CanalSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public void close() {

        try {
            canalSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(CanalSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
