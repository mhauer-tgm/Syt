package calc;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Calculator".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at May 19, 2016 4:09:39 PM
 */

public class CalculatorPOATie
	extends CalculatorPOA
{
	private CalculatorOperations _delegate;

	private POA _poa;
	public CalculatorPOATie(CalculatorOperations delegate)
	{
		_delegate = delegate;
	}
	public CalculatorPOATie(CalculatorOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public CalculatorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(CalculatorOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public int sub(int a, int b)
	{
		return _delegate.sub(a,b);
	}

	public double div(int a, int b) throws calc.CalculatorPackage.DivideZeroException
	{
		return _delegate.div(a,b);
	}

	public int add(int a, int b)
	{
		return _delegate.add(a,b);
	}

	public int mul(int a, int b)
	{
		return _delegate.mul(a,b);
	}

}
