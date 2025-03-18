package 迷路作成2;

public class Display {
	final static int[] CONNUM = { -1, 1, -1, 1 };// 上下左右に移動できるか確認するための定数

	static void User_Display(int w, int h) {
		System.out.println("w" + w + "h" + h);
		int MoveCount = 0;// 動ける箇所があればカウントする
		StringBuilder sb = new StringBuilder();
		sb.append("今は上と下と左と右に壁があるかな、どうしようか？");

		for (int i = 0; i < CONNUM.length; i++) {

			System.out.println("インデックス番号" + sb.indexOf("に"));
			switch (i) {
			case 0: // 上
				if (Maze.Maze[w + CONNUM[i]][h] == 1) {
					System.out.println("上");
					MoveCount++;
				} else {
					sb.delete(sb.indexOf("上"), sb.indexOf("上") + 1);
				}
				break;
			case 1: // 下
				if (Maze.Maze[w + CONNUM[i]][h] == 1) {
					System.out.println("下");
					if (MoveCount == 0) {
						sb.delete(sb.indexOf("下") - 1, sb.indexOf("下"));
					} else {
						;
					}
					MoveCount++;
				} else {
					sb.delete(sb.indexOf("下") - 1, sb.indexOf("下") + 1);

				}
				break;
			case 2: // 左
				if (Maze.Maze[w][h + CONNUM[i]] == 1) {
					System.out.println("左");
					if (MoveCount == 0) {
						sb.delete(sb.indexOf("左") - 1, sb.indexOf("左"));
					} else {
						;
					}
					MoveCount++;
				} else {
					sb.delete(sb.indexOf("左") - 1, sb.indexOf("左") + 1);
				}
				break;
			case 3: // 右
				if (Maze.Maze[w][h + CONNUM[i]] == 1) {
					System.out.println("右");
					if (MoveCount == 0) {
						sb.delete(sb.indexOf("右") - 1, sb.indexOf("右"));
					} else {
						;
					}
					MoveCount++;
				} else {
					sb.delete(sb.indexOf("右") - 1, sb.indexOf("右") + 1);
				}
				break;
			default:
				break;
			}
		}
		System.out.println(sb);
	}


}