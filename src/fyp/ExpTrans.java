/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fyp;

import static fyp.ParseE.*;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class ExpTrans {
     private Queuenm theQueue;
   private String input;
   private String output = "";
  /* public ExpTrans(String in) {
      input = in;
      int queueSize = input.length();
      theQueue = new Queuenm (queueSize);
   }
  /* public String doTrans() {
      for (int j = 0; j < input.length(); j++) {
         char ch = input.charAt(j);
         switch (ch) {
            case '+': 
            case '-':
            //gotOper(ch, 1); 
            //break; 
            case '*': 
            case '/':
            //gotOper(ch, 2); 
            //break; 
            case '(': 
            //theStack.push(ch);
            //break;
            case ')': 
            //gotParen(ch); 
            //break;
            //default: 
            output = output + ch; 
            break;
            default: 
            output = output + 'a'; 
            theQueue.push(ch);
            break;
         }
      }
 
      return output; 
   }*/
 
   public static void main(String[] args) 
   throws IOException {
      String input = "3";//"(12+(2*24 +5)+(3*6))   (( 3 + 4 ) * 5 ))";
      System.out.println("Input is " + input + '\n');
      
      Tokenizer tokn = new Tokenizer(input);
      QueueTokens tokens=tokn.doTokens();
      QueueTokens tokensC1=tokens.copyQueue();//copys the tokens to another queue to be fed to another function
      tokens.push("$");
      
      //System.out.println(tokens.pop()+" "+tokens.pop()+" "+tokens.pop()+" "+tokens.pop()+" "+tokens.pop()+" "+tokens.pop()+" "+tokens.pop());
      
      
      ParseE.st=tokens;
      ParseE.next();
      //System.out.println(tokensC1.pop()+" "+tokensC1.pop()+" "+tokensC1.pop());
     
      int x=ParseE.parseE();
      //System.out.println("OK "+x+ParseE.curr);
	if(ParseE.curr.equalsIgnoreCase("$")) {
	    System.out.println("OK Syntax ");
	} else {
	    ParseE.error("End expected");
	} 
      
       Exp2Mcod theTrans = new Exp2Mcod(tokensC1);
      QueueTokens tokenoutput = theTrans.doTrans();
     // System.out.println("hi+"+tokenoutput.top);
      //System.out.println(tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop()+" "+tokenoutput.pop());
      PostfixEvaluation pe=new PostfixEvaluation(tokenoutput);
      
      
      /* System.out.println(theTrans.theQueue.peek());
      llparser ll1=new llparser(output,theTrans.theQueue);
      ll1.algorithm();*/
      //PostfixEvaluation pe=new PostfixEvaluation(output);
      
   }
   
}
   class Queuenm {
      private int maxSize;
      private char[] queueArray;
      private int top,start;
      public Queuenm (int max) {
         maxSize = max;
         queueArray = new char[maxSize];
         top = -1;
         start=0;
      }
      public void push(char j) {
         queueArray[++top] = j;
      }
      public char pop() {
          if(start<=top)
         return queueArray[start++];
          else 
              return '\0';
      }
      public char peek() {
         return queueArray[top];
      }
      public boolean isEmpty() {
         return (top == -1||start>top);
     }
      public Queuenm copyQueue(Queuenm  q)
      {
          Queuenm  x=new Queuenm(q.maxSize);
          if(q.isEmpty())return x;
          for(int i=q.start;i<=top;i++)x.push(q.queueArray[i]);
          return x;
      }
    
}
