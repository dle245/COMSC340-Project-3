
public class DynamicSequenceAlignment {

	static String[] x = {"A","A","C","A","G","T","T","A","C","C"}; 
	static String[] y = {"T","A","A","G","G","T","C","A"};
	static int m = x.length;
	static int n = y.length;
	
	public static void main(String[] args) throws Exception { //Main method
        int[][] example;
        example = optimalCost();
        for (int i = 0; i < m+1; i++) { //Prints out the result
        	for (int j = 0; j < n+1; j++) {
        		System.out.print(example[i][j] + " ");
        	}
        	System.out.println();
        }
        System.out.println();
        String[][] answer = new String[10][10];
        answer = sequenceAlignment(example);
        for (int i = 0; i < m; i++) { //Prints out the result
        	System.out.println(answer[i][0]);
        }
        System.out.println();
        for (int i = 0; i < m; i++) { //Prints out the result
        	System.out.println(answer[i][1]);
        }
    }
	
	public static int[][] optimalCost() {
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
		return 2*(n-j);
	}
	
	public static int checkBottomRow(int i, int j) { //Checks the bottom most row
		return 2*(m-i);
	}
	
	public static int checkElse(int i, int j, int[][] matrix){ //Checks everything else
		int penalty;
		if (x[i]==y[j]) {
			penalty = 0;
		}
		else {
			penalty = 1;
		}
        int sum1 = matrix[i+1][j+1] + penalty;
    	int sum2 = matrix[i][j+1] + 2;
    	int sum3 = matrix[i+1][j] + 2;
        return min_cost(sum1, sum2, sum3);  
    }
	
	static int min_cost(int a, int b, int c){ //Exists because Math.min() doesn't compare 3 variables
        if (a<=b & a<=c){
            return a;
        } else if (b<=a && b<=c){
            return b;
        } else {
            return c;
        }

    }
	
	static String[][] sequenceAlignment (int[][] matrix) {
		String[][] result = new String[11][2];
		for (int i = 0; i <= m-1; i++) {
			int j = 0;
			result[0][0] = x[i];
			result[0][1] = y[j];
			int penalty;
			if (x[i]==y[j]) {
				penalty = 0;
			}
			else {
				penalty = 1;
			}
	        int sum1 = matrix[i+1][j+1] + penalty;
	    	int sum2 = matrix[i][j+1] + 2;
	    	int sum3 = matrix[i+1][j] + 2;
	    	
	        if (sum1 == matrix[i][j]) {
	        	result[j+1][1] = "-";
	        	j++;
	        }
	        else if (sum2 == matrix[i][j]) {
	        	result[j+1][0] = "-";
	        	j++;
	        }
	        else {
	        	result[j+1][1] = y[j+1];
	        }
	        j++;
		}
		return result;
	}
	
}
