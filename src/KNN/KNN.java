package KNN;

import java.util.HashMap;
import java.util.Iterator;

public class KNN {
	int kValue;//k nearest neighbors
	double[][] group;
	String[] labels;
	Node[] nodes;
	KDTree kdtree;
	
	public KNN(double[][] group,String[] labels,int k){
		kValue=k;
		this.group=group;
		this.labels=labels;
		nodes=new Node[labels.length];
		for(int i=0;i<nodes.length;i++){
			nodes[i]=new Node(group[i],labels[i]);
		}
		kdtree=new KDTree();
	}
	
	/**
	 * 使用kdtree确定k个邻居
	 * @param newNode
	 * @return
	 */
	Node[] getKNN(Node newNode){
		Node[] knn=new Node[kValue];
		kdtree.buildKDTree(nodes);
		for(int i=0;i<kValue;i++){
			BinaryTree<Node> nearest=kdtree.getNearestNeighbor(newNode);
			knn[i]=nearest.data;
			kdtree.deleteNode(nearest);
		}
		return knn;
	}
	
	/**
	 * 利用KNN算法进行预测
	 * @param newNode
	 * @return
	 */
	public String prediction(Node newNode){
		Node[] knn=getKNN(newNode);
		HashMap<String,Integer> hm=new HashMap<String,Integer>();
		for(int i=0;i<knn.length;i++){
			String key=knn[i].label;
			if(hm.containsKey(key)){
				int count=hm.get(key);
				hm.put(key, count++);
			}else{
				hm.put(key, 1);
			}
		}
		int max=Integer.MIN_VALUE;
		String maxLabel="";
		Iterator<String> it=hm.keySet().iterator();
		while(it.hasNext()){
			if(hm.get(it.next())>max){
				max=hm.get(it.next());
				maxLabel=it.next();
			}
		}
		return maxLabel;
		
	}
	
	
	
	
}
