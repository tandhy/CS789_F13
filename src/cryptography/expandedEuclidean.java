package cryptography;

public class expandedEuclidean implements basicAlgorithm {
	// private variables
	// s.m + t.n = 1
	private long varS;
	private long varT;
	
	// constructor
	/**
	 * find the variable to perform expanded euclidean
	 * @param Global
	 * @param withOutput
	 * @author tandhy
	 */
	public expandedEuclidean(global Global, boolean withOutput)
	{
		// get dataM and dataN from global		
		String aMsg = "";
		long dataM[] = Global.getDataM();
		long dataN[] = Global.getDataN();
		long[] dataMulti = Global.getDataMulti();
		long dataRemaining[] = Global.getDataRemaining();
		
		long dataMSum[] = new long[dataM.length];
		long dataNSum[] = new long [dataN.length];
		long str1Tmp[] = new long[dataM.length];
		long str1TmpSum[] = new long[dataM.length];
		long str2Tmp[] = new long[dataM.length];
		long str2TmpSum[] = new long[dataM.length];
		int j = 0;

		try
		{
			dataMSum[j] = 1;
			dataNSum[j] = dataMulti[dataM.length - 1];
			str1Tmp[j] = dataM[dataM.length-2];
			str1TmpSum[j] = 1;
			str2Tmp[j] = dataN[dataM.length-2];
			str2TmpSum[j] = dataMulti[dataM.length-2];
			
			aMsg = "1 = " + str1TmpSum[j] + "." + str1Tmp[j] + " - " + str2TmpSum[j] + "." + str2Tmp[j];
			j++;
			
			if(withOutput) showMsg(aMsg, true);
			for( int i = dataM.length - 2 ; i > 0 ; i-- )
			{
				if( str2Tmp[j-1] == dataRemaining[i-1])
				{
					str1TmpSum[j] = str1TmpSum[j-1] + (str2TmpSum[j-1] * dataMulti[i-1]);
					str1Tmp[j] = str1Tmp[j-1];
					str2TmpSum[j] = str2TmpSum[j-1];
					str2Tmp[j] = dataM[i-1];
					aMsg = "1 = " + str1TmpSum[j] + "." + str1Tmp[j] + " - " + str2TmpSum[j] + "." + str2Tmp[j];
				}
				else if( str1Tmp[j-1] == dataRemaining[i-1] )
				{
					str1TmpSum[j] = str1TmpSum[j-1];
					str1Tmp[j] = dataM[i-1];
					str2TmpSum[j] = str2TmpSum[j-1] + (str1TmpSum[j-1] * dataMulti[i-1]);
					str2Tmp[j] = str2Tmp[j-1];
					aMsg = "1 = " + str1TmpSum[j] + "." + str1Tmp[j] + " - " + str2TmpSum[j] + "." + str2Tmp[j];
				}
				
				if(withOutput) showMsg(aMsg, true);
				j++;			
			}
			
			// summary
			aMsg = "Answer : X = " + str1TmpSum[j-1] + " and Y = (" + (-1*str2TmpSum[j-1]) + ")";
			showMsg(aMsg, true);
			
			// assign str1TmpSum to S and str2TmpSum to T
			varS = str1TmpSum[j-1];
			varT = str2TmpSum[j-1];
			
		}
		catch (Exception e) {
			// TODO: handle exception
			showMsg(e.getMessage(), true);
		}
		
	}
	
	/**
	 * @return varS
	 * @author tandhy
	 */
	public long getVarS()
	{
		return varS;
	}
	
	/**
	 * @return varT
	 * @author tandhy
	 */
	public long getVarT()
	{
		return varT;
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