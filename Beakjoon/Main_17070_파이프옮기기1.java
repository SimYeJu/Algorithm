package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 성공!
 * 파이프가 끝까지 다 안갔을때로 계산했어서 틀렸었다.
 */
public class Main_17070_파이프옮기기1 {

	private static int N, ans;
	private static int[][] map;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int RIGHTDOWN = 3;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, RIGHT);

		System.out.println(ans);
	}

	private static void dfs(int r, int c, int state) {
		if ( c == N - 1 &&r == N - 1) {
			ans++;
			return;
		}

		// 현재 파이프의 상태가 우, 하, 하우이면
		if (state == RIGHT) {
			// 오른쪽으로 가보기
			if (c + 1 < N && map[r][c + 1] == 0) {
				dfs(r, c + 1, RIGHT);
			}
			// 오른쪽아래로 가보기
			if (c + 1 < N && r + 1 < N && map[r + 1][c + 1] == 0 && map[r][c + 1] == 0 && map[r + 1][c] == 0) {
				dfs(r + 1, c + 1, RIGHTDOWN);
			}
		} else if (state == DOWN) {
			// 아래로 가보기
			if (r + 1 < N && map[r + 1][c] == 0) {
				dfs(r + 1, c, DOWN);
			}
			// 오른쪽아래로 가보기
			if (c + 1 < N && r + 1 < N && map[r + 1][c + 1] == 0 && map[r][c + 1] == 0 && map[r + 1][c] == 0) {
				dfs(r + 1, c + 1, RIGHTDOWN);
			}
		} else {// 우하이면
			// 오른쪽으로 가보기
			if (c + 1 < N && map[r][c + 1] == 0) {
				dfs(r, c + 1, RIGHT);
			}
			// 아래로 가보기
			if (r + 1 < N && map[r + 1][c] == 0) {
				dfs(r + 1, c, DOWN);
			}
			// 오른쪽아래로 가보기
			if (c + 1 < N && r + 1 < N && map[r + 1][c + 1] == 0 && map[r][c + 1] == 0 && map[r + 1][c] == 0) {
				dfs(r + 1, c + 1, RIGHTDOWN);
			}
		}

	}

}
