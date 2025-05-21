import java.util.*;

public class AStar
{
	private PriorityQueue<Cell> openSet;
	private HashSet<Cell> closedSet;

	private GameGrid game;

	public AStar(GameGrid game)
	{
		this.game = game;
		openSet = new PriorityQueue<>();
		closedSet = new HashSet<>();
	}

	//Funcion en la cual deben encontrar la primera celula valida
	//Deben utilizar Colas/Queues en una manera similar a como A*
	//Utiliza OpenSets y ClosedSets para mirar las celulas ya revisadasS
	public Cell fixTarget(Cell initialTarget)
	{

		return null;
	}

	public ArrayList<Cell> findPath()
	{
		if (game.getGameSpace(game.target.getX(), game.target.getY()) == 1)
		{
			System.out.println("Invalid Target! Fixing");

			Cell newTarget = fixTarget(game.target);

			if (newTarget == null)
			{
				System.out.println("Unfixable Target!");
				return new ArrayList<>();
			}

			game.target = newTarget;
		}

		openSet.clear();
		closedSet.clear();

		openSet.add(game.start);

		while (!openSet.isEmpty())
		{
			Cell currentCell = openSet.remove();
			closedSet.add(currentCell);

			if (currentCell.equals(game.target))
			{
				return retracePath(game.start, game.target);
			}

			ArrayList<Cell> neighbors = game.getNeighbors(currentCell);

			for (Cell n : neighbors)
			{
				if (closedSet.contains(n)) continue;

				if (game.getGameSpace(n.getX(), n.getY()) == 1) continue;

				int costToNeighbor = n.getGCost() + getDistance(currentCell, n);

				if (costToNeighbor < n.getGCost() || !openSet.contains(n)) {
					n.setGCost(costToNeighbor);
					n.setHCost(getDistance(n, game.target));
					n.setPreviousCell(currentCell);

					if (!openSet.contains(n))
						openSet.add(n);
				}
			}
		}

		return new ArrayList<Cell>();
	}

	private ArrayList<Cell> retracePath(Cell start, Cell end)
	{
		ArrayList<Cell> path = new ArrayList<Cell>();
		Cell currentNode = end;

		while (currentNode != start)
		{
			path.add(game.getCellSpace(currentNode.getX(), currentNode.getY()));
			currentNode = currentNode.getPreviousCell();
		}

		Collections.reverse(path);

		return path;
	}

	private int getDistance(Cell a, Cell b)
	{
		int distX = Math.abs(a.getX() - b.getX());
		int distY = Math.abs(a.getY() - b.getY());

		return distX > distY ?
				14 * distY + 10 * (distX - distY) :
				14 * distX + 10 * (distY - distX) ;
	}
}
