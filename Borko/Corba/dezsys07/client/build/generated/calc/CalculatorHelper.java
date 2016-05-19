package calc;


/**
 * Generated from IDL interface "Calculator".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at May 19, 2016 4:09:39 PM
 */

public abstract class CalculatorHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(CalculatorHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:calc/Calculator:1.0", "Calculator");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final calc.Calculator s)
	{
			any.insert_Object(s);
	}
	public static calc.Calculator extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:calc/Calculator:1.0";
	}
	public static Calculator read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(calc._CalculatorStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final calc.Calculator s)
	{
		_out.write_Object(s);
	}
	public static calc.Calculator narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof calc.Calculator)
		{
			return (calc.Calculator)obj;
		}
		else if (obj._is_a("IDL:calc/Calculator:1.0"))
		{
			calc._CalculatorStub stub;
			stub = new calc._CalculatorStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static calc.Calculator unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof calc.Calculator)
		{
			return (calc.Calculator)obj;
		}
		else
		{
			calc._CalculatorStub stub;
			stub = new calc._CalculatorStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
