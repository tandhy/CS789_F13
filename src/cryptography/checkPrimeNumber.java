package cryptography;

public class checkPrimeNumber implements basicAlgorithm {
	// private variables
	private long number;
	private long divisibleBy;
	
	// constructor
	public checkPrimeNumber(long _number)
	{
		number = _number;
	}
	
	// empty constructor
	public checkPrimeNumber(){
		
	}
	
	/**
	 * set prime number to global variable number
	 * @param _number
	 * @author tandhy
	 */
	public void setPrimeNumber(long _number)
	{
		number = _number;
	}
	
	/**
	 * check whether number is a prime or not
	 * @return boolean 
	 * @author tandhy
	 */
	public boolean isNotPrimeNumber()
	{
		long iter;
		long lastNumber = (long) Math.sqrt(number+1);
		boolean isNotPrimeNumber = false;
		
		for( iter = 2 ; iter < lastNumber ; iter++ )
		{
			if( number % iter == 0 )
			{
				isNotPrimeNumber = true;
				divisibleBy = iter;
				//showMsg("divisible by " + iter, true);
				break;
			}
		}
		
		
		return isNotPrimeNumber;
	}
	
	/**
	 * get the divisor of a prime
	 * @return divisibleBy
	 * @author tandhy
	 */
	public long getDivisibleBy(){
		return divisibleBy;
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