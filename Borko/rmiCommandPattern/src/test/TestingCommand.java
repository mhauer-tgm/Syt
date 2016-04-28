package test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import calculation.ECalc;

public class TestingCommand {

	/**
	 * Testet Ob JUnit prinizpiell Funktioniert indem er 1 mit 1 vergleicht
	 */
	@Test
	public void testest(){
		assertEquals(1,1,0);
	}
	
	/**
	 * Testet ob die Euler Kalkulation mit einer erwarteten Eingabe funktioniert
	 */
	@Test
	public void EulerBerechnungTestGroesser0(){
		ECalc dt = new ECalc();
		dt.calculate(3);
		BigDecimal result = dt.getResult();
		double compare = result.doubleValue();
		assertEquals(compare,2.718,0);
	}
	
	/**
	 * Testet ob die Euler Kalkulation mit einer eher unerwarteten eingabe von 0 funktioniert
	 */
	@Test
	public void EulerBerechnungTest0(){
		ECalc dt = new ECalc();
		dt.calculate(0);
		BigDecimal result = dt.getResult();
		int compare = result.intValue();
		assertEquals(compare,2,0);
	}
	
	
}
