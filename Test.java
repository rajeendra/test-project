package rk.test.java.pojo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import rk.test.java.pojo.business.*;
import java.util.regex.*;

//import javax.print.DocFlavor.URL; // Feature one

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//rec(-1);

		  /*
		  HashMap<Integer,String> hm=new HashMap<Integer,String>();  
		  
		  hm.put(100,"Amit");  
		  hm.put(101,"Vijay");  
		  hm.put(102,"Rahul");  
		  
		  for(Map.Entry m:hm.entrySet()){  
		   System.out.println(m.getKey()+" "+m.getValue());  
		  }		
		
		 //
		 LinkedHashMap<Integer,String> lhm=new LinkedHashMap<Integer,String>();  
		  
		  lhm.put(100,"Amit");  
		  lhm.put(101,"Vijay");  
		  lhm.put(102,"Rahul");  
		  
		for(Map.Entry m:lhm.entrySet()){  
		   System.out.println(m.getKey()+" "+m.getValue());  
		} 
		*/
		
		//String s1 = "para01=val01; para02=val02; para03=val03; para04=val04;";
		//String s2 = "select * from table1 where a.col1=? and a.col2=?  or (a.col3=? and a.col4=?)";
	    //System.out.println(generateSQL(s1,s2));
		
		try {
		  //System.out.println(new Test().readFile("File02.txt"));
		  System.out.println(new Test().generateSQL(new Test().readFile("File01.txt"),new Test().readFile("File02.txt")));

		} catch (Exception e) {
			// TODO: handle exception
		}		
		//regExTest();
	}
	
	private String readFileOne(String file) throws IOException {
		String s = "wp2 one";		
	    	return s;
	}	
	
	private String readFile(String file) throws IOException {
		URL url = getClass().getResource(file);

	    BufferedReader reader = new BufferedReader( new FileReader (url.getPath()));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
        //System.out.println(ls);
	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }

	    return stringBuilder.toString();
	}	
	
	private String generateSQL(String args, String sqlStu){
		String sql="";
		String delimeter = ";";
		String paraStr = "?";
		String[] values = args.split(delimeter);
	    for (int i = 0; i < values.length; i++) {
	    	values[i] = values[i].substring(values[i].indexOf("=")+1);
	    	//System.out.println(values[i]);
		}
		
		
	    int lastIndex = 0;
	    int count = 0;
	    int xFirstIndex=0;
        //0,
	    while (lastIndex != -1) {
	           lastIndex = sqlStu.indexOf(paraStr,lastIndex);
	           //System.out.println(lastIndex);
	           if( lastIndex != -1){
	        	   //System.out.println(sqlStu.substring(xFirstIndex,lastIndex));
	        	   sql += sqlStu.substring(xFirstIndex,lastIndex) + values[count]; 
	        	   xFirstIndex = lastIndex + paraStr.length();
	        	   count++;
	           }
	           else{
	        	   //System.out.println(sqlStu.substring(xFirstIndex));
	        	   sql += sqlStu.substring(xFirstIndex);
	        	   break;
	           }
               //System.out.println(str.substring(0,lastIndex)); 
	           lastIndex += paraStr.length();
	    }
		
		return sql;
	}
	
	private static void regExTest(){
		Pattern pattern = Pattern.compile(".xx.");
		Matcher matcher = pattern.matcher("MxxY");
		System.out.println("Input String matches regex - "+matcher.matches());
		// bad regular expression
		//Pattern = Pattern.compile("*xx*");	
		
		String str = "bbb";
		System.out.println("Using String matches method: "+str.matches(".bb"));
		System.out.println("Using Pattern matches method: "+Pattern.matches(".bb", str));
		
		String REGEX = "cat\\b";
		String INPUT = "cat cat cat cattie cat";
		
	      Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(INPUT); // get a matcher object
	       int count = 0;

	       while(m.find()) {
	         count++;
	         System.out.println("Match number "+count);
	         System.out.println("start(): "+m.start());
	         System.out.println("end(): "+m.end());
	         System.out.println();
	      }		
		
		
	}
	
	private static void rec(int x){
		if (x<0){
			x=new Policy().getPolicy();
		}
		
		if (x==6){ 
			System.out.println("Business ends...");
			return;
		}
		else{ 
			Business b=null; 
			if (x==1){
				b = new Business01();
			}
			else if(x==2){
				b = new Business02();
			}
			else if(x==3){
				b = new Business03();
			}
			else if(x==4){
				b = new Business04();
			}
			else if(x==5){
				b = new Business05();
			}
			x=b.execute();
			rec(x);
		}
	}

}
