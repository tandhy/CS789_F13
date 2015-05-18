package cryptography;

public class findRelativelyPrimeNumber implements basicAlgorithm {
	// private variables
	private boolean[] relativelyPrimeNumber;
	private long someNumber;
	private long[] listOfRelativelyPrimeNumber;

	// constructor
	/**
	 * initialize the array
	 * @param number
	 * @author tandhy
	 */
	public findRelativelyPrimeNumber(long number) {
		someNumber = number;
		relativelyPrimeNumber = new boolean[(int) number];
		// initialize
		for( int i = 0; i < number ; i++ )
			relativelyPrimeNumber[i] = false;

	}
	
	/**
	 * find the list of relatively prime number to someNumber
	 * @return array of relatively prime number to someNumber
	 * @param someNumber
	 * @author tandhy
	 */
	public void listRelativelyPrimeNumber()
	{
		GCD gcd = new GCD();
		relativelyPrimeNumber[1] = true;
		for ( int i = 2 ; i < relativelyPrimeNumber.length ; i++ )
		{
			if(gcd.isRelativelyPrime(someNumber, i, false))
			{
				relativelyPrimeNumber[i] = true;
			}
		}
		// array relativelyPrimeNumber will have true value for relatively prime to someNumber
	}
	
	/**
	 * @return array of relatively prime number
	 * @author tandhy
	 */
	public boolean[] getRelativelyPrimeNumber() {
		return relativelyPrimeNumber;
	}
	
	/**
	 * @return total of relatively prime number
	 * @author tandhy
	 */
	public int getTotalOfRelativelyPrimeNumber()
	{
		int totalNumber = 1;
		
		for( int i = 1; i < someNumber ; i++ )
		{
			if(relativelyPrimeNumber[i])
				totalNumber++;
		}
		return totalNumber;
	}
	
	/**
	 * create an array of relatively prime number 
	 * @author tandhy
	 */
	public void createListOfRelativelyPrimeNumber()
	{
		long totalNumber = 0;
		int k = 0;

		if( relativelyPrimeNumber != null ) 
			totalNumber = getTotalOfRelativelyPrimeNumber();
		
		// init array
		listOfRelativelyPrimeNumber = new long[(int) (totalNumber-1)];
		
		for( int i = 1; i < totalNumber ; i++ )
		{
			if(relativelyPrimeNumber[i])
			{
				listOfRelativelyPrimeNumber[k] = i;
				k++;
			}
		}
	}
	
	/**
	 * @return array of relatively prime number
	 * @author tandhy
	 */
	public long[] getListOfRelativelyPrimeNumber() {
		return listOfRelativelyPrimeNumber;
	}
	/**
	 * @return string of the array of relatively prime number
	 * @author tandhy
	 */
	public String getRelativelyPrimeNumberInString() 
	{
		String aMsg = "";
		
		for( int i = 1; i < someNumber ; i++ )
		{
			if(relativelyPrimeNumber[i])
				aMsg += i + ", ";
		}
		
		return aMsg.substring(0, aMsg.length()-2);
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