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

    import java.io.*;
import java.util.*;

/**
 *
 * @author HP
 */
public class ParseE {

    //static StringTokenizer st;
    static QueueTokens st=null;
    static String curr=null,sym=null;

    /** read the next token into curr */
    static void next() {
	try {
	    //curr=st.nextToken().intern();
            curr=st.pop().trim();
            //System.out.println(curr);
	    // use of intern() allows us to check equality with ==.
	} catch( NoSuchElementException e) {
	    curr=null;
	}
    }

    static void error(String msg) {
	System.err.println(msg);
	System.exit(-1);
    }

    static int parseE() {
	// E -> T E1
        //System.out.println("E");
	int x=parseT();
	return parseE1(x);
    }

    static int parseE1(int x) {
	// E1 -> + T E1 | - T E1 | epsilon
        //System.out.println("E1");
	if (curr.equalsIgnoreCase("+")||curr.equalsIgnoreCase("-")) {
            sym=curr;
	    next();
	    int y = parseT();
            if (sym.equalsIgnoreCase("+"))
	    return parseE1(x+y);
            else return parseE1(x-y);
	} else if(curr.equalsIgnoreCase(")") || curr.equalsIgnoreCase("$" )) {
	    return x;
	} else {
	    error("Unexpected :"+curr);
	    return x; // to make compiler happy
	}
    }

    static int parseT() {
	// T -> F T1
        //System.out.println("T");
	int x=parseF();
	return parseT1(x);
    }

    static int parseT1(int x) {
	// T1 -> * F T1 | / F T1 | epsilon
       // System.out.println("T1");
	if (curr.equalsIgnoreCase("*") || curr.equalsIgnoreCase("/")) {
            sym=curr;
	    next();
	    int y=parseF();
            if(sym.equalsIgnoreCase("*"))
	    return parseT1(x*y);
            else return parseT1(x/y);
                
	} else if(curr.equalsIgnoreCase("+") || curr.equalsIgnoreCase(")") || curr.equalsIgnoreCase("$") || curr.equalsIgnoreCase("-")) {
	    return x;
	} else {
	    error("Unexpected :"+curr);
	    return x; // to make compiler happy
	}
    }
    
    static int parseF() {
	// F -> ( E ) | a
        //System.out.println("F");
       
	if( curr.equalsIgnoreCase("(")) {
	    next();
	    int x=parseE();
	    if(curr.equalsIgnoreCase(")") ){
		next();
		return x;
	    } else {
		error (") expected.");
		return -1; // to make compiler happy
	    }
	} else try {
	    //int x=Integer.valueOf(curr).intValue();
            int x=Integer.parseInt(curr);
	    next();
	    return x;
	} catch(NumberFormatException e) {
	    error("Number expected.");
	    return -1; // to make compiler happy
	}
    }

   /* public static void main(String args []) throws IOException {
	BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
	String line=in.readLine();
	//st = new StringTokenizer(line+" $");
	next();
	int x=parseE();
	if(curr=="$") {
	    System.out.println("OK "+x);
	} else {
	    error("End expected");
	} 
    }*/
}
    
