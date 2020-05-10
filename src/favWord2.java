import java.util.*;
import java.io.*;
public class favWord2 {
	private static StringBuilder s=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		String s1=f.readLine().trim();
		String s2=f.readLine().trim();
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		HashMap<Character,HashMap<Character,Long>> costs=new HashMap<>();
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(f.readLine());
			char a=st.nextToken().charAt(0);
			char b=st.nextToken().charAt(0);
			long cost=Long.parseLong(st.nextToken());
			if(!costs.containsKey(a)) {
				costs.put(a,new HashMap<Character,Long>());
			}
			if(costs.get(a).containsKey(b)) {
				costs.get(a).put(b, Math.min(cost, costs.get(a).get(b)));
			}
			else {
				costs.get(a).put(b, cost);
			}
		}
		if(s1.length()!=s2.length()) {
			System.out.println(-1);
			System.exit(0);
		}
		HashMap<Character,HashMap<Character,Long>> shortest=new HashMap<>();
		for(int i=0; i<26; i++) {
			char c1=(char)(i+'a');
			shortest.put(c1, new HashMap<Character,Long>());
		}
		for(int i=0; i<26; i++) {
			char c1=(char)(i+'a');
			PriorityQueue<Node> pq=new PriorityQueue<>();
			pq.add(new Node(0,0,c1));
			while(!pq.isEmpty()) {
				Node node=pq.poll();
				if(shortest.get(c1).containsKey(node.c)) {
					continue;
				}
				shortest.get(c1).put(node.c, node.cost);
				if(costs.containsKey(node.c)) {
					for(char k:costs.get(node.c).keySet()) {
						pq.add(new Node(node.cost+costs.get(node.c).get(k),0,k));
					}
				}
			}
		}
		long cost=0;
		for(int i=0; i<s1.length(); i++) {
			char c1=s1.charAt(i);
			char c2=s2.charAt(i);
			if(c1==c2) {
				s.append(c1);
				continue;
			}
			long k=-1;
			char minChar='z';
			for(int j=0; j<26; j++) {
				char x=(char)(j+'a');
				if(shortest.get(c1).containsKey(x)&&shortest.get(c2).containsKey(x)) {
					long newCost=shortest.get(c1).get(x)+shortest.get(c2).get(x);
					if(k<0||newCost<k) {
						k=newCost;
						minChar=x;
					}
					else if(newCost==k&&x<minChar)
						minChar=x;
				}
			}
			if(k<0) {
				System.out.println(-1);
				System.exit(0);
			}
			cost+=k;
			s.append(minChar);
		}
		System.out.println(cost);
		System.out.println(s.toString());
	}
	private static class Node implements Comparable<Node>{
		public long cost;
		public int ind;
		public char c;
		public Node(long cost, int ind,char c) {
			this.cost=cost;
			this.ind=ind;
			this.c=c;
		}
		public int compareTo(Node oth) {
			if(this.cost>oth.cost)
				return 1;
			if(this.cost==oth.cost)
				return this.c-oth.c;
			return -1;
		}
	}
}
