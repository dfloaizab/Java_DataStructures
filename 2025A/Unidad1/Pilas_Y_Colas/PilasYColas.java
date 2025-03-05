
import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;


public class PilasYColas 
{
	
	
	public static void main(String[] args)
	{
		Scanner lector = new Scanner(System.in);
		Stack<String> pilaTexto = new Stack<String>();
		String textoActual = "", nuevoTexto;
		int opcion;
		
		/*ciclo infinito del mini-editor:*/
		while(true)
		{
			System.out.println("Seleccione una opción: (1) Ingresar (2) Deshacer (3) Salir");
			System.out.println("(4) Imprimir texto completo");
			System.out.println("(5) Imprimir desde la pila");
			opcion = lector.nextInt();
			
			//leer texto desde el editor:
			lector.nextLine();
			switch(opcion)
			{	
				//agregar texto:
				case 1:
					System.out.println("Escribe tu texto:");
					nuevoTexto = lector.nextLine();
					/* AGREGAR ELEMENTO A LA PILA: */
					pilaTexto.push(nuevoTexto);
					textoActual += nuevoTexto;
					break;
				/* elminar texto de la pila:*/	
				case 2:
					if(!pilaTexto.isEmpty())
					{
						/* sacar texto de la pila*/
						textoActual = pilaTexto.pop();
					}
					else
					{
						System.out.println("No hay texto para eliminar");
					}
					break;
					
				case 3:
					System.out.println("Cerrando...");
					lector.close();
					return;					
				case 4:
					System.out.println("Texto actual en el editor:"+textoActual);
					break;
				case 5:
					/* sacar elementos de la pila con pop e irlos imprimiendo en pantalla:*/
					/* ¿cómo hacer para que imprima el texto en orden?*/
					break;
					
				default:
					System.out.println("Opción no válida...");
					
			}
			
		}
		
	}

}
