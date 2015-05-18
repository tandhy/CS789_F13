package cryptography;

public class fastExponentiation implements basicAlgorithm {
	private int finalResult;
		
	// empty constructor
	public fastExponentiation() {
		
	}
	
	/**
	 * as a constructor
	 * get a Result with x^e mod z, with given integer input
	 * @param x number  to raise the power of e
	 * @param e power of the number x
	 * @param z modulo
	 * @return Y the result
	 * @author tandhy
	 * date 11.11.13
	 */
	public fastExponentiation(int x, int e, int z, boolean withOutput)
	{
		// equation will be x^e mod z = result
		int Y = 1;
		if(withOutput) {
			showMsg("Fast Exponential Algorithm", true);
			showMsg("x | e | Y", true);
			showMsg("---------------", true);
			showMsg(x + " | " + e + " | " + Y, true);
		}
		
		while (e>0)
		{
			if(e%2==0)
			{
				// if e is even
				x = (int) ((Math.pow(x, 2)) % z); 
				e = e/2;
			}
			else
			{
				// if e is odd
				Y = (x*Y) % z;
				e = e - 1;
			}
			if(withOutput) showMsg(x + " | " + e + " | " + Y, true);
		}
		
		finalResult = Y;
	}
	
	/**
	 * as a constructor
	 * get a Result with x^e mod z, with given LONG input
	 * @param x number  to raise the power of e
	 * @param e power of the number x
	 * @param z modulo
	 * @return Y the result
	 * @author tandhy
	 * date 11.11.13
	 */
	public fastExponentiation(long x, long e, long z, boolean withOutput) {
		// TODO Auto-generated constructor stub
		// equation will be x^e mod z = result
		int Y = 1;
		if(withOutput) {
			showMsg("Fast Exponential Algorithm", true);
			showMsg("x | e | Y", true);
			showMsg("---------------", true);
			showMsg(x + " | " + e + " | " + Y, true);
		}
		
		while (e>0)
		{
			if(e%2==0)
			{
				// if e is even
				x = (int) ((Math.pow(x, 2)) % z); 
				e = e/2;
			}
			else
			{
				// if e is odd
				Y = (int) ((x*Y) % z);
				e = e - 1;
			}
			if(withOutput) showMsg(x + " | " + e + " | " + Y, true);
		}
		
		finalResult = Y;
	}

	/**
	 * get a Result with x^e mod z, with given LONG input
	 * @param x number  to raise the power of e
	 * @param e power of the number x
	 * @param z modulo
	 * @return Y the result
	 * @author tandhy
	 * date 11.11.13
	 */
	public int runFastExponentiation(long x, long e, long z, boolean withOutput)
	{
		// equation will be x^e mod z = result
		int Y = 1;
		if(withOutput) {
			showMsg("Fast Exponential Algorithm", true);
			showMsg("x | e | Y", true);
			showMsg("---------------", true);
			showMsg(x + " | " + e + " | " + Y, true);
		}
		
		while (e>0)
		{
			if(e%2==0)
			{
				// if e is even
				x = (int) ((Math.pow(x, 2)) % z); 
				e = e/2;
			}
			else
			{
				// if e is odd
				Y = (int) ((x*Y) % z);
				e = e - 1;
			}
			if(withOutput) showMsg(x + " | " + e + " | " + Y, true);
		}
		
		return Y;
	}
	
	/**
	 * get a Result with x^e mod z, with given INTEGER input
	 * @param x number  to raise the power of e
	 * @param e power of the number x
	 * @param z modulo
	 * @return Y the result
	 * @author tandhy
	 * date 11.11.13
	 */
	public int runFastExponentiation(int x, int e, int z, boolean withOutput)
	{
		// equation will be x^e mod z = result
		int Y = 1;
		if(withOutput) {
			showMsg("Fast Exponential Algorithm", true);
			showMsg("x | e | Y", true);
			showMsg("---------------", true);
			showMsg(x + " | " + e + " | " + Y, true);
		}
		
		while (e>0)
		{
			if(e%2==0)
			{
				// if e is even
				x = (int) ((Math.pow(x, 2)) % z); 
				e = e/2;
			}
			else
			{
				// if e is odd
				Y = (x*Y) % z;
				e = e - 1;
			}
			if(withOutput) showMsg(x + " | " + e + " | " + Y, true);
		}
		
		return Y;
		
	}
	
	/**
	 * @return finalResult
	 * @author tandhy
	 */
	public int getFinalResult()
	{
		return finalResult;
	}

	public void showMsg(String aMsg,boolean newLine) {
		if(newLine)
			System.out.println(aMsg);
		else
			System.out.print(aMsg);
		
	}
	public void showMsg(double aMsg, boolean newLine) {
		if(newLine)
			System.out.println(aMsg);
		else
			System.out.print(aMsg);
		
	}
}