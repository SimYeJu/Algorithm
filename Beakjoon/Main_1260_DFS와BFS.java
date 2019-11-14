package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 성공!!
 */
public class Main_1260_DFS와BFS {
	
	private static boolean[] visited;
	private static int[][] adjust;
	private static int[] save;
	private static int N;
	private static int M;
	private static int V, ansIndex;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 갯수
		M = Integer.parseInt(st.nextToken()); // 간선의 갯수
		V = Integer.parseInt(st.nextToken()) - 1; // 탐색을 시작할 정점의 번호

		adjust = new int[N][N];
		visited = new boolean[N];
		save = new int[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());

			int tmp = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			adjust[tmp - 1][tmp2 - 1] = adjust[tmp2 - 1][tmp - 1] = 1;
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(adjust[i]));
//		}

		save[ansIndex++] = V;
		visited[V] = true;
		dfs(V);
		for (int i = 0; i < ansIndex; i++) {
			System.out.print(save[i]+1 + " ");
		}
		System.out.println();
		
		Arrays.fill(save, -1);
		Arrays.fill(visited, false);
		
		ansIndex = 0;
		save[ansIndex++] = V;
		visited[V] = true;
		bfs(V);
		for (int i = 0; i < ansIndex; i++) {
			System.out.print(save[i]+1 + " ");
		}
	}

	private static void bfs(int index) {
		Queue<Integer> qu = new LinkedList<Integer>();
		qu.add(index);
		
		while(!qu.isEmpty()) {
			int tmp = qu.poll();
			
			for (int i = 0; i < N; i++) {
				if(adjust[tmp][i] == 1 && !visited[i]) {
					save[ansIndex++] = i;
					visited[i] = true;
					qu.add(i);
				}
			}
		}
		
	}

	private static void dfs(int index) {
		
		for (int i = 0; i < N; i++) {
			if(adjust[index][i] == 1 && !visited[i]) {
				save[ansIndex++] = i;
				visited[i] = true;
				dfs(i);
			}
		}
	}

}
