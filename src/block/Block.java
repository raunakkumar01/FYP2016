/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package block;

import java.util.ArrayList;
import java.util.Collections;
import Comp.Runtime;
/**
 *
 * @author HP
 */
public class Block {
    private Block superBlock;

    private ArrayList<Block> subBlocks;
	
	public Block(Block superBlock) {
		this.superBlock = superBlock;
		this.subBlocks = new ArrayList<>();
	}
	
	public Block getSuperBlock() {
		return superBlock;
	}
	
	public ArrayList<Block> getBlockTree() {/* i.e we nee to get the block tree
													  class
													  /   \
												methd1     methd2
												/    \        /  \
											var(1)  var(2) var(2) var(3)  */
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		Block block = this;
		
		do {//adds all the blocks above one to the arraylist block  (ex for var(1) => [var(1),methd1,class])
			blocks.add(block);
			block = block.getSuperBlock();
		} while (block != null);
		
		Collections.reverse(blocks);//reverse block array
		
		return blocks;
	}
	
	public Block[] getSubBlocks() {
		return subBlocks.toArray(new Block[subBlocks.size()]);//return all sublocks after changing it to an array
	}
	
	public void addBlock(Block block) {
		subBlocks.add(block);
	}
	//if a class has the main method then it needs to be found and run it ||  if statement checks the condition and runs accordingly the statement  || similarly  for loop
    //we make it abstract class 	
	/*public Variable getVariable(String name) {
		// Check the superblocks first.
		
		for (Variable v : variables) {
			if (v.getName().equals(name)) {
				return v;
			}
		}
		
		return null;
	}
	
	public void addVariable(Variable v) {
		variables.add(v);
	}
	*/
	public  void run()
        {
            
               for (Block b : getSubBlocks()) {
                   //System.out.println(b+"its superis "+b.getSuperBlock());
			if (b instanceof Statement) {
				Statement st = (Statement) b;
                                
                                Runtime.symbltabl.put(st.getName(),st);
                                //System.out.println(st.getAddress());
                                st.run();
			}
                        else if (b instanceof Expression) {
				Expression ex = (Expression) b;
                                
                                Statement st=Runtime.symbltabl.get(ex.getName());
                                ex.setAddress(st.getAddress());
                                ex.run();
                                //Runtime.symbltabl.put(st.getName(),st);
                                //System.out.println(st.getAddress());
                                
			}
                        else if (b instanceof If) {
				If ie = (If) b;
                                ie.run();
			}
                        else if (b instanceof While) {
				While w = (While) b;
                                w.run();
			}
		}
    
        }
}