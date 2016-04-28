package calculation;

import java.io.Serializable;
import java.math.BigDecimal;

public class ECalc implements Calculation<BigDecimal>, Serializable {

	private BigDecimal solution;
	private int polylength = 10;

	@Override
	public void calculate(int digits) {
		BigDecimal e = BigDecimal.ONE;
		int fact = 1;
		BigDecimal ww; 
		BigDecimal one = BigDecimal.ONE;
		
		for (int i = 1; i <= polylength; i++) {
			fact = fact*i;
			ww = one.divide(BigDecimal.valueOf(fact), digits, BigDecimal.ROUND_HALF_DOWN);
			e = e.add(ww);
		}
		solution = e;
		System.out.println(solution);
	}

	@Override
	public BigDecimal getResult() {
		return solution;

	}
}
