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
    Socket echoSocket;
    PrintWriter out;
    BufferedReader in;
    BufferedReader stdIn;

    public String getMsg(){
        return msg;
    }

    public CommunicationSocket(String name, int port, String msg){
        this.name = name;
        this.port = port;
        this.msg = msg;


        try {
            echoSocket = new Socket(name, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void SocketGet(){
        System.out.println("msg beginn socket get : "+msg);
        this.msg = null;
        System.out.println("bist du dieser communicationsocket heering vor dem try gedöns? msg :"+msg);
        try  {
            System.out.println("bist du dieser communicationsocket heering nach dem try gedöns?");
            String inputLine;
            System.out.println("bist du dieser communicationsocket heering nach dem try gedöns nach dem String inputLine?");
            while(true) {
                System.out.println("hallo while ");
                inputLine = in.readLine();
                if((inputLine) != null) {
                    System.out.println("CS msg empfangen : " + inputLine);
                    msg = inputLine;
                    break;
                } else {
                    System.out.println("socket while true if else");
                }
            }

            System.out.println("hier nach der while");
            Close();
        } catch(Exception e){
            e.printStackTrace();
        }


    }
    public void SocketSend(){
        //new CommunicationSocket("127.0.0.1",4444,"hallo kommunikation");
        System.out.println(port + " | " + name);

            out.println(msg);
            out.flush();
    }
    public void Close() {
        try {
            echoSocket.close();
            out.close();
            in.close();
            stdIn.close();
            System.out.println("Alles Closed");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}