/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package block;
import Comp.Runtime;
/**
 *
 * @author HP
 */
public class IfElse extends Block{
    private String type,expression,label;

    public IfElse(String type, String expression, String label, Block superBlock) {
        super(superBlock);
        this.type = type;
        this.expression = expression;
        this.label = label;
        //System.out.println(label);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    static boolean isInt(String s)
    {
     try
      { int i = Integer.parseInt(s); return true; }

     catch(NumberFormatException er)
      { return false; }
    }
    
    
    public void run()
    {

        if(type.equals("IF"))
        {
            String tk[]=expression.split(" ");
            
            if(isInt(tk[3]))System.out.println(" MVI A,"+tk[3]);
            else System.out.println(" LDA "+((Statement)Comp.Runtime.symbltabl.get(tk[3])).getAddress());
            
            System.out.println(" MOV B, A ");
           
            if(isInt(tk[1]))System.out.println(" MVI A,"+tk[1]);
            else System.out.println(" LDA "+((Statement)Comp.Runtime.symbltabl.get(tk[1])).getAddress());
            
         switch(tk[2])  
         {  
           case ">":System.out.println(" SUB B");//a=a-b;
                    System.out.println(" JM "+label);
              break;  
           case "<":System.out.println(" SUB B");//a=a-b;
                    System.out.println(" JP "+label);
              break;  
           case "=":System.out.println(" SUB B");//r=y-x;
                    System.out.println(" JNZ "+label);
              break;  
          
           default://r=0;  
         }
            
            
            
            
             for (Block b : getSubBlocks()) {
			if (b instanceof Statement) {
				Statement st = (Statement) b;
                                Runtime.symbltabl.put(st.getName(),st);
                                st.run();
			}
		}
             //System.out.println(" JNZ "+this.label);
        }
        else if(type.equals("ELSE"))
        {//for else part
            
            System.out.println(" JMP "+label);
            System.out.print("GL"+Runtime.gl.pop()+":");
             for (Block b : getSubBlocks()) {
			if (b instanceof Statement) {
				Statement st = (Statement) b;
                                Runtime.symbltabl.put(st.getName(),st);
                                st.run();
			}
		}
        }
        else if(type.equals("ENDIF"))
        {//for endinf IF
            System.out.print("GL"+Runtime.gl.pop()+":");
        }
    }
    
}
