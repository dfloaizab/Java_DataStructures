public class Cell implements Comparable<Cell> {
    private int x, y;
    private int fCost, gCost, hCost;
    private Cell comesFrom;
    private boolean isWall = false; // Nueva propiedad para indicar si es una pared

    // Constructor por defecto (no es pared)
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Constructor opcional con estado de pared
    public Cell(int x, int y, boolean isWall) {
        this.x = x;
        this.y = y;
        this.isWall = isWall;
    }

    @Override
    public int compareTo(Cell o) {
        int compare = this.getFCost() - o.getFCost();

        if (compare == 0)
            compare = this.hCost - o.hCost;

        return -compare;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int value) {
        hCost = value;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int value) {
        gCost = value;
    }

    public Cell getPreviousCell() {
        return comesFrom;
    }

    public void setPreviousCell(Cell value) {
        comesFrom = value;
    }

    // Métodos para manejar el atributo isWall
    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean isWall) {
        this.isWall = isWall;
    }
}
