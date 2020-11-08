package deduplication.sqf;

import java.util.ArrayList;

/**
 * Main method that takes in fileName form commandline and carries out deduplication methods
 * @author Foqia Shahid
 * @version 7th May 2020
 */
public class Main {

    public static void main(String[] args) {

    	allVoters all = new allVoters(args[0]);
    	//System.out.println("The whole array: " + all);
    	//System.out.println("Size of array: " + all.size());
    	ArrayList<voter> allPairs = all.allPairsDeduplication();
    	all.hashLinearDeduplication();
    	all.quicksortDeduplication();
  
    	System.out.println("Records given:" + all.size());
    	System.out.println("Attributes checked: " + all.attributes(args[0]));
    	System.out.println("Duplicates found:" + (all.size() -allPairs.size()));
  
    }
}
