package cryptography;

import java.math.BigInteger;
import java.util.Random;

public class blumblumShubGenerator implements basicAlgorithm {
	// private variables
	private long maxNumber;
	private long generatedNumber;
	private long p;
	private long q;
	private long n;
	private long[] s;
	private long[] b;

	// constructor
	/**
	 * generate a number of bits and convert to number
	 * @param maxNumber
	 * return the number in generatedNumber variable
	 * @author tandhy
	 */
	public blumblumShubGenerator(long maxNumber) {
		// 1. randomly choose large prime : p and q, such that p and q is 3 mod 4
		p = generateNumber(maxNumber);
		q = generateNumber(maxNumber);
		//showMsg("prime number p : " + p, true);
		//showMsg("prime number q : " + q, true);
		
		// 2. compute n = p.q
		n = p*q;
		
		// 3. find list of all number, from 1 to n-1 that relatively prime to n
		findRelativelyPrimeNumber frpn = new findRelativelyPrimeNumber(n);
		frpn.listRelativelyPrimeNumber();
		frpn.createListOfRelativelyPrimeNumber();
		long[] listOfRelativelyPrimeNumber = new long[(int) frpn.getTotalOfRelativelyPrimeNumber()];
		listOfRelativelyPrimeNumber = frpn.getListOfRelativelyPrimeNumber();
		
		// 4. compute s1 = s0 mod n, s2=s1 mod n, s3=s2 mod n, and so on..
		// 5. assign b0 =s0 mod 2, b1=s1 mod 2, b2 = s2 mod 2, and so on
		long[] s = new long[(int) frpn.getTotalOfRelativelyPrimeNumber()];
		long[] b = new long[(int) frpn.getTotalOfRelativelyPrimeNumber()];
		
		// choose any number in Zn
		
		s[0] = listOfRelativelyPrimeNumber[(int) generateNumber(frpn.getTotalOfRelativelyPrimeNumber())]; 
		b[0] = s[0] % 2;
		
		for( int i = 1 ; i < frpn.getTotalOfRelativelyPrimeNumber() ; i++ )
		{
			s[i] = (int) ((Math.pow(s[i-1], 2)) % n);
			b[i] = s[i] % 2;
		}
		
		long total = 0;
		int j = 0;
		for ( int i = b.length - 1; i > 0 ; i-- ) 
		{
			//showMsg("" + b[i],false);
			if( b[i] == 1)
				total += Math.pow(2,j);
			j++;
		}
		//showMsg("",true);

		//showMsg("generated number : " + total,true);
		generatedNumber = total;
	}	
	
	/**
	 * return private long generatedNumber
	 * @return long
	 * @author tandhy
	 */
	public long getGeneratedNumber() {
		return generatedNumber;
	}
	
	/**
	 * generate a number that are prime and congruent to 3 mod 4
	 * @param _maxNumber
	 * @return long
	 */
	public long generateNumber(long _maxNumber)
	{
		long aNumber = 0;
		boolean isNotPrime = true;
		checkPrimeNumber cpn = new checkPrimeNumber(); // init
		while(isNotPrime || (aNumber % 4 != 3))
		{
			Random randomNumber = new Random();
			aNumber = randomNumber.nextInt((int) _maxNumber);
			cpn.setPrimeNumber(aNumber); // assign generateNumber() to cpn
			isNotPrime = cpn.isNotPrimeNumber();
		}
		return aNumber;

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