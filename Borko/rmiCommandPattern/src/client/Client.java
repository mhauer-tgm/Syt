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
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// Einlesen der gew�nschten Anzahl an Nachkommastellen
			Scanner scanner = new Scanner(System.in);
			System.out.println("Willkommen! Wie viele Kommastellen von e wollen Sie berechnen?");
			String input = scanner.nextLine();
			int digits = Integer.parseInt(input);

			Registry registry = LocateRegistry.getRegistry(1234);

			DoSomethingService uRemoteObject = (DoSomethingService) registry.lookup("Service");
			System.out.println("Service found");

			//hinzuf�gen von einem Commandobj namens cc und aufrufen durch �bergabe an die doSomething Methode des uRemoteObjects
			Command rc = new RegisterCommand();
			Command lc = new LoginCommand();
			Command cc = new CalculationCommand(digits);
			uRemoteObject.doSomething(rc);
			uRemoteObject.doSomething(lc);
			uRemoteObject.doSomething(cc);
			
			
		} catch (RemoteException re) {
			System.err.println("Service not found?" + " Check your RMI-Registry!");
			re.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Service exception:");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
