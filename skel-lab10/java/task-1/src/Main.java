import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 200005;

		int n;
		int m;
		int[] parent;
		int[] size;

		public class Edge {
			public int node1;
			public int node2;
			public int cost;

			Edge(int _node1, int _node2, int _cost) {
				node1 = _node1;
				node2 = _node2;
				cost = _cost;
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge> edges = new ArrayList<>();

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();

				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					edges.add(new Edge(x, y, w));
				}
				parent = new int[n + 1];
				size = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					parent[i] = i;
					size[i] = 1;
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

		private int findRoot(int node) {
			if (node == parent[node]) {
				return node;
			}
			return parent[node] = findRoot(parent[node]);
		}

		private void mergeForests(int root1, int root2) {
			if (size[root1] <= size[root2]) {
				size[root2] += size[root1];
				parent[root1] = root2;
			} else {
				size[root1] += size[root2];
				parent[root2] = root1;
			}
		}

		private int getResult() {
			/*
			TODO: Calculati costul minim al unui arbore de acoperire
			folosind algoritmul lui Kruskal.
			*/
			int minCost = 0;
			List<Edge> AMA = new ArrayList<>();
			Collections.sort(edges, (x, y) -> x.cost - y.cost);
			for (Edge e : edges) {
				if (AMA.size() == n - 1)
					break;
				if (findRoot(e.node1) != findRoot(e.node2)) {
					AMA.add(e);
					minCost += e.cost;
					mergeForests(findRoot(e.node1), findRoot(e.node2));
				}
			}
			return minCost;
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
