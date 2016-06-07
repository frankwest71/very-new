import java.io.DataInputStream;
import java.util.*;
public class MethodInfo{
	String access;
	String name;
	String type;
	int maxs;
	int maxl;
	int alen;
	int code[];
	int attr_count;
	int l;
	int xx=0;
	public MethodInfo()
	{
	}
	public MethodInfo(DataInputStream is,ArrayList list)
	{
		try{
		//System.out.println("\n=========================================================\n");
    access=getAccessType(is.readShort());
    //System.out.println("Access Type\t:"+access);
		name=((PoolData)list.get(is.readShort()-1)).getV2();
		type=((PoolData)list.get(is.readShort()-1)).getV2();
    attr_count=is.readShort();
		//System.out.println("Name\t\t:"+name);
		//System.out.println("Type\t\t:"+type);
		//System.out.println("Attribute Count\t:"+attr_count);
			//System.out.println("Attribute\t:"+
		((PoolData)list.get(is.readShort()-1)).getV2(); 
		///////////NEEDS ATENTION//////////
		int x=is.readShort()+is.readShort();
		alen=x;
		//System.out.println("Attribute Length:"+x);
		maxs=is.readUnsignedShort();
		maxl=is.readShort();
		x=is.readShort()+is.readShort();
		
		
		code=new int[x];
		int i=0;
		while(i<x)
			{
				code[i]=is.read();
				i++;
			}
			
		//is.skip(alen-(29+x));
		is.skip((alen-x-8));
		}catch(Exception e)
		{
			System.out.println("\nException Raised In The Method\t:"+e);
		}
	}
	
	
	///////////////////////NAME OF THE METHOD/////////////
	public String getName()
	{
		return(name);
	}
	///////////////////////RETURN TYPE OF THE METHOD//////
	public String getReturnType()
	{
		String str=type.substring(type.indexOf(')')+1);
		return(parseString(str)+" ");
	}
	
	///////////////////////ARGUMENTS PASSED///////////////
public String getArguments()
{
String str="";
String temp=type.substring(1,type.indexOf(')'));
while(true)
	{
		String arr="";
       		if(temp.equals(""))
			break;
			
			while(temp.charAt(0)=='[')
			{
			temp=temp.substring(1);
			arr+="[]";
			}
			str=str+parseString(temp)+arr;
			if(temp.charAt(0)=='L')
				{
				temp=temp.substring(temp.indexOf(';')+1);
				}
				else
					temp=temp.substring(1);
str=str+"#";					
			
	}
//System.out.println(temp);
/*try{
str=type.substring(1,type.indexOf(')'));
}
catch(Exception e)
	{
	}
    str=parseString(str);
	int i=0;
	while(i<l)
	{
		str+="[]";
		i++;
	}*/

	
return(convertString(str));
}

///////////////////////ACCESS TYPE OF THE METHOD///////////	
public String getAccess()
	{
		return(access+" ");
	}
	
public int[] getCode()
	{
		return(code);
	}	
	public String toString()
	{
		String str=access+" "+getReturnType()+" "+name+"("+getArguments()+")";
		return(str);
	}
	public String getAccessType(int a)
	{
		
		String str="";
		String s1="",s2="";
		if(a>=2048)
		{
			s2=s2+"strictfp ";
			a-=2048;
		}
		if(a>=1024)
		{
			s2=s2+"abstract ";
			a-=1024;
		}
		if(a>=256)
		{
			s2=s2+"native ";
			a-=256;
		}
		if(a>=32)
		{
			s2=s2+"synchronized ";
			a-=32;
		}
		if(a>=16)
		{
			s2=s2+"final ";
			a-=16;
		}
		if(a>=8)
		{
			s2=s2+"static ";
			a-=8;
		}
if(a>=4)
{
s1=s1+"protected ";
a-=4;
}
if(a>=2)
{
s1=s1+"private ";
a-=2;
}
if(a>=1)
{
s1=s1+"public ";
a-=1;
}
str=s1+s2;
return str;
	}
//////////////////////REMOVES THE / FROM STRING///////////
public String convertString(String str)
	{
		char c[]=str.toCharArray();
		int i=0;
		while(i<str.length())
		{
			if(c[i]=='/')
				c[i]='.';
				i++;
		}
		return(new String(c));
	}	
//////////////////////PARSES THE STRING//////////////////
	public String parseString(String name)
	{
	String arg="";
	if(name.charAt(0)=='L')
	{
	name=name.substring(1);
	arg+=name.substring(0,name.indexOf(';'));
	return arg;
	}
	else{
				if(name.charAt(0)=='I')
				arg+="int";
			if(name.charAt(0)=='V')
			arg+="void";
					if(name.charAt(0)=='B')
					arg+="byte";
						if(name.charAt(0)=='C')
						arg+="char";
			if(name.charAt(0)=='D')
			arg+="double";
				if(name.charAt(0)=='F')
				arg+="float";
					if(name.charAt(0)=='J')
					arg+="long";
						if(name.charAt(0)=='S')
						arg+="short";
			if(name.charAt(0)=='Z')
			arg+="boolean";
	}			
			return arg;
			

	}
}