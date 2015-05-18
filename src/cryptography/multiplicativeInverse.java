package cryptography;

public class multiplicativeInverse implements basicAlgorithm {
	// private variables
	private int varInverseX;
	
	// constructors
	/**
	 * a constructor
	 * find the inverse of x in group m
	 * @param x
	 * @param m
	 * @author tandhy
	 */
	public multiplicativeInverse(long x, long m)
	{
		// y is a multiplicative inverse of x modulo m if (xy) mod m = 1
		int y = 1;
		long remaining = 0;
		while (remaining != 1)
		{
			remaining = ( x * y ) % m ;
			y++;
			// while loop will end if remaining = 1 and we will get Y
		}
		
		varInverseX = y - 1;
	}
	
	/**
	 * @return the inverse of x in group m
	 * @author tandhy
	 */
	public int getInverseX() {
		return varInverseX;
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