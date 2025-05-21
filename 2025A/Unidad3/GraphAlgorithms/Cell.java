public class Cell implements Comparable<Cell>
{
	int x, y;
	//Costo G, lo que me cuesta moverme desde un nodo en una dirección
	//Costo H, heuristica, distancia desde un nodo hasta el objetivo
	//Costo F, suma de los costos G y F
	int fCost, gCost, hCost;
	Cell comesFrom;

	public Cell(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Cell o)
	{
		int compare = this.getFCost() - o.getFCost();

		if (compare == 0)
			compare = this.hCost - o.hCost;

		return -compare;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getFCost()
	{
		return gCost + hCost;
	}

	public int getHCost()
	{
		return hCost;
	}

	public void setHCost(int value)
	{
		hCost = value;
	}

	public int getGCost()
	{
		return gCost;
	}

	public void setGCost(int value)
	{
		gCost = value;
	}

	public Cell getPreviousCell()
	{
		return comesFrom;
	}

	public void setPreviousCell(Cell value)
	{
		comesFrom = value;
	}
}
