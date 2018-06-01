/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fyp;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 *
 * @author HP
 */
public class Fyp {

    /**
     * @param args the command line arguments
     */
    
    public static   HashMap<String, Object> lt = new HashMap<String, Object>();//lookup table
    public static Stack<Node> stk=new Stack<Node>();
    public void strucident(Node fth,Node cn,HashMap<String, Object> hm)//,Queue qu)//fth for father node ; cn for current node
    {
        Node son=null;
        
       /* for(int j=0;j<hsn.size();j++)
        {
            //Node n=(Node)qu.get(j);System.out.println(n.opcode+" "+n.operand);
           // q.remove();
            System.out.println(hsn.contains(cn));
            //System.out.println(hsn.remove(cn));
        }*/
        
        //Node n=(Node)qu.remove();
        //Node n=((Node)hm.get(n.address));
        if(cn.son!=(String)null){
        son=(Node)hm.get(cn.son);}
        System.out.println(cn.opcode+" "+cn.operand);//+" "+cn.son);
        //System.out.println(son.opcode+" "+son.operand);
       // strucident(cn,son,hm);
        if(cn==null)return;
        
        else  if(fth!=null&&(fth.tp.equalsIgnoreCase("Judgement"))&&(fth==null||(fth!=null&&(Integer.parseInt(cn.address)>Integer.parseInt(fth.address))))){//[11]
           System.out.println("Convrgnc");
            if(cn.traverse==false)//[12]
            {
                cn.traverse=true;
                Node temp=stk.pop();
                cn.directJudgmentNode=temp;
                temp.directJudgmentConvergence.add(cn);
                System.out.println("jo");
                //cn.code=temp.code;
            }
            else
            {
                Node temp=stk.pop();
                cn.directJudgmentNode=temp;
                temp.directJudgmentConvergence.add(cn);
                System.out.println("jo");
            }
            return;
        }
        
        
        else if((cn.tp.equalsIgnoreCase("Process"))){//[1]//&&(fth==null||(fth!=null&&!(fth.tp.equalsIgnoreCase("Judgement"))))){
            System.out.println("Process");
            if(cn.traverse==false)//[2]
            {
                cn.traverse=true;
                if(cn.son!=(String)null)
                strucident(cn,son,hm);//,qu);//[2.1]
            }
            else if(fth!=null&&fth.tp.equalsIgnoreCase("Judgement"))//[3]//&&fth.type.equals(null)&&(cn.type.equals(null)))
            {
                  fth.type="dowhile";
                  cn.doWhileCounter++;
                  fth.doWhileCounter=cn.doWhileCounter;
                  fth.doWhileNode=cn;
            }
       }
        
        
      else if((cn.tp.equalsIgnoreCase("Judgement"))){//[4]
           System.out.println("Judgmnt");
            if(cn.traverse==false)//[5]
            {
                cn.traverse=true;
                stk.push(cn);//push judgement node to stack
               
                //traverse for its sons[5.1]
                
                if(!((Node)hm.get(cn.operand)).equals((Node)hm.get(cn.son)))//sees if next addr and jump addr are not the same address
                {
                    stk.push(cn);
                    strucident(cn,(Node)hm.get(cn.operand),hm);
                }
                if(cn.son!=null)
                strucident(cn,son,hm);
                
                if(cn.type==null)//[6]
                {
                    cn.type="selection";
                }
                System.out.println("ji");
                for (Iterator<Node> iterator = cn.directJudgmentConvergence.iterator(); iterator.hasNext();) {//[5.2]
                    Node n = iterator.next();//to iterate allthe branches of judgement node
                    System.out.println("jo");
                    //cn=cn.directJudgmentConvergence;
                    if(n.son!=(String)null)
                    strucident(n,(Node)hm.get(n.son),hm);
                }
                
                
                
            }
            else{       //[8]    
                if(cn.type==null)//[9]
                {
                    cn.type="whilefor";
                }
                    
                else{//[10]
                    fth.type="dowhile";
                    cn.doWhileCounter++;
                    fth.doWhileCounter=cn.doWhileCounter;
                    fth.doWhileNode=cn;
                 }
                
            }
        }
      
      
       
       
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        HashMap<String, Object> hm = new HashMap<String, Object>();
        HashSet<Object> hs = new HashSet<Object>();
        Queue<Node> qu = new LinkedList<Node>();//isEmpty(), size(), add(E element), remove(), and element()
        //HashMap<String, Object> map = new DefaultedHashMap<String, Object>(new Type(0,"[NO ENTRY FOUND]"));
        lt.put("3A", new Type(2,"Process","LDA"));lt.put("47", new Type(0,"Process","MOV B,A"));lt.put("80", new Type(0,"Process","ADD B"));lt.put("32", new Type(2,"Process","STA"));
        lt.put("CF", new Type(0,"Process","RST 1"));
        lt.put("21", new Type(2,"Process","LXI H"));lt.put("3E", new Type(1,"Process","MVI A"));lt.put("06", new Type(1,"Process","MVI B"));lt.put("DA", new Type(2,"Judgement","JC"));
        lt.put("36", new Type(1,"Process","MVI M"));lt.put("C3", new Type(2,"Judgement","JMP"));
         lt.put("0E", new Type(1,"Process","MVI C")); lt.put("0D", new Type(0,"Process","DCR C"));lt.put("C2", new Type(2,"Judgement","JNZ"));
        //Type t=(Type)lt.get("3A");
         //System.out.println("3A- "+t.no);
        //System.out.println(lt.containsKey("FA"));
        //line l[]=new line[100];
        int i=0,j,sv=0,lno=0;//sv is for no of opcodes in a operand and lno to signify line no
        String nodeopc="",nodeopr="",nadd="",tp="",naddo=null;//nodeopc for opcode and nodeopr for operand ;nadd for address of the instruction 
        //tp for process;naddo for remembering the old address
        BufferedReader bfRdr = null;        
     String str;
        
        try 
        {
            bfRdr= new BufferedReader(new FileReader("iput2.txt"));
            str=bfRdr.readLine();
            while((str=bfRdr.readLine())!=null)  //
            {
                String prm[]=new String[2];  
                String ch=""+(char)13;//Ascii for enter is 13
                StringTokenizer stn1= new StringTokenizer(str,ch); 
                String str1= stn1.nextToken().trim();
                //System.out.println(str1);                
                if(!stn1.hasMoreTokens())
                {
                    StringTokenizer stn2= new StringTokenizer(str1," ");                    
                    for(j=0;j<2;j++)
                        prm[j]=stn2.nextToken().trim();
                        
                        if(sv==0){
                        nadd=prm[0];
                        Type t=(Type)lt.get(prm[1]);
                        sv=t.no;
                        tp=t.tp;
                        nodeopc=t.v.toString();
                        nodeopr="";
                       // System.out.println("typr no-"+prm[1]);
                        }
                        else
                        {
                            nodeopr=prm[1]+nodeopr;
                            sv--;
                        }
                                   
                }                
                 // l[i++]=new line(prm[0],prm[1]);
                
		//System.out.println(" "+l[i-1].addr+ " " + l[i-1].code);
                if(sv==0)  {
                        //System.out.println("Input:-"+ hm.put(lno++,node));
                    //hm.put(nadd,new Node(nadd,nodeopc,nodeopr,null,0,null));
                    if(naddo!=null){
                        ((Node)hm.get(naddo)).son=nadd;
                    }
                    Node n=new Node(nadd,naddo,nodeopc,nodeopr,tp,null,0,null,null,new Stack<Node>(),false);
                    hm.put(nadd,n);
                    hs.add(n);
                    qu.add(n);
                    naddo=nadd;
                    //Node n=(Node)hm.get(nadd);
                   // System.out.println(n.opcode+" "+n.operand);
                    //System.out.println(n.opcode);
                    
                }
                            
            }
        bfRdr.close();
        //System.out.println(nadd);
        ((Node)hm.get(nadd)).son=null;
        }catch (FileNotFoundException e) {
			 System.out.println("Error opening the input file!" + e.getMessage());
			 System.exit(0);
	}catch ( IOException e) {
			 System.out.println(" IO Error!" + e.getMessage());
			 e.printStackTrace();
			 System.exit(0);			 		
	}
        
        
        
            //Node n = entry.getValue();}
       /* for(j=0;j<qu.size();j++)
        {
            Node n=(Node)qu.get(j);System.out.println(n.opcode+" "+n.operand);
        }*/
        Queue q=new LinkedList(qu);
       // HashSet<Object> hsn = new HashSet<Object>(hs);
        
        new Fyp().strucident(null,(Node)hm.get("2000"),hm);//,q);
        System.out.println("Output Structure:-");
        /*for (Map.Entry<String, Object> entry : hm.entrySet()) {
            String key = entry.getKey();System.out.println(((Node)hm.get(key)).opcode+" "+((Node)hm.get(key)).operand+" "+((Node)hm.get(key)).type);}
      */ Node n=(Node)hm.get("2000");
        while(n!=null)
        {
           System.out.print(n.opcode+" "+n.operand+" "+n.type); 
            for (Iterator<Node> iterator = n.directJudgmentConvergence.iterator(); iterator.hasNext();) {
                    Node no = iterator.next();//to iterate allthe branches of judgement node
                    System.out.print(" "+no.address);
                }
            if(n.doWhileNode!=null)System.out.print(" dowhile"+(n.doWhileNode).address);
            System.out.println();
           n=((Node)hm.get(n.son));
        }
    }
    
   
}
