package server.commands;

import java.io.Serializable;
import calculation.Calculation;
import calculation.ECalc;

public class CalculationCommand implements Command, Serializable {

	private static final long serialVersionUID = 3202369269194172790L;
	private Calculation calc;
	private int digits;

	public CalculationCommand(int digits) {
		this.digits = digits;
		calc = new ECalc();
	}

	@Override
	public void execute() {
		// System.out.println("CalculationCommand called!");
		calc.calculate(digits);
		calc.getResult();
	}
}
