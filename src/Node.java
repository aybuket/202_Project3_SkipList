
public class Node {

	private int key;
	private Node next;
	private Node prev;
	private Node below;
	private Node up;
	private int height;
	private int index;

	public Node(int elt, int height,int index){
		key=elt;
		this.height=height;
		this.index=index;
	}
	
	public void incIndex(){
		index++;
	}
	
	public void decIndex(){
		index--;
	}
	
	public void setIndex(int newIndex){
		index=newIndex;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int getKey(){
		return key;
	}
	
	public void setKey(int elt){
		key=elt;
	}
	
	public int height(){
		return height;
	}
	
	public Node getNext(){
		return next;
	}
	
	public Node getPrev(){
		return prev;
	}
	
	public Node getBelow(){
		return below;
	}
	
	public Node getUp(){
		return up;
	}
	
	public void setNext(Node newNext){
		next=newNext;
	}
	
	public void setPrev(Node newPrev){
		prev=newPrev;
	}
	
	public void setBelow(Node newBelow){
		below=newBelow;
	}
	
	public void setUp(Node newUp){
		up=newUp;
	}
	
	public boolean hasNext(){
		if (next!=null){ 
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasPrev(){
		if (prev!=null){ 
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasBelow(){
		if (below!=null){ 
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasUp(){
		if (up!=null){ 
			return true;
		}else{
			return false;
		}
	}
}
