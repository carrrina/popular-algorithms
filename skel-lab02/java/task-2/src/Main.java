import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n, m;
		int[] dist;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				m = sc.nextInt();
				dist = new int[n];
				for (int i = 0; i < n; i++) {
					dist[i] = sc.nextInt();
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

		private int getResult() {
			// TODO: Aflati numarul minim de opriri necesare pentru
			// a ajunge la destinatie.
			int stops = 0, kmAvailable = m, distanceUntilNext;
			kmAvailable -= dist[0];
			for (int i = 0; i < n - 1; i++) {
				distanceUntilNext = dist[i + 1] - dist[i];
				if (distanceUntilNext > kmAvailable) {
					stops++;
					kmAvailable = m;
				}
				kmAvailable -= distanceUntilNext;
			}
			return stops;
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
