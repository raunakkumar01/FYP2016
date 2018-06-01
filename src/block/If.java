/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package block;

/**
 *
 * @author HP
 */
public class If extends Block{
     private String type,expression,label;

    public If(String type, String expression, String label, Block superBlock) {
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
            
            if(isInt(tk[3]))
            {
                //System.out.println(" MVI A,"+tk[3]);
                Comp.Runtime.out+=" MVI A,"+tk[3]+"\n";
            }
            else 
            {
                //System.out.println(" LDA "+((Statement)Comp.Runtime.symbltabl.get(tk[3])).getAddress());
                Comp.Runtime.out+=" LDA "+((Statement)Comp.Runtime.symbltabl.get(tk[3])).getAddress()+"\n";
            }
            
            //System.out.println(" MOV B, A ");
            Comp.Runtime.out+=" MOV B, A \n";
           
            if(isInt(tk[1]))
            {
                //System.out.println(" MVI A,"+tk[1]);
                Comp.Runtime.out+=" MVI A,"+tk[1]+"\n";
            }
            else 
            {
                //System.out.println(" LDA "+((Statement)Comp.Runtime.symbltabl.get(tk[1])).getAddress());
                Comp.Runtime.out+=" LDA "+((Statement)Comp.Runtime.symbltabl.get(tk[1])).getAddress()+"\n";
            }
            
            switch(tk[2])  
            {  
              case ">"://System.out.println(" SUB B");//a=a-b;
                       Comp.Runtime.out+=" SUB B \n";
                       //System.out.println(" JM "+label);
                        Comp.Runtime.out+=" JM "+label+" \n";
                 break;  
              case "<"://System.out.println(" SUB B");//a=a-b;
                        Comp.Runtime.out+=" SUB B \n";
                       //System.out.println(" JP "+label);
                       Comp.Runtime.out+=" JP "+label+" \n";
                 break;  
              case "="://System.out.println(" SUB B");//r=y-x;
                       Comp.Runtime.out+=" SUB B \n";
                       System.out.println(" JNZ "+label);
                       Comp.Runtime.out+=" JNZ "+label+" \n";
                 break;  

              default://r=0;  
            }
                        
             for (Block b : getSubBlocks()) {
                 //System.out.println(b+"its superis "+b.getSuperBlock());
			if (b instanceof Statement) {
				Statement st = (Statement) b;                                
                                Comp.Runtime.symbltabl.put(st.getName(),st);
                                //System.out.println(st.getAddress());
                                st.run();
			}
                        else if (b instanceof Expression) {
				Expression ex = (Expression) b;                                
                                Statement st=Comp.Runtime.symbltabl.get(ex.getName());
                                ex.setAddress(st.getAddress());
                                ex.run();
                                //Runtime.symbltabl.put(st.getName(),st);
                                //System.out.println(st.getAddress());
                                
			}
                        if (b instanceof If) {
				If ie = (If) b;
                                ie.run();
			}
                        if (b instanceof While) {
				While w = (While) b;
                                w.run();
			}
		}
             //System.out.println(" JNZ "+this.label);
        }
        else if(type.equals("ENDIF"))
        {//for endinf IF
            //System.out.println("GL"+Comp.Runtime.gl.pop()+": NOP");
            Comp.Runtime.out+="GL"+Comp.Runtime.gl.pop()+": NOP \n";
        }
    }
    
}
