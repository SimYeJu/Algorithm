package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 성공!!! 
 * 시뮬..?
 */

public class Main_16987_계란으로계란치기 {

	private static int[][] eggs;
	private static int N, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		eggs = new int[N][2];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken()); // 내구도
			eggs[i][1] = Integer.parseInt(st.nextToken()); // 무게
		}

		breakEgg(0);

		System.out.println(ans);
	}

	public static void breakEgg(int nowEggIndex) {
		if (nowEggIndex == N) {
			int tmp = 0;
			for (int i = 0; i < N; i++) {
				if (eggs[i][0] <= 0)
					tmp++;
			}

			if (tmp > ans)
				ans = tmp;

			return;
		}
		if (eggs[nowEggIndex][0] <= 0) {
			breakEgg(nowEggIndex + 1);

		} else {
			for (int i = 0; i < N; i++) {
				if (i == nowEggIndex) continue;
				if (eggs[i][0] <= 0) continue;

				// 내 내구도와 상대 무게 빼기
				eggs[i][0] = eggs[i][0] - eggs[nowEggIndex][1];
				eggs[nowEggIndex][0] = eggs[nowEggIndex][0] - eggs[i][1];
				breakEgg(nowEggIndex + 1);
				eggs[i][0] = eggs[i][0] + eggs[nowEggIndex][1];
				eggs[nowEggIndex][0] = eggs[nowEggIndex][0] + eggs[i][1];
			}
			
			if(nowEggIndex == N-1)
				breakEgg(nowEggIndex + 1);
		}
	}
}
