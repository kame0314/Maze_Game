package 迷路作成2;

import java.util.*;

public class Move {
	final static String[] COM = { "w", "a", "s", "d", "r", "e", "q" };
	static int s_UsrRow = Maze.S_POSITION_W;// ユーザーのスタート位置のRow
	static int s_UsrCol = Maze.S_POSITION_H;// ユーザーのスタート位置のCOl
	static int userRow = s_UsrRow;// 入力されたコマンドに対して移動したユーザのRow情報を保持する
	static int userCol = s_UsrCol;// 入力されたコマンドに対して移動したユーザのCol情報を保持する
	static int Count = 0;// 何回移動したか数える
	static boolean End = false;// 終了コマンドが打たれた際のフラグ

	static void moveUsr(String key) {
		String errMes = "そこには動くことができません。";
		s_UsrRow = userRow;
		s_UsrCol = userCol;
		for (int i = 0; i < COM.length; i++) {
			if (i == COM.length - 1 && !key.equals(COM[i])) {
				System.out.println("w,a,s,d,r,e,qのいずれかのコマンドを入力してください。");
				return;
			} else if (key.equals(COM[i])) {
				break;
			}
		}
		switch (key) {
		case "w": // 上
			s_UsrRow--;
			Count++;
			if (Maze.Maze[s_UsrRow][s_UsrCol] == 1) {
				s_UsrRow++;
				Count--;
			} else {
				;
			}
			break;
		case "s": // 下
			s_UsrRow++;
			Count++;

			if (Maze.Maze[s_UsrRow][s_UsrCol] == 1) {
				s_UsrRow--;
				Count--;
			} else {
				;
			}
			break;
		case "a": // 左
			s_UsrCol--;
			Count++;
			if (Maze.Maze[s_UsrRow][s_UsrCol] == 1) {
				s_UsrCol++;
				Count--;
			} else {
				;
			}
			break;
		case "d": // 右
			s_UsrCol++;
			Count++;
			if (Maze.Maze[s_UsrRow][s_UsrCol] == 1) {
				s_UsrCol--;
				Count--;
			} else {
				;
			}
			break;
		case "r": // リスタート
			resetUsr();
			return;
		case "e": // 新規ゲームの開始
			Scanner sc = new Scanner(System.in);
			int Width = sc.nextInt();
			int Higth = sc.nextInt();
			Maze.makeMaze(Width, Higth);
			Count = 0;

			return;
		case "q":// ゲームの終了
			End = true;
			return;
		default:
			System.out.println(errMes);
			break;
		}

		if (s_UsrRow > Maze.MazesizeW - 1) {
			System.out.println(errMes);
			return;
		}

		userRow = s_UsrRow;
		userCol = s_UsrCol;
	}

	// リセットした際にユーザーの位置を初期位置に戻す
	static void resetUsr() {
		s_UsrRow = Maze.S_POSITION_W;
		s_UsrCol = Maze.S_POSITION_H;
		Count = 0;
	}

}
