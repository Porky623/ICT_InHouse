import java.util.*;
import java.io.*;
public class snakeCity {
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(f.readLine());
		int[] costs=new int[N];
		HashMap<Integer,HashSet<Integer>> adjMap=new HashMap<>();
		for(int i=0; i<N; i++) {
			costs[i]=Integer.parseInt(st.nextToken());
			adjMap.put(i, new HashSet<Integer>());
		}
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(f.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			adjMap.get(a).add(b);
			adjMap.get(b).add(a);
		}
		HashSet<Integer> seen=new HashSet<Integer>();
		long out=0;
		for(int i=0; i<N; i++) {
			if(!seen.contains(i)) {
				out+=BFS(costs,adjMap,seen,i);
			}
		}
		System.out.println(out);
	}
	public static int BFS(int[] costs,HashMap<Integer,HashSet<Integer>> adjMap,HashSet<Integer> seen,int start) {
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(start);
		int min=costs[start];
		while(!q.isEmpty()) {
			int x=q.poll();
			if(seen.contains(x))
				continue;
			seen.add(x);
			min=Math.min(min, costs[x]);
			q.addAll(adjMap.get(x));
		}
		return min;
	}
}
