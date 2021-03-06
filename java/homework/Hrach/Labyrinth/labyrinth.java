class Labyrinth {
	final static int c = 4;//columns
	final static int r = 5;//rows
	static int si, sj;//start point
	static int ei, ej;//end point
	final static int wall = r*c;
	final static int way = wall-1;
	final static int end = wall-2;
	final static int start = 0;
	final static int shortestWay = -1;
	static int[ ][ ] array = new int[r][c];
	static int k = 0;//counter for steps

	public static void main(String args[]) {
		arrayElements();
		printLabirint();
		System.out.println();
		applyLeeAlgorithm(si, sj, k);
		labirintNormalization();
		printLabirint();
	}

    // AREG: use verbs for method names
    // AREG: think about splitting data and algorithm into 2 different classes
	public static void arrayElements () {
		array[0][0] = way;
		array[0][1] = wall;
		array[0][2] = way;
		array[0][3] = way;
		array[1][0] = way;
		array[1][1] = start;
		si = 1;
		sj = 1;
		array[1][2] = way;
		array[1][3] = wall;
		array[2][0] = way;
		array[2][1] = wall;
		array[2][2] = wall;
		array[2][3] = way;
		array[3][0] = way;
		array[3][1] = way;
		array[3][2] = way;
		array[3][3] = wall;
		array[4][0] = way;
		array[4][1] = way;
		array[4][2] = end;
		ei = 4;
		ej = 2;
		array[4][3] = way;
	}

	public static void printLabirint () {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (array[i][j]==start) {
					System.out.print("s ");
				} else if (array[i][j]==end) {
					System.out.print("e ");
				} else if (array[i][j]==wall) {
					System.out.print("# ");
				} else if (array[i][j]==shortestWay) {
					System.out.print("* ");
				} else {
					System.out.print("+ ");
				}
			}
			System.out.println();
		}
	}

	public static void applyLeeAlgorithm (int x, int y, int weight) {
        if (x > -1 && x < r && y > -1 && y < c && array[x][y] != end && array[x][y] != wall ) {
            if (array[x][y] >= weight) {
                array[x][y] = weight;
            applyLeeAlgorithm(x - 1, y, weight + 1);
            applyLeeAlgorithm(x + 1, y, weight + 1);
            applyLeeAlgorithm(x, y - 1, weight + 1);
            applyLeeAlgorithm(x, y + 1, weight + 1);
            }
        }
    }


    // AREG: this method is not necesary, print labyrinth as you want in the "print" method
	public static void labirintNormalization () {//indicates the shortest path
		int i = ei;
		int j = ej;
		k = end;
		if (i-1 > -1 && array[i-1][j] < k) {
			k = array[i-1][j];
		}
		if (j-1 > -1 && array[i][j-1] < k) {
			k = array[i][j-1];
		}
        if (i+1 < r && array[i+1][j] < k) {
            k = array[i+1][j];
        }
        if (j+1 < c && array[i][j+1] < k) {
            k = array[i][j+1];
        }
		while (k > start) {
			if (i-1 > -1 && array[i-1][j] == k) {
				array[i-1][j] = shortestWay;
				i--;
			} else if (j-1 > -1 && array[i][j-1] == k) {
				array[i][j-1] = shortestWay;
				j--;
			} else if (i+1 < r && array[i+1][j] == k) {
				array[i+1][j] = shortestWay;
				i++;
			} else if (j+1 < c && array[i][j+1] == k) {
				array[i][j+1] = shortestWay;
				j++;
			}
		k--;
		}
	}
}
