
public class Sequence {
	
	protected String seq;
	
	public Sequence() {
	    this.seq = "";
	  }
	  
	  public Sequence(String s) {
		this.seq = s;
	  }
	  
	  public Sequence (Sequence s) {
		this.seq = s.seq;
	  }
	  
	  public String toString() {
		  return this.seq;
	  }
	  
	  public double distance(Sequence otherSeq) {
		  double dist = 0.;
		  int lMin = Math.min(seq.length(), otherSeq.seq.length()); // length of the shortest of the two sequences (current object + otherSeq)
		  int lMax = Math.max(seq.length(), otherSeq.seq.length()); // length of the longest of the two sequences (current object + otherSeq
		  int i = 0;
		  while(i<lMin) {
			  if (seq.charAt(i)!=otherSeq.seq.charAt(i)) {
				  dist = dist+1;
				  
			  }
			  i++;
		  }
		  for (int j = i;j<lMax;j++) {
			  dist = dist+1;
		  }
		  return (dist + lMax - lMin)/lMax;
	  }

	public static void main(String[] args) {
		Sequence seq1 = new Sequence("ATTACG");
		Sequence seq2 = new Sequence("ATATCG");
		Sequence seq3 = new Sequence("ACCCCG");
		Sequence seq4 = new Sequence("GGGGAA");
		Sequence seq5 = new Sequence("TCCCCG");
	    
		System.out.println("dist(seq1,seq1): " + seq1.distance(seq1));
	    System.out.println("dist(seq1,seq2): " + seq1.distance(seq2));
	    System.out.println("dist(seq2,seq1): " + seq2.distance(seq1));
	    System.out.println("dist(seq1,seq3): " + seq1.distance(seq3));
	    System.out.println("dist(seq1,seq4): " + seq1.distance(seq4));
	    System.out.println("dist(seq1,seq5): " + seq1.distance(seq5));
	    System.out.println("dist(seq2,seq3): " + seq2.distance(seq3));
	    System.out.println("dist(seq2,seq4): " + seq2.distance(seq4));
	    System.out.println("dist(seq2,seq5): " + seq2.distance(seq5));
	    System.out.println("dist(seq3,seq4): " + seq3.distance(seq4));
	    System.out.println("dist(seq3,seq5): " + seq3.distance(seq5));
	    System.out.println("dist(seq4,seq5): " + seq4.distance(seq5));
	}

}
