public class App {
    
    public static String[] seq1 = {"A","A","C","A","G","T","T","A","C","C"};
    public static String[] seq2 = {"T","A","A","G","G","T","C","A"};
    public static void main(String[] args) throws Exception {
        int optimal_result = opt(0,0);
        System.out.println(optimal_result);
    }

    public static int opt(int i, int j){
        int penalty = 0;
        if (i == seq1.length){
            return 2 * ((seq2.length - j));
        } else if (j == seq2.length){
            return 2 * ((seq1.length - i));
        } else {
            if (seq1[i].equals(seq1[j])){
                penalty = 0;
            } else {
                penalty = 1;
            }
            return min_cost((opt(i + 1, j + 1) + penalty), (opt(i+1, j) + 2), (opt(i, j+1) + 2));  
        }
    }
    public static int min_cost(int a, int b, int c){
        if (a<=b & a<=c){
            return a;
        } else if (b<=a && b<=c){
            return b;
        } else {
            return c;
        }

    }

}
