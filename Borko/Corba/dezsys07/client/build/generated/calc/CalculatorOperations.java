package calc;


/**
 * Generated from IDL interface "Calculator".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at May 19, 2016 4:09:39 PM
 */

public interface CalculatorOperations
{
	/* constants */
	/* operations  */
	int mul(int a, int b);
	int sub(int a, int b);
	int add(int a, int b);
	double div(int a, int b) throws calc.CalculatorPackage.DivideZeroException;
}
