package cryptography;

import java.util.Arrays;

public class babyStepGiantStep implements basicAlgorithm {
	// private variables
	private long groupZ;
	private long base;
	private long number;
	
	
	// constructor
	public babyStepGiantStep(long _groupZ, long _base, long _number)
	{
		groupZ = _groupZ;
		base = _base;
		number = _number;
	}
	
	/**
	 * run baby-step Giant-step to find the private key of a given public key, prime number and generator
	 * @return privateKey
	 * @author tandhy
	 */
	public long runBabyStepGiantStep()
	{
		int m = 0;
		long orderGroupZ = groupZ - 1;
		int inverse = 0;


		
		// 1. find m, ceiling of squareRoot of groupZ-1
		m = (int) Math.ceil(Math.sqrt( orderGroupZ ));
		// 2. find the inverse of base
		multiplicativeInverse mi = new multiplicativeInverse(base, groupZ);
		inverse = mi.getInverseX();
		
		// 3. computer base^j mod groupZ for 0<j<m and put in pair with j
		long[] elementBase = new long[m];		
		fastExponentiation fe = new fastExponentiation();
		for ( int i = 0 ; i < m ; i++ )
		{
			elementBase[i] = fe.runFastExponentiation(base, i, groupZ, false);
		}
		
		// 3. compute (inverse)^m
		long inverseRaiseToM = fe.runFastExponentiation(inverse, m, groupZ, false);
		
		// 4. calculate number*(inverse)^im, and check the result in elementBase
		boolean foundTheI = false;
		long[] arrOfResult = new long[m];
		arrOfResult[0] = number;
		int i = 1;
		long _index = -1;
		_index = getTheIndex(elementBase,arrOfResult[i]);
		if( _index != -1 )
			foundTheI = true;

		while ( i < m && !foundTheI)
		{
			arrOfResult[i] = (arrOfResult[i-1] * inverseRaiseToM) % groupZ;
			_index = getTheIndex(elementBase,arrOfResult[i]);
			if( _index != -1 )
				foundTheI = true;
			i++;
		}
		
		long answer = 0;
		i--;
		if (foundTheI)
		{
			answer = ( i * m ) + _index;
		}
		else
		{
			answer = arrOfResult[i-1] * inverseRaiseToM;
		}
		
		return answer;
	}
	
	/**
	 * find the index of the b^j from the array
	 * @param _elementBase
	 * @param _result
	 * @return the index of the array
	 */
	public long getTheIndex(long[] _elementBase, long _result)
	{
		long _index = -1;
		int counter = 0;
		
		while ( _index == -1 && counter < _elementBase.length )
		{
			if (_elementBase[counter] == _result )
				_index = counter;
			counter++;
		}
		return _index;
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