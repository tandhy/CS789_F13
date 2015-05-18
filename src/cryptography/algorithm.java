package cryptography;

import java.io.IOException;
import java.util.Random;

public class algorithm {
	public static void main(String[] args) throws IOException{
		// Pollar Rho : RSA breaker
		/*long _numberN = 3123;
		long[] _divisorOfNumberN = new long[2];
		// check if _numberN is prime or not, because pollar Rho works in numberN NOT prime
		checkPrimeNumber cpn = new checkPrimeNumber();
		cpn.setPrimeNumber(_numberN);
		if (cpn.isNotPrimeNumber())
		{
			// factorization with Pollar Rho
			pollarRho pr = new pollarRho(_numberN);
			pr.runPollarRho();
			_divisorOfNumberN[0] = pr.getDivisorOfNumberN();
			if( _divisorOfNumberN[0] == _numberN)
			{
				pr.runPollarRho2();
				_divisorOfNumberN[0] = pr.getDivisorOfNumberN();
			}
			
			// divisor of numberN found, find the pair
			_divisorOfNumberN[1] = _numberN / _divisorOfNumberN[0];
				
			showMsg("numberN : " + _numberN,true);
			showMsg("p : " + _divisorOfNumberN[0] , true);
			showMsg("q : " + _divisorOfNumberN[1] , true);
		}
		*/
		
		// Baby-step Giant-step : Diffie Hellman breaker
		// compute log2 3 in Z29
		/*long _groupZ = 25667;
		long _base = 2;
		long _number = 24900;
		babyStepGiantStep bbgs = new babyStepGiantStep(_groupZ, _base, _number);
		long _privateKey = bbgs.runBabyStepGiantStep();
		showMsg("groupZ : " + _groupZ,true);
		showMsg("base : " + _base,true);
		showMsg("number : " + _number ,true);
		showMsg("Answer : " + _privateKey ,true);
		*/ 
		
		// fermat factorization
		/*long numberN = 3123;
		fermatFactorization ff = new fermatFactorization(numberN);
		ff.runFermatFactorization();
		long[] fact = new long[2];
		fact = ff.getNumberNFact();
		showMsg("number to factorize : " + numberN,true);
		showMsg("fact 1 : " + fact[0],true);
		showMsg("fact 2 : " + fact[1] ,true);
		*/
		
		// convert String to Integer and padded zero in front
		// with max n-bit length
		/*long nLength = 5;
		long e = 3;
		long groupZ = 3233;
		String _stringText = "MEET ME";
		convertFunction cf = new convertFunction();
		cf.convertStringToInteger(_stringText);
		*/

		 
		// decrypt text
		//String encryptedText = "0680197619761065043806801976";
		
		
		// blum blum shub generator
		/*long maxNumber = 12;
		long groupZ = 0; 
		while (groupZ == 0)
		{
			blumblumShubGenerator bbsg = new blumblumShubGenerator(maxNumber);
			groupZ = bbsg.getGeneratedNumber();
		}
		// assign groupZ manual
		// groupZ = 922337203685477632;
		showMsg("Generated Number : " + groupZ, true);
		*/
		
		// RSA
		/*long numberP = 149;
		long numberQ = 151;
		long numberN = numberP * numberQ;
		long encryptionKey = 43;
		String plainText = "MEET ME";
		RSA rsa = new RSA(numberP, numberQ , encryptionKey);
		String encryptedText = rsa.encryptText(plainText);
		rsa.decryptText(encryptedText,numberN, encryptionKey);
		showMsg("Plain Text : " + rsa.getPlainText(), true);
		showMsg("Plain Integer : " + rsa.getStringArrOfInteger(), true);	
		showMsg("phiNumberN : " + rsa.getPhiNumberN(), true);
		showMsg("Encryption Key(e) : " + rsa.getEncryptionKey(), true);
		showMsg("Encrypted Text : " + encryptedText, true);
		showMsg("Inverse of e : " + rsa.getInverse(), true);
		showMsg("Decrypted Text : " + rsa.getDecryptedText(), true);
		// --------------------		
		*/
		
		// primitive root search
		/*int primeNumber = 9511;
		primitiveRootSearch prs = new primitiveRootSearch(primeNumber);
		prs.runPrimitiveRootSearch();
		//int[] arr = prs.getPrimitiveRoots();
		showMsg("Primitive Roots of " + primeNumber + " : " + prs.getPrimitiveRootsInString(), true);
		// --------------------
		*/
		
		// numberFactorization
		/*long number = 3123;
		numberFactorization nf = new numberFactorization(number);
		findPrimeNumber fpn = new findPrimeNumber(number+1);
		fpn.listPrimeNumber();
		long[] listOfPrimeNumber = fpn.getListOfPrimeNumber();
		nf.setListOfPrimeNumber(listOfPrimeNumber);
		nf.runNumberFactorization();
		showMsg(nf.getFactorizationResult(), true);
		// --------------------
		*/
		
		// generate random number
		/*int maxNumber = 999999;
		generatePrimeNumber gpn = new generatePrimeNumber(maxNumber);
		showMsg(gpn.getGeneratedPrimeNumber() + " is Prime", true);
		
		// check factorization
		showMsg("Find factorization...",true);
		runFactorization(gpn.getGeneratedPrimeNumber());
		*/
		
		/*int aNumber = gpn.generateNumber();
		checkPrimeNumber cpn = new checkPrimeNumber(aNumber);
		if(cpn.isNotPrimeNumber()) 
		{
			showMsg(aNumber + " is not Prime", true);
			showMsg("Divisible By : " + cpn.getDivisibleBy(),true);
		}
		else 
		{
			showMsg(aNumber + " is Prime", true);
			
		}
		*/

		
		// diffieHellman
		/*diffieHellman dh = new diffieHellman();
		dh.createEncryptedText();
		*/
		
		// factorization
		//runFactorization(1541);
		
		
		// get list of relatively prime number
		/*int number = 15541;
		findRelativelyPrimeNumber frpn = new findRelativelyPrimeNumber(number);
		frpn.listRelativelyPrimeNumber();
		frpn.createListOfRelativelyPrimeNumber();
		long[] arr = frpn.getListOfRelativelyPrimeNumber();
		showMsg("List of Relatively Prime Number of " + number + " = ", true);
		showMsg(frpn.getRelativelyPrimeNumberInString(), true);
		showMsg("Total of Relatively Prime Number = " + frpn.getTotalOfRelativelyPrimeNumber(), true);
		*/
		
		// is Relatively Prime Number ?
		//gcd(614213,51331)
		/*long m = 614213;
		long n = 51331;
		GCD gcd = new GCD();
		if(gcd.isRelativelyPrime(m, n, true)) 
			showMsg(m + " and " + n + " is Relatively Prime", true);
		else
			showMsg(m + " and " + n + " is NOT Relatively Prime", true);
		*/
		
		// count multiplicative inverse of X
		/*long x = 87;
		long m = 103;
		multiplicativeInverse mi = new multiplicativeInverse(x, m);
		showMsg("Multiplicative inverse of " + x + " mod " + m + " = " + mi.getInverseX(), true);
		*/
		
		// list prime number
		//runFindPrimeNumber(21);
		
		// fast Exponentiation
		/*int x=3123;
		int e=2753;
		int z=3233;
		boolean withOutput = false;
		// x^e mod z
		runFastExponentialAlgo(x, e, z, withOutput);
		*/
		
		/*long m = 31;
		long n = 5;
		runNewGCD(m, n, true);
		showMsg("--------------------------", true);
		runExpandedEuclidean(m, n, true);
		*/
		
		/*diffieHellman dh = new diffieHellman();
		long secretKey = dh.getSecretKey(withOutput);
		showMsg("Secret Key = " + secretKey, true);
		inverse inv = new inverse(secretKey, dh.getGroupZ(), withOutput);
		showMsg("Inverse of Secret Key : " + inv.getInverse(), true);*/
	}
	
	public static void runFactorization(long primeNumber)
	{
		factorization fac = new factorization(primeNumber);
		fac.factorizePrimeNumber();
		long[] factPrimeNumber = new long[2];
		factPrimeNumber = fac.getFactorizationPrimeNumber();
		showMsg("Factorization of " + primeNumber + " is " + factPrimeNumber[0] + " and " + factPrimeNumber[1], true);
	}
	
	public static void runFindPrimeNumber(long primeNumber)
	{
		findPrimeNumber fpn = new findPrimeNumber(primeNumber);
		fpn.listPrimeNumber();
		showMsg(fpn.getListOfPrimeNumberInString(), true);
	}
	
	public static void runNewGCD(long m, long n, boolean withOutput) throws NullPointerException
	{
		try {
			//global Global = new global((int) Math.floor(Math.log(n))); 
			//Global = newGCD(m, n, true);
			new GCD(m, n, withOutput);
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void runExpandedEuclidean(long m, long n, boolean withOutput)
	{
		// find GCD first
		GCD gcd = new GCD(m, n, withOutput);
		new expandedEuclidean(gcd.getFinalResult(), withOutput);
	}
		
	public static void runGCD() throws IOException
	{
		/*showMsg("Euclidean Algorithm", true);
		showMsg("-------------------", true);
		showMsg("Find GCD(x,y) :", true);
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		showMsg("x = ", false);
		String inputX = reader.readLine();
		showMsg("y = ", false);
		String inputY = reader.readLine();
		*/
	}
			
	/**
	 * run Fast Exponential Algorithm
	 * @author tandhy
	 */
	public static void runFastExponentialAlgo(int x, int e, int z, boolean withOutput)
	{
		fastExponentiation fe = new fastExponentiation(x, e, z, withOutput);	
		showMsg(x + "^" + e + " mod " + z + " = " + fe.getFinalResult(), true);
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