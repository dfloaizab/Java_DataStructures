
public class MainBinarySearch {

	public static void main(String[] args) {

		// arreglo de prueba:
		int testArray = { 1, 5, 8, 9, 12, 24, 50, 54, 88, 120, 200, 201, 1128 };

	}

	public static int busquedaBinariaIterativa(int[] arr, int objetivo) {
		int izquierda = 0;
		int derecha = arr.length - 1;

		//el límite izquierdo siempre debe ser menor que el límite derecho: es decir,
		//hasta donde se puede partir el espacio de búsqueda
		while (izquierda <= derecha) {
			
			int medio = izquierda + (derecha - izquierda) / 2;

			if (arr[medio] == objetivo) {
				return medio;
			}

			//decide si buscar por la izquierda o por la derecha, en cada iteración:
			if (arr[medio] < objetivo) {
				izquierda = medio + 1;
			} else {
				derecha = medio - 1;
			}
		}

		return -1;
	}

	public static int busquedaBinariaRecursiva(int[] arr, int objetivo, int izquierda, int derecha) {
		
		//caso base
		if (izquierda > derecha) {
			return -1;
		}

		//división del problema
		int medio = izquierda + (derecha - izquierda) / 2;

		if (arr[medio] == objetivo) {
			return medio;
		}

		if (arr[medio] > objetivo) {
			return busquedaBinariaRecursiva(arr, objetivo, izquierda, medio - 1);
		}

		return busquedaBinariaRecursiva(arr, objetivo, medio + 1, derecha);
	}

}
