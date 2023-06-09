import java.util.Arrays;
public class DynamicSequenceAlignmentCounter {
static String[] x = {"A","A","C","A","G","T","T","A","C","C"}; 
	static String[] y = {"T","A","A","G","G","T","C","A"};
	static int m = x.length;
	static int n = y.length;
	static String[] topAlignment = new String[m];
	static String[] bottomAlignment = new String[m];
   static int count = 0;
	
	public static void main(String[] args) throws Exception { //Main method
        int[][] optimalCostArray;
        optimalCostArray = optimalCost();
        for (int i = 0; i < m+1; i++) { //Prints out the minimum cost matrix
        	for (int j = 0; j < n+1; j++) {
        		System.out.print(optimalCostArray[i][j] + " ");
        	}
        	System.out.println();
        }
        System.out.println();
        sequenceAlignment(optimalCostArray); //Assembling and printing out the alignment sequence
        System.out.println(Arrays.toString(topAlignment));
        System.out.println(Arrays.toString(bottomAlignment));
        System.out.println(count + " Basic Operations");
        
    }
    
	
	public static int[][] optimalCost() { //Generates the minimal cost matrix
		int[][] matrix = new int[m+1][n+1];
		
		for(int i = m; i >= 0; i--) {
			for (int j = n; j >= 0; j--) {
				if (i == m) {
					matrix[i][j] = checkRightColumn(i,j);
				}
				else if (j == n) {
					matrix[i][j] = checkBottomRow(i,j);
				}
				else {
					matrix[i][j] = checkElse(i,j, matrix);
				}
			}
		}
		
		return matrix;
		
	}
	
	public static int checkRightColumn(int i, int j) { //Checks the right most column
		count++;
      return 2*(n-j);
	}
	
	public static int checkBottomRow(int i, int j) { //Checks the bottom most row
		count++;
      return 2*(m-i);
	}
	
	public static int checkElse(int i, int j, int[][] matrix){ //Checks everything else
		count++;
      int penalty;
		if (x[i]==y[j]) {
			penalty = 0;
         count++;
		}
		else {
			penalty = 1;
         count += 2;
		}
        int sum1 = matrix[i+1][j+1] + penalty;
    	int sum2 = matrix[i][j+1] + 2;
    	int sum3 = matrix[i+1][j] + 2;
      count +=3;
    	
        return min_cost(sum1, sum2, sum3);  
    }
	
	static int min_cost(int a, int b, int c){ //Exists because Math.min() doesn't compare 3 variables
        count++;
        if (a<=b & a<=c){
            return a;
        } else if (b<=a && b<=c){
            return b;
        } else {
            return c;
        }

    }
	
	static void sequenceAlignment (int[][] matrix) { //Assembles the alignment sequence
		long startTime = System.nanoTime();
      for (int i = 0; i <= m-1; i++) {
			topAlignment[i] = x[i]; 
			for (int j = 0; j <= n-1; j++) {
				
				int penalty;
				if (x[i]==y[j]) {
					penalty = 0;
				}
				else {
					penalty = 1;
				}
				
				int sum1 = matrix[i][j+1] + 2;
				int sum2 = matrix[i+1][j] + 2;
				int sum3 = matrix[i+1][j+1] + penalty;
				count += 3;
				if (sum1 == matrix[i][j]) {
					bottomAlignment[i] = "-";
				}
				else if (sum2 == matrix[i][j]) {
					bottomAlignment[i] = "-";
				}
				else {
					bottomAlignment[j] = y[j];
				}
				count++;
			}
		}
      long endTime = System.nanoTime();
   long total = endTime - startTime;
   System.out.println(total + " ns");

	}
}
