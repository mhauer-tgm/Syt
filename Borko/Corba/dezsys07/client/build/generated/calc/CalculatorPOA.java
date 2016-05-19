package calc;


/**
 * Generated from IDL interface "Calculator".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at May 19, 2016 4:09:39 PM
 */

public abstract class CalculatorPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, calc.CalculatorOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "sub", Integer.valueOf(0));
		m_opsHash.put ( "div", Integer.valueOf(1));
		m_opsHash.put ( "add", Integer.valueOf(2));
		m_opsHash.put ( "mul", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:calc/Calculator:1.0"};
	public calc.Calculator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		calc.Calculator __r = calc.CalculatorHelper.narrow(__o);
		return __r;
	}
	public calc.Calculator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		calc.Calculator __r = calc.CalculatorHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // sub
			{
				int _arg0=_input.read_long();
				int _arg1=_input.read_long();
				_out = handler.createReply();
				_out.write_long(sub(_arg0,_arg1));
				break;
			}
			case 1: // div
			{
			try
			{
				int _arg0=_input.read_long();
				int _arg1=_input.read_long();
				_out = handler.createReply();
				_out.write_double(div(_arg0,_arg1));
			}
			catch(calc.CalculatorPackage.DivideZeroException _ex0)
			{
				_out = handler.createExceptionReply();
				calc.CalculatorPackage.DivideZeroExceptionHelper.write(_out, _ex0);
				if (handler instanceof org.jacorb.orb.dsi.ServerRequest && !calc.CalculatorPackage.DivideZeroExceptionHelper.id().equals(_ex0.getMessage()))
				{
					((org.jacorb.orb.giop.ReplyOutputStream)_out).addServiceContext (org.jacorb.orb.dsi.ServerRequest.createExceptionDetailMessage (_ex0.getMessage()));
				}
			}
				break;
			}
			case 2: // add
			{
				int _arg0=_input.read_long();
				int _arg1=_input.read_long();
				_out = handler.createReply();
				_out.write_long(add(_arg0,_arg1));
				break;
			}
			case 3: // mul
			{
				int _arg0=_input.read_long();
				int _arg1=_input.read_long();
				_out = handler.createReply();
				_out.write_long(mul(_arg0,_arg1));
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
