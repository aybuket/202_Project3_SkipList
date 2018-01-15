
public class Visualization {

	public static void print(SkipList list){
		Node first=list.topLmost();
		Node last=list.topRmost();
		for(int i=1; i<=list.height()+1;i++){
			if(first.getNext()==last){
				System.out.print("-inf--");
				for(int j=0;j<list.size();j++){
					System.out.print("----");
				}
				System.out.print("inf\n");
			}else{
				System.out.print("-inf--");
				Node current=first.getNext();
				for(int j=1;j<=list.size();j++){
					if(current.getIndex()!=j){
						System.out.print("--");
					}else{
						System.out.print(current.getKey());
						current=current.getNext();
					}
					System.out.print("--");
				}
				System.out.print("inf\n");
			}
			first=first.getBelow();
			last=last.getBelow();
		}
	}

	public static void searchPrint(SkipList list, int key){
		Node first=list.topLmost();
		Node last=list.topRmost();
		int lastkey=-200;
		int k=0;
		for(int i=1; i<=list.height()+1;i++){
			if(first.getNext()==last){
				System.out.print("-inf--");
				for(int j=0;j<list.size();j++){
					System.out.print("----");
				}
				System.out.print("inf\n");
			}else{
				System.out.print("-inf");
				Node current=first.getNext();
				if(current.getKey()<=key && lastkey<current.getKey()){
					System.out.print("**");
					k=1;
				}else{
					System.out.print("--");
					k=0;
				}
				for(int j=1;j<=list.size();j++){
					if(current.getIndex()!=j){
						if(current.getKey()<=key && lastkey<current.getKey()){
							System.out.print("**");
							k=1;
						}else{
							System.out.print("--");
							k=0;
						}
					}else{
						System.out.print(current.getKey());
						if(k==1){
							lastkey=current.getKey();
							k++;
						}
						current=current.getNext();
					}
					if(lastkey<current.getKey() && current.getKey()<=key){
						System.out.print("**");
						k=1;
					}else{
						System.out.print("--");
						k=0;
					}
				}
				System.out.print("inf\n");
			}
			first=first.getBelow();
			last=last.getBelow();
		}
	}
}
