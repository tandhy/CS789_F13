package cryptography;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

import com.sun.tools.javac.main.RecognizedOptions.GrumpyHelper;

public class diffieHellman implements basicAlgorithm {
	// private variables
	// RN = random number
	// groupZ = prime number
	// SK = secret key
	// PK = public key
	private long groupZ;
	private long primitiveRoot;
	private long aliceSK;
	private long alicePK;
	private long bobSK;
	private long bobPK;
	private long keyProtocol; // alicePK^bobSK mod groupZ
	private long sharedKey; // bobPK^aliceSK mod groupZ
	private long decryptKey; // the inverse of keyProtocol
	private long encryptKey; // encryptKey = bobPK^aliceSK mod groupZ
	private long plainText;
	private long encryptedText; //encryptedText = plainText.encryptKey mod groupZ
	private long decryptedText; // decryptedText = encryptedText.decryptKey mod groupZ
	private long[] arrOfPlainTextInInteger;
	
	// empty constructor
	public diffieHellman()
	{
		
	}
	
	// contructor
	public diffieHellman(long _groupZ, long _primitiveRoot)
	{
		groupZ = _groupZ;
		primitiveRoot = _primitiveRoot;
	}
	
	/**
	 * @return groupZ
	 * @author tandhy
	 */
	public long getGroupZ() {
		return groupZ;
	}
	
	/**
	 * @return primitiveRoot
	 * @author tandhy
	 */
	public long getPrimitiveRoot() {
		return primitiveRoot;
	}
	
	/**
	 * Set aliceSK from given input
	 * @author tandhy
	 */
	public void setAliceSK(long _aliceSK) {
		aliceSK = _aliceSK;
	}
	
	/**
	 * @return aliceSK
	 * @author tandhy
	 */
	public long getAliceSK() {
		return aliceSK;
	}
	
	/**
	 * set alicePK from given input
	 * @param _alicePK
	 */
	public void setAlicePK(long _alicePK) {
		alicePK = _alicePK;
	}

	/**
	 * @return alicePK
	 * @author tandhy
	 */
	public long getAlicePK() {
		return alicePK;
	}
	
	/**
	 * set bobSK from given input
	 * @param _bobSK
	 */
	public void setBobSK(long _bobSK) {
		bobSK = _bobSK;
	}
	
	/**
	 * @return bobSK
	 * @author tandhy
	 */
	public long getBobSK() {
		return bobSK;
	}
	
	/**
	 * set bobPK from given input
	 * @param _bobPK
	 */
	public void setBobPK(long _bobPK) {
		bobPK = _bobPK;
	}
	
	/**
	 * @return bobPK
	 * @author tandhy
	 */
	public long getBobPK() {
		return bobPK;
	}
	
	/**
	 * @return sharedKey
	 * @author tandhy
	 */
	public long getSharedKey() {
		return sharedKey;
	}
	
		
	/**
	 * create encrypted text from given plain text
	 * @param _plainText
	 * @author tandhy
	 * @throws IOException 
	 */
	public void createEncryptedText() throws IOException
	{
		showMsg("Find Text", true);
		showMsg("-------------------", true);
		showMsg("Please Input the following Data :", true);
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		
		// read input for group Z
		long maxNumber = 15;
		blumblumShubGenerator bbsg = new blumblumShubGenerator(maxNumber);
		showMsg("choose primitive root = ", false);
		String _groupZ = reader.readLine();
		groupZ = Long.parseLong(_groupZ);
		//groupZ = gpn.getGeneratedPrimeNumber();
		showMsg("Larger prime Number for group Z = " + groupZ, true);
		
		// show the available primitive root for primeNumber
		//primitiveRootSearch prs = new primitiveRootSearch((int) groupZ);
		//prs.runPrimitiveRootSearch();
		//showMsg("Primitive Roots available :", true);
		//showMsg(prs.getPrimitiveRootsInString(),true);
		// read input for primitive root
		showMsg("choose primitive root = ", false);
		String _primitiveRoot = reader.readLine();
		primitiveRoot = Long.parseLong(_primitiveRoot);
		
		// read input for alice's secret key
		showMsg("choose alice secret number = ", false);
		String _aliceSK = reader.readLine();
		aliceSK = Long.parseLong(_aliceSK);
		
		// compute alice public key, alicePK, primitiveRoot^aliceSK mod groupZ
		boolean withOutput = false;
		fastExponentiation fe = new fastExponentiation(primitiveRoot, aliceSK, groupZ, withOutput);
		alicePK = fe.getFinalResult();
		showMsg("Alice Key = " + primitiveRoot + ", " + alicePK, true);
		
		// read input for bob's secret key
		showMsg("choose bob secret number = ", false);
		String _bobSK = reader.readLine();
		bobSK = Long.parseLong(_bobSK);
		
		// compute bob's public key, bobPK, primitiveRoot^bobSK mod groupZ
		fe = new fastExponentiation(primitiveRoot, bobSK, groupZ, withOutput);
		bobPK = fe.getFinalResult();
		showMsg("Bob Key = " + primitiveRoot + ", " + bobPK, true);
		
		// key protocol
		// alicePK^bobSK mod groupZ
		fe = new fastExponentiation(alicePK, bobSK, groupZ, withOutput);
		keyProtocol = fe.getFinalResult();
		showMsg("key Protocol = " + keyProtocol, true);
		
		// decryptKey : inverse of keyProtocol
		multiplicativeInverse mi = new multiplicativeInverse(keyProtocol, groupZ);
		decryptKey = mi.getInverseX();
		showMsg("decryptKey = " + decryptKey, true);
		
		// encryptKey : bobPK^aliceSK mod groupZ
		fe = new fastExponentiation(bobPK, aliceSK, groupZ, withOutput);
		encryptKey = fe.getFinalResult();
		showMsg("encryptKey = " + encryptKey, true);
		
		// input plain text to be encrypted with encryptKey
		showMsg("insert plain text = ", false);
		String _plainText = reader.readLine();
		plainText = Long.parseLong(_plainText);
		
		// encryptedText = plainText.encryptKey mod groupZ
		encryptedText = ( plainText * encryptKey ) % groupZ;
		showMsg("encrypted Text = " + encryptedText, true);
		
		// decryptedText = encryptedText.decryptKey mod groupZ
		decryptedText = ( encryptedText*decryptKey ) % groupZ;
		showMsg("decrypted Text = " + decryptedText, true);
		
	}
	
	/**
	 * run diffie hellman algorithm
	 * @throws IOException
	 * @author tandhy
	 */
	public void runDiffieHellman() throws IOException
	{
		showMsg("Creating Secret Key", true);
		showMsg("-------------------", true);
		showMsg("To Get Secret key is using primitiveRoot^privateKeyA mod groupZ :", true);
		showMsg("Please Input the following :", true);

		// generating large number with blum blum shub generator
		// the only problem is, after generating large number, java will not able to find the primitive root of it due to heap space problem
		// therefore, I will use number generator with max 9,999,999 so the primitive root will run.
		/*long maxNumber = 10;
		long groupZ = 0; 
		while (groupZ == 0)
		{
			blumblumShubGenerator bbsg = new blumblumShubGenerator(maxNumber);
			groupZ = bbsg.getGeneratedNumber();
		}
		*/
		// assign groupZ manual
		long maxNumber = 555;
		generatePrimeNumber gpn = new generatePrimeNumber(maxNumber);
		groupZ = gpn.getGeneratedPrimeNumber();
		showMsg("groupZ : " + groupZ, true);
		
		// I can find the primitive root with algorithm, but for the large number, it will yield heap space error due to resource problem
		// so, I will input a number and check whether it is a primitive root or not and continue to input if the number is not primitive root
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		/*showMsg("primitive Root = ", false);
		String _primitiveRoot = reader.readLine();
		primitiveRoot = Long.parseLong(_primitiveRoot);
		*/
		boolean isNotPrimitiveRoot = true;
		
		// check a max n digit number is primitive root or not 
		int maxPrimitive = 9;
		Random rnd = new Random();
		long aNumber = 0;
		boolean showProgress = false;
		primitiveRootSearch prs = new primitiveRootSearch(groupZ);
		GCD gcd = new GCD();
		
		while (isNotPrimitiveRoot || aNumber == 1)
		{
			aNumber = rnd.nextInt(maxPrimitive);
			
			if ( aNumber != 1 && gcd.isRelativelyPrime(groupZ, aNumber, false)) 
				isNotPrimitiveRoot = prs.isPrimitiveRoot(aNumber, showProgress);
			
		}
		primitiveRoot = aNumber;
		
		// command for finding primitive root of a number
		/*primitiveRootSearch prs = new primitiveRootSearch(groupZ);
		prs.runPrimitiveRootSearch();
		primitiveRoot = prs.getOnePrimitiveRoots();
		*/
		showMsg("primitiveRoot : " + Long.toString(primitiveRoot), true);
		
		// the following is ask for input for private key A
		/*showMsg("private key A = ", false);
		String _aliceSK = reader.readLine();
		long aliceSK = Long.parseLong(_privateKeyA);
		*/
		
		// random a number for private key A
		aliceSK = rnd.nextInt(999);
		
		// compute alice public key, alicePK, primitiveRoot^aliceSK mod groupZ
		boolean withOutput = false;
		fastExponentiation fe = new fastExponentiation();
		alicePK = fe.runFastExponentiation(primitiveRoot, aliceSK, groupZ, withOutput);

		showMsg("private key A : " + aliceSK + " so (e,publicKey) = " + primitiveRoot + ", " + alicePK, true);

		showMsg("Input public Key B = ", false);
		String _bobPK = reader.readLine();
		bobPK = Long.parseLong(_bobPK);
		
		// compute the shared Key
		// bobPK^aliceSK mod groupZ
		sharedKey = fe.runFastExponentiation(bobPK, aliceSK, groupZ, withOutput);

		showMsg("shared Key = " + sharedKey, true);
	}
	
	/**
	 * function to set all aliceKey : groupZ, primitiveRoot and aliceSK and compute alicePK
	 * @param _groupZ
	 * @param _primitiveRoot
	 * @param _aliceSK
	 */
	public void setAliceKey(long _groupZ, long _primitiveRoot, long _aliceSK)
	{
		groupZ = _groupZ;
		primitiveRoot = _primitiveRoot;
		aliceSK = _aliceSK;
		
		fastExponentiation fe = new fastExponentiation();
		alicePK = fe.runFastExponentiation(primitiveRoot, aliceSK, groupZ, false);
	}
	
	/**
	 * find the shared key with bob public key, alice secret key and prime number
	 * @param _bobPK
	 * @author tandhy
	 */
	public void computeAliceSharedKey(long _bobPK)
	{
		bobPK = _bobPK;
		fastExponentiation fe = new fastExponentiation();
		sharedKey = fe.runFastExponentiation(bobPK, aliceSK, groupZ, false);
	}
	
	
	/**
	 * this function will :
	 * 1. convert each character in string to array of integer
	 * 2. multiply each integer with sharedKey
	 * 3. padded result with n-bit length zero
	 * 4. return a string
	 * @param _plainText
	 * @return
	 */
	public String encryptText(String _plainText)
	{
		String _encryptedText = "";
		long[] arrOfInt = new long[_plainText.length()];
		convertFunction cf = new convertFunction();
		String paddedZero = "";
		long[] arrOfEncryptedInt = new long[_plainText.length()]; // handle multiplication of message to sharedKey
		
		// 1. convert each character in string to array of integer
		arrOfInt = cf.convertStringToInteger(_plainText);
		// set to global variable
		arrOfPlainTextInInteger = new long[_plainText.length()];
		arrOfPlainTextInInteger = arrOfInt;
		
		// 2. multiply each integer with sharedKey
		for( int i = 0 ; i < arrOfInt.length ; i++ )
		{
			paddedZero = "";
			arrOfEncryptedInt[i] = (arrOfInt[i] * sharedKey ) % groupZ;
			
			// 3. padded arrOfDecryptedInt with maximum nLength zero
			for ( int j = 0 ; j < (Integer.toString((int) groupZ).length() - (Integer.toString((int) arrOfEncryptedInt[i]).length())) ; j++ )
				paddedZero += "0";
				
			_encryptedText += paddedZero + arrOfEncryptedInt[i];
		}
		
		// padded
		return _encryptedText;
	}
	
	/**
	 * function converted encrypted text to plain text
	 * Step is the following :
	 * 1. convert encrypted text to array of integer
	 * 2. find the multiplicative inverse of sharedKey
	 * 3. compute array of integer with the inverse
	 * 4. convert int to text
	 * @param _encryptedText
	 * @param _groupZ
	 * @param _sharedKey
	 * @return
	 */
	public String decryptText(String _encryptedText, long _groupZ, long _sharedKey)
	{
		// 1. convert encrypted text to array of integer
		long[] arrOfInt = new long[_encryptedText.length()];
		convertFunction cf = new convertFunction();
		arrOfInt = cf.convertEncryptedStringToInteger(_encryptedText, Integer.toString((int) _groupZ).length());
		
		// 2. find the multiplicative inverse of sharedKey
		multiplicativeInverse mi = new multiplicativeInverse(_sharedKey, _groupZ);
		long _inverseOfSharedKey = mi.getInverseX();
		
		// 3. compute array of integer with the inverse
		long[] arrOfDecryptedInt = new long[arrOfInt.length];
		for ( int i = 0 ; i < arrOfInt.length ; i++ )
		{
			arrOfDecryptedInt[i] = (_inverseOfSharedKey * arrOfInt[i]) % _groupZ;
		}
		
		// 4. convert int to text
		return cf.convertIntegerToString(arrOfDecryptedInt);
	}
	
	/**
	 * break the enryptedText with private Key
	 * Steps are :
	 * 1. Convert String to array of Integer
	 * 2. find the sharedKey = primitiveRoot^(publicKey*privateKey) mod groupZ
	 * 3. find the multiplicative inverse of sharedKey
	 * 4. compute array of integer with the inverse
	 *  
	 * @param _encryptedText
	 * @param _groupZ
	 * @param _privateKey
	 * @return
	 */
	public String breakEncryptedText(String _encryptedText, long _groupZ, long _primitiveRoot, long _publicKey, long _privateKey)
	{
		String _decryptedText = "";
		
		// 1. Convert String to array of Integer
		long[] arrOfInt = new long[_encryptedText.length()];
		convertFunction cf = new convertFunction();
		arrOfInt = cf.convertEncryptedStringToInteger(_encryptedText, Integer.toString((int) _groupZ).length());
		
		// 2. find the sharedKey = primitiveRoot^(publicKey*privateKey) mod groupZ
		fastExponentiation fe = new fastExponentiation();
		long _sharedKey = fe.runFastExponentiation(_primitiveRoot, (_privateKey*_publicKey), _groupZ, false);
		
		// 3. find the multiplicative inverse of sharedKey
		multiplicativeInverse mi = new multiplicativeInverse(_sharedKey, _groupZ);
		long _inverseOfSharedKey = mi.getInverseX();
		
		// 4. compute array of integer with the inverse
		long[] arrOfDecryptedInt = new long[arrOfInt.length];
		for ( int i = 0 ; i < arrOfInt.length ; i++ )
		{
			arrOfDecryptedInt[i] = (_inverseOfSharedKey * arrOfInt[i]) % _groupZ;
		}
		
		// 4. convert int to text
		return cf.convertIntegerToString(arrOfDecryptedInt);

		//return _decryptedText;
	}
	
	/**
	 * Compute bobPK = primitiveRoot^bobSK mod groupZ
	 * compute sharedKey = alicePK^bobSK mod groupZ
	 * @param _primitiveRoot
	 * @param _bobSK
	 * @param _groupZ
	 * @param _alicePK
	 * @throws IOException 
	 */
	public long computeSharedKey(long _primitiveRoot, long _groupZ, long _alicePK) throws IOException
	{
		System.out.println("Input private Key B :");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String _bobSK = reader.readLine();
		bobSK = Long.parseLong(_bobSK);
		
		long _sharedKey = 0;
		fastExponentiation fe = new fastExponentiation();
		long _bobPK = fe.runFastExponentiation(_primitiveRoot, bobSK, _groupZ, false);
		return fe.runFastExponentiation(_alicePK, bobSK, _groupZ, false);
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