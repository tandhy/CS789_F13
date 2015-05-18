package cryptography;

public class GCD implements basicAlgorithm {
	// private variables
	private global globalGCD;
	
	// empty constructor
	public GCD()
	{
		// empty
	}
	
	/**
	 * a constructor
	 * find the GCD of m and n, where m > n
	 * @param m
	 * @param n
	 * @param withOutput : to output the string in console
	 * @author tandhy
	 */
	public GCD(long m, long n, boolean withOutput)
	{
		int totalStep = (int) Math.floor(Math.sqrt(m));
		
		long tmpM[] = new long[totalStep];
		long tmpN[] = new long[totalStep];
		long tmpMulti[] = new long[totalStep];
		long tmpRemaining[] = new long[totalStep];
				
		int i = 0;
		long remaining = n;
		long multi = 0;
		
		while ( remaining != 0 )
		{
			tmpM[i] = m;
			tmpN[i] = n;
			remaining = tmpM[i] % tmpN[i];
			multi = (int) Math.floor(tmpM[i] / tmpN[i]);
			tmpMulti[i] = multi;
			tmpRemaining[i] = remaining;
			if(withOutput) showMsg(tmpM[i] + " = " + tmpMulti[i] + "." + tmpN[i] + " + " + tmpRemaining[i], true);
			m = n;
			n = remaining;
			i += 1;
		}

		long dataM[] = new long[i];
		long dataN[] = new long[i];
		long dataMulti[] = new long[i];
		long dataRemaining[] = new long[i];

		for(int j = 0 ; j < i ; j++ )
		{
			dataM[j] = tmpM[j];
			dataN[j] = tmpN[j];
			dataMulti[j] = tmpMulti[j];
			dataRemaining[j] = tmpRemaining[j];
		}

		// assign dataM and dataN to global
		//global Global = new global(dataM, dataN, dataMulti, dataRemaining);
		// assign to globalGCD
		globalGCD = new global(dataM, dataN, dataMulti, dataRemaining);
		
	}
	
	/**
	 * Find whether m is relatively prime to n
	 * @param m
	 * @param n
	 * @param withOutput
	 * @return boolean
	 * @author tandhy
	 */
	public boolean isRelativelyPrime(long m, long n, boolean withOutput)
	{
		boolean isRelativelyPrime;
		isRelativelyPrime = false;
		
		try 
		{	
			int totalStep = (int) Math.floor(Math.sqrt(m)) + 2;
			
			long tmpM[] = new long[totalStep];
			long tmpN[] = new long[totalStep];
			int tmpMulti[] = new int[totalStep];
			long tmpRemaining[] = new long[totalStep];
					
			int i = 0;
			long remaining = n;
			int multi = 0;
			isRelativelyPrime = false;
			
			while ( remaining != 0 )
			{
				tmpM[i] = m;
				tmpN[i] = n;
				remaining = tmpM[i] % tmpN[i];
				multi = (int) Math.floor(tmpM[i] / tmpN[i]);
				tmpMulti[i] = multi;
				tmpRemaining[i] = remaining;
				if(withOutput) showMsg(tmpM[i] + " = " + tmpMulti[i] + "." + tmpN[i] + " + " + tmpRemaining[i], true);
				m = n;
				n = remaining;
				i += 1;
			}
	
			if( tmpN[i-1] == 1 ) 
				isRelativelyPrime = true;
			else
				isRelativelyPrime = false;

		} catch (Exception e) {
			// TODO: handle exception
			//showMsg(e.getMessage(),true);
		}

		return isRelativelyPrime;
	}
	
	/**
	 * NOT a constructor
	 * find the GCD of m and n, where m > n
	 * @param m
	 * @param n
	 * @param withOutput : to output the string in console
	 * @author tandhy
	 */
	public void runGCD(long m, long n, boolean withOutput)
	{
		int totalStep = (int) Math.floor(Math.sqrt(m));
		
		long tmpM[] = new long[totalStep];
		long tmpN[] = new long[totalStep];
		long tmpMulti[] = new long[totalStep];
		long tmpRemaining[] = new long[totalStep];
				
		int i = 0;
		long remaining = n;
		long multi = 0;
		
		while ( remaining != 0 )
		{
			tmpM[i] = m;
			tmpN[i] = n;
			remaining = tmpM[i] % tmpN[i];
			multi = (int) Math.floor(tmpM[i] / tmpN[i]);
			tmpMulti[i] = multi;
			tmpRemaining[i] = remaining;
			if(withOutput) showMsg(tmpM[i] + " = " + tmpMulti[i] + "." + tmpN[i] + " + " + tmpRemaining[i], true);
			m = n;
			n = remaining;
			i += 1;
		}

		long dataM[] = new long[i];
		long dataN[] = new long[i];
		long dataMulti[] = new long[i];
		long dataRemaining[] = new long[i];

		for(int j = 0 ; j < i ; j++ )
		{
			dataM[j] = tmpM[j];
			dataN[j] = tmpN[j];
			dataMulti[j] = tmpMulti[j];
			dataRemaining[j] = tmpRemaining[j];
		}

		// assign dataM and dataN to global
		//global Global = new global(dataM, dataN, dataMulti, dataRemaining);
		// assign to globalGCD
		globalGCD = new global(dataM, dataN, dataMulti, dataRemaining);
		
	}
	
	public global getFinalResult() {
		return globalGCD;
	}
	
	@Override
	public void showMsg(String aMsg, boolean newLine) {
		// TODO Auto-generated method stub
		if(newLine)
			System.out.println(aMsg);
		else
			System.out.print(aMsg);
				
	}

	@Override
	public void showMsg(double aMsg, boolean newLine) {
		// TODO Auto-generated method stub
		if(newLine)
			System.out.println(aMsg);
		else
			System.out.print(aMsg);
				
	}
	
}