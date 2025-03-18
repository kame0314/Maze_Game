package 迷路作成2;

import java.util.*;

public class Maze {

	static int[][] Maze;
	static int StartW;// スタート位置から穴を開けていくRowを保持する
	static int StartH;// スタート位置から穴を開けていくColを保持する
	static int GoalW;// スタート位置から穴を開けていくRowを保持する
	static int GoalH;// スタート位置から穴を開けていくColを保持する
	static int S_POSITION_W;// スタート位置のRowを保持する //final にしたかった
	static int S_POSITION_H;// スタート位置のColを保持する //final にしたかった
	static Stack<Integer> rowStack = new Stack<Integer>();// Rowの値を一時保存する
	static Stack<Integer> colStack = new Stack<Integer>();// Colの値を一時保存する
	static int MazesizeW = 0;// 入力された迷路サイズのRowを保持する
	static int MazesizeH = 0;// 入力された迷路サイズのColを保持する

	// Width*Higthの迷路の作成をする
	// 壁は「1」 空白は「0」 スタートは「2」 ゴールは「3」 で表す
	public static void makeMaze(int w, int h) {
		MazesizeW = w;
		MazesizeH = h;
		Random rand = new Random();// ランダム関数
		Maze = new int[w][h];
		for (int i = 0; i < MazesizeW; i++) {
			for (int j = 0; j < MazesizeH; j++) {
				Maze[i][j] = 1;
				System.out.print(Maze[i][j]);
			}
			System.out.println("");
		}
		// 穴を開けていくスタート位置を決める
		while (true) {
			StartW = rand.nextInt(MazesizeW - 1);// NPCスタート位置のRow
			StartH = rand.nextInt(MazesizeH - 1);// NPCスタート位置のColumn

			if (StartW == 1 && StartH != 0 || StartH == 1 && StartW != 0) {
				break;
			}
		}

		S_POSITION_W = StartW;
		S_POSITION_H = StartH;
		Maze[StartW][StartH] = 0;

		rowStack.push(StartW);
		colStack.push(StartH);

		boolean continueFlag = true;

		// スタート位置から穴を開けていく
		// 条件は上下左右を確認し、上下左右の2マス先が道でなければ穴を開ける
		// 開ける際は移動可能な場所の中でランダムに決定する
		// １つ目の穴あけが完了後、穴が空いている箇所のランダムな位置から同じ条件で穴を広げていくを繰り返す
		// 以下、wall[][]全体を埋めるまで繰り返し

		while (continueFlag) {

			// 上下左右のいずれかに限界まで道を伸ばす
			extendPath();

			// 既にある道から次の開始位置を選ぶ（0 〜 mazeSize - 1（かつ 偶数？））
			continueFlag = false;
			while (!rowStack.empty() && !colStack.empty()) {
				System.out.println("1 新たに道を作成する");

				StartW = rowStack.pop();
				StartH = colStack.pop();

				if (canExtendPath()) {
					continueFlag = true;
					break;
				}

			}
		}
	}

	// 道を拡張するメソッド
	public static void extendPath() {
		boolean extendFlag = true;

		while (extendFlag) {
			extendFlag = extendPathSub();
		}
	}

	// 道の拡張に成功したらtrue、失敗したらfalseを返すメソッド
	public static boolean extendPathSub() {

		Random rmd = new Random();
		// 上: 0, 下: 1, 左: 2, 右: 3
		int direction = rmd.nextInt(4);
		for (int i = 0; i < 4; i++) {
			direction = (direction + i) % 4;

			if (canExtendPathWithDir(direction)) {
				movePoint(direction);
				return true;
			}

		}

		return false;
	}

	// 指定した方向へ拡張可能ならばtrue、不可能ならばfalseを返すメソッド
	public static boolean canExtendPathWithDir(int direction) {

		int exRow = StartW, exCol = StartH;

		switch (direction) {
		case 0: // 上
			exRow--;
			break;

		case 1: // 下
			exRow++;
			break;

		case 2: // 左
			exCol--;
			break;

		case 3: // 右
			exCol++;
			break;
		}

		if (countSurroundingPath(exRow, exCol) > 1) {
			return false;
		}
		return true;
	}

	// 周囲1マスにある道の数を数えるメソッド
	public static int countSurroundingPath(int row, int col) {
		int num = 0;
		if (row - 1 < 0 || Maze[row - 1][col] == 0) {
			num++;
		}
		if (row + 1 > MazesizeW - 1 || Maze[row + 1][col] == 0) {
			num++;
		}
		if (col - 1 < 0 || Maze[row][col - 1] == 0) {
			num++;
		}
		if (col + 1 > MazesizeH - 1 || Maze[row][col + 1] == 0) {
			num++;
		}
		return num;
	}

	// 指定した方向へ1マスrowとcolを移動させるメソッド
	public static void movePoint(int direction) {
		switch (direction) {
		case 0: // 上
			StartW--;
			break;

		case 1: // 下
			StartW++;
			break;

		case 2: // 左
			StartH--;
			break;

		case 3: // 右
			StartH++;
			break;
		}

		Maze[StartW][StartH] = 0;
		rowStack.push(StartW);
		colStack.push(StartH);
	}

	static boolean canExtendPath() {
		return (canExtendPathWithDir(0) || canExtendPathWithDir(1) || canExtendPathWithDir(2)
				|| canExtendPathWithDir(3));
	}

	// ゴールの決定
	static void Goal() {
		while (true) {
			Random rand = new Random();// ランダム関数
			GoalW = rand.nextInt(MazesizeW - 1);// NPCスタート位置のRow
			GoalH = rand.nextInt(MazesizeH - 1);// NPCスタート位置のColumn
			if (GoalW == 1 || GoalH == 1 && Maze[GoalW][GoalH] == 0 && Maze[GoalW][GoalH] != 2) {
				break;
			}
		}
		Maze[GoalW][GoalH] = 3;
	}

}
