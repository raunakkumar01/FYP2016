/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Comp;

import block.*;
import java.util.ArrayList;
import java.util.HashMap;
import expevl.*;

/**
 *
 * @author HP
 * @param <L>
 * @param <R>
 */
class Pair<L,R> {

  private final L left;
  private final R right;

  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public L getLeft() { return left; }
  public R getRight() { return right; }

  @Override
  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pair)) return false;
    Pair pairo = (Pair) o;
    return this.left.equals(pairo.getLeft()) &&
           this.right.equals(pairo.getRight());
  }

}


public class Runtime {
    public static String code=null,out="";
    public static HashMap<String,Statement> symbltabl=new HashMap<String,Statement>();
    public static int varbadd=1000,globalLabel=1;
    //public static Queuen gl=new Queuen(100);
    public static Stackn gl=new Stackn(100);
    public static int getvarbadd()
        {return varbadd++;}
    
    public static int incrGlobalLabel()
            {return globalLabel++;}
    
    public Runtime() {
		
		/*String code =   "10 LET A = 0" + "\n" +
                                "20 LET E = 0" + "\n" +
                                "50 WHILE ( E < 11 ) THEN "+"\n"+
                                "60 DO E = ( E + 1 )"+"\n"+
                                "70 DO A = ( A + E )"+"\n"+
                                "80 WEND";*/
            //"50 ELSE "+"\n"+
                //"30 LET C = ( B / A ) * A + A"+"\n"+
                                /*"20 LET C = 2" + "\n" +
                                "30 LET D = A + ( B - C )";*/

		
		boolean success = false;
		
                
                Block blockm=new Block(null);//for the whole program
                Block block=blockm;
		for (String line : code.split("\n")) {
			success = false;
			line = line.trim();
			Tokenize tokenizer = new Tokenize();
                        String token[]=tokenizer.givetoken(line);
                        
                        if(token[1].equals("LET"))
                        {
                            /*if(!token[3].equals("("))
                            {
                                symbltabl.put(token[1],new Pair(Integer.parseInt(token[3]),(""+varbadd)));
                                varbadd++;
                            }*/
                            
                             Block newBlock= new Statement(block,token[2],line.substring(line.indexOf(token[4],4)));
                             //symbltabl.put(token[2],(Statement)newBlock);
                            //passes the current block as parent, name of variable, and its value;
                            block.addBlock(newBlock);  
                            //System.out.println("Let"+line.substring(line.indexOf(token[4],4)));
                        }
                        else if(token[1].equals("DO"))
                        {
                           
                            
                             Block newBlock= new Expression(block,token[2],line.substring(line.indexOf(token[4],4)));
                             //symbltabl.put(token[2],(Statement)newBlock);
                            //passes the current block as parent, name of variable, and its value;
                            block.addBlock(newBlock);  
                            //System.out.println("Let"+line.substring(line.indexOf(token[4],4)));
                        }
                        else if(token[1].equals("IF"))
                        {
                              Block newBlock= new If("IF",line.substring(line.indexOf('('),line.indexOf(')')+1),"GL"+globalLabel,block);
                              //System.out.println(line.substring(line.indexOf('('),line.indexOf(')')+1)+" ; "+"GL"+globalLabel);
                              
                              gl.push(globalLabel);incrGlobalLabel();
                              block.addBlock(newBlock);
                              block=newBlock;
                        }
                        else if(token[1].equals("ENDIF"))
                        {
                              
                              Block newBlock= new If("ENDIF","","",block.getSuperBlock());
                              block=block.getSuperBlock();
                              block.addBlock(newBlock);
                        }
                        else if(token[1].equals("WHILE"))
                        {
                              
                              Block newBlock= new While("WHILE",line.substring(line.indexOf('('),line.indexOf(')')+1),"GL"+globalLabel,block);
                              //System.out.println(line.substring(line.indexOf('('),line.indexOf(')')+1)+" ; "+"GL"+globalLabel);
                              
                              gl.push(globalLabel);incrGlobalLabel();
                              block.addBlock(newBlock);
                              block=newBlock;
                        }
                        else if(token[1].equals("WEND"))
                        {
                              
                              Block newBlock= new While("WEND","","",block.getSuperBlock());
                              block=block.getSuperBlock();
                              block.addBlock(newBlock);
                        }
			//System.out.println(token[1]);
			
			/*if (!success) {//if no parser could parse the line thus we say it is invalid
				throw new IllegalArgumentException("Invalid line " + line);
			}*/
		}
                
                
		block.run();
		//System.out.println(" HLT");
		this.out+="HLT\n";
                
		
	}

	/*public static void main(String[] args) {
		new Runtime();
	}*/
    
}
