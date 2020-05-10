import java.util.*;
import java.io.*;
public class homeworkHelp {
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(f.readLine().trim());
		char[] s=new char[N];
		String temp=f.readLine().trim();
		int[] bit=new int[N+1];
		for(int i=0; i<N; i++) {
			s[i]=temp.charAt(i);
			if(s[i]=='1')
				update(bit,i,1);
		}
		int Q=Integer.parseInt(f.readLine().trim());
		StringTokenizer st;
		String[] out=new String[Q];
		for(int i=0; i<Q; i++) {
			st=new StringTokenizer(f.readLine());
			int a=Integer.parseInt(st.nextToken());
			if(a==0) {
				int x=Integer.parseInt(st.nextToken());
				if(x>=0&&x<N&&s[x]=='0') {
					update(bit,x,1);
					s[x]='1';
				}
			}
			else if(a==1){
				int l=Integer.parseInt(st.nextToken());
				int r=Integer.parseInt(st.nextToken());
				if(l>r)
					out[i]="0";
				else if(l==0)
					out[i]=sum(bit,r)+"";
				else
					out[i]=convert(s,Math.max(l, 0),Math.min(r, N-1),bit)+"";
			}
		}
		for(int i=0; i<Q; i++) {
			if(out[i]!=null)
			System.out.println(out[i]);
		}
	}
	public static int sum(int[] bit, int index) {
		int sum=0;
		index++;
		int origIndex=index;
		for(;index>0;index-=index&(-index)) {
			int k=2;
			if((origIndex-index)%2==0)
				k=1;
			sum=(sum+k*bit[index])%3;
		}
		return sum;
	}
	public static void update(int[] bit, int index, int val) {
		index++;
		int origIndex=index;
		for(;index<bit.length; index+=index&(-index)) {
			int k=2;
			if((index-origIndex)%2==0) {
				k=1;
			}
			bit[index]=(bit[index]+k)%3;
		}
	}
	public static int convert(char[] s,int l,int r,int[] bit) {
		int x=2;
		if((r+1-l)%2==0) {
			x=1;
		}
		return (sum(bit,r)-sum(bit,l-1)*x+9)%3;
	}
}