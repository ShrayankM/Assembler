import java.*;
import java.io.*;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.Scanner;
class assembler {

	public static void main(String[] args) throws IOException {
		Scanner input=new Scanner(System.in);
		int LC_index=1;
		int choice=1;
		int lc[] = new int[20];
		lc[0]=0;
		String strline = new String();
		
		
		FileInputStream fin;
		BufferedReader br;
		while(choice!=5)
		{
			System.out.println("1)Display Source");
			System.out.println("2)Display MOT");
			System.out.println("3)Create LC");
			System.out.println("4)Create Symbol Table");
			System.out.println("5)Exit");
			choice=input.nextInt();
			switch(choice)
			{
				case 1:
					fin = new FileInputStream("in.txt");
					br = new BufferedReader(new InputStreamReader(fin));
					while((strline=br.readLine())!=null)
					{
						System.out.println(strline);
					}
					fin.close();
					break;
					
				case 2:
					fin = new FileInputStream("mot.txt");
					br = new BufferedReader(new InputStreamReader(fin));
					while((strline=br.readLine())!=null)
					{
						System.out.println(strline);
					}
					fin.close();
					break;
					
				case 3:
					String codestr = new String();
					  String motstr = new String();
					String strT = new String();
					int indexD=0,indexM=0;
					fin = new FileInputStream("in.txt");
					br = new BufferedReader(new InputStreamReader(fin));
					String codeT[]=new String[4];
					String mot[]=new String[4];
					while((codestr=br.readLine())!=null)
					{
						indexD=0;
						StringTokenizer stC=new StringTokenizer(codestr," ,");
						while(stC.hasMoreTokens())
						{
							
							codeT[indexD]=stC.nextToken();
							indexD++;
						}
						FileInputStream fin2=new FileInputStream("mot.txt");
						BufferedReader br2 = new BufferedReader(new InputStreamReader(fin2));
						strT = codeT[1];
						switch(codeT[1])
						{
							case "START":
								System.out.println("Found Start");
								lc[LC_index]=lc[LC_index-1]+0;
								LC_index++;
								break;

							case "USING":
								System.out.println("Found USING");
								lc[LC_index]=lc[LC_index-1]+0;
								LC_index++;
								break;

							case "DC":
								
								String tempF = new String();
								StringTokenizer for_f = new StringTokenizer(codeT[2],"'");
								tempF = for_f.nextToken();
								if(tempF.equals("F"))
								{
									System.out.println("Found DC");
									lc[LC_index]=lc[LC_index-1]+4;
									LC_index++;
								}
								break;

							default:
								break;
						}
						while((motstr=br2.readLine())!=null)
						{
							indexM=0;
							StringTokenizer stM=new StringTokenizer(motstr," ,");
							while(stM.hasMoreTokens())
							{
								mot[indexM]=stM.nextToken();
								indexM++;
							}
							if(mot[0].equals(codeT[1]))
							{
								lc[LC_index]=lc[LC_index-1]+Integer.parseInt(mot[2]);
								
								LC_index++;  
								break;
							}
							else
								continue;
							

						}
					}
					
					for(int k=0;k<7;k++)
						System.out.println(lc[k]);
					break;
					
				case 4:
					String code[]=new String[4];
					int indexC;
					int loop_count=0;
					String value_temp=new String();
					File file = new File("test1.txt");
					FileWriter fileWriter = new FileWriter(file);
					fin = new FileInputStream("in.txt");
					br = new BufferedReader(new InputStreamReader(fin));
					while((codestr=br.readLine())!=null)
					{
						indexC=0;
						StringTokenizer stC=new StringTokenizer(codestr," ,");
						while(stC.hasMoreTokens())
						{
							
							code[indexC]=stC.nextToken();
							indexC++;
						}
						if(code[0].equals("\t"))
						{
							loop_count++;
							continue;

						}
						else
							{
								int k=0;
								value_temp = new Integer(lc[loop_count]).toString();
								fileWriter.write(code[0]);
								fileWriter.write(" "+value_temp);
								StringTokenizer strTemp = new StringTokenizer(code[2],"'");
								value_temp = strTemp.nextToken();
								
							
								fileWriter.write(" "+value_temp);
								fileWriter.write(" Length");
								fileWriter.write("\n");
								fileWriter.flush();
							}
						loop_count++;
						
					}
					fin.close();
					fileWriter.close();
					break;
			}
		}
		input.close();
	}
}
