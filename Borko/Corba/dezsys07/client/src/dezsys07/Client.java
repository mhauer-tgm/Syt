package dezsys07;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;
import calc.Calculator;
import calc.CalculatorHelper;
import calc.CalculatorPackage.DivideZeroException;

/**
 * @author Hagen Aad Fock <hagen.fock@gmail.com>
 * @version 13.03.2015
 * 
 * Ruft die Echo Methode des C++ Servers auf und gibt einen String auf der Konsole aus.
 * Sollte ein Fehler aufgetreten sein, so wird eine Exception geworfen und eine Fehlermeldung zusammen mit dem Stracktrace auf der Konsole ausgegeben.
 */
public class Client {
	public static void main(String[] args)  {
		Calculator calculator;
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
			name[1] = new NameComponent("Calculator", "Object");
			
			/* Aufloesen der Objektreferenzen  */
			calculator = CalculatorHelper.narrow(rootContext.resolve(name));
			
			System.out.println("5 * 5 = "+calculator.mul(5,5));
			System.out.println("5 - 5 = "+calculator.sub(5,5));
			System.out.println("5 + 5 = "+calculator.add(5,5));
			try{
				System.out.println("5/0 = "+calculator.div(5,0));
			}catch(DivideZeroException e){
				System.out.println("Es ist ein Fehler aufgetreten weil die Division mit 0 nicht definiert ist.");
			}

			
		}	catch (Exception e)	{
			System.err.println("Es ist ein Fehler aufgetreten: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
