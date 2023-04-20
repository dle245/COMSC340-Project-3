public class DynamicSequenceAlignment {

	static String[] m = {"A","A","C","A","G","T","T","A","C","C","-"};
	static String[] n = {"T","A","A","G","G","T","C","A","-"};
	
	public static void main(String[] args) throws Exception {
        int[][] example;
        example = optimalCost(0,0);
        for (int i = 0; i < m.length; i++) {
        	for (int j = 0; j < n.length; j++) {
        		System.out.print(example[i][j] + " ");
        	}
        	System.out.println();
        }
    }
	
	public static int[][] optimalCost(int i, int j) {
		int[][] matrix = new int[m.length+1][n.length+1];
		
		for(int k = m.length; k > 0; k--) {
			for (int l = n.length; l > 0; l--) {
				if (k == 10) {
					matrix[k][l] = equality2(i,j);
				}
				if (l == 8) {
					matrix[k][l] = equality3(i,j);
				}
				else {
					matrix[k][l] = equality1(i,j);
				}
			}
		}
		
		return matrix;
		
	}
	
	public static int equality1(int i, int j){
        int penalty = 0;
        i = m.length;
        j = n.length;
        int result = 0;
        while (i != m.length || j != n.length) {
        	int sum1 = (i + 1 + j + 1) + penalty;
        	int sum2 = i+1 + j + 2;
        	int sum3 = i + j +1 + 2;
        	result = result + min_cost(sum1, sum2, sum3);
        	i++;
        	j++;
        }
        return result;  
    }
	
	public static int equality2(int i, int j) {
		return 2*(m.length-j);
	}
	
	public static int equality3(int i, int j) {
		return 2*(n.length-i);
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
	
}
