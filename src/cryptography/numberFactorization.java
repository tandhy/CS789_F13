package cryptography;

public class numberFactorization implements basicAlgorithm {
	// private variables
	private long number;
	private long[] divisor;
	private long[] totalDivisor;
	private long[] listOfPrimeNumber;

	// constructor
	public numberFactorization(long _number) {
		number = _number;
	}	
	
	/**
	 * set the private variable from given input
	 * @param _listOfPrimeNumber
	 * @author tandhy
	 */
	public void setListOfPrimeNumber(long[] _listOfPrimeNumber) {
		listOfPrimeNumber = _listOfPrimeNumber;
	}
	/**
	 * find the factorization a number
	 * for example : 72 = 2.2.2.3.3
	 * @author tandhy / 11.27.13
	 */
	public void runNumberFactorization() {
		// 1. List all prime number less than number
		// define by the following command :
		/*findPrimeNumber fpn = new findPrimeNumber(number+1);
		fpn.listPrimeNumber();
		int listOfPrimeNumber[] = fpn.getListOfPrimeNumber();
		*/

		divisor = new long[(int) Math.sqrt(number)+1];
		totalDivisor = new long[(int) Math.sqrt(number)+1];
		long result = number;
		int iterDiv = 0;
		boolean addIterDev = false;
		
		// initialization
		for ( int i = 0; i < (int) Math.sqrt(number) ; i++ )
		{
			divisor[i] = 0;
			totalDivisor[i] = 0;
		}
		
		for( int i = 1 ; i < listOfPrimeNumber.length ; i++ )
		{
			while(result % listOfPrimeNumber[i] == 0 && result != 1 )
			{
				divisor[iterDiv] = listOfPrimeNumber[i];
				totalDivisor[iterDiv] = totalDivisor[iterDiv] + 1;
				result = result / listOfPrimeNumber[i];
				addIterDev = true;
			}
			if( addIterDev )
			{
				iterDiv++;
				addIterDev = false;
			}
			if( result == 1) break;
		}		
		
	}
	
	public long getNumber() {
		return number;
	}
	
	public long[] getDivisor() {
		return divisor;
	}
	
	public long[] getTotalDivisor() {
		return totalDivisor;
	}
	
	/**
	 * @return string of factorization result
	 * @author tandhy
	 */
	public String getFactorizationResult()
	{
		String aMsg = "";
		for ( int j = 0; j < divisor.length ; j++ )
		{
			if(totalDivisor[j] != 0)
			{
				aMsg += divisor[j] + "^" + totalDivisor[j] + ".";				
			}
		}
		
		//showMsg(aMsg.substring(0, aMsg.length()-1), true);
		return aMsg.substring(0, aMsg.length()-1);
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