import java.util.*;
import java.io.*;
public class gradeBump {
	public static boolean decChange=false;
	public static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(f.readLine());
		st.nextToken();
		K=Integer.parseInt(st.nextToken());
		String s=f.readLine().trim();
		int N=s.length();
		int lastZeroInd=N;
		while(s.charAt(lastZeroInd-1)=='0') {
			lastZeroInd--;
		}
		if(lastZeroInd<N) {
			s=s.substring(0,lastZeroInd);
		}
		if(s.charAt(s.length()-1)=='.') {
			s=s.substring(0,s.length()-1);
		}
		int decInd=s.indexOf('.');
		if(decInd==-1) {
			System.out.println(s);
			System.exit(0);
		}
		boolean changed=true;
		while(K>0&&decInd!=-1&&changed) {
			K--;
			boolean cont=true;
			changed=false;
			for(int i=decInd+1; i<N&&cont; i++) {
				if(s.charAt(i)>='5') {
					changed=true;
					s=update(s.substring(0, i));
					N=s.length();
					if(decChange==true) {
						decInd=-1;
					}
					cont=false;
				}
			}
		}
		System.out.println(s);
	}
	public static String updateInt(String x) {
		int lastNine=x.length();
		while(lastNine>0&&x.charAt(lastNine-1)=='9')
			lastNine--;
		if(lastNine==0) {
			StringBuilder s=new StringBuilder();
			s.append('1');
			for(int i=0; i<x.length(); i++) {
				s.append('0');
			}
			return s.toString();
		}
		StringBuilder s=new StringBuilder(x.substring(0, lastNine-1));
		s.append((char)(x.charAt(lastNine-1)+1));
		for(int i=lastNine; i<x.length(); i++) {
			s.append('0');
		}
		return s.toString();
	}
	public static String update(String x) {
		for(int i=x.length()-1; i>=0; i--) {
			if(x.charAt(i)=='.') {
				decChange=true;
				if(i==0) {
					return "1";
				}
				return updateInt(x.substring(0,i));
			}
			else if((int)(x.charAt(i)-'0')<9) {
				if((int)(x.charAt(i)-'0')==4&&K>0) {
					K--;
					return update(x.substring(0,i));
				}
				StringBuilder s=new StringBuilder(x.substring(0,i));
				s.append((char)(x.charAt(i)+1));
				return s.toString();
			}
		}
		return "";
	}
}
