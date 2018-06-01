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
public class Node {
    String address;
    String son;
    String opcode;
    String operand;
    String tp;
    String type;
    int doWhileCounter;
    Node doWhileNode;
    Node directJudgmentNode;
    Stack<Node> directJudgmentConvergence;
    boolean traverse;
    
    public Node()
    {
        address=null;
        son=null;
        opcode=null;
        operand=null;
        tp=null;
        type=null;
        doWhileCounter=0;
        doWhileNode=null;
        directJudgmentNode=null;
        directJudgmentConvergence=new Stack<Node>();
        traverse=false;
    }
    
     public Node(String add,String son,String opcode,String operand,String tp,String t,int c,Node n,Node jn,Stack<Node> jcn,boolean tr)
    {
        this.address=add;
        this.son=son;
        this.opcode=opcode;
        this.operand=operand;
        this.tp=tp;
        this.type=t;
        this.doWhileCounter=c;
        this.doWhileNode=n;
        this.directJudgmentNode=jn;
        this.directJudgmentConvergence=jcn;
        this.traverse=tr;
    }
    
}
