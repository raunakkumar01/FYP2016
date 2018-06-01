/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fyp;
import java.util.Stack;
/**
 *
 * @author HP
 */
public class llparser {
    

/**
LL1

G -> E$
E -> TK
K-> +TK | empty
T -> FH
H-> *FH | empty
F -> (E)  | a
 
 
 
 
 
 =========================================
         a        +           *         (        )         $        
--------------------------------------------------------------------
G| G->E$     |         |         | G->E$    |          |       |                
--------------------------------------------------------------------
E| E->TK     |         |         |  E->TK  |          |       |
--------------------------------------------------------------------
K|           | K->+TK  |         |          | K->     | K->  |  
--------------------------------------------------------------------
T|    T->FH  |         |         |  T->FH  |          |       | 
--------------------------------------------------------------------
H|           |   H->   | H->*FH  |          |    H->  | H->  |                                                  
--------------------------------------------------------------------
F|    F->a   |         |         | F->(E)    |         |       |                                                  
--------------------------------------------------------------------
===============================================================================
 */

//input
   public String input="";//"i*i$"
    private int indexOfInput=-1;
    private Queuenm theQueue;
    private Stackr theStackReg;
    private int reg=0;
    //Stack
    Stack <String> strack=new Stack<String>();
    //Table of rules
    String [][] table=
    {
        {"E$",null,null,"E$",null,null}
            ,
        {"TK",null,null,"TK",null,""}
            ,
        {null,"+TK",null,null,"",""}
            ,
        {"FH",null,null,"FH",null,null}
            ,
        {null,"","*FH",null,"",""}
            ,
        {"a",null,null,"(E)",null,null}
    
    
    };
    String [] nonTers={"G","E","K","T","H","F"};
String [] terminals={"a","+","*","(",")","$"};


public llparser(String in,Queuenm inqueue)
{
this.input=in;
theQueue=inqueue;
theStackReg = new Stackr(in.length());
}

private  void pushRule(String rule)
{
for(int i=rule.length()-1;i>=0;i--)
{
char ch=rule.charAt(i);
String str=String.valueOf(ch);
push(str);
}
}

    //algorithm
public void algorithm    ()
{

    
   // push(this.input.charAt(0)+"");//pushes first charachter to stack
    push("G");
    //Read one token from input
    
    String token=read();//reads the first character from input i.e a*a gives 1st a
    String top=null;
    do
    {
        top=this.pop();//pops out the 1st element of stack
        //if top is non-terminal
        //System.out.println(token+""+top+"");
        
        if(isNonTerminal(top))
        {
            String rule=this.getRule(top, token);
            this.pushRule(rule);
        }
        else if(isTerminal(top))
        {
            if(!top.equals(token))//sees the top of stack matches with the first element of input right now
            {
            error("this token is not corrent , By Grammer rule . Token : ("+token+")");
            }
            else
            {
                System.out.println("Matching: Terminal :( "+token+" )");
              
                char ch=token.charAt(0);    
                switch (ch) {
                   case '+': 
                   case '-':
                   //gotOper(ch, 1); 
                   //break; 
                   case '*': 
                   case '/':
                   //gotOper(ch); 
                   theStackReg.push(ch);
                   break;
                   case '(': 
                   theStackReg.push(ch);
                   //theStack.push(ch);
                   break;
                   case ')':  
                   gotParen(); 
                   break;
                   default: 
                   //output = output + ch; 
                       if(!theStackReg.isEmpty()&&(theStackReg.peek()=='+'||theStackReg.peek()=='*'))
                       {
                           theStackReg.push((""+reg).charAt(0));
                           
                           System.out.println("load t"+reg+" "+theQueue.pop());
                           reg++;
                           evaluate();}
                       else
                       {
                           theStackReg.push((char)reg);
                           System.out.println("load t"+reg+" "+theQueue.pop());
                           reg++;
                       }
                   break;
                }
                
                token =read();
            //top=pop();

            }
        }
        else
        {
            error("Never Happens , Because top : ( "+top+" )");
        }
        if(token.equals("$"))
        {
            break;
        }
        //if top is terminal
    
    }while(true);//out of the loop when $
    
    //accept
    if(token.equals("$"))
        {
         System.out.println("Input is Accepted by LL1");   
        }
    else
    {
     System.out.println("Input is not Accepted by LL1");   
    }
}

    private boolean isTerminal(String s) {
               for(int i=0;i<this.terminals.length;i++)
        {
        if(s.equals(this.terminals[i]))
        {
        return true;
        }

        }
              return false;
    }

    private boolean isNonTerminal(String s) {
        for(int i=0;i<this.nonTers.length;i++)
        {
        if(s.equals(this.nonTers[i]))
        {
        return true;
        }

        }
              return false;
    }

    private String read() {
        indexOfInput++;//as when reading acharacte the previous element has already been processed so now input string starts from  starts from the current indexOfInput 
        char ch=this.input.charAt(indexOfInput);
String str=String.valueOf(ch);

        return str;
    }

    private void push(String s) {
     this.strack.push(s);   
    }
        private String pop() {
   return this.strack.pop();   
    }

    private void error(String message) {
        System.out.println(message);
        throw new RuntimeException(message);
    }
    public String getRule(String non,String term)
    {
        
    int row=getnonTermIndex(non);
    int column=getTermIndex(term);
    String rule=this.table[row][column];
    if(rule==null)
    {
    error("There is no Rule by this , Non-Terminal("+non+") ,Terminal("+term+") ");
    }
    return rule;
    }

    private int getnonTermIndex(String non) {
       for(int i=0;i<this.nonTers.length;i++)
       {
       if(non.equals(this.nonTers[i]))
       {
       return i;
       }
       }
       error(non +" is not NonTerminal");
       return -1;
    }

    private int getTermIndex(String term) {
              for(int i=0;i<this.terminals.length;i++)
       {
       if(term.equals(this.terminals[i]))
       {
       return i;
       }
       }
       error(term +" is not Terminal");
       return -1;
    }
    public void evaluate(){ 
    
        String op1,op2,opr;
        char chx=theStackReg.pop();
        
        op2="t"+(--reg);
        chx=theStackReg.pop();
        opr=""+chx;
        chx=theStackReg.pop();
        op1="t"+(--reg);
        chx = theStackReg.pop();
         if (chx == '(') 
         {
        //System.out.println("t"+(reg)+"="+op1+opr+op2);
         theStackReg.push(chx);
         theStackReg.push((""+reg).charAt(0));
         reg++;
         outputcode(op1,op2,opr.charAt(0));
         }
         
         
     // }
   }
     public void gotParen(){ 
        char prn,chx=theStackReg.pop();
        if((prn=theStackReg.pop())=='(')
        {
            theStackReg.push(chx);
        }
        else
        {
            char chx1=theStackReg.pop();
            //System.out.println("t"+(reg-2)+"="+"t"+(reg-3)+prn+"t"+(reg-2));
            outputcode("t"+(reg-3),"t"+(reg-2),prn);
            
            reg--;
            theStackReg.push((""+(reg-1)).charAt(0));
        }
     }
     public void outputcode(String y,String x,char ch)
   {
       
       switch(ch)  
         {  
           case '+':System.out.println("t"+(reg-1)+"=add "+y+" "+x);  
              break;  
           case '-':System.out.println("t"+(reg-1)+"=sub "+y+" "+x);  
              break;  
           case '*':System.out.println("t"+(reg-1)+"=mul "+y+" "+x);  
              break;  
           case '/':System.out.println("t"+(reg-1)+"=div "+y+" "+x);  
              break;  
           default:System.out.println("t"+(reg-1)+"=cmd-"+y+" "+ch+" "+x); 
         }  
       
   }
}  
        


    

 class Stackr {
      private int maxSize;
      private char[] stackArray;
      private int top;
      public Stackr(int max) {
         maxSize = max;
         stackArray = new char[maxSize];
         top = -1;
      }
      public void push(char j) {
         stackArray[++top] = j;
      }
      public char pop() {
         return stackArray[top--];
      }
      public char peek() {
         return stackArray[top];
      }
      public boolean isEmpty() {
         return (top == -1);
     }
   }