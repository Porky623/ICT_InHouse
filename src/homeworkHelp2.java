import java.util.*;
import java.io.*;
public class homeworkHelp2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(f.readLine().trim());
		char[] s=new char[N];
		String temp=f.readLine().trim();
		for(int i=0; i<N; i++) {
			s[i]=temp.charAt(i);
		}
		int Q=Integer.parseInt(f.readLine().trim());
		StringTokenizer st;
		String[] out=new String[Q];
		int[] prefTree=new int[N];
		prefTree[0]=s[0]-'0';
		for(int i=1; i<N; i++) {
			prefTree[i]=(2*prefTree[i-1]+s[i]-'0')%3;
		}
		for(int i=0; i<Q; i++) {
			st=new StringTokenizer(f.readLine());
			int a=Integer.parseInt(st.nextToken());
			if(a==0) {
				int x=Integer.parseInt(st.nextToken());
				if(x>=0&&x<N&&s[x]=='0') {
					for(int j=x; j<N; j++) {
						if((j-x)%2==0) {
							prefTree[j]=(prefTree[j]+1)%3;
						}
						else {
							prefTree[j]=(prefTree[j]+2)%3;
						}
					}
					s[x]='1';
				}
			}
			else {
				int l=Integer.parseInt(st.nextToken());
				int r=Integer.parseInt(st.nextToken());
				if(l>r)
					out[i]="0";
				else if(l==0)
					out[i]=""+prefTree[r];
				else
					out[i]=convert(s,Math.max(l, 0),Math.min(r, N-1),prefTree)+"";
			}
		}
		for(int i=0; i<Q; i++) {
			if(out[i]!=null)
			System.out.println(out[i]);
		}
	}
	public static int convert(char[] s,int l,int r,int[] prefTree) {
		if((r+1-l)%2==0) {
			return (prefTree[r]-prefTree[l-1]+3)%3;
		}
		return (prefTree[r]-2*prefTree[l-1]+6)%3;
	}
}
