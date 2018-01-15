import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Operations {

	public void save(SkipList list,BufferedWriter wr) throws IOException{
		Node current=list.leftmost().getNext();
		for(int i=0; i<list.size(); i++){
			wr.write(current.getKey()+" "+current.height());
			wr.newLine();
			current=current.getNext();
		}
		wr.close();
		System.out.println("Skiplist has been saved succesfully.");
	}

	public SkipList load(BufferedReader rd) throws IOException{
		SkipList list=new SkipList();
		int index=1;
		while(true){
			String line=rd.readLine();
			if(line==null) break;
			StringTokenizer token=new StringTokenizer(line);
			String tk=token.nextToken();
			int key=0;
			for(int i=0; i<tk.length();i++){
				key=key*10+(int)(tk.charAt(i)-'0');
			}
			tk=token.nextToken();
			int h=0;
			for(int i=0; i<tk.length();i++){
				h=h*10+(int)(tk.charAt(i)-'0');
			}
			h++;
			list.incSize();
			Node prevNode=search(list,key);
			Node belowNode=new Node(key,h,index);
			list.addAfter(prevNode, belowNode);
			for(int i=0; i<h;i++){
				Node newNode=new Node(key,h,index);
				if(h>=list.height()){
					list.incHeight();
				}
				list.addAbove(belowNode, newNode);
				while(!prevNode.hasUp()){
					prevNode=prevNode.getPrev();
				}
				prevNode=prevNode.getUp();
				belowNode=newNode;
				list.addAfter(prevNode, belowNode);
			}
			index++;
		}
		System.out.println("Skiplist has been loaded succesfully.");
		return list;
	}

	public Node search(SkipList list, int key){
		Node current=list.topLmost();
		while (current.hasBelow()){
			current=current.getBelow();
			while(key>=current.getNext().getKey()){
				current=current.getNext();
			}
			if(current.getKey()==key){
				while(current.hasBelow()){
					current=current.getBelow();
				}
			}
		}
		return current;
	}

	public void insert(SkipList list, int key){
		list.incSize();
		int heightofkey= key%(int) Math.ceil(Math.log(128)/Math.log(2));
		Node prevNode=search(list,key);
		int index=prevNode.getIndex()+1;
		Node belowNode=new Node(key,heightofkey,index);
		int currentHeight=0;
		list.addAfter(prevNode, belowNode);
		while(currentHeight<=heightofkey){
			Node newNode=new Node(key,heightofkey,index);
			if(heightofkey>=list.height()){
				list.incHeight();
			}
			list.addAbove(belowNode, newNode);
			while(!prevNode.hasUp()){
				prevNode=prevNode.getPrev();
			}
			prevNode=prevNode.getUp();
			belowNode=newNode;
			list.addAfter(prevNode, belowNode);
			currentHeight++;
		}
		incIndices(list,key);
	}

	private void incIndices(SkipList list, int key) {
		Node forinc=search(list,key);
		while(forinc.hasNext()){
			forinc=forinc.getNext();
			forinc.incIndex();
			if(forinc.hasUp()){
				Node forinc2=forinc.getUp();
				forinc2.incIndex();
				while(forinc2.hasUp()){
					forinc2=forinc2.getUp();
					forinc2.incIndex();
				}
			}
		}
	}

	private void decIndices(SkipList list, int key) {
		Node fordec=search(list,key);
		while(fordec.hasNext()){
			fordec=fordec.getNext();
			fordec.decIndex();
			if(fordec.hasUp()){
				Node fordec2=fordec.getUp();
				fordec2.decIndex();
				while(fordec2.hasUp()){
					fordec2=fordec2.getUp();
					fordec2.decIndex();
				}
			}
		}
	}

	public void remove(SkipList list, int key){
		Node current=search(list,key);
		if(current!=null){
			decIndices(list,key);
			list.decSize();
		}
		while(current!=null){
			current.getNext().setPrev(current.getPrev());
			current.getPrev().setNext(current.getNext());
			current.setNext(null);
			current.setPrev(null);
			if(current.hasBelow()){
				current.getBelow().setUp(null);
				current.setBelow(null);
			}
				current=current.getUp();
		}
	}
	
	public int toNumber(String str){
		int num=0;
		for(int i=0; i<str.length();i++){
			num=num*10+(int)(str.charAt(i)-'0');
		}
		return num;
	}
	
	public void build(SkipList list,StringTokenizer stk){
		while(stk.hasMoreTokens()){
			int key=toNumber(stk.nextToken());
			insert(list,key);
		}
		System.out.println("input.txt has been built succesfully.");
	}
}
