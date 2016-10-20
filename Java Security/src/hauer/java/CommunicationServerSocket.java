package hauer.java;

import java.net.Socket;
import java.net.ServerSocket;

import static sun.java2d.cmm.ColorTransform.In;
import static sun.java2d.cmm.ColorTransform.Out;

import java.io.*;
import java.net.*;
import java.lang.Thread;

/**
 * http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoServer.java
 */

import java.net.*;
import java.io.*;

public class CommunicationServerSocket{
    static int port;
    static String msg;

    public String getMsg(){
        return msg;
    }

    public CommunicationServerSocket(int port){
        this.port=port;
    }

    public static void SocketSend(String msgsend){
        System.out.println("msgsend : " + msgsend);
        try (
                ServerSocket serverSocket =
                        new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            out.println(msgsend);
            out.flush();
            System.out.println("msgsent already : " + msgsend);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void SocketGet(){
        try (
                ServerSocket serverSocket =
                        new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("SS : "+inputLine);
                msg = inputLine;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}