package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 8월에 실패했던 치킨배달..ㅠㅠ
 * 성공!!
 */
public class Main_15686_치킨배달 {

	private static List<Info> chickenList, homeList;
	private static int[][] map;
	private static int N, M, ans = Integer.MAX_VALUE, totalChichen;
	private static boolean[] chickenVisited;
	private static int[] Distance;


	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 최대 M개를 살려야한다.
		
		map = new int[N][N];
		chickenList = new ArrayList<>();
		homeList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					homeList.add(new Info(i,j));
				if(map[i][j] == 2)
					chickenList.add(new Info(i,j));
			}
		}
		
		totalChichen = chickenList.size();
		chickenVisited = new boolean[totalChichen];
		Distance = new int[homeList.size()];
		
		//-----------------입력부
		
		// 각 집의 치킨거리 구하기
		
		//치킨집 M개 고르기 (조합)
		comchicken(0, 0);
		
		//도시의 치킨 거리의 최소값을 출력
		System.out.println(ans);
	}

	private static void comchicken(int index, int cnt) {
		if(cnt >= M) { //조합 완성
//			System.out.println(Arrays.toString(chickenVisited));
			// 각 집들과의 거리 구하러 가자~
			distance();
			return;
		}
		
		if(index >= totalChichen) return;
		
		chickenVisited[index] = true;
		comchicken(index+1, cnt+1);
		chickenVisited[index] = false;
		comchicken(index+1, cnt);
	}
	
	private static void distance() {
		
		Arrays.fill(Distance, Integer.MAX_VALUE);
		
		for (int i = 0; i < chickenVisited.length; i++) {
			if(!chickenVisited[i]) continue;
			
			// 내가 고른 치킨집이면
			for (int j = 0; j < homeList.size(); j++) {
				// 각 집들과 거리 구하고
				int tmpDis = Math.abs(chickenList.get(i).r - homeList.get(j).r) + Math.abs(chickenList.get(i).c - homeList.get(j).c);
				
				// Distance배열에 더 작은 값으로 저장
				if(Distance[j] > tmpDis) Distance[j] = tmpDis;
			}
		}

		int sum = 0;
		for (int j = 0; j < Distance.length; j++) {
			sum += Distance[j];
		}
		
		if(sum < ans) ans = sum;
	}

	private static class Info{
		int r, c;
		public Info(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
