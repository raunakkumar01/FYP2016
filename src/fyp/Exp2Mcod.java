/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fyp;

import java.io.IOException;

/**
 *
 * @author HP
 */
public class Exp2Mcod {
    private Stack theStack;
   private QueueTokens input;
   private QueueTokens output;
   public Exp2Mcod(QueueTokens in) {
      output=new QueueTokens(in.size());
      input = in;
      int stackSize = input.size();
      theStack = new Stack(stackSize);
   }
   public QueueTokens doTrans() {
      //for (int j = 0; j < input.size(); j++) 
       //System.out.println(input.start+"exp2 "+input.top);
       while(!input.isEmpty())
      {
         String ch = input.pop();
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
            //output = output + ch; 
            break;
         }
      }
      while (!theStack.isEmpty()) {
         output.push(theStack.pop());
         //output = output + theStack.pop();
      }
      //System.out.println(output);
      return output; 
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
            //output = output + opTop;
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
         //output = output + chx; 
      }
   }
  /* public static void main(String[] args) 
   throws IOException {
      String input = "(1+(2*4/5-7)+(3/6))";
      System.out.println("Input is " + input + '\n');
      String output;
      Exp2Mcod theTrans = new Exp2Mcod(input);
      output = theTrans.doTrans(); 
     // System.out.println("Postfix is " + output + '\n');
      PostfixEvaluation pe=new PostfixEvaluation(output);
      
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

