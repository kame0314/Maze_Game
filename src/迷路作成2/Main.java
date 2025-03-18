package 迷路作成2;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		int Width;//迷路の横幅
		int Higth;//迷路の縦幅

		Scanner sc = new Scanner(System.in);
		Width = sc.nextInt();
		Higth = sc.nextInt();

		Maze.makeMaze(Width, Higth);
		Maze.Maze[Maze.StartW][Maze.StartH] = 2;
		System.out.println("StartW" + Maze.StartW + "StartH" + Maze.StartH);
		Maze.Goal();

		while (true) {
			System.out.print("ここまで"+Move.Count + "回移動したよ");
			Display.User_Display(Move.userRow, Move.userCol);
			Move.moveUsr(sc.next());
			if(Move.End) {
				System.out.println("迷路を終了します");
				sc.close();
				return;
			}
			if (Maze.Maze[Move.userRow][Move.userCol] == 3) {
				System.out.println("やった！ゴールについたよ！");
				break;
			}
		}
		sc.close();
	}
}