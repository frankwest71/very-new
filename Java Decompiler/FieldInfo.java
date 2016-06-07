public class FieldInfo{
	int num;
	int l;
	String name;
	String type;
	public FieldInfo(String s,String s1)
	{
		name=s;
		type=parseType(getType(s1));
	}
	public String getName()
	{
		return name;
	}
	public String getType()
	{
		return type;
	}
	public String toString()
	{

		return(type+" "+name);
	}
	public String parseType(String sc)
	{
	char c[]=new char[sc.length()];
	c=sc.toCharArray();
	int i=0;
	while(i<sc.length())
		{
		if(c[i]=='/')
		c[i]='.';	
		i++;	
		}
		String str=new String(c);
		i=0;
		while(i<l)
		{
			str=str+"[]";
			i++;
		}
		return(str);
 }
	public String getType(String str)
	{
		
		boolean yes=true;
		while(yes)
		{
			if(str.charAt(0)=='[')
			{
				l++;
				str=str.substring(1);
			}
			else
				yes=false;
		}
				if(str.equals("I"))
				return("int");
			if(str.equals("B"))
				return("byte");
					if(str.equals("C"))
						return("char");
						if(str.equals("D"))
							return("double");
							if(str.equals("F"))
								return("float");
								if(str.equals("J"))
									return("long");
									if(str.equals("S"))
										return("short");
				  								if(str.equals("Z"))
		      									return("boolean");
													if((str.charAt(0)=='L')&&(str.charAt(str.length()-1)==';'))
													return(str.substring(1,str.length()-1));
	else
		return("Error");

		
	}
}