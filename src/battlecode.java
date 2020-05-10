import java.util.*;
import java.io.*;
public class battlecode {
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int P=Integer.parseInt(st.nextToken());
		int Q=Integer.parseInt(st.nextToken());
		//maps position on the board to index of castle fjds
		HashMap<Integer,Integer> allied=new HashMap<>();
		HashSet<Integer> enemy=new HashSet<>();
		for(int i=0; i<P; i++) {
			st=new StringTokenizer(f.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int ind=N*x+y;
			//if position already in, it also has a smaller index
			if(!allied.containsKey(ind))
			allied.put(ind,i);
		}
		//pq for multi-source djikstra
		PriorityQueue<Node> q=new PriorityQueue<>();
		for(int i=0; i<Q; i++) {
			st=new StringTokenizer(f.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int ind=N*x+y;
			//saves time by not caring about duplicate enemies
			if(!enemy.contains(ind)) {
				//start stocking the pq
				q.add(new Node(0,ind));
				//adds to "seen enemies"
				enemy.add(ind);
			}
		}
		//to reduce computation
		int size=N*N;
		//makes sure there are no duplicates
		HashSet<Integer> seen=new HashSet<Integer>();
		//pre-sets min distance to -1 because it's impossible
		int minDist=-1;
		//once we find an allied castle, we keep adding them here so we can take the min indexed
		TreeSet<Integer> indices=new TreeSet<>();
		while(!q.isEmpty()) {
			//gets next in pq
			Node next=q.poll();
			//sets which is index and which is dist
			int ind=next.b;
			int dist=next.a;
			//if we have already found an allied and we're farther than it, we know we're done
			if(minDist>=0&&minDist<dist)
				break;
			//if we have a repeat we don't care about it
			if(seen.contains(ind))
				continue;
			//add to seen set
			seen.add(ind);
			//if we've hit an allied (must be closest or tied for closest by the above break)
			if(allied.containsKey(ind)) {
				if(minDist<0)
				minDist=dist;
				//add our index to the treeSet
				indices.add(allied.get(ind));
			}
			//if we've hit a min, we don't need to check anymore for farther castles
			else {
				//goes up
				if(ind>=N) {
					q.add(new Node(dist+1,ind-N));
				}
				//goes left
				if(ind%N>0) {
					q.add(new Node(dist+1,ind-1));
				}
				//goes down
				if(ind<size-N) {
					q.add(new Node(dist+1,ind+N));
				}
				//goes right
				if(ind%N<N-1) {
					q.add(new Node(dist+1,ind+1));
				}
			}
		}
		//outputs answer
		System.out.println(indices.first()+" "+minDist);
	}
	private static class Node implements Comparable<Node>{
		//a=dist, b=ind
		public int a,b;
		public Node(int a, int b) {
			this.a=a;
			this.b=b;
		}
		//only care about distances (other ties will be broken later; technically could break them now)
		public int compareTo(Node oth) {
			return this.a-oth.a;
		}
	}
}
