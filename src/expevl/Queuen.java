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
public class Queuen 
{  
   private int[] a;  
   private int front,rear,m;  
   public Queuen(int max)  
   {  
     m=max;  
     a=new int[m];  
     front=-1;rear=0;  
   }  
   public void push(int key)  
   {  
     a[++front]=key;  
   }  
   public int pop()  
   {  
       if(rear<=front)
     return(a[rear++]);  
       
       return -9999;
   }  
}  
