package cryptography;

public class fermatFactorization implements basicAlgorithm {
	// private variables
	private long numberN = 0;
	private long[] numberNFact = new long[2];

	// constructor
	public fermatFactorization(long _numberN) {
		numberN = _numberN;
	}	
	
	/**
	 * 
	    FermatFactor(N): // N should be odd
	        a = ceil(sqrt(N))
	        b2 = a*a - N
	        while b2 isn't a square:
	                a = a + 1 // equivalently: b2 ← b2 + 2*a + 1
	                b2 = a*a - N // a ← a + 1
	        endwhile
	        return a - sqrt(b2) // or a + sqrt(b2)
	 * @author tandhy
	 */
	public void runFermatFactorization(boolean withOutput)
	{
		if( numberN != 0 )
		{
			long a = 0;
			long b = 0;
			double totalB = 0;
			a = (long) Math.ceil(Math.sqrt(numberN));
			totalB = (Math.pow(a, 2) - numberN);
			
			while (Math.sqrt(totalB) != (long)Math.sqrt(totalB))
			{
				a++;
				totalB = (Math.pow(a, 2) - numberN);
			}
			numberNFact[0] = a-(long)Math.sqrt(totalB);
			numberNFact[1] = a+(long)Math.sqrt(totalB);
			if (withOutput) showMsg("... ",true);
		}
	}
	
	/**
	 * @author tandhy
	 * @return numberNFact array
	 */
	public long[] getNumberNFact() {
		return numberNFact;
	}
	
	/**
	 * @author tandhy
	 * @return long getNumberN
	 */
	public long getNumberN() {
		return numberN;
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