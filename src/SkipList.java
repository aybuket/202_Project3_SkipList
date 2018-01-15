
public class SkipList {

	private int height=(int) Math.ceil(Math.log(128)/Math.log(2));
	private int max_capacity=128;
	private int size;
	private Node topLmost=new Node(-200,height+1,0);
	private Node topRmost=new Node(200,height+1,100);
	private Node leftmost=new Node(-200,height+1,0);
	private Node rightmost=new Node(200,height+1,100);

	public SkipList(){
		topLmost.setNext(topRmost);
		topRmost.setPrev(topLmost);
		leftmost.setNext(rightmost);
		rightmost.setPrev(leftmost);
		topLmost.setBelow(leftmost);
		leftmost.setUp(topLmost);
		topRmost.setBelow(rightmost);
		rightmost.setUp(topRmost);
		Node leftBelow=leftmost;
		Node rightBelow=rightmost;
		for(int i=1; i<height+1;i++){
			Node leftNode=new Node(-200,height+1,0);
			Node rightNode=new Node(200,height+1,100);
			leftNode.setBelow(leftBelow);
			leftNode.setUp(leftBelow.getUp());
			leftBelow.getUp().setBelow(leftNode);
			leftBelow.setUp(leftNode);
			rightNode.setBelow(rightBelow);
			rightNode.setUp(rightBelow.getUp());
			rightBelow.getUp().setBelow(rightNode);
			rightBelow.setUp(rightNode);
			leftNode.setNext(rightNode);
			rightNode.setPrev(leftNode);
			leftBelow=leftNode;
			rightBelow=rightNode;
		}
	}


	public int height(){
		return height;
	}

	public void incHeight(){
		height++;
	}

	public void decHeight(){
		height--;
	}

	public Node topLmost(){
		return topLmost;
	}

	public Node topRmost(){
		return topRmost;
	}

	public Node leftmost(){
		return leftmost;
	}

	public Node rightmost(){
		return rightmost;
	}

	public int capacity(){
		return max_capacity;
	}

	public void setCap(int cap){
		max_capacity=cap;
	}
	public int size(){
		return size;
	}

	public void incSize(){
		size++;
	}

	public void decSize(){
		size--;
	}

	public void addAfter(Node prevNode, Node newNode){
		newNode.setNext(prevNode.getNext());
		newNode.setPrev(prevNode);
		prevNode.setNext(newNode);
		newNode.getNext().setPrev(newNode);
	}
	public void addAbove(Node belowNode, Node newNode){
		newNode.setBelow(belowNode);
		belowNode.setUp(newNode);
	}

}
