package deduplication.sqf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
/**
 * 
 * all Voters holds all the voter objects and is able to carry out deduplication methods on the list of voters
 * @author Foqia Shahid
 *@version 7th May 2020
 */

public class allVoters {

	ArrayList<voter> allVoters = new ArrayList<voter>();
	
	
	/**
	 * Constructor that takes in file and reads all of it and makes a list of all voters
	 * @param csvfile file that will be read
	 */
	allVoters(String csvfile){
		String filePath = csvfile;
    	try {
	    	CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(filePath));
			ArrayList<String[]> voters = new ArrayList<String[]>(reader.readAll());
			reader.close();
			for(String[] row : voters) {
				voter newVoter = new voter(row); 
				allVoters.add(newVoter);
			}
    	}
    	catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Second constructor that reads a certain number of lines in given file and makes a list of all voters
	 * @param csvfile file to be read
	 * @param n number of rows to be read in file
	 */
	allVoters(String csvfile, int n){
    	String filePath = csvfile;
    	try {
	    	CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(filePath));
			//ArrayList<String[]> voters = new ArrayList<String[]>(reader.readAll());
			ArrayList<String[]> voters = new ArrayList<String[]>();
			for(int i = 0; i < n ; i++) {//String[] row : voters
				String [] line = reader.readNext();
				voters.add(line);
				voter newVoter = new voter(voters.get(i)); 
				allVoters.add(newVoter);
			}
			reader.close();
    	}
    	catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns size of allVoters arraylist
	 * @return int size of Arraylist of all voters
	 */
	public int size() {
		return allVoters.size();
	}
	
	/**
	 * All pairs deduplication method
	 * @return ArrayList of deduplicated list of voters
	 */
	public ArrayList<voter> allPairsDeduplication(){
		ArrayList<voter> newArray = new ArrayList<voter>();
			for(voter Voter : allVoters) {
				int duplicate = 0;
				if(newArray.size() == 0) {
					duplicate = 0;
				}
					for(voter i : newArray) {
						if(Voter.compareTo(i)== 0) {
							duplicate+=1;
						}
					}
					if(duplicate == 0) {
						newArray.add(Voter);
					}
			}
		//System.out.println(newArray.size());
		return newArray;
		
	}
	
	/**
	 * Hash table deduplication method
	 * @return ArrayList of deduplicated list of voters
	 */
	public ArrayList<voter> hashLinearDeduplication(){
			int N=1000003;
			int duplicates = 0;
			ArrayList<voter> newArray = new ArrayList<voter>();
			ProbeHashMap<String, voter> hashMap = new ProbeHashMap<String, voter>(N);
			for(voter Voter : allVoters) {	
				String vName = Voter.getName();
				if(hashMap.get(vName) == null) {
					hashMap.put(vName, Voter);
				}
				else {
					duplicates+=1;
				}
			}
			for(Entry<String, voter> entry : hashMap.entrySet()) {
				newArray.add(entry.getValue());
			}
			//System.out.println(newArray.size());
			return newArray;
			
	}
	/**
	 * Quick Sort deduplication method
	 * @return ArrayList of deduplicated list of voters
	 */
	public ArrayList<voter> quicksortDeduplication(){
		ArrayList<voter> newArray = new ArrayList<voter>();
		quicksort(0, allVoters.size()-1);
		if(newArray.size()== 0) {
			newArray.add(allVoters.get(0));
		}
		for(int i = 1; i < allVoters.size()-1; i++) {
			if((allVoters.get(i)).compareTo(allVoters.get(i+1)) != 0)
				newArray.add(allVoters.get(i));
		}
		newArray.add(allVoters.get(allVoters.size()-1));
		//System.out.println(allVoters.size());
		//System.out.println(allVoters);
		//System.out.println(newArray.size());
		return newArray;
	}
	
	
	/**
	 * Helper Method for quicksortDeduplication()
	 * sorting method that will sort the AllVoters into an arraylist
	 * @param low start of arraylist to be sorted
	 * @param high end of arraylist to be sorted
	 */
	public void quicksort(int low, int high){
		if(low >= high) {
			return;
		}
		int p = partition(low, high); // index where the pivot should go in the subarray
		quicksort(low, p-1);
		quicksort(p+1, high);
		
	}
	
	/**
	 * helper method for quicksort(low,high)
	 * @param low start of arraylist to be sorted
	 * @param high end of arraylist to be sorted
	 * @return int of index that is in the correct place in sorted list
	 */
	public int partition(int low, int high) {
		voter pivot = allVoters.get(low);
		while(low < high) {
		if( pivot.compareTo(allVoters.get(low)) == 0 ) { //pivot = A[low]
			if( pivot.compareTo(allVoters.get(high)) <= 0 ) { //pivot <= A[high]
				high = high -1;
				}
			else if( pivot.compareTo(allVoters.get(high)) > 0   ) {	//A[low] > A[high]
				swap(low, high);
			}
		}
		if( pivot.compareTo(allVoters.get(high)) == 0) { //pivot = A[high]
			if( (allVoters.get(low)).compareTo(pivot) <= 0 ) {  //A[low] <= pivot
				low = low +1;
			}
			else if( (allVoters.get(low)).compareTo(pivot) > 0 ) {
				swap(low,high);
			}
		}
		} 
		return low;
	}
	
	/**
	 * Helper Method for partition that swaps elements at index low and high
	 * @param low index that will be swapped
	 * @param high index that will be swapped
	 */
	public void swap(int low, int high) {
	Collections.swap(allVoters, low, high);	
	}
	
	
	/**
	 * Returns a string that has attributes that are used to compare voters and find duplicates
	 * @param csvfile file to be read
	 * @return a string that has attributes
	 */
	public String attributes(String csvfile) {
	String attributes;
	String [] allAttributes = null;
	try {
		CSVReader reader = new CSVReader(new FileReader(csvfile));
	    allAttributes = reader.readNext();
	    reader.close();
		}
	catch(FileNotFoundException e) {
		e.printStackTrace();
		}
	catch(IOException e) {
		e.printStackTrace();
		}
		attributes = allAttributes[3] +" " + allAttributes[4];
		return attributes;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(voter Voter : allVoters) {
			sb.append(Voter + ", ");
		}
		return sb.toString();
	}

}
