import java.util.ArrayList;

public class Main
{
	public static GameGrid gameGrid;
	public static AStar aStar;

	public static void main(String[] args)
	{
		int[][] grid =
				{ 		{ 0, 0, 1, 1, 1 },
						{ 1, 0, 0, 1, 1 },
						{ 1, 0, 0, 0, 0 },
						{ 1, 0, 1, 1, 0 },
						{ 0, 0, 1, 0, 0 }
				};

		gameGrid = new GameGrid(grid);

		Cell origin = gameGrid.getCellSpace(2, 1);
		Cell target = gameGrid.getCellSpace(4, 3);

		gameGrid.start = origin;
		gameGrid.target = target;

		aStar = new AStar(gameGrid);

		gameGrid.printLevel();

		ArrayList<Cell> path = aStar.findPath();
		System.out.println(path.size());

		gameGrid.printPath(path);
	}
}