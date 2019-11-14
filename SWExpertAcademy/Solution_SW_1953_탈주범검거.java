package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 성공!
 * 시뮬레이션 문제~
 */
public class Solution_SW_1953_탈주범검거 {

	private static int N, M, R, C, L;
	private static int[][] map;
	private static boolean[][] visited;
	private static int PLUS = 1;
	private static int TOPDOWN = 2;
	private static int LEFTRIGHT = 3;
	private static int TOPRIGHT = 4;
	private static int DOWNRIGHT = 5;
	private static int DOWNLEFT = 6;
	private static int TOPLEFT = 7;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로크기
			M = Integer.parseInt(st.nextToken()); // 가로크기
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑이 위치한 장소 R ( 시작 지점 )
			C = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑이 위치한 장소 C ( 시작 지점 )
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간

			map = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(visited[i][j]) ans++;
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void bfs() {
		Queue<int[]> qu = new LinkedList<int[]>();
		qu.add(new int[] { R, C });
		visited[R][C] = true;
		int cnt = 1;
		while (!qu.isEmpty()) {
			if(cnt >= L) return;
			cnt++;
			int size = qu.size();
			for (int i = 0; i < size; i++) {

				int[] tmp = qu.poll();
				int r = tmp[0];
				int c = tmp[1];

				if (map[r][c] == PLUS) { // 갈 수 있는 방향은 상하좌우
					if (r - 1 >= 0 && map[r - 1][c] != 0 && map[r-1][c] != 3 && map[r-1][c] != 4 && map[r-1][c] != 7 && !visited[r - 1][c]) { // 상
						visited[r - 1][c] = true;
						qu.add(new int[] { r - 1, c });
					}
					if (r + 1 < N && map[r + 1][c] != 0 && map[r+1][c] != 3 && map[r+1][c] != 5 && map[r+1][c] != 6 && !visited[r + 1][c]) { // 하
						visited[r + 1][c] = true;
						qu.add(new int[] { r + 1, c });
					}
					if (c - 1 >= 0 && map[r][c - 1] != 0 && map[r][c - 1] != 2 &&map[r][c - 1] != 6 &&map[r][c - 1] != 7 && !visited[r][c - 1]) { // 좌 
						visited[r][c - 1] = true;
						qu.add(new int[] { r, c - 1 });
					}
					if (c + 1 < M && map[r][c + 1] != 0 && map[r][c + 1] != 2 && map[r][c + 1] != 4 && map[r][c + 1] != 5 && !visited[r][c + 1]) { // 우 
						visited[r][c + 1] = true;
						qu.add(new int[] { r, c + 1 });
					}
				} else if (map[r][c] == TOPDOWN) { // 갈 수 있는 방향은 상, 하
					if (r - 1 >= 0 && map[r - 1][c] != 0 && map[r-1][c] != 3 && map[r-1][c] != 4 && map[r-1][c] != 7 && !visited[r - 1][c]) { // 상
						visited[r - 1][c] = true;
						qu.add(new int[] { r - 1, c });
					}
					if (r + 1 < N && map[r + 1][c] != 0 && map[r+1][c] != 3 && map[r+1][c] != 5 && map[r+1][c] != 6 && !visited[r + 1][c]) { // 하
						visited[r + 1][c] = true;
						qu.add(new int[] { r + 1, c });
					}
				} else if (map[r][c] == LEFTRIGHT) { // 갈 수 있는 방향은 좌, 우
					if (c - 1 >= 0 && map[r][c - 1] != 0 && map[r][c - 1] != 2 &&map[r][c - 1] != 6 &&map[r][c - 1] != 7 && !visited[r][c - 1]) { // 좌 
						visited[r][c - 1] = true;
						qu.add(new int[] { r, c - 1 });
					}
					if (c + 1 < M && map[r][c + 1] != 0 && map[r][c + 1] != 2 && map[r][c + 1] != 4 && map[r][c + 1] != 5 && !visited[r][c + 1]) { // 우 
						visited[r][c + 1] = true;
						qu.add(new int[] { r, c + 1 });
					}
				} else if (map[r][c] == TOPRIGHT) { // 갈 수 있는 방향은 상, 우
					if (r - 1 >= 0 && map[r - 1][c] != 0 && map[r-1][c] != 3 && map[r-1][c] != 4 && map[r-1][c] != 7 && !visited[r - 1][c]) { // 상
						visited[r - 1][c] = true;
						qu.add(new int[] { r - 1, c });
					}
					if (c + 1 < M && map[r][c + 1] != 0 && map[r][c + 1] != 2 && map[r][c + 1] != 4 && map[r][c + 1] != 5 && !visited[r][c + 1]) { // 우 
						visited[r][c + 1] = true;
						qu.add(new int[] { r, c + 1 });
					}
				} else if (map[r][c] == DOWNRIGHT) { // 갈 수 있는 방향은 하, 우
					if (r + 1 < N && map[r + 1][c] != 0 && map[r+1][c] != 3 && map[r+1][c] != 5 && map[r+1][c] != 6 && !visited[r + 1][c]) { // 하
						visited[r + 1][c] = true;
						qu.add(new int[] { r + 1, c });
					}
					if (c + 1 < M && map[r][c + 1] != 0 && map[r][c + 1] != 2 && map[r][c + 1] != 4 && map[r][c + 1] != 5 && !visited[r][c + 1]) { // 우 
						visited[r][c + 1] = true;
						qu.add(new int[] { r, c + 1 });
					}
				} else if (map[r][c] == DOWNLEFT) { // 갈 수 있는 방향은 하, 좌
					if (r + 1 < N && map[r + 1][c] != 0 && map[r+1][c] != 3 && map[r+1][c] != 5 && map[r+1][c] != 6 && !visited[r + 1][c]) { // 하
						visited[r + 1][c] = true;
						qu.add(new int[] { r + 1, c });
					}
					if (c - 1 >= 0 && map[r][c - 1] != 0 && map[r][c - 1] != 2 &&map[r][c - 1] != 6 &&map[r][c - 1] != 7 && !visited[r][c - 1]) { // 좌 
						visited[r][c - 1] = true;
						qu.add(new int[] { r, c - 1 });
					}
				} else if (map[r][c] == TOPLEFT) { // 갈 수 있는 방향은 상, 좌
					if (r - 1 >= 0 && map[r - 1][c] != 0 && map[r-1][c] != 3 && map[r-1][c] != 4 && map[r-1][c] != 7 && !visited[r - 1][c]) { // 상
						visited[r - 1][c] = true;
						qu.add(new int[] { r - 1, c });
					}
					if (c - 1 >= 0 && map[r][c - 1] != 0 && map[r][c - 1] != 2 &&map[r][c - 1] != 6 &&map[r][c - 1] != 7 && !visited[r][c - 1]) { // 좌 
						visited[r][c - 1] = true;
						qu.add(new int[] { r, c - 1 });
					}
				}

			}

		}

	}
}
