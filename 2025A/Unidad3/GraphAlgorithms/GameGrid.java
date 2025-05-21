import java.util.ArrayList;

public class GameGrid
{
	//Espacio del Juego
	public int[][] gameSpace;
	//Espacio de las Celulas
	public Cell[][] cellSpace;
	public Cell start;
	public Cell target;

	public GameGrid(int[][] gameSpace)
	{
		this.gameSpace = gameSpace;

		cellSpace = new Cell[gameSpace.length][gameSpace[0].length];

		for (int i = 0, k = 0; i < gameSpace.length; i++, k++)
		{
			for (int j = 0; j < gameSpace[i].length; j++, k++)
				cellSpace[i][j] = new Cell(i, j);
		}
	}

	public int getGameSpace(int x, int y)
	{
		return gameSpace[x][y];
	}

	public Cell getCellSpace(int x, int y)
	{
		return cellSpace[x][y];
	}

	public boolean isInsideGrid(Cell cell)
	{
		return isInsideGrid(cell.getX(), cell.getY());
	}

	public void printPath(ArrayList<Cell> path)
	{
		for (int i = 0; i < gameSpace.length; i++)
		{
			boolean printedPathO = false;

			System.out.print("[");

			for (int j = 0; j < gameSpace[i].length - 1; j++)
			{
				boolean printedPath = false;

				for (int k = 0; k < path.size(); k++)
				{
					Cell p = path.get(k);

					if ((!p.equals(start) && !p.equals(target)) &&
					p.getX() == i && p.getY() == j)
					{
						printedPath = true;
						System.out.print("*, ");
					}
				}

				if (printedPath) continue;

				if (start != null && (i == start.getX() && j == start.getY()))
					System.out.print("°, ");

				else if (target != null && (i == target.getX() && j == target.getY()))
					System.out.print("X, ");

				else if (gameSpace[i][j] == 0)
					System.out.print(" , ");

				else if (gameSpace[i][j] == 1)
					System.out.print("#, ");
			}

			for (int k = 0; k < path.size(); k++)
			{
				Cell p = path.get(k);

				if ((!p.equals(start) && !p.equals(target)) &&
						p.getX() == i && p.getY() == gameSpace[i].length - 1)
				{
					printedPathO = true;
					System.out.println("*]");
				}
			}

			if (printedPathO) continue;

			if (start != null && (i == start.getX() && gameSpace[i].length - 1 == start.getY()))
				System.out.println("°]");

			else if (target != null && (i == target.getX() && gameSpace[i].length - 1 == target.getY()))
				System.out.println("X]");

			else if (gameSpace[i][gameSpace[i].length - 1] == 0)
				System.out.println(" ]");

			else if (gameSpace[i][gameSpace[i].length - 1] == 1)
				System.out.println("#]");
		}
	}

	public void printLevel()
	{
		for (int i = 0; i < gameSpace.length; i++)
		{
			System.out.print("[");

			for (int j = 0; j < gameSpace[i].length - 1; j++)
			{
				if (start != null && (i == start.getX() && j == start.getY()))
					System.out.print("°, ");

				else if (target != null && (i == target.getX() && j == target.getY()))
					System.out.print("X, ");

				else if (gameSpace[i][j] == 0)
					System.out.print(" , ");

				else if (gameSpace[i][j] == 1)
					System.out.print("#, ");
			}

			if (start != null && (i == start.getX() && gameSpace[i].length - 1 == start.getY()))
				System.out.println("°]");

			else if (target != null && (i == target.getX() && gameSpace[i].length - 1 == target.getY()))
				System.out.println("X]");

			else if (gameSpace[i][gameSpace[i].length - 1] == 0)
				System.out.println(" ]");

			else if (gameSpace[i][gameSpace[i].length - 1] == 1)
				System.out.println("#]");
		}
	}

	public boolean isInsideGrid(int x, int y)
	{
		return x >= 0 && x < gameSpace.length && y >= 0 && y < gameSpace[0].length;
	}

	public ArrayList<Cell> getNeighbors(Cell center)
	{
		ArrayList<Cell> returnNeighbors = new ArrayList<>();

		//Up neighbor
		if (isInsideGrid(center.getX(), center.getY() + 1))
			returnNeighbors.add(getCellSpace(center.getX(), center.getY() + 1));

		//Down neighbor
		if (isInsideGrid(center.getX(), center.getY() - 1))
			returnNeighbors.add(getCellSpace(center.getX(), center.getY() - 1));

		//Right neighbor
		if (isInsideGrid(center.getX() + 1, center.getY()))
			returnNeighbors.add(getCellSpace(center.getX() + 1, center.getY()));

		//Left neighbor
		if (isInsideGrid(center.getX() - 1, center.getY()))
			returnNeighbors.add(getCellSpace(center.getX() - 1, center.getY()));

		//Up-Right neighbor
		if (isInsideGrid(center.getX() + 1, center.getY() + 1))
			returnNeighbors.add(getCellSpace(center.getX() + 1, center.getY() + 1));

		//Up-Left neighbor
		if (isInsideGrid(center.getX() - 1, center.getY() + 1))
			returnNeighbors.add(getCellSpace(center.getX() - 1, center.getY() + 1));

		//Down-Right neighbor
		if (isInsideGrid(center.getX() + 1, center.getY() - 1))
			returnNeighbors.add(getCellSpace(center.getX() + 1, center.getY() - 1));

		//Down-Left neighbor
		if (isInsideGrid(center.getX() - 1, center.getY() - 1))
			returnNeighbors.add(getCellSpace(center.getX() - 1, center.getY() - 1));

		return returnNeighbors;
	}
}
