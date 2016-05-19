package helloworld;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import org.omg.BiDirPolicy.BIDIRECTIONAL_POLICY_TYPE;
import org.omg.BiDirPolicy.BOTH;
import org.omg.BiDirPolicy.BidirectionalPolicyValueHelper;
import org.omg.CORBA.Any;
import org.omg.CORBA.Policy;
import org.omg.PortableServer.IdAssignmentPolicyValue;
import org.omg.PortableServer.ImplicitActivationPolicyValue;
import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
/**
 * @author Hagen Aad Fock <hagen.fock@gmail.com>
 * @version 13.03.2015
 * 
 * Ruft die Echo Methode des C++ Servers auf und gibt einen String auf der Konsole aus.
 * Sollte ein Fehler aufgetreten sein, so wird eine Exception geworfen und eine Fehlermeldung zusammen mit dem Stracktrace auf der Konsole ausgegeben.
 */
public class Client {
	public static void main(String[] args)  {
		Echo echo;
		try {
			
			/* Erstellen und intialisieren des ORB */
			ORB orb = ORB.init(args, null);
			
			/* Erhalten des RootContext des angegebenen Namingservices */
			Object o = orb.resolve_initial_references("NameService");
			
			/* Verwenden von NamingContextExt */
			NamingContextExt rootContext = NamingContextExtHelper.narrow(o);
			
			/* Angeben des Pfades zum Echo Objekt */
			NameComponent[] name = new NameComponent[2];
			name[0] = new NameComponent("test","my_context");
			name[1] = new NameComponent("Echo", "Object");
			
			/* Aufloesen der Objektreferenzen  */
			echo = ServerHelper.narrow(rootContext.resolve(name));
			POA rootp = (POA) orb.resolve_initial_references("root poa");
			rootp.thePOAManager().acitave();
			CallBack cbcl = callbackHelper.narrow(rootp.servant_to_reference(new Client());

			short period = 3;
			echo.one_time(cbcl,"Aufruf Callback 1 mal");
			echo.register(cbcl,"Aufruf Callback x mal",period);
			System.out.println("Der Server sagt: " + echo.echoString("Hallo Welt!"));

		}	catch (Exception e)	{
			System.err.println("Es ist ein Fehler aufgetreten: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void call_back(String msg){
		System.out.println("Msg :"+msg);
	}
}
