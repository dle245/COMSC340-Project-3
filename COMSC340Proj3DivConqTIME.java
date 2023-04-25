public class COMSC340Proj3DivConqTIME {
    
    public static String[] seq1 = {"A","A","C","A","G","T","T","A","C","C"};
    public static String[] seq2 = {"T","A","A","G","G","T","C","A"};
    public static void main(String[] args) throws Exception {
        long optimal_result = opt(0,0);
        System.out.println(optimal_result);
    }

    public static long opt(int i, int j){
    long startTime = System.nanoTime();
        int penalty = 0;
        int x;
        if (i == seq1.length){
            x = 2 * ((seq2.length - j));
        } else if (j == seq2.length){
            x = 2 * ((seq1.length - i));
        } else {
            if (seq1[i].equals(seq2[j])){
                penalty = 0;
            } else {
                penalty = 1;
            }
            x = min_cost(((int)(opt(i + 1, j + 1)) + penalty), ((int)(opt(i+1, j)) + 2), ((int)(opt(i, j+1)) + 2));  
            }
    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    return duration;

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