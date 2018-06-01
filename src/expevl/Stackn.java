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
public class Stackn  
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
