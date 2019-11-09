package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 성공
 */
public class JMain_1681_해밀턴순환회로 {

	static int N, Min = Integer.MAX_VALUE;
	static boolean[] vi;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		arr = new int[N][N];
		vi = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// -------------입력부

		vi[0] = true;
		delivery(0, 1, 0);

		System.out.println(Min);
	}

	public static void delivery(int start, int cnt, int sum) {

		if (sum >= Min)
			return;

		if (cnt >= N) {
			if (arr[start][0] == 0) // 마지막에서 0으로 갈 수 없으면 리턴하자!
				return;

			sum += arr[start][0];
//			System.out.println("sum : " + sum);

			if (sum < Min)
				Min = sum;

			return;
		}

		for (int i = 1; i < N; i++) { // i는 0이어도 상관없고, 1이어도 상관없다. 왜? 위에서 이미 visited[0]을 true처리 해줬으니까
			if (!vi[i] && arr[start][i] != 0) {
				vi[i] = true;
				delivery(i, cnt + 1, sum + arr[start][i]);
				vi[i] = false;
			}
		}
	}

}
