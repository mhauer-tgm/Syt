package test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.rmi.ConnectException;

import org.junit.Test;

import client.Pi;

public class TestingNoCommand {

	/**
	 * Testet Ob JUnit prinizpiell Funktioniert indem er 1 mit 1 vergleicht
	 */
	@Test
	public void testest() {
		assertEquals(1, 1, 0);
	}

	/**
	 * Testet ob die Euler Kalkulation mit einer erwarteten Eingabe funktioniert
	 */
	@Test
	public void PiBerechnungTestGroesser0() {
		Pi dt = new Pi(3);
		BigDecimal result = dt.execute();
		double compare = result.doubleValue();
		assertEquals(compare, 3.142, 0);
	}

	/**
	 * Testet ob die Euler Kalkulation mit einer eher unerwarteten eingabe von 0
	 * funktioniert
	 */
	@Test
	public void PiBerechnungTest0() {
		Pi dt = new Pi(0);

		BigDecimal result = dt.execute();
		int compare = result.intValue();
		assertEquals(compare, 3, 0);
	}

	/**
	 * Testet ob die Euler Kalkulation mit einer eingabe <0 richtig umgeht
	 */
	@Test(expected = ConnectException.class)  
	public void PiBerechnungTestKleiner0() {
		Pi dt = new Pi(-2);

		BigDecimal result = dt.execute();
		int compare = result.intValue();
		assertEquals(compare, 3, 0);
	}
}
