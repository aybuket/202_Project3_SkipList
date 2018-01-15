import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class project3 {

	public static void main(String[] args) throws IOException {

		BufferedReader rd=new BufferedReader(new FileReader("input.txt"));
		Operations op=new Operations();
		String keys=rd.readLine();
		int cap=op.toNumber(rd.readLine());
		rd.close();
		SkipList mySkip=new SkipList();
		mySkip.setCap(cap);
		StringTokenizer token=new StringTokenizer(keys);
		op.build(mySkip, token);
		while(true){
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			String line=input.readLine();
			if(line.length()==0) break;
			StringTokenizer tk=new StringTokenizer(line);
			String command=tk.nextToken();
			if(tk.hasMoreTokens()){
				if(command.equalsIgnoreCase("save")){
					BufferedWriter wr=new BufferedWriter(new FileWriter(tk.nextToken()));
					op.save(mySkip, wr);
				}else if(command.equalsIgnoreCase("load")){
					mySkip=op.load(new BufferedReader(new FileReader(tk.nextToken())));	
				}
			}else{
				tk=new StringTokenizer(command,"*");
				command=tk.nextToken();
				if(command.equalsIgnoreCase("insert")){
					int key=op.toNumber(tk.nextToken());
					op.insert(mySkip,key);
					Visualization.print(mySkip);
				}else if(command.equalsIgnoreCase("search")){
					int key=op.toNumber(tk.nextToken());
					op.search(mySkip, key);
					Visualization.searchPrint(mySkip,key);
				}else if(command.equalsIgnoreCase("print")){
					Visualization.print(mySkip);
				}else if(command.equalsIgnoreCase("remove")){
					int key=op.toNumber(tk.nextToken());
					op.remove(mySkip, key);
					Visualization.print(mySkip);
				}
			}
		}
	}

}
