package calc;

/**
 * Generated from IDL interface "Calculator".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at May 19, 2016 4:09:39 PM
 */

public final class CalculatorHolder	implements org.omg.CORBA.portable.Streamable{
	 public Calculator value;
	public CalculatorHolder()
	{
	}
	public CalculatorHolder (final Calculator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CalculatorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CalculatorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CalculatorHelper.write (_out,value);
	}
}
