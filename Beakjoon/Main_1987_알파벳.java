package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/*
 * 성공! 히히
 * 
 */

public class Main_1987_알파벳 {

	private static char[][] map;
	private static int R, C, ans;
	private static HashMap<Character, Boolean> hashmap;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		hashmap = new HashMap<>();

		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
		}
		for (int i = 0; i < 26; i++) {
			hashmap.put((char) ('A' + i), false);
//			System.out.println("key : " + (char)('A'+i) + " value : " + hashmap.get((char)('A'+i)));
		}

		hashmap.replace(map[0][0], true);
		dfs(0, 0, 1);
		System.out.println(ans);
	}

	public static void dfs(int r, int c, int cnt) {

		if (ans < cnt) {
			ans = cnt;
		}

		if (r - 1 >= 0 && !hashmap.get(map[r - 1][c])) {
			hashmap.replace(map[r - 1][c], true);
			dfs(r - 1, c, cnt + 1);
			hashmap.replace(map[r - 1][c], false);
		}
		if (r + 1 < R && !hashmap.get(map[r + 1][c])) {
			hashmap.replace(map[r + 1][c], true);
			dfs(r + 1, c, cnt + 1);
			hashmap.replace(map[r + 1][c], false);
		}
		if (c - 1 >= 0 && !hashmap.get(map[r][c - 1])) {
			hashmap.replace(map[r][c - 1], true);
			dfs(r, c - 1, cnt + 1);
			hashmap.replace(map[r][c - 1], false);
		}
		if (c + 1 < C && !hashmap.get(map[r][c + 1])) {
			hashmap.replace(map[r][c + 1], true);
			dfs(r, c + 1, cnt + 1);
			hashmap.replace(map[r][c + 1], false);
		}

	}

}
