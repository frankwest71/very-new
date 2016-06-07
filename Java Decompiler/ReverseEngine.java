import java.util.Vector;
import java.util.Stack;
import java.util.ArrayList;
class ReverseEngine{
//	Stack s=new Stack();
	Stack op=new Stack();
	Stack ob=new Stack();
	 int x=0;
	Vector var=new Vector();
	Vector value=new Vector();
	Vector pcount=new Vector();
	Vector v;
	Vector code=new Vector();

	int j=0;
	String temp="";
	int curl=0;
	int ifEnd=0;
	int loop_start=0;
	int loop_end=0;
	int loop_jmp=0;
	String temp1="";
//	boolean curlyes=false;
//	boolean isloop=true;
	boolean isIf=false;
	boolean isLoop=false;
	boolean processed=false;
	ArrayList CPEntries;
	String kode[];
	public ReverseEngine()
	{
	}
	public Vector convert(String kode1[],Vector pcount,ArrayList cp,Vector env)
	{
		this.kode=kode1;
		this.pcount=pcount;
		this.CPEntries=cp;
		code.removeAllElements();
		var.removeAllElements();
		op=new Stack();
		if(env.size()==0)
		{
	op.push("");
	var.addElement("");}else{
			for(int i=0;i<env.size();i++)
			{
				var.addElement(env.elementAt(i));
				op.push(env.elementAt(i));
			}
	}
		
			for(j=0;j<kode.length;j++)
			{
		//	System.out.println("\nVar Size"+var.size());
				process(kode[j]);
	if(isIf)
	{
	if(j==ifEnd)
		{
		code.add("}\n");
		ifEnd=0;
		isIf=false;	
		}
	}
	if(isLoop)
		{
			if((j==loop_end)&&(processed))
			{
			processed=false;	
			isLoop=false;
			j+=3;
			
		}
		}
				
			
	}
			
			return code;
		

}

	public void process(String kode)
	{
		try{
		String zx1="",zx2="";
		System.out.print("\n  "+pcount.elementAt(j)+"\t"+kode);
		if(kode.substring(kode.length()-1).equals(" "))
		{
			kode=kode.substring(0,kode.length()-1);
			
		}
		
		
/////////////////////////IINC/////////////////////////////////////		
		if(isSubstring(kode,"iinc"))
			{
				kode=kode.substring(kode.indexOf(' ')+1);
        int index1=Integer.parseInt(kode.substring(1,2));
        int index2=Integer.parseInt(kode.substring(3,4));

				
			}
/////////////////////////NEW/////////////////////////////////////		
		
		if(isSubstring(kode,"new"))
			{
	kode=kode.substring(kode.indexOf('#')+1);
	kode=kode.substring(0,kode.indexOf(' '));
//	s.push(((PoolData)CPEntries.get(Integer.parseInt(kode)-1)).getV1());
	op.push(((PoolData)CPEntries.get(Integer.parseInt(kode)-1)).getV1());
	temp1="new ";
			}	
/////////////////////////POP/////////////////////////////////////		
			
if(isSubstring(kode,"pop"))
			{
				op.pop();
			}			
			
///////////////////////DUP///////////////////////////////////			
		if(isSubstring(kode,"dup"))
			{
				Object ob=new Object();
//				ob=s.pop();
				//s.push((String)ob);
				//s.push((String)ob);
				ob=op.pop();
				op.push((String)ob);
	op.push((String)ob);
				//var.addElement((String)ob);
		
			}	
/////////////////////////INVOKE SPECIAL/////////////////////////////////////		
			
		if(isSubstring(kode,"invokespecial"))
			{
	kode=kode.substring(kode.indexOf('#')+1);
	kode=kode.substring(0,kode.indexOf(' '));
	temp1+=(((PoolData)CPEntries.get(Integer.parseInt(kode)-1)).getV1());
	temp1+="(";
	String strz=((PoolData)CPEntries.get(Integer.parseInt(kode)-1)).getV2();
	temp1+=getArguments(strz.substring(strz.indexOf('#')+1));
	temp1+=");";
	if(isSubstring(this.kode[j+1],"astore_"))
				{}
				else
				{
					System.out.println(temp1);					
  				code.add(temp1);}

  				}	
			
//////////////////INVOKE VIRTUAL//////////////////////////			
		if(isSubstring(kode,"invokevirtual"))
			{
				try{
kode=kode.substring(kode.indexOf('#')+1);
kode=kode.substring(0,kode.indexOf(' '));
temp1=((PoolData)CPEntries.get(Integer.parseInt(kode)-1)).getV2();

String name=temp1.substring(0,temp1.indexOf('#'));
String art=temp1.substring(temp1.indexOf('#')+1);
String strz=getArguments(art);


strz=(String)op.pop()+"."+name+"("+strz+")";
if(parseReturnType(art)!=0)
	op.push(strz);
	else
code.add(strz);			
				}
				catch(Exception e)
				{
					System.out.println("\nException In ReverseEngine.java (InvokeVirtual Module)\t:"+e);
				}
			}		

	//////////////////////////ASTORE/////////////////////////////		
	if(isSubstring(kode,"astore_"))
			{
				
			int y=Integer.parseInt(kode.substring(7));
		//	y--;
			String z="";
			if(y>=x)
			{
//			z=(String)s.pop();				
			var.add(getVar());
			code.add(op.pop()+" "+(String)var.elementAt(y)+"="+temp1);
			}
			else
			{
//				z=(String)s.pop();
							
				code.add(var.elementAt(y)+"="+z+";");
			}
			
			}
			
///////////////////////ALOAD////////////////////////////			
		if(isSubstring(kode,"aload_"))
			{
//System.out.println(var.elementAt(Integer.parseInt(kode.substring(6))));
op.push(var.elementAt(Integer.parseInt(kode.substring(6))));
				
			}	
	
		
	///////////////////////////ICONST & BIPUSH////////////////	
		
		
		
		if((isSubstring(kode,"iconst_"))||(isSubstring(kode,"bipush")))
		{
			op.push(kode.substring(7));
   	}
	   	
/////////////////////////////////ILOAD///////////////////////////	   	
		if(isSubstring(kode,"iload_"))
		{
		op.push(var.elementAt(Integer.parseInt(kode.substring(6))));
		//String str=(String)op.pop();
		//System.out.println(op
		}
////////////////////////////////IADD////////////////////////////		
		if(isSubstring(kode,"iadd"))
		{
			zx1=(String)op.pop();
			zx2=(String)op.pop();
			//s.push(zx1 +"+"+zx2);
			op.push(zx1 +"+"+zx2);
			
		}
///////////////////////////IMUL//////////////////////////////		
		if(isSubstring(kode,"imul"))
		{
	zx1=(String)op.pop();
	zx2=(String)op.pop();
			//s.push(zx1+"*"+zx2);
	op.push(zx1+"*"+zx2);
		}
///////////////////////////IDIV//////////////////////////////		
if(isSubstring(kode,"idiv"))
{
zx1=(String)op.pop();
zx2=(String)op.pop();
//s.push(zx1+"/"+zx2);
op.push(zx1+"/"+zx2);
}
//////////////////////////////ISTORE////////////////////////		

		if(isSubstring(kode,"istore_"))
		{
			System.out.println("~~~~~~"+var.size());
			int y=Integer.parseInt(kode.substring(7));
			String z="";
	z=(String)op.pop();				
			if(y>=x)
			{
			var.addElement(getVar());
			code.add("int "+(String)var.elementAt(y)+"="+z+";");
			}
			else
			{
				code.add(var.elementAt(y)+"="+z+";");
			}
				
		}
//////////////////////////IF_COMPARE///////////////////		
		if(isSubstring(kode,"if_icmp"))
		{

			String temp="";
			String z="";
			if(((isLoop)&&(processed))||(!isLoop))
			{
				isIf=true;
					z+="if";
			}
				else
			{
					isIf=false;
					z+="while";
			}
								
		//isloop=false;
				
					
			if(isSubstring(kode,"if_icmpeq"))
			{
				temp=(String)op.pop();
				if((isLoop)&&(!processed))
					z+="("+(String)op.pop()+"=="+temp+")";
					else
		z+="("+(String)op.pop()+"!="+temp+")";

			}
				else
		if(isSubstring(kode,"if_icmpne"))
			{
	temp=(String)op.pop();
	if((isLoop)&&(!processed))
  		z+="("+(String)op.pop()+"!="+temp+")";
  		else
	z+="("+(String)op.pop()+"=="+temp+")";

			}
							else
			if(isSubstring(kode,"if_icmplt"))
			{
	temp=(String)op.pop();
	if((isLoop)&&(!processed))
			z+="("+(String)op.pop()+"<"+temp+")";
			else
	z+="("+(String)op.pop()+">="+temp+")";
	
			}
else
  if(isSubstring(kode,"if_icmple"))
			{
	temp=(String)op.pop();
	if((isLoop)&&(!processed))
		  z+="("+(String)op.pop()+"<="+temp+")";
		  else
	z+="("+(String)op.pop()+">"+temp+")";
	
			}
		else
	if(isSubstring(kode,"if_icmpgt"))
			{
	temp=(String)op.pop();
	if((isLoop)&&(!processed))

			z+="("+(String)op.pop()+">"+temp+")";
			else
	z+="("+(String)op.pop()+"<="+temp+")";

			}
				else
		if(isSubstring(kode,"if_icmpge"))
			{
	temp=(String)op.pop();
	if((isLoop)&&(!processed))
			z+="("+(String)op.pop()+">="+temp+")";
			else
	z+="("+(String)op.pop()+"<"+temp+")";
  		}
		 //System.out.println("***************"+z);
		code.add(z+"{");	
		int tempx=kode.indexOf(' ');
		String sx=kode.substring(tempx);
		while(true)
			{
				if(sx.charAt(0)==' ')
					sx=sx.substring(1);
					else
						break;

			}
curl=Integer.parseInt(sx);
//if(!isWhile)
/*if(curl<30000)
			{
int dre1=Integer.parseInt((String)pcount.elementAt(j-1));
curl+=dre1;
			}
			
				
curl++;
loop_jmp=j+1;
curlyes=true;
//if(isWhile)
if(curl>30000)
			{
	processed=true;
	curl=0xffff-curl;
j-=curl;
//j=curl;
//System.out.println("********"+j+"*********");				

j=pcount.indexOf((Object)String.valueOf(j));
j--;
loop_start=j;
System.out.println("*"+loop_start+"*");
			}*/
			if(isIf)
			{
			ifEnd=Integer.parseInt((String)pcount.elementAt(j));	
			ifEnd+=curl;
			ifEnd=pcount.indexOf(String.valueOf(ifEnd));
			//ifEnd=pcount.indexOf((Object)String.valueOf(curl+j));
			//System.out.println("***"+ifEnd+"***");
			}
							if((isLoop)&&(!processed))
							{
								processed=true;
									curl=0xffff-curl;
									j=Integer.parseInt((String)pcount.elementAt(j));
									j-=curl;
									j--;
									j=pcount.indexOf(String.valueOf(j));
									j--;
									loop_start=j;	
									//System.out.println("loopstart"+j);			
							}

		}







////////////////////ELSE PART OF IF/////////////////
if(isSubstring(kode,"goto"))
			{
			if(isIf)
			{
			int tempx=kode.indexOf(' ');
String sx=kode.substring(tempx);
		while(true){
		if(sx.charAt(0)==' ')
		sx=sx.substring(1);
		else
		break;}
curl=Integer.parseInt(sx);
			
			String z="}\nelse{";
			x--;
ifEnd=pcount.indexOf(String.valueOf(curl+Integer.parseInt((String)pcount.elementAt(j))));		
	//System.exit(0);
		
//curlyes=true;
			code.add(z);
			
		}
		
				else
			{
//////////////////////////LOOP STRUCTURE FOUND////////////////				

loopFound(kode);






			
				
			}
		}
		
			
	}catch(Exception e)
			{
				System.out.println("\nException In ReverseEngine.java\t:"+e);
			}
		}
	
	
	
	public void loopFound(String kode)
		{
	isLoop=true;
	int tempx=kode.indexOf(' ');
	String sx=kode.substring(tempx);
	while(true)
	{
		if(sx.charAt(0)==' ')
		sx=sx.substring(1);
		else
		break;
	}
	loop_end=Integer.parseInt((String)pcount.elementAt(j));		
tempx=Integer.parseInt(sx);
loop_end+=tempx;
loop_end=pcount.indexOf(String.valueOf(loop_end));
//System.out.println("Loop End"+loop_end);
	j=loop_end;
	loop_end--;
	j--;
	//System.out.println(j);
		
		}
	
	
	public void getNext()
	{
		j++;
		process((String)v.elementAt(j));
	}
	
	
	
	
	
	////////METHOD TO FIND SUBSTRING///////////
	public boolean isSubstring(String str,String st)
	{
		if(str.length()<st.length())
			return false;
		for(int i=0;i<st.length();i++)
		{
			if(str.charAt(i)!=st.charAt(i))
			{
				return false;
			}
		}
		return(true);
	}
	
	///////////////METHOD TO GENERATE  VARIABLES/////////
	public String getVar()
	{
		x++;
		return(new String("a"+x));
	}
	
	public String getArguments(String name)
		{
			String arg="";
			String ret="";
			name=name.substring(name.indexOf("(")+1);
			while(true)
			{
					if(name.charAt(0)==')')
					break;
	if(!arg.equals(""))
					arg+=",";
				if(name.charAt(0)=='L')
				{
					name=name.substring(1);
					arg+=name.substring(0,name.indexOf(';'));
					name=name.substring(name.indexOf(';')+1);
				}
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
			name=name.substring(1);
			arg=(String)op.pop();
				}
			
//System.out.println("!!!!!!!!!!!!!!!!!"+arg);			
			return arg;
			
		}
	
	public int parseReturnType(String name)
		{
			name=name.substring(name.indexOf(')')+1);
			return name.length();
		}
	
	
	
	
	
	
	
}