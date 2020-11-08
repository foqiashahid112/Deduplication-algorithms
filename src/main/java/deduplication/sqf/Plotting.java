package deduplication.sqf;

import java.util.ArrayList;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.LinePlot;
/**
 * Used for plotting graphs for algorithms
 * @author Foqia Shahid
 * @version 7th May 2020
 */
public class Plotting {
	
	public static void main(String[] args) {
		/**
		 * Following is code for Pre-Lab that tests runtime for function fib(n)
				int range = 35;
				double[] xvals = new double[range];//use arraylist instead
				double[] yvals = new double[range];
				for(int n = 0; n <range; n++) {
					xvals[n] = n;
				double start = 0.001 * System.currentTimeMillis();
				double result = fib(n);
				double end = 0.001* System.currentTimeMillis();
				double timeSec = end-start;
				yvals[n] = timeSec;
				System.out.println(timeSec);
				}
				DoubleColumn column1 = DoubleColumn.create("Fibonacci number, n", xvals);
				DoubleColumn column2 = DoubleColumn.create("Runtime (in seconds)", yvals);

				Table table = Table.create("for plot");
				table.addColumns(column1, column2);
				Plot.show(LinePlot.create("Runtime Graph", table, "Fibonacci number, n", "Runtime (in seconds)"));
		**/
		
		
		String fileName = "vote_files/SWVF_1_22_short.txt";
		int fileSize = 10000;
		int alg = 3; //number of times you want to graph = num of algorithms to test
		int range = alg*fileSize;
		ArrayList<Double> xvals = new ArrayList<Double>(range);
		ArrayList<Double> yvals = new ArrayList<Double>(range);
		ArrayList<String> categories = new ArrayList<String>(range);
		
		for(int n = 0; n <range/alg; n+=200) {
			xvals.add((double) n);
			double start = 0.001 * System.currentTimeMillis();
			allPairsdeduplication(n,fileName);
			double end = 0.001* System.currentTimeMillis();
			double timeSec = end-start;
			yvals.add(timeSec);
			categories.add("All Pairs Deduplication");
		}
		
		for(int n = 0; n <range/alg; n+=200) {
			xvals.add((double) n);
			double start = 0.001 * System.currentTimeMillis();
			hashdeduplication(n, fileName);
			double end = 0.001* System.currentTimeMillis();
			double timeSec = end-start;
			yvals.add(timeSec);
			categories.add("Hash Table Deduplication");
			}
		
		for(int n = 1; n <range/alg; n+=200) {
			xvals.add((double) n);
			double start = 0.001 * System.currentTimeMillis();
			sortdeduplication(n, fileName);
			double end = 0.001* System.currentTimeMillis();
			double timeSec = end-start;
			yvals.add(timeSec);
			categories.add("Quick-sort Deduplication");
			}
		
		DoubleColumn column1 = DoubleColumn.create("number of rows deduplicated, n", xvals);
		DoubleColumn column2 = DoubleColumn.create("Runtime (in seconds)", yvals);
		StringColumn catcolumn = StringColumn.create("algorithm", categories);
		Table table = Table.create("for plot");
		table.addColumns(column1, column2,catcolumn);
		Plot.show(LinePlot.create("Runtime Graph", table, "number of rows deduplicated, n", "Runtime (in seconds)", "algorithm"));

	}
	
	/**
	 * Pre-Lab function
	 * @param n
	 * @return
	 */
	private static int fib(int n) {
		if(n == 0 || n ==1) {
			return 1;
		}
		n = fib(n-1)+fib(n-2);
		return n;
	}
	
	/**
	 * calls all Pairs deduplication
	 * @param n number of rows in csv file to be read and deduplicated
	 * @param file file-Name
	 */
	public static void allPairsdeduplication(int n, String file) {
			allVoters all = new allVoters(file, n);
			all.allPairsDeduplication();
	}
	
	/**
	 * calls Hash table deduplication
	 * @param n number of rows in csv file to be read and deduplicated
	 * @param file file-Name
	 */
	public static void hashdeduplication(int n, String file) {
		allVoters all = new allVoters(file, n);
		all.hashLinearDeduplication();
	}
	
	/**
	 * calls QuickSort deduplication
	 * @param n number of rows in csv file to be read and deduplicated
	 * @param file file-Name
	 */
	public static void sortdeduplication(int n, String file) {
		allVoters all = new allVoters(file, n);
		all.quicksortDeduplication();
	}

}
