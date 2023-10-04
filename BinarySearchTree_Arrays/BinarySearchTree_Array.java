
/**
 * Implementación de un Binary Search Tree (Árbol binario de búsqueda)
 * con arreglos
 * (Un árbol binario de búsqueda es aquel en el que CADA nodo cumple esta regla:
 * los nodos a la izquierda tienen un valor menor, y los nodos a la derecha
 * tiene un valor mayor
 * @author loaiz
 *
 */
public class BinarySearchTree_Array 
{
	
	final int NUM_NODOS=5000;
	final int NODO_NULL = Integer.MIN_VALUE;
	
	//arreglo para guardar los nodos:
	private int[] nodos;
	
	
	//*** métodos del árbol ***	
	//crear un árbol vacío:
	public BinarySearchTree_Array()
	{
		nodos = new int[5000];
		nodos[0] = NODO_NULL;
	}
	
	public boolean arbolEstaVacio()
	{
		if(nodos[0] == NODO_NULL)
			return true;
		else
			return false;
	}
	

	/**
	 * insertar un nodo (de forma recursiva) 
	 * @param root: posición del root, nodo a partir del cual va a insertar
	 * @param val: valor a insertar
	 * @return referencia al nodo insertado (posición del nodo)
	 */
	int insertarNodo(int root, int val)
	{
		
	}
	
	//obtener posición del nodo a la derecha:
	int nodo_izquierdo(int nodo) {
		return (2 * nodo + 1);
	}
	
	//obtener posición del nodo a la izquierda:
	int nodo_derecho(int nodo)
	{
		return (2 * nodo + 2);
	}
	
	/**
	 * retorna el valor de un nodo
	 * @param nodo
	 * @return
	 */
	int val_nodo(int nodo)
	{
		return nodos[nodo];
	}

}
