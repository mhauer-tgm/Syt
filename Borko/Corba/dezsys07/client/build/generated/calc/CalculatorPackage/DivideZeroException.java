package calc.CalculatorPackage;

/**
 * Generated from IDL exception "DivideZeroException".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at May 19, 2016 4:09:39 PM
 */

public final class DivideZeroException
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public DivideZeroException()
	{
		super(calc.CalculatorPackage.DivideZeroExceptionHelper.id());
	}

	public java.lang.String reason = "";
	public DivideZeroException(java.lang.String _reason,java.lang.String reason)
	{
		super(_reason);
		this.reason = reason;
	}
	public DivideZeroException(java.lang.String reason)
	{
		super(calc.CalculatorPackage.DivideZeroExceptionHelper.id());
		this.reason = reason;
	}
}
