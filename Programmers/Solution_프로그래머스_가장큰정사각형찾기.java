package Fab2020;

import java.util.LinkedList;
import java.util.Queue;


public class Solution_프로그래머스_가장큰정사각형찾기 {

	public static void main(String[] args) {

//		int[][] board = [[0, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [0, 0, 1, 0]]
		
		int[][] board = {{0,1,1,1}, {1,1,1,1}, {1,1,1,1}, {0,0,1,0}};
//		int[][] board = {{1,1,1,0,0,1,1,1,1},
//				{1,1,1,0,0,1,1,1,1},
//						{1,1,1,0,0,1,1,1,1},
//						{1,1,1,0,0,1,1,1,1}};
//		int[][] board = {{0}};
		int tmp = solution(board);
		System.out.println(tmp);
		
	}
	
	static class info {
		int r, c;

		public info(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	private static int[] dr = { 0, 1, 1 }; // 우, 하, 우하
	private static int[] dc = { 1, 0, 1 };
	public static int solution(int [][]board)
    {
		int ans = 0;
		
		int size_1 = board.length;
		int size_2 = board[0].length;
		
		for (int i = 0; i < size_1; i++) {
			if(size_1 - i <=  ans) break;
			
			for (int j = 0; j < size_2; j++) {
				if(size_2 - j <= ans) break;
				if(board[i][j] != 0) {
					int tmp = search(i,j,size_1,size_2,board);
					if(ans < tmp)
						ans = tmp;
				}
				
			}
			
		}
		
        return ans*ans;
    }
	
	private static int search(int i, int j, int size1, int size2, int[][] board) {
		Queue<info> qu = new LinkedList<>();
		qu.add(new info(i, j));
		
		boolean[][] visited = new boolean[size1][size2];
		visited[i][j] = true;
		int size = 1;

		exit: while (true) {
			int quSize = qu.size();
			
			for (int k = 0; k < quSize; k++) {
				info dot = qu.poll();
				int r = dot.r;
				int c = dot.c;

				for (int t = 0; t < 3; t++) {
					int nr = r + dr[t];
					int nc = c + dc[t];
					if (nr >= 0 && nc >= 0 && nr < size1 && nc < size2) {
						if (board[nr][nc] != 1)
							break exit;
						if (!visited[nr][nc]) {
							visited[nr][nc] = true;
							qu.add(new info(nr, nc));
						}
					} else
						break exit;
				}
			}

			size++;
		}
		return size;

	}

}
