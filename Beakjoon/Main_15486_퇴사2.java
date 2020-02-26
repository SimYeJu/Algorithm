import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 퇴사2
 * 동적 계획법(DP)으로 풀기
 */
public class Main_15486_퇴사2 {

	private static int[] T, P;
	private static int N;
	private static int max;
	private static int[] DP;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		T = new int[N + 2];
		P = new int[N + 2];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		//-------------입력부
		
		// DP[i]는 i일까지 일했을 때 버는 최고 수익을 의미한다.
		DP = new int[N + 2]; 
		/*
		 * N + 2가 된 이유는?
		 * 1. index 0을 사용하지 않고 1부터 사용하려고 +1 되었고, 
		 * 2. i번째까지 일한 돈은 i+1번째날에 받기 때문에 +1이 또 되었다. 그래서 총 + 2이다.
		 */
		
		for (int i = 1; i < N + 2; i++) {
		
			// 현재 시점까지의 최대 수익을 알아야 i + T[i]까지 일했을 때 P[i]를 더해서 최대 수익을 낼 수 있다.
			if(max < DP[i])
				max = DP[i];
			
			// day날까지 일했을 때, max + P[i]와 이미 구해진 그 날의 수익 중에 최대 수익을 택하자! 
			int day = i + T[i];
			if(day < N + 2)
				DP[day] = Math.max(DP[day], max + P[i]);
			
		}
	
		System.out.println(max);
	}

}
