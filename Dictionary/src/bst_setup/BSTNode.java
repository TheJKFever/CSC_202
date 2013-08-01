package bst_setup;

@SuppressWarnings("rawtypes")
public class BSTNode <T extends Comparable>{
	T data;
	BSTNode <T> left;
	BSTNode <T> right;
	
	public BSTNode(T data) {
		this.data = data;
		left = null;
		right = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BSTNode<T> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	public BSTNode<T> getRight() {
		return right;
	}

	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
	
}
