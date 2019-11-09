package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JMain_1733_오목 {

	private static int[][] arr;
	private static int ans, ansR, ansC;
	private static boolean flag = false;

	// 상 하 좌 우 상우 상좌 하좌 하우
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	private static final int UPRIGHT = 5;
	private static final int UPLEFT = 6;
	private static final int DOWNLEFT = 7;
	private static final int DOWNRIGHT = 8;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		arr = new int[19][19];
		for (int i = 0; i < 19; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 19; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// ---------------입력부
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (arr[i][j] != 0) {
					ansR = i;
					ansC = j;

					for (int k = 1; k <= 8; k++) {
						// 반대방향 살펴보기
						if (!reverseCheck(k, i, j)) {
							check(i, j, 0, k);
							if (flag)
								break;
						}
					}
					if (flag)
						break;
				}
			}
			if (flag)
				break;
		}

		if (flag) {
			System.out.println(ans);
			System.out.println(ansR + " " + ansC);
		} else {
			System.out.println(0);
		}
	}

	public static void check(int r, int c, int cnt, int dir) {

		if (cnt == 4) {
			// 진행방향에 더 오목이 있는지 살피기
			if (keep(dir, r, c)) { // 오목이 더 있으면 return;
				flag = false;
				return;
			} else { // 오목이 없으면 우승
				ans = arr[r][c];
				flag = true;
				findLeft(dir, ansR, ansC);
				ansR += 1;
				ansC += 1;
				return;
			}
		}

		// 하 우 상우 하좌 하우
		// 하
		else if (r + 1 < 19 && arr[r][c] == arr[r + 1][c] && dir == DOWN) {
			check(r + 1, c, cnt + 1, DOWN);
		}
		// 우
		else if (c + 1 < 19 && arr[r][c] == arr[r][c + 1] && dir == RIGHT) {
			check(r, c + 1, cnt + 1, RIGHT);
		}
		// 상우
		else if (r - 1 >= 0 && c + 1 < 19 && arr[r][c] == arr[r - 1][c + 1] && dir == UPRIGHT) {
			check(r - 1, c + 1, cnt + 1, UPRIGHT);
		}
		// 하좌
		else if (r + 1 < 19 && c - 1 >= 0 && arr[r][c] == arr[r + 1][c - 1] && dir == DOWNLEFT) {
			check(r + 1, c - 1, cnt + 1, DOWNLEFT);
		}
		// 하우
		else if (r + 1 < 19 && c + 1 < 19 && arr[r][c] == arr[r + 1][c + 1] && dir == DOWNRIGHT) {
			check(r + 1, c + 1, cnt + 1, DOWNRIGHT);
		}

	}

	public static boolean keep(int dir, int i, int j) {
		int tmp = arr[i][j];
		if (dir == DOWN) {
			if (i + 1 < 19 && arr[i + 1][j] == tmp)
				return true;
		} else if (dir == RIGHT) {
			if (j + 1 < 19 && arr[i][j + 1] == tmp)
				return true;
		} else if (dir == UPRIGHT) {
			if (i - 1 >= 0 && j + 1 < 19 && arr[i - 1][j + 1] == tmp)
				return true;
		} else if (dir == DOWNLEFT) {
			if (i + 1 < 19 && j - 1 >= 0 && arr[i + 1][j - 1] == tmp)
				return true;
		} else if (dir == DOWNRIGHT) {
			if (i + 1 < 19 && j + 1 < 19 && arr[i + 1][j + 1] == tmp)
				return true;
		}
		return false;
	}

	public static void findLeft(int dir, int i, int j) {
		if (dir == DOWNLEFT) {
			ansR = i + 4;
			ansC = j - 4;
		}
	}

	public static boolean reverseCheck(int dir, int r, int c) {
		// '하'이면 상 / '우'이면 '좌' / '하우'이면 '상좌'
		if (dir == DOWN) {
			if (r - 1 >= 0 && arr[r - 1][c] == arr[r][c]) {
				return true;
			}
		} else if (dir == RIGHT) {
			if (c - 1 >= 0 && arr[r][c - 1] == arr[r][c]) {
				return true;
			}
		} else if (dir == DOWNRIGHT) {
			if (r - 1 >= 0 && c - 1 >= 0 && arr[r - 1][c - 1] == arr[r][c]) {
				return true;
			}
		}

		return false;
	}

}
