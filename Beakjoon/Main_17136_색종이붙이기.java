package Jan2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 0120
 * 그리디 방식으로 접근했는데 완탐으로 해야한다더라...
 * 그리디 방식 : 큰 색종이부터 붙이기
 * 완탐 방식 : 붙일 수 있는 모든 경우의 수를 찾기
 */

public class Main_17136_색종이붙이기 {

	private static int[] confetti = { 0, 5, 5, 5, 5, 5 };
	private static boolean[][] visited;
	private static int[][] arr;
	private static ArrayList<dot> squre;

	private static int[] dr = { 0, 1, 1 }; // 우, 하, 우하
	private static int[] dc = { 1, 0, 1 };

	static class info {
		int r, c;

		public info(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class dot {
		int leftTopR, leftTopC, rightDownR, rightDownC, size;

		public dot(int leftTopX, int leftTopY, int rightDownX, int rightDownY, int size) {
			this.leftTopR = leftTopX;
			this.leftTopC = leftTopY;
			this.rightDownR = rightDownX;
			this.rightDownC = rightDownY;
			this.size = size;
		}

		@Override
		public String toString() {
			return "dot [leftTopX=" + leftTopR + ", leftTopY=" + leftTopC + ", rightDownX=" + rightDownR
					+ ", rightDownY=" + rightDownC + ", size=" + size + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		arr = new int[10][10];
		visited = new boolean[10][10];
		squre = new ArrayList<>();
		int total1 = 0;
		int usePaper = 0;

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					total1++;
			}
		}

		// ----------------------입력부

		// 왼쪽 위 좌표, 오른쪽 아래 좌표, 사이즈를 저장하자
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (arr[i][j] == 1) {
					int tmpSize = search(i, j);
				}
			}
		}

		// 사이즈 큰 순으로 정렬
		Collections.sort(squre, new Comparator<dot>() {

			@Override
			public int compare(dot o1, dot o2) {
				return o2.size - o1.size;
			}
		});
//		System.out.println(squre.toString());

		// 색종이 붙여보자
		for (int i = 0; i < squre.size(); i++) {
			dot tmp = squre.get(i);
			int startR = tmp.leftTopR;
			int startC = tmp.leftTopC;
			int endR = tmp.rightDownR;
			int endC = tmp.rightDownC;
			int tmpSize = tmp.size;

			// 검사
			boolean flag = false;
			check: for (int r = startR; r <= endR; r++) {
				for (int c = startC; c <= endC; c++) {
					if (arr[r][c] == 0) {
						flag = true;
						break check;
					}
				}
			}

			// 이미 종이가 붙여진 곳이 있으면 다음 정사각형으로 넘어가자
			if (flag)
				continue;
			if (confetti[tmpSize] == 0)
				continue;
			
			// 붙이기
			for (int r = startR; r <= endR; r++) {
				for (int c = startC; c <= endC; c++) {
					arr[r][c] = 0;
					total1--;
				}
			}

			// 색종이 사용한거 빼주기
			confetti[tmpSize] -= 1;
			usePaper++;
			
			for (int k = 0; k < 10; k++) {
				System.out.println(Arrays.toString(arr[k]));
			}
			System.out.println();
		}

		if (total1 > 0)
			System.out.println(-1);
		else
			System.out.println(usePaper);

	}

	// i,j로 시작하는 정사각형 찾기...
	// 아래, 오른쪽, 오른아래 총 세군데 탐색하기
	private static int search(int i, int j) {
		Queue<info> qu = new LinkedList<>();
		qu.add(new info(i, j));
		visited = new boolean[10][10];
		visited[i][j] = true;
		int size = 1;

		exit: while (true) {
			int quSize = qu.size();
			squre.add(new dot(i, j, i + size - 1, j + size - 1, size));
			for (int k = 0; k < quSize; k++) {
				info dot = qu.poll();
				int r = dot.r;
				int c = dot.c;

				for (int t = 0; t < 3; t++) {
					int nr = r + dr[t];
					int nc = c + dc[t];
					if (nr >= 0 && nc >= 0 && nr < 10 && nc < 10) {
						if (arr[nr][nc] == 0)
							break exit;
						if (!visited[nr][nc] && arr[nr][nc] == 1) {
							visited[nr][nc] = true;
							qu.add(new info(nr, nc));
						}
					} else
						break exit;
				}
			}
			
			size++;
			if(size > 5)
				break;
		}
		return size;

	}

}
