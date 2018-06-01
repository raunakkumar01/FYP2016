/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Comp;

import java.io.*;
import java.util.*;

/**
 *
 * @author HP
 */
public class Tokenize {
    
    public String[] givetoken(String line)
    {
        
        String tokens[]=line.split(" ");
        return tokens;
    }
    
    
    public String[][] makeTokens(String s)
    {
        String tk[][]=new String[100][100];
        BufferedReader bfRdr = null;      
        String str;
        
        int i=0,j,c=0;

            try 
            {
                String prm[]=new String[100];
                bfRdr= new BufferedReader(new FileReader(s));
                
                while((str=bfRdr.readLine())!=null)
                {
                    String ch=""+(char)32;//AScii for space 32
                    StringTokenizer stn1= new StringTokenizer(str,ch); 
                    String str1=stn1.nextToken().trim();
                    tk[i][0]=str1;
                    j=1;
                    while(stn1.hasMoreTokens())
                    {
                             tk[i][j++]=stn1.nextToken().trim();;                   
                    }
                    
                   tk[i][j++]="\0";
                   i++;c++;
                    }
            bfRdr.close();             
           }catch (FileNotFoundException e) {
                             System.out.println("Error opening the input file!" + e.getMessage());
                             System.exit(0);
           }catch ( IOException e) {
                             System.out.println(" IO Error!" + e.getMessage());
                             e.printStackTrace();
                             System.exit(0);			 		
            }
            
            for(i=0;i<c;i++,System.out.println())
                for(j=0;!tk[i][j].equals("\0");j++)
                {
                    System.out.print(tk[i][j]+" ");
                }
            return tk;
        }
    
    
    /*public static void main(String args[])
    {
        Tokenize t= new Tokenize();
        String s[][]=t.makeTokens("source.txt");
    }*/
}
