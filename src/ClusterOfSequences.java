import java.util.ArrayList;

public class ClusterOfSequences {
	

	private ArrayList<ClusterOfSequences> subClusters = new ArrayList<ClusterOfSequences>();
	private ArrayList<Sequence> elements = new ArrayList<Sequence>();
	
	public ClusterOfSequences(Sequence element) {
		elements.add(element);
	}
	
	public ClusterOfSequences(ArrayList<Sequence> eltList) {
		elements.addAll(eltList);
		for(int i = 0; i<eltList.size();i++) {
			ClusterOfSequences temp = new ClusterOfSequences(eltList.get(i));
			subClusters.add(temp);
		}
	}
	
	public ClusterOfSequences(ClusterOfSequences cluster1, ClusterOfSequences cluster2) {
		elements.addAll(cluster1.elements);
		elements.addAll(cluster2.elements);
		subClusters.add(cluster1);
		subClusters.add(cluster2);
		
	}

	private String getNewickIntermediate() {
		String newick = "";
		if (elements.size()==1) {
			newick = newick + elements.get(0);
		}
		else {
			newick = newick + "(";
			for(int i = 0;i<subClusters.size();i++) {	
				if (subClusters.get(i).elements.size()>1) {
					newick = newick   + subClusters.get(i).getNewickIntermediate() ;
				}
				
				if (subClusters.get(i).elements.size()==1) {
				newick = newick + subClusters.get(i).getNewickIntermediate();
				}
				if (i<subClusters.size()-1) newick = newick + ",";
			}
			newick = newick + ")";
		}		
		return newick;
	}
	
	public String getNewick() {
		return this.getNewickIntermediate() + ";";
	}
	
	private double average(ArrayList<Double> a) {
		double x = 0;
		for(int i = 0;i<a.size();i++) {
			x = x  + a.get(i);
		}
		x = x / a.size();
		return x;
	}
		
	public double linkage(ClusterOfSequences aCluster) {
		ArrayList<Double> distances = new ArrayList<Double>();
			for(int i = 0; i<elements.size();i++) {
				for(int j = 0; j<aCluster.elements.size();j++) {
					distances.add(elements.get(i).distance(aCluster.elements.get(j)));
					}			
			}		
		return average(distances); //((A,B),(C,D,E)
	}
	
	public void clusterizeAgglomerative() {
		if (subClusters.size()>2) {
		double min = 2;
		int remove1 = 0;
		int remove2 = 0;
		for(int i=0;i<subClusters.size();i++) {
			for(int j = 0;j<subClusters.size();j++) {
				
				if (subClusters.get(i)!=subClusters.get(j)) {
					
					double temp = subClusters.get(i).linkage(subClusters.get(j));
					if (temp<=min) {
					min = temp;
					remove1 = i;
					remove2 = j;
					}
				}
			}
		}
		ClusterOfSequences toadd = new ClusterOfSequences(subClusters.get(remove1),subClusters.get(remove2));
		subClusters.add(toadd);
		subClusters.remove(remove1);
		subClusters.remove(remove2);

		this.clusterizeAgglomerative();
		}
	}
	
	public void clusterize() {
		clusterizeAgglomerative();
	}
	
	public static void main(String[] args) {
		
		Sequence seq1 = new Sequence("ATTACG");
		Sequence seq2 = new Sequence("ATATCG");
		
		Sequence seq3 = new Sequence("ACCCCG");
		Sequence seq4 = new Sequence("GCCGAG");
		Sequence seq5 = new Sequence("TCCCCG");
		
		ArrayList<Sequence> listSeq = new ArrayList<Sequence>(3);
		listSeq.add(seq1);
		listSeq.add(seq2);

		ArrayList<Sequence> listSeq3 = new ArrayList<Sequence>(3);
		listSeq3.add(seq3);

		ArrayList<Sequence> listSeq2 = new ArrayList<Sequence>(3);
		listSeq2.add(seq4);
		listSeq2.add(seq5);
		
		ArrayList<Sequence> listSeq5 = new ArrayList<Sequence>(5);
		listSeq5.add(seq1);
		listSeq5.add(seq2);
		listSeq5.add(seq3);
		listSeq5.add(seq4);
		listSeq5.add(seq5);
		
		//TEST GETNEWICK
		ArrayList<Sequence> seq35 = new ArrayList<Sequence>(3);
		seq35.add(seq3);
		seq35.add(seq5);
		ClusterOfSequences testCluster35 = new ClusterOfSequences(seq35);
		ClusterOfSequences testCluster4=  new ClusterOfSequences(seq4);
		ClusterOfSequences Clus435 = new ClusterOfSequences(testCluster4,testCluster35);
		ClusterOfSequences Clus12 = new ClusterOfSequences(listSeq);
		ClusterOfSequences testCluster = new ClusterOfSequences(Clus12,Clus435);
		System.out.println("TEST GETNEWICK: " + testCluster.getNewick()); //((ATTACG,ATATCG),(GCCGAG,(ACCCCG,TCCCCG)))
		
		//TEST LINKAGE
		System.out.println("TEST LINKAGE: " + Clus12.linkage(Clus435));
		
		//TEST CLUSTERIZEAGGLO
		ClusterOfSequences bioClusterNEW = new ClusterOfSequences(listSeq5);
		System.out.println("INITIAL: " + bioClusterNEW.getNewick());
		bioClusterNEW.clusterizeAgglomerative();
		System.out.println("AFTER CLUSTERIZE: "  + bioClusterNEW.getNewick());
		
	}

}
