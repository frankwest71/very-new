import java.util.*;
public class ClassInfo{
	String name="",sclass="",access="";
	int interface_count;
	ArrayList FieldEntries;
	ArrayList MethodEntries;
public ClassInfo(String s1,String s2,String s3,int s4,ArrayList fields,ArrayList meth)
	{
		name=s2;
		access=s1;
		sclass=s3;
		interface_count=s4;
		FieldEntries=fields;
		MethodEntries=meth;
	}
	public ArrayList getFieldEntries()
	{
		return(FieldEntries);
	}
	public ArrayList getMethodEntries()
	{
		return(MethodEntries);
	}
	public String toString()
	{
		return("Name :"+name+"\tAccess :"+access+"\tSuper Class :"+sclass+"\tInterface Count :"+interface_count);
	}
	public String getName()
	{
		return(name);
	}
public String getAccess()
{
return(access);
}
public String getSClass()
{
return(sclass);
}
public int getICount()
{
return(interface_count);
}
	
}