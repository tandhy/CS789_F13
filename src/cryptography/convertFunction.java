package cryptography;

public class convertFunction implements basicAlgorithm {
	// private variables
	
	// empty constructor
	public convertFunction() {

	}	
	
	/**
	 * convert original string to array of integer
	 * this function will convert char by char and get the ascii code
	 * @param stringText
	 * @return array of integer
	 * @author tandhy
	 */
	public long[] convertStringToInteger(String stringText)
	{
		long[] _integerText = new long[stringText.length()];

		for ( int i = 0 ; i < stringText.length() ; i++ )
			_integerText[i] = (int) stringText.charAt(i);
		

		return _integerText;
	}
	
	/**
	 * convert array of integer, contain number, to char
	 * @param integerText
	 * @param nLength
	 * @return String
	 * @author tandhy
	 */
	public String convertIntegerToString(long[] integerText)
	{
		String _string = "";
		for( int i = 0 ; i < integerText.length ; i++ )
		{
			_string += (char) integerText[i];
		}
		
		return _string;
	}
	
	/**
	 * convert a given encrypted message to integer
	 * check whether the string length is divisibly by the length of numberN
	 * @param _encryptedText
	 * @param nLength
	 * @return array of encrypted text in integer/long type
	 * @author tandhy
	 */
	public long[] convertEncryptedStringToInteger(String _encryptedText, long nLength)
	{
		long[] _arrOfInt = new long[(int) (_encryptedText.length()/nLength)+1];
		int i = 0;
		// check whether _encryptedText length is divisible by nLength
		if( _encryptedText.length() % nLength == 0 )
		{
			// read _encryptedText from the beginning
			for( i = 0 ; i < (_encryptedText.length()/nLength) ; i++)
			{
				_arrOfInt[i] = Integer.parseInt(_encryptedText.substring( (int) (i*nLength), (int) ((i*nLength) + nLength) ));
			}
		}
		else
		{
			// read _encryptedText from the end
			for( i = (int) (_encryptedText.length()/nLength) - 1 ; i > 0 ; i-- )
			{
				_arrOfInt[i] = Integer.parseInt(_encryptedText.substring((int)(i*nLength)-5, (int)(i*nLength)));
			}
		}
		
		long[] arrOfInt = new long[i];
		int j = 0;
		while(_arrOfInt[j] != 0 )
		{
			arrOfInt[j] = _arrOfInt[j];
			j++;
		}
		
		return arrOfInt;
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