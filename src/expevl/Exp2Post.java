/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package expevl;

import fyp.*;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class Exp2Post {
    private Stack theStack;
    private String input[];
    private QueueTokens output;
    public Exp2Post(String in) {
        input = in.split(" ");
        output=new QueueTokens(input.length+1);
        theStack = new Stack(input.length+1);
    }
    public void doTrans() {
       int i=0;
       while(i<input.length)
       {
           
            String ch = input[i];
            //System.out.println(ch);
            switch (ch) {
                case "+": 
                case "-":
                    gotOper(ch, 1); 
                    break; 
                case "*": 
                case "/":
                    gotOper(ch, 2); 
                    break; 
                case "(": 
                    theStack.push(ch);
                    break;
                case ")": 
                    gotParen(ch); 
                    break;
                default: 
                    output.push(ch);
                    break;
            }
            i++;
        }
        while (!theStack.isEmpty()) {
            output.push(theStack.pop());
        }
       // return output; 
        PostfixEvaluation pe=new PostfixEvaluation(output);
   }
   public void gotOper(String opThis, int prec1) {
      while (!theStack.isEmpty()) {
         String opTop = theStack.pop();
         if (opTop.equalsIgnoreCase("(")) {
            theStack.push(opTop);
            break;
         }
         else {
            int prec2;
            if (opTop.equalsIgnoreCase("+") || opTop.equalsIgnoreCase("-"))
            prec2 = 1;
            else
            prec2 = 2;
            if (prec2 < prec1) { 
               theStack.push(opTop);
               break;
            }
            else output.push(opTop);
         }
      }
      theStack.push(opThis);
   }
   public void gotParen(String ch){ 
      while (!theStack.isEmpty()) {
        String chx = theStack.pop();
        if (chx.equalsIgnoreCase("(")) 
        break; 
        else output.push(chx);
      }
   }
  /* public static void main(String[] args) 
   throws IOException {
      String input = "A + B * C + ( D - F )";//"(1+(2*4/5-7)+(3/6))";
      System.out.println("Input is " + input + '\n');
      QueueTokens output;
      Exp2Post theTrans = new Exp2Post(input);
     // output = 
      theTrans.doTrans();
      //System.out.println("Postfix is " + output.pop()+output.pop()+output.pop()+output.pop()+output.pop() + '\n');
      
      
   }*/
   
}
   class Stack {
      private int maxSize;
      private String[] stackArray;
      private int top;
      public Stack(int max) {
         maxSize = max;
         stackArray = new String[maxSize];
         top = -1;
      }
      public void push(String j) {
         stackArray[++top] = j;
      }
      public String pop() {
         return stackArray[top--];
      }
      public String peek() {
         return stackArray[top];
      }
      public boolean isEmpty() {
         return (top == -1);
     }
   }

class QueueTokens {
      private int maxSize;
      private String[] queueArray;
      public int rear,start;
      public QueueTokens (int max) {
         maxSize = max;
         queueArray = new String[maxSize];
         rear = -1;
         start=0;
      }
      public void push(String j) {
         queueArray[++rear] = new String(j);
      }
      public String pop() {
          if(start<=rear)
         return queueArray[start++];
          else 
              return "";
      }
      public String dequeue() {
          if(start<=rear)
         return queueArray[rear--];
          else 
              return "";
      }
      public String peek() {
         return queueArray[start];
      }
      public boolean isEmpty() {
         return (rear == -1||start>rear);
     }
      public int size()
      {
          return maxSize;
      }
      public QueueTokens copyQueue()//(QueueTokens  q)
      {
          QueueTokens  x=new QueueTokens(this.maxSize);
          if(this.isEmpty())return x;
          for(int i=this.start;i<=this.rear&&!(this.isEmpty());i++)x.push(this.queueArray[i]);
          return x;
      }
    
}