import java.util.*;
import java.io.*;
public class favWord {
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
		long cost=0;
		for(int i=0; i<s1.length(); i++) {
			if(s1.charAt(i)==s2.charAt(i)) {
				s.append(s1.charAt(i));
				continue;
			}
			long k=minCost(costs,s1.charAt(i),s2.charAt(i));
			if(k<0) {
				System.out.println(-1);
				System.exit(0);
			}
			cost+=k;
		}
		System.out.println(cost);
		System.out.println(s.toString());
	}
	public static long minCost(HashMap<Character,HashMap<Character,Long>> costs,char c1,char c2) {
		ArrayList<HashMap<Character,Long>> map=new ArrayList<>();
		for(int i=0; i<2; i++) {
			map.add(new HashMap<Character,Long>());
		}
		PriorityQueue<Node> q=new PriorityQueue<>();
		q.add(new Node(0,0,c1));
		q.add(new Node(0,1,c2));
		long minCost=-1;
		char minChar='z';
		while(!q.isEmpty()) {
			Node node=q.poll();
			char c=node.c;
			int ind=node.ind;
			if(map.get(ind).containsKey(c)||minCost>=0&&node.cost>minCost)
				continue;
			if(map.get(1-ind).containsKey(c)) {
				long newCost=map.get(1-ind).get(c)+node.cost;
				if(minCost<0) {
					minCost=newCost;
					minChar=c;
				}
				else if(minCost>newCost){
					minCost=newCost;
					minChar=c;
				}
				else if(minCost==newCost&&minChar>c)
					minChar=c;
			}
			map.get(ind).put(c, node.cost);
			if(costs.containsKey(c)) {
				for(char k:costs.get(c).keySet()) {
					q.add(new Node(node.cost+costs.get(c).get(k),ind,k));
				}
			}
		}
		if(minCost>=0)
			s.append(minChar);
		return minCost;
	}
//	public static long minCost(HashMap<Character,HashMap<Character,Long>> costs,char c1,char c2) {
//		HashMap<Character,Long> m1=new HashMap<>();
//		PriorityQueue<Node> q=new PriorityQueue<>();
//		q.add(new Node(0,c1));
//		while(!q.isEmpty()) {
//			Node node=q.poll();
//			char c=node.c;
//			if(m1.containsKey(c))
//				continue;
//			m1.put(c, node.cost);
//			if(costs.containsKey(c))
//			for(char k:costs.get(c).keySet()) {
//				q.add(new Node(node.cost+costs.get(c).get(k),k));
//			}
//		}
//		HashMap<Character,Long> m2=new HashMap<>();
//		q=new PriorityQueue<>();
//		q.add(new Node(0,c2));
//		long min=-1;
//		char minChar='z';
//		while(!q.isEmpty()) {
//			Node node=q.poll();
//			char c=node.c;
//			if(m2.containsKey(c))
//				continue;
//			m2.put(c, node.cost);
//			if(m1.containsKey(c)) {
//				long newCost=node.cost+m1.get(c);
//				if(min<0||min>newCost||min==newCost&&c<minChar) {
//					min=newCost;
//					minChar=c;
//				}
//			}
//			if(costs.containsKey(c))
//			for(char k:costs.get(c).keySet()) {
//				q.add(new Node(node.cost+costs.get(c).get(k),k));
//			}
//		}
//		if(min>=0) {
//			s.append(minChar);
//		}
//		return min;
//	}
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
