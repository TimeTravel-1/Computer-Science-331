import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class A3Q6 {
	
	 public static void main (String [] args) {
		 //Accept command line input
		 //Note that m is entered before n
		 int m = Integer.parseInt(args[0]);
		 int n = Integer.parseInt(args[1]);
		 
		 int maxHeight = 0;
		 int minHeight = 0;
		 double totalHeight = 0;
		 
		 //Repeat for m number of bineary search trees
		 for (int i = 0; i < m; i++)
		 { 
			
			 BSTMap <Integer, String> tree = new BSTMap <Integer, String> ();
			 
			 int random;
			 int count = 0;
			 
			 //Loop until n number of distinct random integers are inserted into tree
			 while (count < n)
			 {
				 
				 //Randomly generate integers
				 Random generator = new Random();
				 random = generator.nextInt();
			 
				 //Try inserting into tree
				 try
				 {
					 tree.insert(random, "");
					 count ++;
				 }
				 //If key already in tree, try again without incrementing count
				 catch (KeyFoundException k)
				 {
				 }
			 }
			 
			 //Obtain the height of the tree
			 int heightTree = tree.findHeight();
			 
			 //Set max height
			 if (heightTree > maxHeight)
			 {
				 maxHeight = heightTree;
			 }
			 //Set min height
			 if ((heightTree < minHeight) || (minHeight == 0))
			 {
				 minHeight = heightTree;
			 }
			 
			 //Keep track of total height of all m trees
			 totalHeight += heightTree;
			 
			 
		 }
		 
		 //Find average height
		 double aveHeight = totalHeight/m;
		 
		 //Print out information
		 System.out.println();
		 System.out.println("1. Minimum height is \t\t\t\t\t\t" + minHeight);
		 System.out.println("2. Maximum height is \t\t\t\t\t\t" + maxHeight);
		 System.out.println("3. Average height is \t\t\t\t\t\t" + aveHeight);
		 System.out.println("4. Upper Bound on Expected Height from Average Case Analysis\t" + 3*Math.log(n)/Math.log(2));
		 System.out.println("5. Worst Case Upper Bound on Maximum Height of Red Black Tree\t" + 2*Math.log(n+1)/Math.log(2));
		 
		 
		 
 }

}
