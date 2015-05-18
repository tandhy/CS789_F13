package cryptography;

public class factorization implements basicAlgorithm {
	// private variables
	private long primeNumber;
	private long[] factorizationPrimeNumber = new long[2];
	// constructor
	public factorization(long n) {
		primeNumber = n;
	}	
	
	/**
	 * prime number is factored by 2 prime number that congruent to 3 mod 4
	 * Steps :
	 * 1. get the list of prime number less than primeNumber
	 * 2. for each prime number, if mod primeNumber = 0 and mod 4 = 3, save it as factor 1
	 * 3. factor 2 : primeNumber / factor 1 and factor 2 mod 4 = 3 or primenumber/ fact[0]
	 * this is not a fermat factorization or pollar Rho factorization
	 * @return factor of a prime number
	 * @author tandhy
	 */
	public void factorizePrimeNumber() 
	{
		// initialization
		factorizationPrimeNumber[0] = 1;
		factorizationPrimeNumber[1] = (int) primeNumber;	
		boolean exit = false;
		int iteration = 2; // start from prime number = 2
		
		// prime number is factored by 2 prime number that congruent to 3 mod 4
		// 1. get the list of prime number less than primeNumber
		findPrimeNumber fpn = new findPrimeNumber(primeNumber);
		fpn.listPrimeNumber(); // start generating array of prime number
		long[] listPrimeNumber = new long[fpn.getTotalListOfPrimeNumber()];
		listPrimeNumber = fpn.getListOfPrimeNumber();
		//showMsg("Prime Number = " + fpn.getListOfPrimeNumberInString(), true);
		//showMsg("Total Prime Number = " + fpn.getTotalListOfPrimeNumber(), true);
		
		// 2. for each prime number, if mod primeNumber = 0 and mod 4 = 3, save it as factor 1
		while ( iteration < listPrimeNumber.length && !exit)
		{
			if( primeNumber % listPrimeNumber[iteration] == 0 && listPrimeNumber[iteration] % 4 == 3)
			{
				factorizationPrimeNumber[0] = listPrimeNumber[iteration];
				// 3. factor 2 : primeNumber / factor 1 and factor 2 mod 4 = 3 or primenumber/ fact[0]
				factorizationPrimeNumber[1] = (int) ( primeNumber / factorizationPrimeNumber[0] );
				exit = true;
			}
			iteration++;
		}
	}
	
	/**
	 * get the array of prime number factor
	 * @return array
	 * @author tandhy
	 */
	public long[] getFactorizationPrimeNumber() {
		return factorizationPrimeNumber;
	}
	
	/**
	 * get the prime number
	 * @return primeNumber
	 * @author tandhy
	 */
	public long getPrimeNumber() {
		return primeNumber;
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