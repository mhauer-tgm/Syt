package calc.CalculatorPackage;

/**
 * Generated from IDL exception "DivideZeroException".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at May 19, 2016 4:09:39 PM
 */

public final class DivideZeroExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public calc.CalculatorPackage.DivideZeroException value;

	public DivideZeroExceptionHolder ()
	{
	}
	public DivideZeroExceptionHolder(final calc.CalculatorPackage.DivideZeroException initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return calc.CalculatorPackage.DivideZeroExceptionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = calc.CalculatorPackage.DivideZeroExceptionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		calc.CalculatorPackage.DivideZeroExceptionHelper.write(_out, value);
	}
}
