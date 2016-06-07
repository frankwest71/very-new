//package DCompiler;

public class PoolData{
	int tag;
	String v1;
	String v2;
	public  PoolData(int tag1,String value1,String value2)
	{
		tag=tag1;
		v1=value1;
		v2=value2;
	}
	public PoolData()
	{
	}
	public int getTag()
	{
		return tag;
	}
	public String getV1()
	{
		return v1;
	}
	public String getV2()
	{
		return v2;
	}
	public void putTag(int t)
	{
		tag=t;
	}
	public void putV1(String s)
	{
		v1=s;
	}
	public void putV2(String s)
	{
		v2=s;
	}
	
}