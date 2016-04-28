package calculation;

import java.math.BigDecimal;

public interface Calculation<T> {

	public void calculate(int digits);
	public T getResult();
}
