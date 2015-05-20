package KNN;
public class KNN {
	int kValue;//k nearest neighbors
	double[][] group;
	String[] labels;
	Node[] nodes;
	
	public KNN(double[][] group,String[] labels,int k){
		kValue=k;
		this.group=group;
		this.labels=labels;
		nodes=new Node[labels.length];
		for(int i=0;i<nodes.length;i++){
			nodes[i]=new Node(group[i],labels[i]);
		}
	}
	
	
	
	
	
	
}
