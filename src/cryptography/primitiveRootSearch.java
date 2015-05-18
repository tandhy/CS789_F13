package cryptography;

import java.util.Random;

public class primitiveRootSearch implements basicAlgorithm {
	// private variables
	private long primeNumber;
	private long[] primitiveRoots;

	
	// constructor
	public primitiveRootSearch(long groupZ)
	{
		primeNumber = groupZ;
	}
	
	/**
	 * find the primitive root of a prime number
	 * @return array of primitive roots
	 * @author tandhy
	 */
	public void runPrimitiveRootSearch()
	{
		long numberFact = primeNumber - 1;
		long[] arrNumberFactorization = new long[(int) Math.sqrt(numberFact)+1];

		// 1. list all relatively prime number less equal than prime number
		findRelativelyPrimeNumber frpn = new findRelativelyPrimeNumber(primeNumber);
		frpn.listRelativelyPrimeNumber();
		frpn.createListOfRelativelyPrimeNumber();
		long[] listOfRelativelyPrimeNumber = new long[ frpn.getTotalOfRelativelyPrimeNumber()];
		listOfRelativelyPrimeNumber = frpn.getListOfRelativelyPrimeNumber();
		
		
		// 2. Find primeNumber-1 factorization
		numberFactorization nf = new numberFactorization(numberFact);
		// provide list of prime number to process when factoring
		nf.setListOfPrimeNumber(listOfRelativelyPrimeNumber);
		nf.runNumberFactorization();
		arrNumberFactorization = nf.getDivisor();
		
		// 3. traverse to listOfPrimeNumber, to check whether the prime is primitive root
		// start from array no 1, which holds "2"
		int result = 0;
		fastExponentiation fe = null;
		long e = 0;
		boolean withOutput = false;
		boolean addToArrPrimitiveRoot = true;
		long[] _primitiveRoots = new long[frpn.getTotalOfRelativelyPrimeNumber()];
		int iterRoots = 0;
		
		for( int i = 1; i < listOfRelativelyPrimeNumber.length ; i++ )
		{
			// 4. compute listOfPrimeNumber[i]^((primeNumber-1)/factorization[j]) mod primeNumber
			//    if the result = 1, go to next listOfPrimeNumber
			addToArrPrimitiveRoot = true;
			
			for ( int j = 0 ; j < arrNumberFactorization.length ; j++ )
			{
				if( arrNumberFactorization[j] != 0 ) // only process array with non-zero value
				{
					e = numberFact/arrNumberFactorization[j];
					fe = new fastExponentiation(listOfRelativelyPrimeNumber[i], e, primeNumber, withOutput );
					if(fe.getFinalResult() == 1)
						addToArrPrimitiveRoot = false;
					//showMsg(listOfRelativelyPrimeNumber[i] + "^" + e + " mod " + primeNumber + " = " + fe.getFinalResult() + " ; ", true);
				}				
			}

			if (addToArrPrimitiveRoot)
			{
				_primitiveRoots[iterRoots] = listOfRelativelyPrimeNumber[i];
				iterRoots++;
			}
		}
		
		// assign _primitiveRoots to primitiveRoots
		int j = 0;
		primitiveRoots = new long[iterRoots];
		for( int i = 0 ; i < _primitiveRoots.length ; i++ )
		{
			if(_primitiveRoots[i] != 0)
			{
				primitiveRoots[j] = _primitiveRoots[i];
				j++;
			}
		}

	}
	
	/**
	 * check whether thenumber is a primitive root to a number
	 * @param theNumber
	 * @param showProgress
	 * @return boolean
	 * @author tandhy
	 */
	public boolean isPrimitiveRoot(long theNumber, boolean showProgress)
	{
		
		// 2. Find primeNumber-1 factorization
		/*numberFactorization nf = new numberFactorization(numberFact);
		// provide list of prime number to process when factoring
		nf.setListOfPrimeNumber(listOfRelativelyPrimeNumber);
		nf.runNumberFactorization();
		arrNumberFactorization = nf.getDivisor();
		*/
		// 2. Find primeNumber-1 factorization with fermatFactorization
		long numberFact = primeNumber - 1;
		fermatFactorization ff = new fermatFactorization(numberFact);
		ff.runFermatFactorization(showProgress);
		long[] arrNumberFactorization = new long[(int) Math.sqrt(numberFact)+1];
		arrNumberFactorization = ff.getNumberNFact();
		
		
		// 3. check whether theNumber is primitive root
		// start from array no 1, which holds "2"
		fastExponentiation fe = null;
		long e = 0;
		boolean withOutput = false;
		boolean isPrimitiveRoot = true;
		
		// 4. compute theNumber^((primeNumber-1)/factorization[j]) mod primeNumber
		//    if the result = 1, then it is NOT primitive Root
			
		for ( int j = 0 ; j < arrNumberFactorization.length ; j++ )
		{
			if( arrNumberFactorization[j] != 0 ) // only process array with non-zero value
			{
				e = numberFact/arrNumberFactorization[j];
				fe = new fastExponentiation(theNumber, e, primeNumber, withOutput );
				if(fe.getFinalResult() == 1)
					isPrimitiveRoot = false;
				//showMsg(listOfRelativelyPrimeNumber[i] + "^" + e + " mod " + primeNumber + " = " + fe.getFinalResult() + " ; ", true);
			}				
		}
		return isPrimitiveRoot;
	}
	
	public long[] getPrimitiveRoots() {
		return primitiveRoots;
	}
	
	/**
	 * random any index to get the primitive roots from the array
	 * @return primitive root of a prime number from the array
	 * @author tandhy
	 */
	public long getOnePrimitiveRoots()
	{
		Random rnd = new Random();
		return primitiveRoots[rnd.nextInt(primitiveRoots.length)];
	}
	
	/**
	 * return the primitive root in string
	 * @return string
	 * @author tandhy
	 */
	public String getPrimitiveRootsInString() {
		String aMsg = "";
		for( int i = 0 ; i < primitiveRoots.length ; i++ )
			aMsg += primitiveRoots[i] + ", ";
		
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