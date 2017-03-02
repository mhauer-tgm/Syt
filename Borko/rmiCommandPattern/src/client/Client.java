package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import remoteService.DoSomethingService;
import server.commands.CalculationCommand;
import server.commands.Command;
import server.commands.LoginCommand;
import server.commands.RegisterCommand;

public class Client {

	public static void main(String[] args) {
		int digits = -1;
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// Einlesen der gewünschten Anzahl an Nachkommastellen
			Scanner scanner = new Scanner(System.in);
			do{
			System.out.println("Willkommen! Wie viele Kommastellen von e wollen Sie berechnen? Bitte bemühen sie sich um eine positive, ganzzahlige Zahl.");
			String input = scanner.nextLine();
			digits = Integer.parseInt(input);
			} while (digits < 0);
			
			Registry registry = LocateRegistry.getRegistry(1234);
			DoSomethingService uRemoteObject = (DoSomethingService) registry.lookup("Service");
			System.out.println("Service found");

			//hinzufügen von einem Commandobj namens cc und aufrufen durch übergabe an die doSomething Methode des uRemoteObjects
			Command rc = new RegisterCommand();
			Command lc = new LoginCommand();
			Command cc = new CalculationCommand(digits);
			uRemoteObject.doSomething(rc);
			uRemoteObject.doSomething(lc);
			uRemoteObject.doSomething(cc);
			
			
		} catch (RemoteException re) {
			System.err.println("Service not found?" + " Check your RMI-Registry!");
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Service exception:");
			System.exit(1);
		}
	}
}
