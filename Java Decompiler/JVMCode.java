public class JVMCode{
		String opcode[][]={
						{"nop","null"},
/////////////////1-10//////////////////////												
					
			      {"aconst_null","null"},
			      {"iconst_m1","null"},
			      {"iconst_0","null"},
			      {"iconst_1","null"},
			      {"iconst_2","null"},
				{"iconst_3","null"},
				{"iconst_4","null"},
				{"iconst_5","null"},
				{"lconst_0","null"},
				{"lconst_1","null"},
/////////////////10-20//////////////////////												
				{"fconst_0","null"},
				{"fconst_1","null"},
				{"fconst_2","null"},
				{"dconst_0","null"},
				{"dconst_1","null"},
				{"bipush","byte"},
				{"sipush","bytes"},
				{"ldc","index"},
				{"ldc_w","indexs"},
				{"ldc2_w","indexs"},
/////////////////20-30//////////////////////												
				{"iload","byte"},
				{"lload","byte"},
				{"fload","byte"},
				{"dload","byte"},
				{"aload","byte"},
				{"iload_0","null"},
				{"iload_1","null"},
				{"iload_2","null"},
				{"iload_3","null"},
				{"lload_0","null"},
/////////////////30-40//////////////////////												
				{"lload_1","null"},
				{"lload_2","null"},
				{"lload_3","null"},
				{"fload_0","null"},
				{"fload_1","null"},
				{"fload_2","null"},
				{"fload_3","null"},
				{"dload_0","null"},
				{"dload_1","null"},
				{"dload_2","null"},
/////////////////41-50//////////////////////												
				{"dload_3","null"},
				{"aload_0","null"},
				{"aload_1","null"},
				{"aload_2","null"},
				{"aload_3","null"},
				{"iaload","null"},
				{"laload","null"},
				{"faload","null"},
				{"daload","null"},
				{"aaload","null"},
/////////////////51-60//////////////////////												

				{"baload","null"},
				{"caload","null"},
				{"saload","null"},
				{"istore","byte"},
				{"lstore","byte"},
				{"fstore","byte"},
				{"dstore","byte"},
				{"astore","byte"},
				{"istore_0","null"},
				{"istore_1","null"},
/////////////////61-70//////////////////////												
				
				
				{"istore_2","null"},
				{"istore_3","null"},
				{"lstore_0","null"},
				{"lstore_1","null"},
				{"lstore_2","null"},
				{"lstore_3","null"},
				{"fstore_0","null"},
				{"fstore_1","null"},
				{"fstore_2","null"},
				{"fstore_3","null"},
/////////////////71-80//////////////////////												

				{"dstore_0","null"},
				{"dstore_1","null"},
				{"dstore_2","null"},
				{"dstore_3","null"},
				{"astore_0","null"},
				{"astore_1","null"},
				{"astore_2","null"},
				{"astore_3","null"},
				{"iastore","null"},
				{"lastore","null"},
/////////////////81-90//////////////////////												

				
				{"fastore","null"},
				{"dastore","null"},
				{"aastore","null"},
				{"bastore","null"},
				{"castore","null"},
				{"sastore","null"},
				{"pop","null"},
				{"pop2","null"},
				{"dup","null"},
				{"dup_x1","null"},

/////////////////91-100//////////////////////												

				{"dup_x2","null"},
				{"dup2","null"},
				{"dup2_x1","null"},
				{"dup2_x2","null"},
				{"swap","null"},
				{"iadd","null"},
				{"ladd","null"},
				{"fadd","null"},
				{"dadd","null"},
				{"isub","null"},
/////////////////101-110//////////////////////												

				
				{"lsub","null"},
				{"fsub","null"},
				{"dsub","null"},
				{"imul","null"},
				{"lmul","null"},
				{"fmul","null"},
				{"dmul","null"},
				{"idiv","null"},
				{"ldiv","null"},
				{"fdiv","null"},
/////////////////111-120//////////////////////												
					
				{"ddiv","null"},
				{"irem","null"},
				{"lrem","null"},
				{"frem","null"},
				{"drem","null"},
				{"ineg","null"},
				{"lneg","null"},
				{"fneg","null"},
				{"dneg","null"},
				{"ishl","null"},
/////////////////121-130//////////////////////												

				
				{"lshl","null"},
				{"ishr","null"},
				{"lshr","null"},
				{"iushr","null"},
				{"lushr","null"},
				{"iand","null"},
				{"land","null"},
				{"ior","null"},
				{"lor","null"},
				{"ixor","null"},
/////////////////131-140//////////////////////												

				
				{"lxor","null"},
				{"iinc","2bytes"},
				{"i2l","null"},
				{"i2f","null"},
				{"i2d","null"},
				{"l2i","null"},
				{"l2f","null"},
				{"l2d","null"},
				{"f2i","null"},
				{"f2l","null"},
/////////////////141-150//////////////////////												


				{"f2d","null"},
				{"d2i","null"},
				{"d2l","null"},
				{"d2f","null"},
				{"int2byte","null"},
				{"int2char","null"},
				{"int2short","null"},
				{"lcmp","null"},
				{"fcmpl","null"},
				{"fcmpg","null"},
/////////////////151-160//////////////////////												

				
				{"dcmpl","null"},
				{"dcmpg","null"},
				{"ifeq","bytes"},
				{"ifne","bytes"},
				{"iflt","bytes"},
				{"ifge","bytes"},
				{"ifgt","bytes"},
				{"ifle","bytes"},
				{"if_icmpeq","cmp"},
				{"if_icmpne","cmp"},
/////////////////161-170//////////////////////												
			
				{"if_icmplt","cmp"},
				{"if_icmpge","cmp"},
				{"if_icmpgt","cmp"},
				{"if_icmple","cmp"},
				{"if_acmpeq","cmp"},
				{"if_acmpne","cmp"},
				{"goto","cmp"},
				{"jsr","bytes"},
				{"ret","byte"},
				{"tableswitch","null"},
/////////////////171-180//////////////////////												

				
				{"lookupswitch","null"},
				{"ireturn","null"},
				{"lreturn","null"},
				{"freturn","null"},
				{"dreturn","null"},
				{"areturn","null"},
				{"return","null"},
				{"getstatic","bytes"},
				{"putstatic","null"},
				{"getfield","null"},
/////////////////181-190//////////////////////												


				{"putfield","null"},
				{"invokevirtual","bytes"},
				{"invokespecial","bytes"},
				{"invokestatic","null"},
				{"invokeinterface","null"},
				{"","null"},
				{"new","bytes"},
				{"newarray","null"},
				{"anewarray","null"},
				{"arraylength","null"},

/////////////////191-200//////////////////////												

				{"athrow","null"},
				{"checkcast","null"},
				{"instanceof","null"},
				{"monitorenter","null"},
				{"monitorexit","null"},
				{"wide","null"},
				{"multianewarray","null"},
				{"ifnull","bytes"},
				{"ifnonnull","bytes"},
				{"goto_w","bytes4"},
/////////////////201-202//////////////////////												

				{"jsr_w","bytes4"},
				{"breakpoint","null"},


			    };
    	public JVMCode()
	{

	}
	public String getCode(int a)
	{
		return(opcode[a][0]);
	}
	public String getType(int a)
	{
		return(opcode[a][1]);
	}

/*	public JVMFormat getCode(int a)
	{
		return(new JVMFormat(a));
	}
	class JVMFormat{
	String code;
	String type;
	JVMFormat(int a)
	{
	code=opcode[a][0];
	type=opcode[a][1];	
	}
	public String getCode()
		{
			return(code);
		}
}*/
			
}