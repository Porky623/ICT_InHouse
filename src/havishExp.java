import java.util.*;
import java.io.*;
public class havishExp {
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int n=Integer.parseInt(st.nextToken());
		HashMap<Integer,HashMap<Integer,Long>> map=new HashMap<>();
		long sum=0;
		long mod=1000000007;
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(f.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			if(a==1||b==0)
				sum+=1;
			else
				sum+=exp(a,b,mod);
		}
		System.out.println(sum%mod);
	}
	public static long exp(int b, int e,long mod) {
		long x=b;
		long y=1;
		while(e>1) {
			if(e%2==0) {
				x=(x*x)%mod;
				e/=2;
			}
			else {
				y=(x*y)%mod;
				x=(x*x)%mod;
				e/=2;
			}
		}
		return (x*y)%mod;
	}
}
