package cryptography;

/**
 * class to hold variable for GCD
 * @author tandhy
 */
public class global{
    private long dataM[];
    private long dataN[];
    private long dataMulti[];
    private long dataRemaining[];

    public global(long[] sDataM, long[] sDataN, long[] sDataMulti, long[] sDataRemaining)
    {
    	dataM = sDataM;
    	dataN = sDataN;
    	dataMulti = sDataMulti;
    	dataRemaining = sDataRemaining;
    }
    
    public global(int arrSize)
    {
        dataM = new long[arrSize];
        dataN = new long[arrSize];
        dataMulti = new long[arrSize];
        dataRemaining = new long[arrSize];
    }
    
    public long[] getDataM(){
        return dataM;
    }

    public void setDataM(long[] data){
        dataM = data;
    }
    
    public long getDataMLength()
    {
    	return dataM.length;
    }

    public long[] getDataN(){
        return dataN;
    }

    public void setDataN(long[] data){
        dataN = data;
    }

    public long getDataNLength()
    {
    	return dataN.length;
    }

    public long[] getDataMulti(){
        return dataMulti;
    }

    public void setDataMulti(long[] data){
    	dataMulti = data;
    }

    public long getDataMultiLength()
    {
    	return dataMulti.length;
    }

    public long[] getDataRemaining(){
        return dataRemaining;
    }

    public void setDataRemaining(long[] data){
    	dataRemaining = data;
    }

    public long getDataRemainingLength()
    {
    	return dataRemaining.length;
    }

}