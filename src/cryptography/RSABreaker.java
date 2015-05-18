package cryptography;

public class RSABreaker implements basicAlgorithm {
	// private variables
	private String encryptedText = "";
	private long numberN;
	private long encryptionKey;
	private long inverse;
	private long[] arrOfDecryptedInt;
	private String decryptedText = "";
	
	// constructor
	public RSABreaker() {

	}	
	
	// constructor
	public RSABreaker(String _encryptedText, long _numberN, long _encryptionKey) {
		encryptedText = _encryptedText;
		numberN = _numberN;
		encryptionKey = _encryptionKey;
	}	
	/**
	 * function to find the original Text of encryptedText
	 * Steps are :
	 * 1. encryptedText and 2 public key are known : numberN, and encryptionKey. convert encryptedText to integer
	 * 2. factorization numberN with pollar Rho --> p and q
	 * 3. find the phiNumberN = (p-1) * (q-1)
	 * 4. find the inverse of encryptionKey in phiNumberN
	 * 5. find the m = cipherInteger^inverse mod numberN
	 * 
	 * @author tandhy
	 */
	public void runRSABreaker()
	{
		// 1. convert encryptedText to integer
		convertFunction cf = new convertFunction();
		long[] arrOfEncryptedInt = new long[encryptedText.length()];
		arrOfEncryptedInt = cf.convertEncryptedStringToInteger(encryptedText, Long.toString(numberN).length());
		
		// 2. factorization numberN with pollar Rho --> p and q
		long[] _divisorOfNumberN = new long[2];
		// check if _numberN is prime or not, because pollar Rho works in numberN NOT prime
		checkPrimeNumber cpn = new checkPrimeNumber();
		cpn.setPrimeNumber(numberN);
		if (cpn.isNotPrimeNumber())
		{
			// factorization with Pollar Rho
			pollarRho pr = new pollarRho(numberN);
			pr.runPollarRho();
			_divisorOfNumberN[0] = pr.getDivisorOfNumberN();
			if( _divisorOfNumberN[0] == numberN)
			{
				pr.runPollarRho2();
				_divisorOfNumberN[0] = pr.getDivisorOfNumberN();
			}
			
			// divisor of numberN found, find the pair
			_divisorOfNumberN[1] = numberN / _divisorOfNumberN[0];
		}
		
		// 3. find the phiNumberN = (p-1) * (q-1)
		long phiNumberN = (_divisorOfNumberN[0]-1) * (_divisorOfNumberN[1]-1);

		// 4. find the inverse of encryptionKey in phiNumberN
		multiplicativeInverse mi = new multiplicativeInverse(encryptionKey, phiNumberN);
		inverse = mi.getInverseX();
		
		// 5. find the m = cipherInteger^inverse mod numberN
		long[] _arrOfDecryptedInt = new long[arrOfEncryptedInt.length];
		fastExponentiation fe = new fastExponentiation();
		for ( int i = 0 ; i < arrOfEncryptedInt.length ; i++ )
		{
			_arrOfDecryptedInt[i] = fe.runFastExponentiation(arrOfEncryptedInt[i], inverse, numberN, false);
		}
		arrOfDecryptedInt = _arrOfDecryptedInt;
		decryptedText = cf.convertIntegerToString(arrOfDecryptedInt);
		
	}
	
	/**
	 * find any possibly factorization for numberN
	 * @author tandhy
	 */
	public void findNumberNFactorization()
	{
		long[][] _divOfNumberN = new long[Long.toString(numberN).length()][2];
		// 1. convert encryptedText to integer
		convertFunction cf = new convertFunction();
		long[] arrOfEncryptedInt = new long[encryptedText.length()];
		arrOfEncryptedInt = cf.convertEncryptedStringToInteger(encryptedText, Long.toString(numberN).length());
		
	}
	
	public String getDescryptedText() {
		return decryptedText;
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