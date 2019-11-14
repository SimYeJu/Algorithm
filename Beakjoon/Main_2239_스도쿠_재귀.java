package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 성공!
 * 백트래킹은 종료지점에 오면 출력하고 끝내야한다!
 */
public class Main_2239_스도쿠_재귀 {

	private static int[][] sudoku;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		sudoku = new int[9][9];

		for (int i = 0; i < 9; i++) {
			String str = in.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = str.charAt(j) - '0';
			}
		}
		sudoku(0, 0);
	}

	private static void sudoku(int r, int c) {

		if (r == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
			return;
		}

		if (r < 9 && c < 9 && r >= 0 && c >= 0 && sudoku[r][c] == 0) {

			for (int i = 1; i < 10; i++) {
				boolean flag = false;

				// 행체크
				for (int j = 0; j < 9; j++) {
					if (i == sudoku[r][j]) {
						flag = true;
						break;
					}
				}

				if (flag)
					continue;

				// 열체크
				for (int j = 0; j < 9; j++) {
					if (i == sudoku[j][c]) {
						flag = true;
						break;
					}
				}

				if (flag)
					continue;

				// 해당하는 3X3칸 체크
				if (r <= 2) {
					if (c <= 2)
						flag = check3X3(0, 0, i);
					else if (c <= 5)
						flag = check3X3(0, 3, i);
					else
						flag = check3X3(0, 6, i);
				} else if (r <= 5) {
					if (c <= 2)
						flag = check3X3(3, 0, i);
					else if (c <= 5)
						flag = check3X3(3, 3, i);
					else
						flag = check3X3(3, 6, i);
				} else {
					if (c <= 2)
						flag = check3X3(6, 0, i);
					else if (c <= 5)
						flag = check3X3(6, 3, i);
					else
						flag = check3X3(6, 6, i);
				}

				if (flag)
					continue;

				sudoku[r][c] = i; // 지정해보기

				if (c == 8) {
					sudoku(r + 1, 0);

				} else
					sudoku(r, c + 1);

				sudoku[r][c] = 0;
			} // end for

			return;
		} // end if

		if (c == 8) {
			sudoku(r + 1, 0);
		} else
			sudoku(r, c + 1);

	}// end sudoku

	public static boolean check3X3(int startR, int startC, int checknum) {
		for (int i = startR; i < startR + 3; i++) {
			for (int j = startC; j < startC + 3; j++) {
				if (checknum == sudoku[i][j])
					return true;
			}
		}
		return false;
	}

}
