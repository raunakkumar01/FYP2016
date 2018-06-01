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
public class Tokenizer {
    
    public QueueTokens tokens;
    private String input;
    public Tokenizer(String in) {
      input = in;
      int queueSize = input.length()+1;
      tokens = new QueueTokens(queueSize);
   }
    public QueueTokens doTokens() {
      String output="",prev;
      
      for (int j = 0; j < input.length(); j++) {
         char ch = input.charAt(j);
         switch (ch) {
            case '+': 
            case '-':
            case '*': 
            case '/':
            case '(': 
            case ')': if(output!=""){System.out.println(output);tokens.push(output);}
                    System.out.println(ch);
                    output="";   
                    tokens.push(""+ch);
            break;
            case ' ':if(output!=""){System.out.println(output);
                                    tokens.push(output);}
                     output=""; 
                break;
            case '0': 
            case '1':
            case '2':
            case '3': 
            case '4':
            case '5':
            case '6': 
            case '7':
            case '8':
            case '9': output = output + ch; 
                    prev=output;
                break;
            default: 
                System.out.println(ch+" is Invalid Token");
            break;
         }
      }
      
       if(output!=""){System.out.println(output);tokens.push(output);}
                    
      return tokens;
   }
     
}

class QueueTokens {
      private int maxSize;
      private String[] queueArray;
      public int top,start;
      public QueueTokens (int max) {
         maxSize = max;
         queueArray = new String[maxSize];
         top = -1;
         start=0;
      }
      public void push(String j) {
         queueArray[++top] = new String(j);
      }
      public String pop() {
          if(start<=top)
         return queueArray[start++];
          else 
              return "";
      }
      public String dequeue() {
          if(start<=top)
         return queueArray[top--];
          else 
              return "";
      }
      public String peek() {
         return queueArray[start];
      }
      public boolean isEmpty() {
         return (top == -1||start>top);
     }
      public int size()
      {
          return maxSize;
      }
      public QueueTokens copyQueue()//(QueueTokens  q)
      {
          QueueTokens  x=new QueueTokens(this.maxSize);
          if(this.isEmpty())return x;
          for(int i=this.start;i<=this.top&&!(this.isEmpty());i++)x.push(this.queueArray[i]);
          return x;
      }
    
}