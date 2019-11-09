package November;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_D4_4261_빠른휴대전화키패드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			HashMap<String, Integer> map = new HashMap<>();
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			String S = st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			int cnt = 0;
			
			String[] arr = new String[N];
			arr = in.readLine().split(" ");
			
			//-----------------입력부
			
			//해시맵에 저장
			map.put("a", 2);
			map.put("b", 2);
			map.put("c", 2);
			map.put("d", 3);
			map.put("e", 3);
			map.put("f", 3);
			map.put("g", 4);
			map.put("h", 4);
			map.put("i", 4);
			map.put("j", 5);
			map.put("k", 5);
			map.put("l", 5);
			map.put("m", 6);
			map.put("n", 6);
			map.put("o", 6);
			map.put("p", 7);
			map.put("q", 7);
			map.put("r", 7);
			map.put("s", 7);
			map.put("t", 8);
			map.put("u", 8);
			map.put("v", 8);
			map.put("w", 9);
			map.put("x", 9);
			map.put("y", 9);
			map.put("z", 9);
			
			for (int i = 0; i < N; i++) {
				String tmp = arr[i];
				String ans = "";
				for (int j = 0; j < tmp.length() ; j++) {
					String str = tmp.substring(j, j+1);
					ans += map.get(str);
				}
				if(ans.equals(S)) {
					cnt++;
				}
			}
		
			System.out.println("#" + t + " " + cnt);
		}

	}

}
