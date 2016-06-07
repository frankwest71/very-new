import java.io.*;
import java.util.*;

class DeCompiler extends Gui{
	DataInputStream dis;
	ArrayList CPEntries;
	ArrayList FieldEntries;
	ArrayList MethodEntries;
	int CPCount;
	int field_count;
	int method_count;
	ClassInfo classinfo;
	String arg[];
	
	
	/*public static void main(String a[])
	{
		DeCompiler dc=new DeCompiler();
		//if(a.length<3)
		a=dc.get();
		dc.start(a);
	}*/
public DeCompiler(File inp)
	{
		start(inp);
	}	
	
	
	
	public String[] get()
	{
		String x[]=new String[3];
		try{
			DataInputStream in=new DataInputStream(System.in);
		
		/*System.out.print("Enter The Options (h for Help)\t:");
		x[0]=in.readLine();
		if(x[0].equals("h"))
			usage();
		System.out.print("Enter The Source File Name\t:");
		x[1]=in.readLine();
		System.out.print("Enter The Detination File Name\t:");
		x[2]=in.readLine();*/
	x[1]="Test.class";
	x[2]="mytest.java";	
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return x;
	}
	///////METHOD TO PRINT THE COMMAND LINE ARGUMENTS/////////
	public void usage()
	{
		//System.out.println("Error Parsing Command Line Options!!!");
		System.out.println("Usage : java DeCompiler -Options InputFile OutputFile");
		System.exit(0);
	}
	
	///////////////STARTING POINT OF THE PROGRAM//////////
	public void start(File file)
	{
		try{
			
	//arg=a;
	//		File file=new File(a[1]);
			FileInputStream fis=new FileInputStream(file);
			ByteArrayInputStream bis=getByteArray(fis,(int)file.length());
			dis=new DataInputStream(bis);
			readMagic();
			readVersion();
			readCPCount();
			readCPEntries();
                  displayClassDetails();
		}
		catch(Exception e)
		{
			System.out.println("Method Start\t:"+ e);
//			e.printStackTrace();
		}
	}
	
	//////CONVERTS THE FILE INPUT STREAM TO BYTE ARRAY////
	public ByteArrayInputStream getByteArray(InputStream is,int length) throws Exception
	{
		
		ByteArrayOutputStream obis=new ByteArrayOutputStream(length);
		byte b[]=new byte[length];
		int read;
		while((read = is.read(b, 0, b.length)) > 0) 
   	obis.write(b,0,read);
		return new ByteArrayInputStream(obis.toByteArray());
	
	}
	
////////READS THE MAGIC NUMBER/////////
public void readMagic() throws Exception
	{
		
		 int magic[]={202,254,186,190};
		 int i=0;
		 int x;
		 while(i<4)
		{
			x=dis.read();
			if(magic[i]!=x)
			{
				System.out.println("Magic Number Exception. May Not Be A Class File!!!");
				System.exit(0);
			}
			i++;
		}
		System.out.println("\nChecking Magic Number....Magic Number CAFEBABE Found!!!");
	}
	
/////////READS  VERSIONS/////////	
	public void readVersion() throws Exception
	{
		System.out.println("Minor Version\t\t:"+dis.readShort());
    System.out.println("Major Version\t\t:"+dis.readShort());

	}
	
/////////READS POOL COUNT///////////	
	public void readCPCount() throws Exception
	{
	 CPCount=dis.readShort();
	 CPCount--;
   System.out.println("Constant Pool Count\t:"+CPCount);
	}
	public void readCPEntries() throws Exception
	{
		int i=0;
		byte b;
		CPEntries =new ArrayList();
		FieldEntries=new ArrayList(); 
		MethodEntries=new ArrayList();
		
		while(i<CPCount)
		{
			b=dis.readByte();
	//System.out.println(i+"  "+b);	 	
			switch(b)
			{
				case 1:
			
					addUTF();
					break;
					case 3:
      
						addInt();
						break;
						case 4:
			
							addFloat();
							break;
							case 5:
								
								addLong();
								break;
								case 6:
								
									addDouble();
									break;
									case 7:
								
										addClass();
										break;
										case 8:
								
											addString();
											break;
											case 9:
								
												addFieldRef();
												break;
												case 10:
								
													addMethodRef();
													break;
													case 11:
								
														addIMR();
														break;
														case 12:
								
															addNT();
															break;
															
					
			}
			i++;
			
		}
		//System.out.println(i);
	//displayPool();	
	ProcessPool pp=new ProcessPool(CPEntries);
	displayPool();
	//displayClassDetails();
	}
	
	public void addUTF() throws Exception
	{
		short s=dis.readShort();
		byte b[]=new byte[s];
		dis.read(b);
		CPEntries.add(new PoolData(1,Integer.toString((int)s),new String(b,0,b.length)));
			}
	public void addInt() throws Exception
	{	
						
		byte b[]=new byte[4];
		dis.read(b);
								
	}
	public void addFloat() throws Exception
	{
		byte b[]=new byte[4];
		dis.read(b);

		
	}
	public void addLong() throws Exception
	{
    byte b[]=new byte[4];
		dis.read(b);
		dis.read(b);
	}
	public void addDouble() throws Exception
	{
    byte b[]=new byte[4];
		dis.read(b);
		dis.read(b);
	}
	public void addClass() throws Exception
	{
		CPEntries.add(new PoolData(7,Integer.toString(dis.readShort()),"null"));
	}
	public void addString() throws Exception 
	{
		short s=dis.readShort();
		//byte b[]=new byte[s];
		//dis.read(b);
		//CPEntries.add(new PoolData(8,Integer.toString((int)s),new String(b,0,b.length)));
CPEntries.add(new PoolData(8,Integer.toString((int)s),"null"));
		
	}
	public void addFieldRef() throws Exception
	{
		CPEntries.add(new PoolData(9,Integer.toString(dis.readShort()),Integer.toString(dis.readShort())));
	}
	public void addMethodRef() throws Exception
	{
    CPEntries.add(new PoolData(10,Integer.toString(dis.readShort()),Integer.toString(dis.readShort())));
	}
	public void addIMR() throws Exception
	{
    CPEntries.add(new PoolData(11,Integer.toString(dis.read()),Integer.toString(dis.read())));
	}
	public void addNT() throws Exception
	{
    CPEntries.add(new PoolData(12,Integer.toString(dis.readShort()),Integer.toString(dis.readShort())));
	}
	
/////////////////////DISPLAY'S	RAW POOL DATA///////////
	public void displayPool()
	{
		int i=0;
		while(i<(CPCount))
		{
			System.out.println("Entry :\t"+i+"\tTag:\t"+((PoolData)CPEntries.get(i)).getTag()+"\tValue\t:"+((PoolData)CPEntries.get(i)).getV1()+"\tValue\t:"+((PoolData)CPEntries.get(i)).getV2());
			i++;
		}
	//	System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	//		System.out.println("\t\tFINISHED POOL PROCESSING\n");
//System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			
	}
	///////////DISPLAYS CLASS DETAILS///////////
	public void displayClassDetails()  
	{
		
		try{
		String s1="",s2="",s3="",s4="",s5="";
		int ic;
		int access=dis.readShort();
		//System.out.print("Access Type\t\t:"+access);
		if(access>=1024)
		{
			s1+="abstract ";
		System.out.print("abstract ");
		access-=1024;
		}
		if(access>=512)
		{
			s1+="interface ";
			System.out.print("interface ");
			access-=512;
		}
		if(access>=32)
		{
			s1+="final ";
	System.out.print("final ");
	access-=32;
		}
		if(access==1)
		{
			s1+="public ";
			System.out.print("Public ");
		}
		s2=((PoolData)CPEntries.get(dis.readShort()-1)).getV1();
		s3=((PoolData)CPEntries.get(dis.readShort()-1)).getV1();
		ic=dis.readShort();
		
		System.out.println("\nThis Class\t\t:"+s2);
		System.out.println("Super Class\t\t:"+s3);
		System.out.println("Interface Count\t:"+ic);
		field_count=dis.readShort();
		//System.out.println("Field Count\t\t:"+field_count);
		int i=0;
		while(i<field_count)
		{
			
///////////////AMATURE CODE/////////////			
			byte b[]=new byte[4];
			int r,r1;
			b=read4Byte();
			r=b[0]+b[1]+b[2]+b[3];
	b=read4Byte();
			r1=b[0]+b[1]+b[2]+b[3];
			FieldEntries.add(new FieldInfo(((PoolData)CPEntries.get(r-1)).getV2(),((PoolData)CPEntries.get(r1-1)).getV2()));
	
	
			i++;
//////////////////////////////////////			
		}
		
	method_count=dis.readShort();	
	System.out.println("Method count\t:"+method_count);
	i=0;
	while(i<method_count)
		{
		MethodEntries.add(new MethodInfo(dis,CPEntries));
		i++;
		}
	

	classinfo=new ClassInfo(s1,s2,s3,ic,FieldEntries,MethodEntries);
	

	new WriteFile("temp.dec",classinfo,CPEntries);
	
//	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}catch(Exception e)
		{
			System.out.println("Exception In Display Class\t"+e);
		}
	}
	
	
	
	
	
public byte[] read4Byte() throws Exception
	{
		byte b[]=new byte[4];
		dis.read(b);
		return(b);
		
	}
		
		
}