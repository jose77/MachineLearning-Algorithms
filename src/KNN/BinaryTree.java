package KNN;

public class BinaryTree<T>{
	T data;
	BinaryTree<T> parent;
	BinaryTree<T> left;
	BinaryTree<T> right;
	int dim=0;//ªÆ∑÷Œ¨∂»
	
	public BinaryTree(){
		data=null;
		parent=null;
		left=null;
		right=null;
		dim=0;
	}
	
	public void makeRoot(T data,int dim){
		if(data!=null){
			throw new TreeViolateException();
		}
		this.data=data;
		this.dim=dim;
	}
	public void attachLeft(BinaryTree<T> tree){
		if(tree.left!=null){
			throw new TreeViolateException();
		}
		if(this!=null){
			this.left=tree;
			tree.parent=this;
		}
	}
	
	public void attachRight(BinaryTree<T> tree){
		if(tree.right!=null){
			throw new TreeViolateException();
		}
		if(this!=null){
			this.right=tree;
			tree.parent=this;
		}
	}
	
	public BinaryTree<T> detachLeft(){
		BinaryTree<T> detachTree=this.left;
		this.left=null;
		return detachTree;
	}
	
	public BinaryTree<T> detachRight(){
		BinaryTree<T> detachTree=this.right;
		this.right=null;
		return detachTree;
	}
	
}