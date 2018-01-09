package com.JGather;
//xindeceshi
public class IpMeterCMD {
	//电表命令集 水表命令

	//FE FE FE 68 11 {03 00 00 00 00} 07 07 68 27 00 00 {19} 16
	/*
	//水表
	//0表号 1检验码 
	private String COMMAND_CB = "6811%s00%s68240000";
	private String COMMAND_JY = "FEFEFE%s%s16"; //校验

	//写 水表底数命令
	//写底数： FE FE FE 68 11 01 00 00 00 00 07 07 68 32 05 05 20 42 00 00 2C BA 16 
	//回码：FE FE FE 68 11 01 00 00 00 00 07 07 68 B2 00 00 A2 16 
	private String COMMAND_SB_XDS = "6811%s00%s68320505%s2C";
	private String COMMAND_SB_XDS_JY = "FEFEFE%s%s16"; //校验
	*/

	//慧怡水表
	//FE FE FE FE 68 10 08 01 00 01 01 00 00 01 03 90 1F 01 37 16
	//FE FE FE FE 68 10 08 01 00 01 01 00 00 81 16 90 1F 01 70 56 34 12  2C 70 56 34 12 2C 00 00 00 00 00 00 00 01 00 3B 16
	private String COMMAND_CB = "6810%s0103901F%s";
	private String COMMAND_JY = "FEFEFEFEFEFEFEFE%s%s16"; //校验

	//写 水表底数命令
	//写底数： 　　FE FE FE FE 68 10 08 01 00 01 01 00 00 16 08 A0 16 01 83 67 05 00 2C 73 16
	//回码：FE FE FE FE 68 10 08 01 00 01 01 00 00 96 05 A0 16 01 00 00 D5 16 
	private String COMMAND_SB_XDS = "6810%s1608A016%s%s2C";
	private String COMMAND_SB_XDS_JY = "FEFEFEFEFEFEFEFE%s%s16"; //校验

	//07规约电表 - 两项
	private String COMMAND_CB_ZX_YG_2007 = "68%s68110433333433";//正向有功总电能
	private String COMMAND_CB_FX_YG_2007 = "68%s68110433333533";//反向有功总电能
	private String COMMAND_CB_DY_A_2007 = "68%s68110433343435";//电压00 01 01 02
	private String COMMAND_CB_D_A_2007 = "68%s68110433343535";//电流00 01 02 02
	private String COMMAND_CB_ZGYS_2007 = "68%s68110433333935";//总功率因数D0D1D2D3 00000602
	private String COMMAND_CB_ZXYGZG_2007 = "68%s68110434413337";//正向有功总功率上限值D0D1D2D3 01 0E 00 04
	private String COMMAND_CB_FXYGZG_2007 = "68%s68110435413337";//反向有功总功率下限值D0D1D2D3 02 0E 00 04
	//private String COMMAND_DWP ="68%s6811043533B335";   //电网频率 02 00 80 02 
	private String COMMAND_CB_SSZYGG_2007="68%s68110433333635"; //瞬时总有功功率 00 00 03 02  // 03 00 80 02  ping jun gong lv 
	private String COMMAND_JY_2007 = "FEFEFEFE%s%s16";//校验

	//97规约电表 - 三项
	private String COMMAND_CB_ZX_YG = "68%s68010243C3";//正向有功总电能 9010
	private String COMMAND_CB_FX_YG = "68%s68010253C3";//反向有功总电能 9020
	private String COMMAND_CB_DY_A = "68%s68010244E9";//电压A  b611
	private String COMMAND_CB_DY_B = "68%s68010245E9";//电压B  b612
	private String COMMAND_CB_DY_C = "68%s68010246E9";//电压C  b613
	private String COMMAND_CB_D_A = "68%s68010254E9";//电流A  b621
	private String COMMAND_CB_D_B = "68%s68010255E9";//电流B  b622
	private String COMMAND_CB_D_C = "68%s68010256E9";//电流C  b623
	private String COMMAND_ZGYS = "68%s68010283E9";//总功率因数 b650
	private String COMMAND_ZXYGZG = "68%s68010267E9";//正向有功总功率 b634
	private String COMMAND_FXYGZG = "68%s68010268E9";//反向有功总功率 b635

	private String COMMAND_ZXWGZDN = "68%s68010243C4";//正向无功总电能 9110
	private String COMMAND_FXWGZDN = "68%s68010253C4";//反向无功总电能 9120
	private String COMMAND_SSZYGG ="68%s68010263E9";//b630 瞬时有功功率
	private String COMMAND_JY_1997 = "FEFEFE%s%s16"; //校验
	/*
	函数功能： 得到电表水表的标号，并反转 例如 电表标号为201105001052 反转成 521000051120。
	参数意义： sMeterCode 标号，sRemark 表类型
	*/
	public String GetMeterCode(String sMeterCode)
	{
		String stMeterCode="";
		sMeterCode.trim();
		int nlength=sMeterCode.length();
		if(nlength!=8&&nlength!=12)
			return "";
		String s1;
		for(int i=nlength-2;i>=0;i-=2)
		{
			s1=sMeterCode.substring(i,i+2);
			stMeterCode+=s1;
		}
		return stMeterCode;
	}
   public String GetHexStringCheckcode(String HexString)
   {
		int nstrlength=HexString.length();
		if(0!=nstrlength%2)
		{
			return null;
		}
		int c=0;
		int ntotal=0;
		int nresult =0;
		for (int i=0;i<nstrlength;i+=2)
		{
			c=Integer.parseInt(HexString.substring(i, i+2), 16);
			ntotal+=c;
			nresult=ntotal%256;
		}
		String strReturn;
		strReturn=String.format("%02x",nresult);
		return strReturn;
	}
   /*
  	函数功能： 得到水表和电表的命令
  	参数意义：
  	返回值：
  */
  public String GetCBCommandS(String sMeterCode,String sRemark,String sGuiYue,String sQYBM)
  {
  	String sCmd="error";
  	sMeterCode=GetMeterCode(sMeterCode);
  	sGuiYue.trim();
  //水表命令
  	if(sRemark.equals("W"))
  	{
  		sMeterCode=sMeterCode+"00";
  		String sCommand,sSer;
  		sSer="01";//GetWaterSer(sMeterCode);
  		sCommand=String.format(COMMAND_CB, sMeterCode,sSer);
  		String jyCommand = GetHexStringCheckcode(sCommand);
  		sCmd=String.format(COMMAND_JY, sCommand, jyCommand);
  		return sCmd;
  	}
  // 水表命令
  //电表命令
  	if (sRemark.equals("E"))
  	{
  		if (sGuiYue.equals("2"))
  		{
  			String cbCommand_ZX_YG_2007,rCommand_ZX_YG_2007;
  			cbCommand_ZX_YG_2007=String.format(COMMAND_CB_ZX_YG_2007, sMeterCode);
  			String jyCommand_ZX_YG_2007 = GetHexStringCheckcode(cbCommand_ZX_YG_2007);
  			rCommand_ZX_YG_2007=String.format(COMMAND_JY_2007, cbCommand_ZX_YG_2007, jyCommand_ZX_YG_2007);
  			
  			String cbCommand_FX_YG_2007,rCommand_FX_YG_2007;
  			cbCommand_FX_YG_2007=String.format(COMMAND_CB_FX_YG_2007, sMeterCode);
  			String jyCommand_FX_YG_2007 = GetHexStringCheckcode(cbCommand_FX_YG_2007);
  			rCommand_FX_YG_2007=String.format(COMMAND_JY_2007, cbCommand_FX_YG_2007, jyCommand_FX_YG_2007);

  			String cbCommand_DY_A_2007,rCommand_DY_A_2007;
  		    cbCommand_DY_A_2007=String.format(COMMAND_CB_DY_A_2007, sMeterCode);
  			String jyCommand_DY_A_2007 = GetHexStringCheckcode(cbCommand_DY_A_2007);
  			rCommand_DY_A_2007=String.format(COMMAND_JY_2007, cbCommand_DY_A_2007, jyCommand_DY_A_2007);

  			String cbCommand_D_A_2007,rCommand_D_A_2007;
  			cbCommand_D_A_2007=String.format(COMMAND_CB_D_A_2007, sMeterCode);
  			String jyCommand_D_A_2007 = GetHexStringCheckcode(cbCommand_D_A_2007);
  			rCommand_D_A_2007=String.format(COMMAND_JY_2007, cbCommand_D_A_2007, jyCommand_D_A_2007);

  			String cbCommand_ZGYS_2007,rCommand_ZGYS_2007;
  			cbCommand_ZGYS_2007=String.format(COMMAND_CB_ZGYS_2007, sMeterCode);
  			String jyCommand_ZGYS_2007 = GetHexStringCheckcode(cbCommand_ZGYS_2007);
  			rCommand_ZGYS_2007=String.format(COMMAND_JY_2007, cbCommand_ZGYS_2007, jyCommand_ZGYS_2007);

  			String cbCommand_ZXYGZG_2007,rCommand_ZXYGZG_2007;
  			cbCommand_ZXYGZG_2007=String.format(COMMAND_CB_ZXYGZG_2007, sMeterCode);
  			String jyCommand_ZXYGZG_2007 = GetHexStringCheckcode(cbCommand_ZXYGZG_2007);
  			 rCommand_ZXYGZG_2007=String.format(COMMAND_JY_2007, cbCommand_ZXYGZG_2007, jyCommand_ZXYGZG_2007);

  			String cbCommand_FXYGZG_2007,rCommand_FXYGZG_2007;
  			 cbCommand_FXYGZG_2007=String.format(COMMAND_CB_FXYGZG_2007, sMeterCode);
  			String jyCommand_FXYGZG_2007 = GetHexStringCheckcode(cbCommand_FXYGZG_2007);
  			rCommand_FXYGZG_2007=String.format(COMMAND_JY_2007, cbCommand_FXYGZG_2007, jyCommand_FXYGZG_2007);

  			String cbCommand_SJZYGG_2007,rCommand_SJZYGG_2007;
  			cbCommand_SJZYGG_2007=String.format(COMMAND_CB_SSZYGG_2007,sMeterCode);
  			String jyCommand_SJZYGG_2007 =GetHexStringCheckcode(cbCommand_SJZYGG_2007);
  			rCommand_SJZYGG_2007=String.format(COMMAND_JY_2007,cbCommand_SJZYGG_2007,jyCommand_SJZYGG_2007);
  			sCmd = rCommand_ZX_YG_2007 + "," + rCommand_FX_YG_2007 + "," 
  				+ rCommand_DY_A_2007 + "," + rCommand_D_A_2007 + "," + rCommand_ZGYS_2007 
  				+ "," + rCommand_ZXYGZG_2007 + "," + rCommand_FXYGZG_2007+","+rCommand_SJZYGG_2007;
  			return sCmd;
  		}
  		else if (sGuiYue.equals("1"))
  		{
  			//正向有功总电能
  			String cbCommand_ZX_YG,rCommand_ZX_YG ;
  			cbCommand_ZX_YG=String.format(COMMAND_CB_ZX_YG, sMeterCode);
  			String jyCommand_ZX_YG = GetHexStringCheckcode(cbCommand_ZX_YG);
  			rCommand_ZX_YG=String.format(COMMAND_JY_1997, cbCommand_ZX_YG, jyCommand_ZX_YG);

  			//反向有功总电能
  			String cbCommand_FX_YG,rCommand_FX_YG;
  			cbCommand_FX_YG=String.format(COMMAND_CB_FX_YG, sMeterCode);
  			String jyCommand_FX_YG =GetHexStringCheckcode(cbCommand_FX_YG);
  			 rCommand_FX_YG=String.format(COMMAND_JY_1997, cbCommand_FX_YG, jyCommand_FX_YG);

  			String cbCommand_DY_A,rCommand_DY_A;
  			cbCommand_DY_A=String.format(COMMAND_CB_DY_A, sMeterCode);
  			String jyCommand_DY_A = GetHexStringCheckcode(cbCommand_DY_A);
  			rCommand_DY_A=String.format(COMMAND_JY_1997, cbCommand_DY_A, jyCommand_DY_A);

  			String cbCommand_DY_B,rCommand_DY_B;
  			cbCommand_DY_B=String.format(COMMAND_CB_DY_B, sMeterCode);
  			String jyCommand_DY_B = GetHexStringCheckcode(cbCommand_DY_B);
  			rCommand_DY_B=String.format(COMMAND_JY_1997, cbCommand_DY_B, jyCommand_DY_B);

  			String cbCommand_DY_C ,rCommand_DY_C;
  			cbCommand_DY_C=String.format(COMMAND_CB_DY_C, sMeterCode);
  			String jyCommand_DY_C = GetHexStringCheckcode(cbCommand_DY_C);
  			rCommand_DY_C=String.format(COMMAND_JY_1997, cbCommand_DY_C, jyCommand_DY_C);

  			String cbCommand_D_A ,rCommand_D_A;
  			cbCommand_D_A=String.format(COMMAND_CB_D_A, sMeterCode);
  			String jyCommand_D_A = GetHexStringCheckcode(cbCommand_D_A);
  			rCommand_D_A=String.format(COMMAND_JY_1997, cbCommand_D_A, jyCommand_D_A);

  			String cbCommand_D_B,rCommand_D_B;
  			cbCommand_D_B=String.format(COMMAND_CB_D_B, sMeterCode);
  			String jyCommand_D_B = GetHexStringCheckcode(cbCommand_D_B);
  			rCommand_D_B=String.format(COMMAND_JY_1997, cbCommand_D_B, jyCommand_D_B);

  			String cbCommand_D_C,rCommand_D_C ;
  			cbCommand_D_C=String.format(COMMAND_CB_D_C, sMeterCode);
  			String jyCommand_D_C =GetHexStringCheckcode(cbCommand_D_C);
  			rCommand_D_C=String.format(COMMAND_JY_1997, cbCommand_D_C, jyCommand_D_C);

  			//总功率因素
  			String cbCommand_ZGYS,rCommand_ZGYS;
  			cbCommand_ZGYS=String.format(COMMAND_ZGYS, sMeterCode);
  			String jyCommand_ZGYS = GetHexStringCheckcode(cbCommand_ZGYS);
  			rCommand_ZGYS=String.format(COMMAND_JY_1997, cbCommand_ZGYS, jyCommand_ZGYS);

  			//正向有功总功率
  			String cbCommand_ZXYGZG,rCommand_ZXYGZG ;
  			cbCommand_ZXYGZG=String.format(COMMAND_ZXYGZG, sMeterCode);
  			String jyCommand_ZXYGZG =GetHexStringCheckcode(cbCommand_ZXYGZG);
  			rCommand_ZXYGZG=String.format(COMMAND_JY_1997, cbCommand_ZXYGZG, jyCommand_ZXYGZG);

  			//反向有功总功率
  			String cbCommand_FXYGZG ,rCommand_FXYGZG;
  			cbCommand_FXYGZG=String.format(COMMAND_FXYGZG, sMeterCode);
  			String jyCommand_FXYGZG = GetHexStringCheckcode(cbCommand_FXYGZG);
  			rCommand_FXYGZG=String.format(COMMAND_JY_1997, cbCommand_FXYGZG, jyCommand_FXYGZG);

  			//正向无功总电能
  			String cbCommand_ZXWGZDN,rCommand_ZXWGZDN;
  			cbCommand_ZXWGZDN=String.format(COMMAND_ZXWGZDN, sMeterCode);
  			String jyCommand_ZXWGZDN = GetHexStringCheckcode(cbCommand_ZXWGZDN);
  			rCommand_ZXWGZDN=String.format(COMMAND_JY_1997, cbCommand_ZXWGZDN, jyCommand_ZXWGZDN);

  			//反向无功总电能
  			String cbCommand_FXWGZDN ,rCommand_FXWGZDN;
  			cbCommand_FXWGZDN=String.format(COMMAND_FXWGZDN, sMeterCode);
  			String jyCommand_FXWGZDN = GetHexStringCheckcode(cbCommand_FXWGZDN);
  			rCommand_FXWGZDN=String.format(COMMAND_JY_1997, cbCommand_FXWGZDN, jyCommand_FXWGZDN);
  			//瞬时总有功功率
  			String cbCommand_SSZYGG,rCommand_SSZYGG;
  			cbCommand_SSZYGG=String.format(COMMAND_SSZYGG,sMeterCode);
  			String jyCommand_SSZYGG =GetHexStringCheckcode(cbCommand_SSZYGG);
  			rCommand_SSZYGG=String.format(COMMAND_JY_1997,cbCommand_SSZYGG,jyCommand_SSZYGG);
  			sCmd = rCommand_ZX_YG + "," + rCommand_FX_YG + "," 
  				+ rCommand_DY_A + "," + rCommand_DY_B + "," 
  				+ rCommand_DY_C + "," + rCommand_D_A + "," 
  				+ rCommand_D_B + "," + rCommand_D_C + "," 
  				+ rCommand_ZGYS + "," + rCommand_ZXYGZG + ","
  				+ rCommand_FXYGZG + "," + rCommand_ZXWGZDN + ","
  				+ rCommand_FXWGZDN+","+rCommand_SSZYGG;
  			return sCmd;
  		}
  		else
  		{
  			sCmd="error";
  		}
  	}
  	return sCmd; 
  }
  /*
  得到水表的序列号。
  */
  public String GetWaterSer(String sMeter)
  {
  int nstrlength=sMeter.length();
  if((8!=nstrlength)&&(14!=nstrlength))
  {
  	return null;
  }
  int c=0;
  int ntotal=0;
  int nresult =0;
  for (int i=0;i<nstrlength;i+=2)
  {
  	c=Integer.parseInt(sMeter.substring(i, i+2), 16);
  	ntotal+=c;
  }
  nresult=ntotal/256+1;

  String strReturn;
  strReturn=String.format("%2x",nresult);
  strReturn.replace(" ", "0");
  return strReturn;
  }
}

