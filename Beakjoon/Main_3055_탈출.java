package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 성공
 * BFS + 시뮬레이션
 */

public class Main_3055_탈출 {

	private static int R, C, gR, gC, ans;
	private static char[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					gR = i;
					gC = j;
				}
			}
		}

		// ---------입력부

		// idea! 고슴도치 이동하고, 물채우고!

		bfs();

		if (ans == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(ans + 1);
		}

	}

	private static void bfs() {
		Queue<Info> qu = new LinkedList<>();
		Queue<Info> gQu = new LinkedList<>(); // 고슴도치
		gQu.add(new Info(gR, gC));
		visited[gR][gC] = true;
		// 물이 현재 어느 위치에 있는지 확인
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '*')
					qu.add(new Info(i, j));
			}
		}

		Info info;
		while (!gQu.isEmpty()) {

			int size = gQu.size();
			for (int i = 0; i < size; i++) {
				info = gQu.poll();
				int r = info.r;
				int c = info.c;
				if (map[r][c] == '*') {
					continue;
				}
				map[r][c] = '.';

				// 고슴도쥐가 갈 수 있는 구역이 있는지 알아보기!
				// 상
				if (r - 1 >= 0 && (map[r - 1][c] == '.' || map[r - 1][c] == 'D') && !visited[r-1][c]) { //
					if (check(r - 1, c))
						return;
					map[r - 1][c] = 'S';
					gQu.add(new Info(r - 1, c));
				}
				// 하
				if (r + 1 < R && (map[r + 1][c] == '.' || map[r + 1][c] == 'D') && !visited[r+1][c]) {
					if (check(r + 1, c))
						return;
					map[r + 1][c] = 'S';
					gQu.add(new Info(r + 1, c));
				}
				// 좌
				if (c - 1 >= 0 && (map[r][c - 1] == '.' || map[r][c - 1] == 'D') && !visited[r][c-1]) {
					if (check(r, c - 1))
						return;
					map[r][c - 1] = 'S';
					gQu.add(new Info(r, c - 1));
				}

				// 우
				if (c + 1 < C && (map[r][c + 1] == '.' || map[r][c + 1] == 'D') && !visited[r][c+1]) {
					if (check(r, c + 1))
						return;
					map[r][c + 1] = 'S';
					gQu.add(new Info(r, c + 1));
				}
			}

			if (gQu.isEmpty()) {
				ans = -1;
				return;
			}

			ans++;

			// 물 채우기!
			size = qu.size();
			for (int i = 0; i < size; i++) {
				info = qu.poll();
				int r = info.r;
				int c = info.c;

				// 상
				if (r - 1 >= 0 && (map[r - 1][c] == '.' || map[r - 1][c] == 'S')) {
					map[r - 1][c] = '*'; // 물이 찰 수 있는 지역이면
					qu.add(new Info(r - 1, c));
				}
				// 하
				if (r + 1 < R && (map[r + 1][c] == '.' || map[r + 1][c] == 'S')) {
					map[r + 1][c] = '*'; // 물이 찰 수 있는 지역이면
					qu.add(new Info(r + 1, c));
				}
				// 좌
				if (c - 1 >= 0 && (map[r][c - 1] == '.' || map[r][c - 1] == 'S')) {
					map[r][c - 1] = '*'; // 물이 찰 수 있는 지역이면
					qu.add(new Info(r, c - 1));
				}

				// 우
				if (c + 1 < C && (map[r][c + 1] == '.' || map[r][c + 1] == 'S')) {
					map[r][c + 1] = '*'; // 물이 찰 수 있는 지역이면
					qu.add(new Info(r, c + 1));
				}
			}

		}

	}

	public static boolean check(int r, int c) {
		return map[r][c] == 'D' ? true : false;
	}

	private static class Info {
		int r, c;

		public Info(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
