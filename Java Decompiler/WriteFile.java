import java.io.*;
import java.util.*;
public class WriteFile{
			int xyz=0;
	
	public WriteFile(String str,ClassInfo info,ArrayList CPEntries)
	{
		
		JVMCode op=new JVMCode();
		try{
		Vector env=new Vector();	
		ArrayList list=new ArrayList();
		Vector v=new Vector();
		Vector pcount=new Vector();
		File file=new File(str);
		FileOutputStream fin=new FileOutputStream(file);
		//////////////	WRITES CLASS INFO/////////////
		fin.write((info.getAccess()).getBytes());
		fin.write(" class ".getBytes());
		fin.write(info.getName().getBytes());
		fin.write(" extends ".getBytes());
		fin.write((parseSClass(info.getSClass())).getBytes());
		fin.write("\n{\n".getBytes());
		/////////////// WRITES FIELDS INFO/////////////
		
		list=info.getFieldEntries();
		int l=list.size();
		for(int i=0;i<l;i++)
			{
				fin.write((((FieldInfo)list.get(i)).toString()).getBytes());
				fin.write(";\n".getBytes());
			}
		
		////////////////WRITES METHOD INFO////////////
		list=info.getMethodEntries();
		//System.out.println(info.getMethodEntries());
		l=list.size();
		String temp="";
		MethodInfo minfo=new MethodInfo();
		ReverseEngine re=new ReverseEngine();
		env.removeAllElements();
			for(int i=0;i<l;i++)
			{
				minfo=(MethodInfo)list.get(i);
				temp=minfo.getAccess();
				System.out.println("\nMethod :"+(i+1)+"\n");
				if(minfo.getName().equals("<init>"))
				{
  			temp+=info.getName();
				}
				else
				{
				temp+=minfo.getReturnType();					
				temp+=minfo.getName();
				}
				temp+="(";
				String x1=minfo.getArguments();
				boolean isFirst=true;
				String x2="";			
				while(!x1.equals(""))
				{
					if(!isFirst)
					{
					temp=temp+",";}
					isFirst=false;
					
				temp+=x1.substring(0,x1.indexOf('#'));	
				x1=x1.substring(x1.indexOf('#')+1);
				x2=getVar();
				temp+=" "+x2;
				env.addElement(x2);
				}
				
				
				temp+=")";
				fin.write(temp.getBytes());
				fin.write("\n{\n".getBytes());

				/////////////WRITES THE OPCODE FOR THE CODE//////////
				int code[]=minfo.getCode();
				System.out.println("\n");
				String s="";
				int x;
				PoolData pd;
				int ds=0;
				String temp1="";
				String ins[]=new String[code.length];
				
				for(int kj=0;kj<code.length;kj++)
				{
					
					pcount.addElement(String.valueOf(kj));
					s=op.getType(code[kj]);
				//	fin.write(("   "+kj+" "+op.getCode(code[kj])).getBytes());
					ins[ds]=op.getCode(code[kj])+" ";
					//System.out.print(code[kj]+" "+ins[ds]+"\n");
					if(s.equals("bytes"))
					{
						
						x=code[++kj]*16+code[++kj];
			//			fin.write((" #"+Integer.toString(x)).getBytes());
						temp1+=" #"+Integer.toString(x);
						pd=(PoolData)CPEntries.get(x-1);
						if(pd.getTag()==9)
						{
		//fin.write((" <"+parseSClass(pd.getV2())+">").getBytes());
		temp1+=parseSClass(pd.getV2());		
						}
						else
						{
			//			fin.write((" <"+parseSClass(pd.getV1())+">").getBytes());
						temp1+=" <"+parseSClass(pd.getV1())+">";
						}
					}	
					else
					if(s.equals("cmp"))
					{
				kj++;
				x=code[kj]<<8;
				kj++;
				x+=code[kj];
				

	  		temp1+=" "+Integer.toString(x);
					}
					
					else
						if(s.equals("index"))
					{
						
		//*fin.write((op.getCode(code[kj])).getBytes());
		x=code[++kj];
		//fin.write((" #"+Integer.toString(x)).getBytes());
		temp1+=Integer.toString(x);
		if(((PoolData)CPEntries.get(x-1)).getTag()==9)
						{
			temp1+=((PoolData)CPEntries.get(x-1)).getV2();
		//fin.write((" "+((PoolData)CPEntries.get(x-1)).getV2()).getBytes());
						}
		else
						{
System.out.println((" "+((PoolData)CPEntries.get(x-1)).getV1()));
							temp1+=((PoolData)CPEntries.get(x-1)).getV1();
		//fin.write((" "+((PoolData)CPEntries.get(x-1)).getV1()).getBytes());
						}
			

					}
					else
						if(s.equals("byte"))
					{
	//*fin.write((op.getCode(code[kj])).getBytes());
	x=code[++kj];
			//fin.write((" "+Integer.toString(x)).getBytes());
	temp1+=Integer.toString(x);

					}
					else
					{
						if(s.equals("2bytes"))
						{
							x=code[++kj];
							temp1=temp1+" "+Integer.toString(x)+" ";
							x=code[++kj];
    		temp1+=Integer.toString(x);
		}

	//fin.write((op.getCode(code[kj])).getBytes());
					}
					
						//fin.write(";\n".getBytes());
						ins[ds++]+=temp1;
						temp1="";
				}
	String kode[]=new String[ds];
	for(int k=0;k<ds;k++)
		{
		kode[k]=ins[k];
		}
///////////////CALL REVERSE ENGINEERING ENGINE///////////		
  
	v=re.convert(kode,pcount,CPEntries,env);
	for(int xy=0;xy<v.size();xy++)
					{
						fin.write(((String)v.elementAt(xy)+"\n").getBytes());
					}
	
	
	
	pcount.removeAllElements();
	
	
	
	
	
	
	
	////////////////END OF METHOD///////////////
			fin.write("}\n".getBytes());
    }	
////////////////METHODS PROCESSING OVER//////////////		
	fin.write("\n}".getBytes());
	fin.close();
	//display();


		}catch(Exception e)
		{
			
			System.out.println("Exception In Write File\t:"+e);
		}
		
		System.out.println("\nFinished Processing Write File\t:");
	}
	
	
	
	
	
	
	
	
	
	
	public String getVar()
	{
		
		xyz++;
		return(new String("arg"+xyz));
	}
public String parseSClass(String sc)
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
return(new String(c));		
   }
	public WriteFile(String str,MethodInfo info)
	{
	}
}