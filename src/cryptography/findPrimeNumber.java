package cryptography;

public class findPrimeNumber implements basicAlgorithm {
	// private variables
	private long lastNumber;
	private boolean[] listOfPrimeNumber;
	private long[] orderedListOfPrimeNumber;
	
	// constructor
	public findPrimeNumber(long n) {
		lastNumber = n;
	}
	
	/**
	 * Get the list of a prime number less than lastNumber
	 * @return array of prime number
	 * @author tandhy
	 */
	public void listPrimeNumber()
	{
		boolean array[] = new boolean[(int) lastNumber+1];
		
		// initialization
		array[0] = false;
		for ( int i = 1 ; i < lastNumber + 1 ; i++ )
		{
			array[i] = true;
		}
		
		// find all prime number less than lastNumber
		// starting with 2k, mark every k integer
		long squareOfLastNumber = (long) Math.floor(Math.sqrt(lastNumber));
		for ( int j = 2 ; j < squareOfLastNumber + 1 ; j++ )
		{
			for( int k = 2*j ; k < lastNumber + 1 ; k += j )
			{
				array[k] = false;
			}
		}
		
		listOfPrimeNumber = array;
		
		// reorder the listOfPrimeNumber
		reAssignListOfPrimeNumber();
	}
	
	/**
	 * remove empty value from an array and set a new array
	 * @author tandhy
	 */
	private void reAssignListOfPrimeNumber()
	{
		int totalPrimeNumber = this.getTotalListOfPrimeNumber();
		int start = 0;
		long[] _listOfPrimeNumber = new long[ totalPrimeNumber];
		for ( int i = 1 ; i < lastNumber ; i++ )
		{
			if(listOfPrimeNumber[i]) 
			{
				_listOfPrimeNumber[start] = i;
				start++;
			}
		}
		orderedListOfPrimeNumber = _listOfPrimeNumber;
	}
	
	/**
	 * @return array of ordered prime number
	 * @author tandhy
	 */
	public long[] getListOfPrimeNumber() {
		return orderedListOfPrimeNumber;
	}
	
	/**
	 * @return array of prime number
	 * @author tandhy
	 */
	public boolean[] getOriginalListOfPrimeNumber() {
		return listOfPrimeNumber;
	}
	
	/**
	 * @return string of ordered prime number
	 * @author tandhy
	 */
	public String getListOfPrimeNumberInString()
	{
		String aMsg = "";
		for( int i = 1 ; i < lastNumber ; i++ )
		{
			if(listOfPrimeNumber[i])
				aMsg += i + ", ";
			
		}
		return aMsg.substring(0, aMsg.length()-2);
	}
	
	/**
	 * @return total number of prime number less than lastNumbe
	 * @author tandhy
	 */
	public int getTotalListOfPrimeNumber()
	{
		int totalNumber = 0;
		for( int i = 1 ; i < lastNumber ; i++ )
		{
			if(listOfPrimeNumber[i])
				totalNumber++;
			
		}
		return totalNumber;
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