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
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;

    public String getMsg(){
        return msg;
    }

    public CommunicationServerSocket(int port){

        this.port=port;

        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public  void SocketSend(String msgsend){
        System.out.println("msgsend : " + msgsend);
        try  {
            System.out.println("vor die out.println");
            out.println(msgsend);
            System.out.println("vor die flush");
            out.flush();
            System.out.println("msgsent already : " + msgsend);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void SocketGet(){
        try {
            String inputLine;
            //while ((inputLine = in.readLine()) != null) {
            //    System.out.println("SS : "+inputLine);
            //    msg = inputLine;
            //    System.out.println("msg received " +msg);
            //}

            inputLine = in.readLine();
            msg = inputLine;
            System.out.println("msg received " +msg);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
    public void Close() {
        try {
            serverSocket.close();
            clientSocket.close();
            out.close();
            in.close();
            System.out.println("Alles Closed");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}