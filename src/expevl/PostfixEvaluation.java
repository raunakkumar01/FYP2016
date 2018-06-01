/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package expevl;

/**
 *
 * @author HP
 */

import fyp.*;
import java.io.*;  

class Evaluation{ 
    static int i=0,stckLabel=1;
    static boolean isInt(String s)
    {
     try
      { int i = Integer.parseInt(s); return true; }

     catch(NumberFormatException er)
      { return false; }
    }
   public String calculate(QueueTokens q)  
   {  
     int n,stckadd=2500;  
     String r;
     n=q.size();  
     Stack a=new Stack(n);
     Stackn lb=new Stackn(100); 
     //for(int i=0;i<n;i++) 
     //System.out.println(" LXI H, "+stckadd);
     Comp.Runtime.out+=" LXI H, "+stckadd+" \n";
     
     while(!q.isEmpty())
     {  
        // System.out.println(q.size()+" "+q.top);
       String ch=q.pop();  
       if(!(ch.equalsIgnoreCase("+")||ch.equalsIgnoreCase("-")||ch.equalsIgnoreCase("*")||ch.equalsIgnoreCase("/")))  
       {   
           
           if(!isInt(ch))
           {
                //System.out.println(ch);
               
                //System.out.println(" LDA "+Comp.Runtime.symbltabl.get(ch).getAddress());
                Comp.Runtime.out+=" LDA "+Comp.Runtime.symbltabl.get(ch).getAddress()+" \n";
                
                //loads the content of the memory locn to accumulator
                //System.out.println(" MOV M, A");
                Comp.Runtime.out+=" MOV M, A \n";
                //System.out.println(" INR L");
                Comp.Runtime.out+=" INR L \n";
                a.push(ch);//(Integer.parseInt(ch));
           }
           else 
           {
               //System.out.println(" MVI A, "+ch);
                Comp.Runtime.out+=" MVI A, "+ch+" \n";
               //System.out.println(" MOV M, A");
               Comp.Runtime.out+=" MOV M, A \n";
               //System.out.println(" INR L");
               Comp.Runtime.out+=" INR L \n";
               a.push(ch);//(Integer.parseInt(ch));
           }
               
        
       }
       
       else  
       {  
         String x=a.pop();  
         String y=a.pop(); 
         //System.out.println(" DCR L");
         Comp.Runtime.out+=" DCR L \n";
         //System.out.println(" MOV B, M");
         Comp.Runtime.out+=" MOV B, M\n";
         //System.out.println(" DCR L");
         Comp.Runtime.out+=" DCR L\n";
         //System.out.println(" MOV A, M");
         Comp.Runtime.out+=" MOV A, M\n";
         //System.out.println("cmd-"+y+" "+ch+" "+x);
         //outputcode(x,y,ch);
         switch(ch)  
         {  
           case "+"://System.out.println(" ADD B");//r=x+y;                    
                    Comp.Runtime.out+=" ADD B \n";
              break;  
           case "-"://System.out.println(" SUB B");//r=y-x; 
                    Comp.Runtime.out+=" SUB B \n";
              break;  
           case "*"://System.out.println(" MUL B");//r=x*y;  
                    //System.out.println(" MOV C, A"); 
                    Comp.Runtime.out+=" MOV C, A\n";
                    //System.out.println(" MVI A, "+0);
                    Comp.Runtime.out+=" MVI A, "+0+"\n";
                    //System.out.println("L"+(stckLabel)+": ADD B");                    
                    Comp.Runtime.out+="L"+(stckLabel)+": ADD B\n";
                    lb.push(stckLabel);stckLabel++;
                    //System.out.println(" DCR C");                    
                    Comp.Runtime.out+=" DCR C\n";
                    //System.out.println(" JNZ L"+lb.pop());                    
                    Comp.Runtime.out+=" JNZ L"+lb.pop()+"\n";
                    //stckLabel--;      
              break;  
           case "/"://System.out.println(" DIV B");//r=y/x;
                    //System.out.println(" MVI C, "+0);
                    Comp.Runtime.out+=" MVI C, "+0+"\n";
                    //System.out.println("L"+(stckLabel)+": SUB B");
                    Comp.Runtime.out+="L"+(stckLabel)+": SUB B\n";
                    lb.push(stckLabel);stckLabel++;
                    //System.out.println(" INR C");                    
                    Comp.Runtime.out+=" INR C\n";
                    //System.out.println(" CMP B");                                        
                    Comp.Runtime.out+=" CMP B\n";
                    //System.out.println(" JNC L"+lb.pop());                                       
                    Comp.Runtime.out+=" JNC L"+lb.pop()+"\n";                    
                    //System.out.println(" MOV A,C");                                                          
                    Comp.Runtime.out+=" MOV A,C\n";
              break;  
           default://r=0;  
         }
         
         a.push("("+y+" "+ch+" "+x+")"); 
         //System.out.println(" MOV M, A");                                                                   
         Comp.Runtime.out+=" MOV M, A\n";
         //System.out.println(" INR L");                                                                   
         Comp.Runtime.out+=" INR L\n";
         
       } 
         
     }  
     r=a.pop();  
     return(r);  
   }  
  
}  
class PostfixEvaluation  
{  
    public PostfixEvaluation(QueueTokens s)
    {
        //String input=s;  
    
        Evaluation e=new Evaluation(); 
        String x=e.calculate(s);
       //System.out.println("Result:- "+x); 
    }
  
}  
