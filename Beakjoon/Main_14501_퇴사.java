import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 퇴사
 * 완전탐색(Brute Force)으로 풀기
 */
public class Main_14501_퇴사 {

	private static int[][] arr;
	private static int N;
	private static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		arr = new int[N+1][2];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//-------------입력부

		go(1, 0);			
		
		System.out.println(max);
		
	}

	private static void go(int index, int sum) {
//		System.out.println("index : "+ index + " sum : "+sum);
		
		if(max < sum)
			max = sum;
		
		if(index > N)
			return;
		
		// 내가 오늘(index) 상담을 할 수 있는지 검사
		int tmp = index + arr[index][0];
		if(tmp <= N + 1) {
			go(tmp, sum + arr[index][1]); // 오늘(index) 상담 해보기
		}
		go(index + 1, sum); // 오늘(index) 상담 안해보기
		
	}

}
