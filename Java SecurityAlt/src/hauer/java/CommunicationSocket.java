package hauer.java;

import java.net.Socket;

import static sun.java2d.cmm.ColorTransform.In;
import static sun.java2d.cmm.ColorTransform.Out;

import java.io.*;
import java.net.*;

/**
 * http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoClient.java
 */
public class CommunicationSocket {

    static String name;
    static int port;
    static String msg;

    public String getMsg(){
        return msg;
    }

    public CommunicationSocket(String name, int port, String msg){
        this.name = name;
        this.port = port;
        this.msg = msg;
    }

    public void SocketGet() throws IOException{
        System.out.println("msg beginn socket get : "+msg);
        this.msg = null;
        System.out.println("bist du dieser communicationsocket heering vor dem try gedöns? msg :"+msg);
        try (
                Socket echoSocket = new Socket(name, port);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in));

        ) {
            System.out.println("bist du dieser communicationsocket heering nach dem try gedöns?");
            String inputLine;
            System.out.println("bist du dieser communicationsocket heering nach dem try gedöns nach dem String inputLine?");
            while(true) {
                inputLine = in.readLine();
                System.out.println("hallo while | input :"+inputLine);
                if((inputLine) != null) {
                    System.out.println("CS msg empfangen : " + inputLine);
                    msg = inputLine;
                    break;
                } else {
                    System.out.println("socket while true if else");
                }
            }
        }

    }
    public void SocketSend() throws IOException {
        //new CommunicationSocket("127.0.0.1",4444,"hallo kommunikation");
        System.out.println(port + " | " + name);

        try (
                Socket echoSocket = new Socket(name, port);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in));

        ) {
           // System.out.println("msg : "+msg);
            out.println(msg);
            out.flush();


        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Don't know about host " + name);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Couldn't get I/O for the connection to " +
                    name);
            System.exit(1);
        }


    }
}