import java.util.ArrayList;
public class ProcessPool{
	public ProcessPool(ArrayList list)
	{
		int length=list.size();
		boolean yes;
		PoolData p,p1;
		int x;
		
		
		for(int i=0;i<length;i++)
		{
			p=(PoolData)list.get(i);
			if(p.getTag()==12)
			{
				x=Integer.parseInt(p.getV1());
				x--;
				p1=(PoolData)list.get(x);
				p.putV1(p1.getV2());
				
				x=Integer.parseInt(p.getV2());
				x--;
				p1=(PoolData)list.get(x);
				p.putV2(p1.getV2());

				
			}
			else
			{
				if(p.getTag()==7)
				{
				x=Integer.parseInt(p.getV1());
				x--;
				p1=(PoolData)list.get(x);
				p.putV1(p1.getV2());

				}
			}
		}
		
		
		for(int i=0;i<length;i++)
		{
			p=(PoolData)list.get(i);
			if((p.getTag()==9)||(p.getTag()==10)||(p.getTag()==11))
			{

	x=Integer.parseInt(p.getV1());
	x--;
	p1=(PoolData)list.get(x);
	p.putV1(p1.getV1());
	
		x=Integer.parseInt(p.getV2());
		x--;
		p1=(PoolData)list.get(x);
		p.putV2(p1.getV1()+"#"+p1.getV2());


				
			}
			
		}
		
		
		for(int i=0;i<length;i++)
		{
p=(PoolData)list.get(i);
p.putV1(convertString(p.getV1()));
p.putV2(convertString(p.getV2()));
System.out.println(p.getTag()+"\t"+p.getV1()+"\t"+p.getV2());
		}
		
	}
	public String convertString(String str)
		{
		if(str.indexOf('/')!=-1)
			str=str.replace('/','.');
			return str;	
		}	
		
		
		
		
		
////////////////////////OBSELETE CODE//////////////////		
		
		/*
		
		for(int i=0;i<length;i++)
		{
			yes=true;
			p=(PoolData)list.get(i);
			//if(!(p.getTag()==1)||(p.getTag()==8))
			if(!(p.getTag()==1))
			{
			while(yes)
			{
				try{
				x=Integer.parseInt(p.getV1());
				x--;
				p1=(PoolData)list.get(x);
				
				//if((p1.getTag()==1)||(p1.getTag()==8))
				if((p1.getTag()==1))
				{
					p.putV1(p1.getV2());
					yes=false;
				}
				else
				{
					p.putV1(p1.getV1());
				}
				}catch(Exception e){
					yes=false;
				}
		  }
		  
		  yes=true;

		  if((p.getTag()==9)||(p.getTag()==10)||(p.getTag()==11)||(p.getTag()==5)||(p.getTag()==6)||(p.getTag()==12))
		  while(yes)
			{
				try{
			x=Integer.parseInt(p.getV2());
				x--;
				p1=(PoolData)list.get(x);
				
				if((p1.getTag()==1)||(p1.getTag()==8))
				{
					if(p.getTag()==9||p.getTag()==10||p.getTag()==11)
					{
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~"+x);
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~"+p1.getV1());
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~"+p1.getV2());
						p.putV2(p1.getV1()+"#"+p1.getV2());
					}
						else
    				p.putV2(p1.getV2());
					yes=false;
				}
				else
				{
					p.putV2(p1.getV2());
				}	
				}catch(Exception e)
				{
					yes=false;
				}	
							
			}
			}
		}*/
		
	
	
	
}