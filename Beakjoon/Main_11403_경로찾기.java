package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DFS
 *  그래프의 인접 행렬(간선)이 주어지고, 정점 i에서 j로 가는 경로가 있으면 1, 없으면 0인 배열을 출력하는 문제
 */
public class Main_11403_경로찾기 {

	private static int[][] arr;
	private static int N;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(i);
			for (int j = 0; j < N; j++) {
				if (visited[j])
					System.out.print(1 + " ");
				else
					System.out.print(0 + " ");
			}
			System.out.println();
		}

	}

	public static void dfs(int index) {
		for (int i = 0; i < N; i++) {
			if (arr[index][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
	}

}
