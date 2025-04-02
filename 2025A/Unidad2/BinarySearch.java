public class BinarySearch
{

public static void main(String[] args)
  {
  }
  

public static int busquedaBinariaIterativa(int[] arr, int objetivo) {
    int izquierda = 0;
    int derecha = arr.length - 1;
    
    while (izquierda <= derecha) {
        int medio = izquierda + (derecha - izquierda) / 2;
        
        if (arr[medio] == objetivo) {
            return medio;
        }
        
        if (arr[medio] < objetivo) {
            izquierda = medio + 1;
        } else {
            derecha = medio - 1;
        }
    }
    
    return -1;
}

  public static int busquedaBinariaRecursiva(int[] arr, int objetivo, int izquierda, int derecha) {
    if (izquierda > derecha) {
        return -1;
    }
    
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
