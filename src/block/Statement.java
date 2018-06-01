/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package block;
import Comp.Runtime;
import expevl.Exp2Post;
/**
 *
 * @author HP
 */
public class Statement extends Block{
    	private String name, value,address;
        
	public Statement(Block superBlock, String name,String value) {
		super(superBlock);
		this.name = name;
                this.value=value;
                this.address=null;
	}

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getAddress() {
        return address;
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

        if(isInt(value))
        {
            //System.out.println(" MVI A, "+value);
            Comp.Runtime.out+=" MVI A, "+value+" \n";
            
            
            this.address=""+Runtime.getvarbadd();
            //System.out.println(this.address);
            
            //System.out.println(" STA "+this.address);
            Comp.Runtime.out+=" STA "+this.address+" \n";
        }
        else
        {//for evaluating expression
            Exp2Post theTrans = new Exp2Post(value);
            theTrans.doTrans();
            this.address=""+Runtime.getvarbadd();
            //System.out.println(this.address);
            
            //System.out.println(" STA "+this.address);
            Comp.Runtime.out+=" STA "+this.address+" \n";
        }
    }
    public void retrieve()
    {


            //System.out.println(" MVI A, "+value);
            Comp.Runtime.out+=" MVI A, "+value+" \n";
            
            this.address=""+Runtime.getvarbadd();
            
            //System.out.println(" STA "+this.address);
            Comp.Runtime.out+=" STA "+this.address+" \n";


    }
    
}
