package Jan2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 2020년 01월 14일
 * 바이너리 서치
 * 성공!
 * 주의! >= or > 중에 어떤건지, 왜 start+1 end-1인지 세부 조건을 제대로 이해하자!
 */
public class Main_1654_랜선자르기 {

	private static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		arr = new long[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 오름차순 정렬
		Arrays.sort(arr);

		long start = 1;
		long end = arr[K - 1];
		long ans = 0;

		long mid = (start + end) / 2;

		while (start <= end) {
			int tmp = 0;

			for (int i = 0; i < K; i++) {
				tmp += arr[i] / mid;
			}

			if (tmp >= N) { // mid를 키워야한다.
				if (ans < mid) ans = mid;
				start = mid+1;
			} else { // mid가 작아져야한다.
				end = mid-1;
			}
			
			mid = (start + end) / 2;

		}

		System.out.println(ans);
	}

}
