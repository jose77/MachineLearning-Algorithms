package KNN;

import java.util.Stack;

public class KDTree{
	BinaryTree<Node> tree=new BinaryTree<Node>();
	Stack<BinaryTree<Node>> searchPath;
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
	BinaryTree<Node> buildTree(Node[] nodes,int k,int depth){
		BinaryTree<Node> tree=new BinaryTree<Node>();
		if(nodes.length==1){
			tree.makeRoot(nodes[0],0);
		}
		else{
			int dim=depth%k;
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
			int mid=(nodes.length)/2;
			tree.makeRoot(nodes[mid],dim);
			Node[] leftNodes=new Node[mid];
			Node[] rightNodes=new Node[nodes.length-mid-1];
			for(int i=0;i<mid;i++){
				leftNodes[i]=nodes[i];
			}
			for(int j=mid+1;j<nodes.length;j++){
				rightNodes[j]=nodes[j];
			}
			BinaryTree<Node> leftTree=buildTree(leftNodes,k,depth+1);
			BinaryTree<Node> rightTree=buildTree(rightNodes,k,depth+1);
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
		tree=buildTree(nodes,nodes[0].group.length,0);
	}
	
	
	/**
	 * 使用KDTree查找最近邻
	 * @param newNode
	 * @return
	 */
	public BinaryTree<Node> getNearestNeighbor(Node newNode){
		buildSearchPath(newNode);
		BinaryTree<Node> currTree;
		BinaryTree<Node> nearest=null;
		double minDist=Double.MAX_VALUE;
		int dim;//分割维度
		while(!searchPath.isEmpty()){
			currTree=searchPath.pop();
			dim=currTree.dim;
			double dist=getDistance(newNode,currTree.data);
			if(dist<minDist){
				nearest=currTree;
				minDist=dist;
			}
			if(Math.abs(newNode.group[dim]-currTree.data.group[dim])<minDist){
				if(currTree.equals(currTree.parent.left)){
					currTree=currTree.parent.right;
				}else{
					currTree=currTree.parent.left;
				}
				searchPath.push(currTree);
			}
		}
		return nearest;
		
	}
	
	
	/**
	 * 寻找新节点落入的初始区域
	 * @param newNode
	 */
	void buildSearchPath(Node newNode){
		searchPath=new Stack<BinaryTree<Node>>();
		BinaryTree<Node> curr=tree;
		int depth=0;
		int dim=0;
		while(curr.left!=null && curr.right!=null){
			searchPath.push(curr);
			dim=depth%newNode.group.length+1;
			if(curr.data.group[dim]>newNode.group[dim]){
				curr=curr.left;
			}else{
				curr=curr.right;
			}
		}
	}
	
	void deleteNode(BinaryTree<Node> nearest){
		if(nearest.left==null){
			nearest.parent.detachLeft();
		}else{
			BinaryTree<Node> curr=nearest.left;
			while(curr.right!=null){
				curr=curr.right;
			}
			nearest.data=curr.data;
			curr.parent.detachRight();
		}
	}
	
	
	double getDistance(Node node1,Node node2){
		double dist=0;
		for(int i=0;i<node1.group.length;i++){
			dist+=Math.pow(node1.group[i]-node2.group[i], 2);
		}
		return Math.sqrt(dist);
	}
}