import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n;
		int[] v;

		private final static int MOD = 1000000007;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				v = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					v[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public int getResult() {
			// TODO: Aflati numarul de subsiruri (ale sirului stocat in v,
			// indexat de la 1 la n), nevide cu suma numerelor para.
			// Rezultatul se va intoarce modulo MOD (1000000007).
			int [][]dp = new int[n + 1][2];
			dp[0][0] = 1;
			dp[0][1] = 0;
			for (int i = 1; i <= n; i++) {
				if (v[i] % 2 == 0) {
					dp[i][0] = (2 * dp[i - 1][0]) % MOD;
					dp[i][1] = (2 * dp[i - 1][1]) % MOD;
				} else {
					dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
					dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
				}
			}	
			return dp[n][0] - 1;
		}


		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
