import java.util.Arrays;

public class AlignmentNW {
 private static String s1;
 private static String s2;
 private static  int scoreMatch = 5;
 private static int scoreMismatch = -4;
 private static int scoreIndel = -3;
 private int[][] alignmentMatrix;

 public AlignmentNW(String s1, String s2) {
	 this.s1 = s1;
	 this.s2 = s2;
	 alignmentMatrix = initMatrix(s1,s2);
	 alignmentMatrix = initGlobal(alignmentMatrix);
	 fillNW(alignmentMatrix,s1,s2);
 }

 public static int [][] initMatrix(String S,String T ) {
     int [][] matrix = new int [T.length()+2][S.length()+2];
     char[] S1 = S.toCharArray();
     char[] S2 = T.toCharArray();
     
     for(int i = 0; i< S1.length;i++) {
         matrix[0][i+2] =  S1[i];
                 }
     for(int i = 0; i< S2.length;i++) {
         matrix[i+2][0] =  S2[i];
                 }
     return matrix;
 }
 
 public static int [][] initGlobal(int [][] matrix){
     matrix[1][1] = 0;
     for(int i = 2; i <matrix[0].length;i++) {
         matrix[1][i] = matrix[1][i-1] + scoreIndel;
         //System.out.println("Test Matrix" + matrix[1][i]);
     }
     for(int i = 2; i < matrix.length;i++) {
         matrix[i][1] = matrix[i-1][1] + scoreIndel;
     }
     return matrix;
 }
 
 public static int max3(int a, int b, int c){
     return Math.max(a, Math.max(b, c));
 }
 
 public static int fillscore(char s, char t) {
     if (s == t)
         return scoreMatch;
     else
         return scoreMismatch;
 }
 

 
 public static int scoreMax() {
	 return scoreMatch*Math.min(s1.length(), s2.length());
 }
 
 public static int scoreMin() {
	 return scoreIndel*(s1.length()+s2.length());
 }
 
 public static double getDistance() {
	 int score = 0;
	 if (s1.equals(s2)) score = 1; else score = 0;
	 double top = (scoreMax()-score);
	 double bottom = (scoreMax()-scoreMin());
	 
	 return (top/bottom);
 }
 
 public static void fillNW (int [][] matrix, String S, String T) {
     //System.out.println(matrix.length + "AND " + matrix[0].length);
     for (int i=2; i< matrix.length; i++) { //I =Lines
         for (int j=2; j< matrix[0].length; j++) { // j = Columns
             int a = matrix[i-1][j] + scoreIndel; // To calculate from left to right
             //System.out.println("i = " + i);
             //System.out.println("j = " + j);
             int b = matrix[i][j-1] + scoreIndel; // To calculate from top down
             
             int s = fillscore(S.charAt(j-2),T.charAt(i-2));
             int c = matrix[i-1][j-1] + s;
             matrix[i][j] = max3(a,b,c);
         }
     }
    
 }
 

 
 public void printMatrix() {
	 for(int i = 0;i<alignmentMatrix.length;i++) {
		 System.out.println();
		 for(int j = 0;j<alignmentMatrix[0].length;j++) {
			 if (i==0 && j>=2) {
				 char ch = (char) alignmentMatrix[i][j];
				 System.out.print(ch);
				 if(j<alignmentMatrix[0].length-1)
				 System.out.print(",");
			 }
			 else if (j==0 && i>=2) {
				 char ch = (char) alignmentMatrix[i][j];
				 System.out.print(ch);
				 if(j<alignmentMatrix[0].length-1)
				 System.out.print(",");
			 }
			 else {
				 System.out.print(alignmentMatrix[i][j]);
				 if(j<alignmentMatrix[0].length-1)
					 System.out.print("," );
			 }
		 }
		 System.out.println("]");
	 }
	 
 }
 
 
 public static void main(String[] args) {

      String seq1 = "ATTACG";
      String seq2 = "ATATCG";
      String seq3 = "ACCCCG";
      String seq4 = "GGGGAA";
      String seq5 = "TTTACG";
	
      AlignmentNW test = new AlignmentNW(seq1,seq2);
      AlignmentNW test2 = new AlignmentNW(seq1,seq3);
      AlignmentNW test3 = new AlignmentNW(seq2,seq3);
      test.printMatrix();
      
      System.out.println(test.getDistance());
      System.out.println("#################### END OF ANW SEQ1SEQ2");
      test2.printMatrix();
      System.out.println(test2.getDistance());
      System.out.println("#################### END OF ANW SEQ1SEQ3");
      test3.printMatrix();
      System.out.println(test3.getDistance());
      System.out.println("#################### END OF ANW SEQ2SEQ3");
     // test.fillNW(test.alignmentMatrix, S, T);
     // test.printMatrix();
 }	
 
}