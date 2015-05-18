package cryptography;

import java.io.IOException;
import java.math.BigInteger;

/**
 * main program to run all the cryptograph
 * @author tandhy
 *
 */
public class mainProgram {
	public static void main(String[] args) throws IOException
	{
		
		String _plainText = "We love Cryptograph!!";
		long _groupZ = 8719;
		long _primitiveRoot = 3;
		long _aliceSK = 385;
		long _bobPK = 8301;
		runDiffieHellman(_plainText, _groupZ, _primitiveRoot, _aliceSK, _bobPK);
		

		
		// Decrypt with Diffie Hellman
		/*long _groupZ = 9511;
		long _base = 3;
		long _number = 6228;
		long _bobPK = 8194;
		String _encryptedText = "507952955476540304670575529554766016425043581728677204677817425002511728035967376737";
		DecryptWithDH(_encryptedText, _groupZ, _bobPK);
		*/
		
		// Diffie Hellman breaker
		/*long _groupZ = 9511;
		long _base = 3;
		long _alicePK = 8030;
		long _bobPK = 6692;
		String _encryptedText = "8388843106616115753902153654611505757007";
		//1. get private key with baby step from receiver public key
		//2. use doris pk to the power of chang public key 
		breakWithDH(_encryptedText, _groupZ, _base, _bobPK, _alicePK);
		*/

		// run RSA
		/*String _plainText = "CS789";
		long numberP = 81219;
		long numberQ = 15541;
		// numberN = 13004897;
		long encryptionKey = 8399;
		runRSA(_plainText, numberP, numberQ, encryptionKey);
		*/
		
		// cipher with RSA
		/*long _numberN = 121981;
		long _encryptionKey = 883;
		cipherWithRSA(_plainText, _numberN, _encryptionKey);
		*/
		
		// run RSA Breaker
		/*String _encryptedText = "07667276620934638216042376308902056275240577584501";
		// 397*73
		long _numberN = 1262224479;
		long _encryptionKey = 8399;
		runRSABreaker(_encryptedText, _numberN, _encryptionKey);
		*/
	}
	
	/**
	 * break encrypted text with diffie hellman
	 * @param _encryptedText
	 * @param _groupZ
	 * @param _base
	 * @param _bobPK
	 * @param _alicePK
	 * @author tandhy
	 */
	private static void breakWithDH(String _encryptedText, long _groupZ, long _base, long _bobPK, long _alicePK)
	{
		long _bobPrivateKey = findDiffieHellmanPrivateKey(_groupZ, _base, _bobPK);
		diffieHellman df = new diffieHellman();

		// find shared key
		fastExponentiation fe = new fastExponentiation();
		long _sharedKey = fe.runFastExponentiation(_alicePK, _bobPrivateKey, _groupZ, false);

		String _decryptedText = df.decryptText(_encryptedText, _groupZ, _sharedKey);
		
		showMsg("prime Number = " + _groupZ, true);
		showMsg("generator = " + _base, true);
		showMsg("Sender Public Key = " + _alicePK, true);
		showMsg("Receiver Public Key = " + _bobPK, true);
		showMsg("Decrypted Text = " + _decryptedText, true);

		
		//String _decryptedText = df.breakEncryptedText(_encryptedText, _groupZ, _base, _alicePK, _bobPrivateKey);
		//showMsg(_decryptedText, true);
	}
	
	/**
	 * function to run Diffie Hellman key excahnge
	 * @author tandhy
	 */
	private static void runDiffieHellman(String _plainText, long _groupZ, long _primitiveRoot, long _aliceSK, long _bobPK)
	{
		diffieHellman df = new diffieHellman();
		//df.runDiffieHellman();
		//String _plainText = "MEET ME";
		df.setAliceKey(_groupZ, _primitiveRoot, _aliceSK);
		long _alicePK = df.getAlicePK();
		System.out.println("GroupZ = " + _groupZ);
		System.out.println("primitiveRoot = " + _primitiveRoot);
		System.out.println("alice Secret Key = " + _aliceSK);
		System.out.println("alice Public Key = " + _alicePK);
		
		// compute shared Key
		System.out.println("bob Public Key = " + _bobPK);
		df.computeAliceSharedKey(_bobPK);
		long _sharedKey = df.getSharedKey();
		System.out.println("Shared Key = " + _sharedKey);
		
		// encrypt the text
		System.out.println("plain Text = " + _plainText);
		String _encryptedText = df.encryptText(_plainText);
		//System.out.println("plain Text in Integer = " + df.g);
		System.out.println("Encrypted Text = " + _encryptedText);
		
		//_encryptedText = "27861002417941798258012022951433010014531002229559831904600301202295682545906374139359237336"; 
		// decrypt the text
		//String _decryptedText = df.decryptText(_encryptedText, _groupZ, _sharedKey);
		//System.out.println("Decrypted Text = " + _decryptedText);
		
	}
	
	/**
	 * decrypt a message with diffie hellman
	 * @param _encryptedText
	 * @param _groupZ
	 * @param _bobPK
	 * @author tandhy
	 */
	private static void DecryptWithDH(String _encryptedText, long _groupZ, long _bobPK)
	{
		diffieHellman df = new diffieHellman();
		df.computeAliceSharedKey(_bobPK);
		long _sharedKey = df.getSharedKey();
		String _decryptedText = df.decryptText(_encryptedText, _groupZ, _sharedKey);
		System.out.println("Decrypted Text = " + _decryptedText);
	}
	
	/**
	 * find the private key from a given _number(public key), _base(generator), _groupZ(prime number)
	 * @param _groupZ
	 * @param _base
	 * @param _number
	 * @return private key
	 * @author tandhy
	 */
	private static long findDiffieHellmanPrivateKey(long _groupZ, long _base, long _number)
	{
		// Baby-step Giant-step
		// compute log2 3 in Z29
		long startTime = 0, endTime = 0, diffTime = 0;
		startTime = System.nanoTime();
		babyStepGiantStep bbgs = new babyStepGiantStep(_groupZ, _base, _number);
		endTime = System.nanoTime(); // catch end time
		diffTime = endTime - startTime;
		long _privateKey = bbgs.runBabyStepGiantStep();
		/*showMsg("groupZ : " + _groupZ,true);
		showMsg("base : " + _base,true);
		showMsg("number : " + _number ,true);
		showMsg("Private Key : " + _privateKey ,true);
		showMsg("Breaking time = " + diffTime + " ns", true);
		*/
		return _privateKey;
	}
	
	/**
	 * run RSA encryption with a given plain text, p, q, and encryption key
	 * @param _plainText
	 * @param _numberP
	 * @param _numberQ
	 * @param _encryptionKey
	 * @author tandhy
	 */
	public static void runRSA(String _plainText, long _numberP, long _numberQ, long _encryptionKey)
	{
		long startTime = 0, endTime = 0, diffTime = 0;
		long numberN = _numberP * _numberQ;
		long _phiNumberN = ( _numberP - 1 ) * ( _numberQ - 1 );
		//String plainText = "MEET ME";
		startTime = System.nanoTime();
		RSA rsa = new RSA(_numberP, _numberQ , _encryptionKey);
		String encryptedText = rsa.encryptText(_plainText);
		endTime = System.nanoTime(); // catch end time
		diffTime = endTime - startTime;
		showMsg("Plain Text : " + rsa.getPlainText(), true);
		showMsg("Plain Integer : " + rsa.getStringArrOfInteger(), true);	
		showMsg("NumberN : " + numberN, true);
		showMsg("phiNumberN : " + _phiNumberN, true);
		showMsg("Encryption Key(e) : " + rsa.getEncryptionKey(), true);
		showMsg("Encrypted Text : " + encryptedText, true);
		showMsg("Encryption time = " + diffTime + " ns", true);
		startTime = System.nanoTime();
		rsa.decryptText( encryptedText , numberN , _encryptionKey );
		endTime = System.nanoTime(); // catch end time
		diffTime = endTime - startTime;
		showMsg("Inverse of e : " + rsa.getInverse(), true);
		//showMsg("Decrypted Text : " + rsa.getDecryptedText(), true);
		showMsg("Decryption time = " + diffTime + " ns", true);
	}
	
	/**
	 * encrypt plain text with RSA with given plaintetx, number N(p*q) and encryption Key
	 * @param _plainText
	 * @param _numberN
	 * @param _encryptionKey
	 * @author tandhy
	 */
	public static void cipherWithRSA(String _plainText, long _numberN, long _encryptionKey)
	{
		long startTime = 0, endTime = 0, diffTime = 0;
		//String plainText = "MEET ME";
		startTime = System.nanoTime();
		RSA rsa = new RSA(_numberN , _encryptionKey);
		String encryptedText = rsa.encryptText(_plainText);
		endTime = System.nanoTime(); // catch end time
		diffTime = endTime - startTime;
		showMsg("Plain Text : " + rsa.getPlainText(), true);
		showMsg("Plain Integer : " + rsa.getStringArrOfInteger(), true);	
		showMsg("NumberN : " + _numberN, true);
		showMsg("Encryption Key(e) : " + rsa.getEncryptionKey(), true);
		showMsg("Encrypted Text : " + encryptedText, true);
		showMsg("Encryption time = " + diffTime + " ns", true);
		startTime = System.nanoTime();
		showMsg("Decryption time = " + diffTime + " ns", true);
	}
	
	/**
	 * find the plain text from a given encrypted text, numberN and encryption key
	 * @param _encryptedText
	 * @param _numberN
	 * @param _encryptionKey
	 * @author tandhy
	 */
	public static void runRSABreaker(String _encryptedText, long _numberN, long _encryptionKey)
	{
		long startTime = 0, endTime = 0, diffTime = 0;
		startTime = System.nanoTime();
		RSABreaker rsab = new RSABreaker(_encryptedText, _numberN, _encryptionKey);
		rsab.runRSABreaker();
		endTime = System.nanoTime(); // catch end time
		diffTime = endTime - startTime;
		showMsg("Encrypted Text : " + _encryptedText, true);
		showMsg("Public N : " + _numberN, true);
		showMsg("encryption Key : " + _encryptionKey, true);
		showMsg("Decrypted Text : " + rsab.getDescryptedText(), true);
		showMsg("Decryption time = " + diffTime + " ns", true);
	}
	
	/**
	 * show Message to console
	 * @param aMsg double
	 * @param newLine
	 * @author tandhy
	 */
	public static void showMsg(double aMsg, boolean newLine) {
		if(newLine)
			System.out.println(aMsg);
		else
			System.out.print(aMsg);
		
	}
	
	/**
	 * show Message in console
	 * @param aMsg String
	 * @param newLine boolean
	 * @author tandhy
	 * date 11.11.13
	 */
	public static void showMsg(String aMsg,boolean newLine)
	{
		if(newLine)
			System.out.println(aMsg);
		else
			System.out.print(aMsg);
		
	}
	
	
}
/*String strNumberN = "d94d889e88853dd89769a18015a0a2e6bf82bf356fe14f251fb4f5e2df0d9f9" +
"a94a68a30c428b39e3362fb3779a497eceaea37100f264d7fb9fb1a97fbf6211" +
"33de55fdcb9b1ad0d7a31b379216d79252f5c527b9bc63d83d4ecf4d1d45cbf8" +
"43e8474babc655e9bb6799cba77a47eafa838296474afc24beb9c825b73ebf549";

String strE = 	"47b9cfde843176b88741d68cf096952e950813151058ce46f2b048791a26e507a109579" +
"3c12bae1e09d82213ad9326928cf7c2350acb19c98f19d32d577d666cd7bb8b2b5ba629" +
"d25ccf72a5ceb8a8da038906c84dcdb1fe677dffb2c029fd8926318eede1b58272af22b" +
"da5c5232be066839398e42f5352df58848adad11a1";

//  BigInteger value = new BigInteger(hex, 16);
BigInteger numberN = new BigInteger(strNumberN, 16);
System.out.println(" numberN = " + numberN.toString());
BigInteger e = new BigInteger(strE, 16);
System.out.println(" e = " + e.toString());
*/
