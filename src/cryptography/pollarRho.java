package cryptography;

public class pollarRho implements basicAlgorithm {
	// private variables
	private long numberN;
	private long divisorOfNumberN;

	// empty constructor
	public pollarRho() {
		
	}	
	
	// constructor
	public pollarRho(long _numberN) {
		numberN = _numberN;
	}
	
	/**
	 * Factorization of numberN, works only if numberN is a composite number
	 * form of y = x^2 + 1
	 * @author tandhy
	 */
	public void runPollarRho()
	{
		long x = 3;
		long y = (long) (Math.pow(x, 2) + 1);
		GCD gcd = new GCD();
		long substractXY = x - y;
		if( substractXY < 0 ) substractXY = substractXY * -1;
		gcd.runGCD(numberN , substractXY , false);
		global g = gcd.getFinalResult();
		long[] arrOfDataN = g.getDataN(); 
		while ( arrOfDataN[arrOfDataN.length-1] == 1 ) // exit when g != 1
		{
			x = (long) (Math.pow(x, 2) + 1) % numberN;
			y = (long) ((Math.pow((Math.pow(y, 2) + 1), 2) + 1) % numberN);
			substractXY = x - y;
			if( substractXY < 0 ) substractXY = substractXY * -1;
			gcd.runGCD( numberN , substractXY , false);
			g = gcd.getFinalResult();
			arrOfDataN = g.getDataN(); 
		}
		
		if( arrOfDataN[arrOfDataN.length-1] > 1 && arrOfDataN[arrOfDataN.length-1] < numberN )
		{
			divisorOfNumberN = arrOfDataN[arrOfDataN.length-1];
		}
		else
		{
			divisorOfNumberN = numberN;
		}
	}
	
	/**
	 * this function only need to be run if runPollarRho method return divisorOfNumberN = numberN
	 * Factorization of numberN, works only if numberN is a composite number
	 * form of y = x^2 - 1
	 * @author tandhy
	 */
	public void runPollarRho2()
	{
		long x = 3;
		long y = (long) (Math.pow(x, 2) - 1);
		GCD gcd = new GCD();
		long substractXY = x - y;
		if( substractXY < 0 ) substractXY = substractXY * -1;
		gcd.runGCD(substractXY, numberN, false);
		global g = gcd.getFinalResult();
		long[] arrOfMulti = g.getDataMulti(); 
		while ( arrOfMulti[arrOfMulti.length-1] == 1 ) // exit when g != 1
		{
			x = (long) (Math.pow(x, 2) + 1) % numberN;
			y = (long) ((Math.pow((Math.pow(y, 2) - 1), 2) - 1) % numberN);
			substractXY = x - y;
			if( substractXY < 0 ) substractXY = substractXY * -1;
			gcd.runGCD(substractXY, numberN, false);
			g = gcd.getFinalResult();
			arrOfMulti = g.getDataMulti(); 
		}
		
		if( arrOfMulti[arrOfMulti.length-1] > 1 && arrOfMulti[arrOfMulti.length-1] < numberN )
		{
			divisorOfNumberN = arrOfMulti[arrOfMulti.length-1];
		}
		else
		{
			divisorOfNumberN = numberN;
		}
	}
	
	public long getDivisorOfNumberN() {
		return divisorOfNumberN;
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