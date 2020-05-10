import java.util.*;
import java.io.*;
public class winnerWinner {
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		int[] board=new int[N*N];
		for(int i=0; i<N; i++) {
			String in=f.readLine().trim();
			for(int j=0; j<N; j++) {
				board[i*N+j]=(int)(in.charAt(j)-'0');
			}
		}
		boolean down=true;
		for(int i=0; i<K; i++) {
			boolean[] del=delRegions(board,N);
			board=getNewBoard(board,del,down,N);
			down=!down;
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(board[i*N+j]>0) {
					System.out.println("NO");
					System.exit(0);
				}
			}
		}
		System.out.println("YES");
	}
	public static int[] getNewBoard(int[] board, boolean[] del,boolean down,int N) {
		for(int i=0; i<board.length; i++) {
			if(del[i])
				board[i]=0;
		}
		int[] ret=new int[board.length];
		for(int j=0; j<N; j++) {
			if(down) {
				int ind=N-1;
				for(int i=N-1; i>=0; i--) {
					if(board[N*i+j]>0) {
						ret[N*ind+j]=board[N*i+j];
						ind--;
					}
				}
			}
			else {
				int ind=0;
				for(int i=0; i<N; i++) {
					if(board[N*i+j]>0) {
						ret[N*ind+j]=board[N*i+j];
						ind++;
					}
				}
			}
		}
		return ret;
	}
	public static void findRegions(int[] board,int startInd,boolean[] del,int N) {
		Queue<Integer> q=new LinkedList<Integer>();
		int startVal=board[startInd];
		q.add(startInd);
		HashSet<Integer> change=new HashSet<>();
		while(!q.isEmpty()) {
			int x=q.poll();
			if(del[x]||board[x]!=startVal||change.contains(x))
				continue;
			change.add(x);
			if(x%N>0) {
				q.add(x-1);
			}
			if(x%N<N-1) {
				q.add(x+1);
			}
			if(x>=N) {
				q.add(x-N);
			}
			if(x<board.length-N) {
				q.add(x+N);
			}
		}
		if(change.size()>1) {
			for(int x:change) {
				del[x]=true;
			}
		}
	}
	public static boolean[] delRegions(int[] board,int N) {
		boolean[] ret=new boolean[board.length];
		for(int i=0; i<board.length; i++) {
			if(board[i]>0&&!ret[i]) {
				findRegions(board,i,ret,N);
			}
		}
		return ret;
	}
}
