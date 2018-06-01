/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fyp;

/**
 *
 * @author HP
 */

import java.io.*;  
class Stackn  
{  
   private int[] a;  
   private int top,m;  
   public Stackn(int max)  
   {  
     m=max;  
     a=new int[m];  
     top=-1;  
   }  
   public void push(int key)  
   {  
     a[++top]=key;  
   }  
   public int pop()  
   {  
     return(a[top--]);  
   }  
}  
class Evaluation{ 
    static int i=0;
   public int calculate(QueueTokens q)  
   {  
     int n,r=0;  
     n=q.size();  
     Stackn a=new Stackn(n);  
     //for(int i=0;i<n;i++)  
     while(!q.isEmpty())
     {  
        // System.out.println(q.size()+" "+q.top);
       String ch=q.pop();  
       if(!(ch.equalsIgnoreCase("+")||ch.equalsIgnoreCase("-")||ch.equalsIgnoreCase("*")||ch.equalsIgnoreCase("/")))  
         a.push(Integer.parseInt(ch));  
       else  
       {  
         int x=a.pop();  
         int y=a.pop(); 
         System.out.println("cmd-"+y+" "+ch+" "+x);
         //outputcode(x,y,ch);
         switch(ch)  
         {  
           case "+":r=x+y;  
              break;  
           case "-":r=y-x;  
              break;  
           case "*":r=x*y;  
              break;  
           case "/":r=y/x;  
              break;  
           default:r=0;  
         }  
         a.push(r);  
       }  
     }  
     r=a.pop();  
     return(r);  
   }  
   public void outputcode(int x,int z,String ch)
   {
       String y=""+(x);
       if(i>0)y="t"+(i-1);
       switch(ch)  
         {  
           case "+":System.out.println("t"+(i++)+"=add "+y+" "+z);  
              break;  
           case "-":System.out.println("t"+(i++)+"=sub "+y+" "+z);  
              break;  
           case "*":System.out.println("t"+(i++)+"=mul "+y+" "+z);  
              break;  
           case "/":System.out.println("t"+(i++)+"=div "+y+" "+z);  
              break;  
           default:System.out.println("t"+(i++)+"=cmd-"+y+" "+ch+" "+z); 
         }  
       
   }
}  
class PostfixEvaluation  
{  
    public PostfixEvaluation(QueueTokens s)
    {
        //String input=s;  
    
        Evaluation e=new Evaluation(); 
        int x=e.calculate(s);
        //System.out.println("acc=t"+(e.i-1)); 
       System.out.println("Result:- "+x); 
    }
   /*public static void main(String[] args)throws IOException  
   {  
     String input;  
     while(true)  
     {  
       System.out.println("Enter the postfix expresion");  
       input=getString();  
       if(input.equals(""))  
         break;  
       Evaluation e=new Evaluation();  
       System.out.println("Result:- "+e.calculate(input));  
     }  
   } */ 
   public String getString()throws IOException  
   {  
     DataInputStream inp=new DataInputStream(System.in);  
     String s=inp.readLine();  
     return s;  
   }  
}  
