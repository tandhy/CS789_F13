package cryptography;
import java.math.BigInteger;
import java.util.Random;

/**
 * Result a large prime number less equal than maxDigit
 * using Blum Blum Shum pseudorandom number generator
 * @author tandhy / 11.26.13
 */
public class generatePrimeNumber implements basicAlgorithm {
	// private variables
	private long maxGeneratedNumber;
	private long generatedPrimeNumber;
	
	// constructors
	/**
	 * a constructor
	 * Generate a prime number
	 * @param maxNumber
	 * @return generatedPrimeNumber
	 * @author tandhy
	 */
	public generatePrimeNumber(long maxNumber)
	{
		maxGeneratedNumber = maxNumber;
		//showMsg("Random Number : " + generateNumber(maxNumber), true);
		boolean isNotPrime = true;
		checkPrimeNumber cpn = new checkPrimeNumber(); // init
		while(isNotPrime)
		{
			generatedPrimeNumber = generateNumber();
			cpn.setPrimeNumber(generatedPrimeNumber); // assign generateNumber() to cpn
			isNotPrime = cpn.isNotPrimeNumber();	
		}

	}
	
	/**
	 * @return maxGeneratedNumber
	 * @author tandhy
	 */
	public long getMaxNumber() {
		return maxGeneratedNumber;
	}
	
	/**
	 * @return generatedPrimeNumber
	 * @author tandhy
	 */
	public long getGeneratedPrimeNumber() {
		return generatedPrimeNumber;
	}
	
	/**
	 * generate a random number with max generated dnumber
	 * @return generated number
	 * @author tandhy
	 */
	public long generateNumber()
	{
		Random randomNumber = new Random();
		return randomNumber.nextInt((int) maxGeneratedNumber);
		
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