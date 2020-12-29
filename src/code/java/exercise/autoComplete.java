package code.java.exercise;

//------------------import packages--

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Scanner; 

//------------Java class -------------

public class autoComplete {
	
	public static void main(String[] args) {
		
		System.out.print("+-------------------------------------------------+\n");
		System.out.print("+   Hi! Welcome in the Java Autocomplete Tool     +\n");
		System.out.print("+-------------------------------------------------+\n");
		System.out.print("This program displays top 4 suggested keywords, sorted alphabetically; it is not case sensitive.\n"
				+ "Type a String and Press enter to submit.\nType ':q' to quit the program.\n");
		
	    String file =  args[0]; //path to file containing the list of keywords: argument given through the command line when running the program
		
		System.out.print("\n> Enter a string: \n");
        Scanner scan = new Scanner(System.in);// Using Scanner for Getting Input from User prompt box
        
        while (scan.hasNextLine()) {
	        	
		        String in = scan.nextLine();//store the user input from prompt box, in a string variable 'in'
		        
			        if (in.equals(":q")) {//exit the program if the user type ':q' string in the prompt box
			        
			    		System.out.print("\n+------------------------+\n");
			    		System.out.print("+  Thank you, bye (^-^)  +\n");
			    		System.out.print("+------------------------+\n");
			        	scan.close();//close the scanner
			        	System.exit(0);//stops the program
			        }
			        else {
				    	       if (in.equals("")) 
				    	       { //message if user types an empty string
				    	    	   System.out.print("(You typed an empty string. Please type a non-empty string.)\n");
				    	       }
				    	       else 
				    	       { //handle the case when user types a non-empty string, here the variable 'in'
				    	    	    
					        	System.out.print("\nyou typed: '" + in + "'\n\n");
					        	
									try (Stream<String> stream = Files.lines(Paths.get(file))) {
							
										List<String> result = stream.map(x -> x.toString())
												.filter(f -> f.startsWith(in.toLowerCase()))  //apply a filter, to match any keywords starting with user input string 'in'; convert input to Lower case to allow non-sensitive matching
												.sorted()  									  //sort the keywords alphabetically
												.limit(4)  									  //select the top 4 results
												.collect(Collectors.toList());                //store the results in a list
							
											if(result.size()==0) 
											{//output if no matches found 
												System.out.print("|-> no macthes found.\n");
											}
											else 
											{//output if matches found 
												System.out.print("|-> suggested keywords:\n");
												result.forEach(x -> System.out.print(x + "\n"));
											}
							
									} catch (IOException e) {//handle errors, e.g if keywords file has not been found 
										e.printStackTrace();
										System.exit(0);//stops the program if any error is occurred
									}
				    	       }//--end if then else statements
				    	       
			        }//--end if then else statements
			        
		        System.out.println("\n> Enter a string: \n");
		        
        }//--end while loop
        
	}//--end of main method
	
}//--end of program



