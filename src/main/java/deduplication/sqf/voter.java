package deduplication.sqf;

/**
 * Defines the voter object
 * @author Foqia Shahid
 * @version 7th May 2020
 *
 */

public class voter implements Comparable<voter> {
	
	private String fname;
	private String lname;
	private int attfName = 4; //Index in csv file
	private int attlName = 3; //Index in csv file
	
	/**
	 * Constructor to form voter based on first and last name
	 * @param newVoter takes in information from csv file about a single voter
	 */
	voter(String [] newVoter){
		fname = newVoter[attfName];
		lname = newVoter[attlName];
	}
	
	/**
	 * getter for Name
	 * @return String name
	 */
	public String getName() {
		return fname+lname;
	}
	
	
	@Override 
	public int compareTo(voter otherVoter) {
		int x = -2;
		if(this.lname.compareTo(otherVoter.lname) == 0 ) {
			if(this.fname.compareTo(otherVoter.fname) == 0)
				 x = 0; //last name and first name is same
			else if(this.fname.compareTo(otherVoter.fname) > 0) 
				return 	1;
			else if(this.fname.compareTo(otherVoter.fname) < 0) 
				return -1;	
			}
		else if(this.lname.compareTo(otherVoter.lname) > 0){
			x= 1; //this.lame >=other.lame
		}
		else if(this.lname.compareTo(otherVoter.lname) < 0) {
			x= -1; //this.last name < otherVoter last name
		}
		return x;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(fname + " " + lname);
		return sb.toString();
	}
	

}
