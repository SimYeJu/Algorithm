package Jan2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1249_D4_보급로 {

	private static int N, ans = Integer.MAX_VALUE;
	private static char[][] arr;
	private static int[][] memo;
//	private static int[] dr = { -1, 1, 0, 0 }; // dfs로 풀 때 상하좌우로 탐색하면 터지더라!!
//	private static int[] dc = { 0, 0, -1, 1 };
	private static int[] dr = { 1, 0, -1, 0 }; // 하 우 상 좌로 탐색해야 안터진다.
	private static int[] dc = { 0, 1, 0, -1 };
	private static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine()); // 배열 사이즈

			arr = new char[N][N];
			visited = new boolean[N][N];
			ans = Integer.MAX_VALUE;
			memo = new int[N][N];

			for (int i = 0; i < N; i++) {
				arr[i] = in.readLine().toCharArray();
			}

			// 시작점 0,0
			// 도착점 N-1,N-1
			visited[0][0] = true;
			dfs(0, 0, 0);

			System.out.println("#" + t + " " + ans);
		}

	}

	private static void dfs(int i, int j, int sum) {
		if (ans <= sum)
			return;

		if (i == N - 1 && j == N - 1) {
			if (ans > sum)
				ans = sum;
			return;
		}

		if (memo[i][j] == 0)
			memo[i][j] = sum;
		else {
			if (memo[i][j] > sum)
				memo[i][j] = sum;
			else
				return;
		}

		for (int k = 0; k < 4; k++) {
			int nr = i + dr[k];
			int nc = j + dc[k];

			if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, sum + (arr[nr][nc] - '0'));
				visited[nr][nc] = false;
			}
		}

	}

}
