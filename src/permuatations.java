import java.util.*;
import java.io.*;
public class permuatations {
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		String s=f.readLine().trim();
		int[] counts=new int[26];
		long mod=1000000007;
		for(int i=0; i<N; i++) {
			counts[(int)(s.charAt(i)-'a')]++;
		}
		long out=factorial(N,mod);
		for(int i=0; i<26;i++) {
			if(counts[i]>1)
				out=(out*modInv(factorial(counts[i],mod),mod))%mod;
		}
		System.out.println(out);
	}
	public static long factorial(int N,long mod) {
		long ret=1;
		for(int i=2; i<=N; i++) {
			ret=(ret*i)%mod;
		}
		return ret;
	}
	public static long modInv(long count,long mod) {
		long m0 = mod; 
        long y = 0, x = 1, a=count; 
        while (a > 1) 
        { 
            long q=a/mod; 
            long t=mod; 
            mod = a % mod; 
            a = t; 
            t = y; 
            y = x - q * y; 
            x = t; 
        } 
        if (x < 0) 
            x += m0; 
        return x; 
	}
}
