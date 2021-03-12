package fr.univ_rennes1.bioinfo;

import java.util.ArrayList;

public class ClusterOfSequences {
	

	private ArrayList<ClusterOfSequences> subClusters;
	private ArrayList<Sequence> elements;
	
	public ClusterOfSequences(Sequence element) {
		// complete
	}
	
	public ClusterOfSequences(ArrayList<Sequence> eltList) {
		// complete
	}
	
	public ClusterOfSequences(ClusterOfSequences cluster1, ClusterOfSequences cluster2) {
		// complete
	}
	
	private String getNewickIntermediate() {
		String newick = "";
		// complete
		return newick;
	}
	
	public String getNewick() {
		return this.getNewickIntermediate() + ";";
	}
	
	public double linkage(ClusterOfSequences aCluster) {
		// complete
		return 0.;
	}
	
	public void clusterize() {
		// complete
	}

	public static void main(String[] args) {
		Sequence seq1 = new Sequence("ATTACG");
		Sequence seq2 = new Sequence("ATATCG");
		Sequence seq3 = new Sequence("ACCCCG");
		Sequence seq4 = new Sequence("GCCGAG");
		Sequence seq5 = new Sequence("TCCCCG");
		
		ArrayList<Sequence> listSeq = new ArrayList<Sequence>(5);
		listSeq.add(seq1);
		listSeq.add(seq2);
		listSeq.add(seq3);
		listSeq.add(seq4);
		listSeq.add(seq5);
		
		ClusterOfSequences bioCluster = new ClusterOfSequences(listSeq);
		System.out.println(bioCluster.getNewick());
		bioCluster.clusterize();
		System.out.println(bioCluster.getNewick());
	}

}
