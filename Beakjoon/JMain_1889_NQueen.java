package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JMain_1889_NQueen {

	private static boolean[][] map;
	private static int N, cnt;
	private static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	private static int[] dc = { 0, 0, -1, 1, 1, -1, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		map = new boolean[N][N];

		nqueen(0);

		System.out.println(cnt);
	}

	private static void nqueen(int r) {

		if (r >= N) {
			cnt++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			// 퀸 놓아보고
			map[r][i] = true;
			// 가능한지 검사
			boolean flag = false;
			imp:
			for (int j = 0; j < 8; j++) {
				int nr = r + dr[j];
				int nc = i + dc[j];
				while (0 <= nr && nr < N && 0 <= nc && nc < N) {

					if (map[nr][nc] == true) {
						flag = true;
						break imp;
					}

					nr += dr[j];
					nc += dc[j];
				}
			}

			if (flag) {
				map[r][i] = false;
				if (i == N - 1)
					return;
			} else {
				nqueen(r + 1);
				map[r][i] = false;
			}

		}

	}

}
