import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Inventory 
{

	//EJERCICIO PREPARACIÓN DEL PARCIAL: 
	//1. Usar la clase Producto de la primera tarea para agregar Producto y no String a la cola
	//2. No permitir hacer poll de un objeto si tiene cantidad == 0
	public static void main(String[] args)
	{
		Queue<String> inventario = new LinkedList<>();
		
		//con el add se agrega a la cola (se agrega al inventario)
		inventario.add("Nuevo producto 1");
		inventario.add("Nuevo producto 2");
		inventario.add("Nuevo producto 3");
		
		System.out.println("Inventario actual:"+inventario);
		
		/* Se vende un producto y sale un producto del inventario: */
		/* con el poll de despacha o se elimina de la cola: */
		System.out.println("Producto vendido:"+inventario.poll());
		System.out.println("Se vendió otro producto:"+inventario.poll());
		
		System.out.println("Inventario final:"+inventario);	
		
		inventario.add("Nuevo producto 4");
		
		System.out.println("Inventario final:"+inventario);	
		
	}
	
}
