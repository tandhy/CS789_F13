package cryptography;

public class RSA implements basicAlgorithm {
	// private variables
	private long numberP;
	private long numberQ;
	private long numberN;
	private long encryptionKey;
	private String plainText;
	private String decryptedText;
	private String encryptedText;
	private long[] numberNFact = new long[2];
	private long inverse;
	private long phiNumberN;
	private long[] arrOfInteger;
	private long[] arrOfDecryptedInt;
	
	// constructor
	public RSA(long _numberN, long _encryptionKey) {
		numberN = _numberN;
		encryptionKey = _encryptionKey;
	}	
	
	// constructor
	public RSA(long _numberP, long _numberQ, long _encryptionKey)
	{
		numberP = _numberP;
		numberQ = _numberQ;
		//phiNumberN = (numberP-1) * (numberQ-1);
		numberN = _numberP * _numberQ;
		encryptionKey = _encryptionKey;
	}
	
	
	/**
	 * return the original text of encrypted text
	 * @param _encryptedText
	 * @author tandhy
	 */
	public void decryptText(String _encryptedText, long _numberN, long _encryptionKey)
	{
		// 1. factorization numberN with pollar Rho
		long[] _divisorOfNumberN = new long[2];
		// check if _numberN is prime or not, because pollar Rho works in numberN NOT prime
		checkPrimeNumber cpn = new checkPrimeNumber();
		cpn.setPrimeNumber(_numberN);
		if (cpn.isNotPrimeNumber())
		{
			// factorization with Pollar Rho
			pollarRho pr = new pollarRho(_numberN);
			pr.runPollarRho();
			numberNFact[0] = pr.getDivisorOfNumberN();
			if( numberNFact[0] == _numberN)
			{
				pr.runPollarRho2();
				numberNFact[0] = pr.getDivisorOfNumberN();
			}
			
			// divisor of numberN found, find the pair
			numberNFact[1] = _numberN / numberNFact[0];
		}
		
		// 2. compute phiNumberN = factorization[0]*factorization[1]
		long _phiNumberN = (numberNFact[0]-1) * (numberNFact[1]-1);
		phiNumberN = _phiNumberN;
		
		// 3. find inverse of encryptionKey in numberN
		multiplicativeInverse mi = new multiplicativeInverse(_encryptionKey, _phiNumberN);
		inverse = mi.getInverseX();
		
		// 4. convert encryptedText to array of Integer
		long[] arrOfInt = new long[_encryptedText.length()];
		convertFunction cf = new convertFunction();
		arrOfInt = cf.convertEncryptedStringToInteger(_encryptedText, Integer.toString((int) _numberN).length());
		
		// 5. to find decryptedText, compute encryptedText^inverse mod numberN
		fastExponentiation fe = new fastExponentiation();
		arrOfDecryptedInt = new long[arrOfInt.length];
		for ( int i = 0 ; i < arrOfInt.length ; i++ )
		{
			arrOfDecryptedInt[i] = fe.runFastExponentiation(arrOfInt[i], inverse, _numberN, false);
		}
		
		// 6. convert arr of integer to string
		decryptedText = cf.convertIntegerToString(arrOfDecryptedInt);
	}
	
	/**
	 * Encrypt supplied plainText to cipher.
	 * Steps are :
	 * 1. convert String to array of integer
	 * 2. compute integer^encryptionKey mod numberN -> return an integer
	 * 3. padded the integer with maximum nLength zero
	 * 4. return string with padded-with-zero integer
	 * @param _plainText
	 */
	public String encryptText(String _plainText)
	{
		boolean withOutput = false;
		long[] arrOfInt = new long[_plainText.length()];
		long[] arrOfEncryptedInt = new long[_plainText.length()];
		arrOfInteger = new long[_plainText.length()];
		fastExponentiation fe = new fastExponentiation();
		convertFunction  cf = new convertFunction();
		String paddedZero = "";
		encryptedText = "";
		plainText = _plainText;

		// 1. convert string to array of integer
		arrOfInt = cf.convertStringToInteger(_plainText);
		arrOfInteger = arrOfInt;

		// 2. compute integer^encryptionKey mod numberN -> return an integer
		for ( int i = 0 ; i < arrOfInt.length ; i++ )
		{
			paddedZero = "";
			arrOfEncryptedInt[i] = fe.runFastExponentiation(arrOfInt[i], encryptionKey, numberN, withOutput);
			
			// 3. padded arrOfDecryptedInt with maximum nLength zero
			for ( int j = 0 ; j < (Integer.toString((int) numberN).length() - (Integer.toString((int) arrOfEncryptedInt[i]).length())) ; j++ )
				paddedZero += "0";
				
			encryptedText += paddedZero + arrOfEncryptedInt[i];
		}
		return encryptedText;
	}
	
	public long[] getArrOfInteger() {
		return arrOfInteger;
	}
	
	/**
	 * return a string type of array
	 * @return string
	 * @author tandhy
	 */
	public String getStringArrOfInteger() 
	{
		String _strArrOfInteger = "";
		for ( int i = 0 ; i < arrOfInteger.length ; i++ )
			_strArrOfInteger += arrOfInteger[i];
		
		return _strArrOfInteger;
	}
	
	public String getEncryptedText() {
		return encryptedText;
	}
	
	public long getEncryptionKey() {
		return encryptionKey;
	}
	
	public String getDecryptedText() {
		return decryptedText;
	}
	
	public long getInverse() {
		return inverse;
	}
	
	public long getPhiNumberN() {
		return phiNumberN;
	}
	
	public long getNumberN() {
		return numberN;
	}
	
	public String getPlainText() {
		return plainText;
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