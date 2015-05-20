class BinaryTree<T>{
	T data;
	BinaryTree<T> parent;
	BinaryTree<T> left;
	BinaryTree<T> right;
	
	BinaryTree(){
		data=null;
		parent=null;
		left=null;
		right=null;
	}
	
	void makeRoot(T data){
		if(data!=null){
			throw new TreeViolateException();
		}
		this.data=data;
	}
	void attachLeft(BinaryTree<T> tree){
		if(tree.left!=null){
			throw new TreeViolateException();
		}
		if(this!=null){
			this.left=tree;
			tree.parent=this;
		}
	}
	
	void attachRight(BinaryTree<T> tree){
		if(tree.right!=null){
			throw new TreeViolateException();
		}
		if(this!=null){
			this.right=tree;
			tree.parent=this;
		}
	}
	
	BinaryTree<T> detachLeft(){
		BinaryTree<T> detachTree=this.left;
		this.left=null;
		return detachTree;
	}
	
	BinaryTree<T> detachRight(){
		BinaryTree<T> detachTree=this.right;
		this.right=null;
		return detachTree;
	}
	
}

public class KDTree<T>{
	BinaryTree<T> tree;
	public KDTree(){
		tree=null;
	}
	public void buildTree(double[][] group,int depth){
		int dim=
	}
}