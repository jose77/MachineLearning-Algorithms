package KNN;
public class KDTree{
	BinaryTree<Node> tree=new BinaryTree<Node>();
	public KDTree(){
		tree=null;
	}
	/**
	 * 递归构造KDTree
	 * @param tree
	 * @param nodes
	 * @param k
	 * @param depth
	 * @return
	 */
	BinaryTree<Node> buildTree(BinaryTree<Node> tree ,Node[] nodes,int k,int depth){
		if(nodes.length==1){
			tree.makeRoot(nodes[0]);
		}
		else{
			int dim=depth%k+1;
			for(int i=0;i<nodes.length;i++){
				for(int j=1;j<nodes.length-i;j++){
					if(nodes[i].group[dim]<nodes[j].group[dim]){
						Node tmpNode;
						tmpNode=nodes[i];
						nodes[i]=nodes[j];
						nodes[j]=tmpNode;
					}
				}
			}
			int mid=(nodes.length-1)/2;
			tree.makeRoot(nodes[mid]);
			Node[] leftNodes=new Node[mid];
			Node[] rightNodes=new Node[nodes.length-mid-1];
			for(int i=0;i<mid;i++){
				leftNodes[i]=nodes[i];
			}
			for(int j=mid+1;j<nodes.length;j++){
				rightNodes[j]=nodes[j];
			}
			BinaryTree<Node> leftTree=buildTree(tree.left,leftNodes,k,depth+1);
			BinaryTree<Node> rightTree=buildTree(tree.right,rightNodes,k,depth+1);
			tree.attachLeft(leftTree);
			tree.attachRight(rightTree);
		}
		return tree;
	}
	
	/**
	 * 递归构造KDTree的启动器
	 * @param group
	 */
	public void buildKDTree(Node[] nodes){
		tree=buildTree(tree,nodes,nodes[0].group.length,0);
	}
	
	/**
	 * 使用KDTree查找k近邻
	 * @param newNode
	 * @param k
	 * @return
	 */
	public Node getNearestNeighbor(Node newNode,int k){
		BinaryTree<Node> nstNeighbor=getLeaf(newNode);
		
	}
	
	BinaryTree<Node> getLeaf(Node newNode){
		BinaryTree<Node> curr=tree;
		int depth=0;
		int dim=0;
		while(curr.left!=null && curr.right!=null){
			dim=depth%newNode.group.length+1;
			if(curr.data.group[dim]>newNode.group[dim]){
				curr=curr.left;
			}else{
				curr=curr.right;
			}
		}
		return curr;
	}
	
	double getDistance(Node node1,Node node2){
		double dist=0;
		for(int i=0;i<node1.group.length;i++){
			dist+=Math.pow(node1.group[i]-node2.group[i], 2);
		}
		return Math.sqrt(dist);
	}
}