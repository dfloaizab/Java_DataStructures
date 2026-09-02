
import java.util.*;

public class TestComparacion {

    public static void main(String[] main)
    {
        //Crear un objeto de la clase vehiculo:
        Vehiculo moto1 = new Vehiculo(2005, 25000000.0f, "Honda");

        //Crear otro objeto de la clase vehiculo:
        Vehiculo carro1 = new Vehiculo(2020, 70000000.0f, "Toyota");

        Vehiculo carro2 = new Vehiculo(2026, 120000000.0f, "Toyota");

        //Comparar:
        //a. Cuál de los dos vehículos es de un modelo más reciente?
        //(si se usa el compareTo, ya compara por modelo? o debo cambiar la implementación?)

        //b. Cuál de los dos vehículos es más costoso?

        //Crear lista de vehiculos y ordenar:
        List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        vehiculos.add(moto1);
        vehiculos.add(carro1);
        vehiculos.add(carro2);

        //Cuando se llama el  método sort de la clase collections
        //usa el método de comparación natural del objeto, en este caso
        //el que está implementado en compareTo:
        Collections.sort(vehiculos);

        //c. qué se debe cambiar para que ordene por modelo?

        //d. qué método usar el sort de Collections?

        //e. Ya se ordena el arreglo de vehículos, Agregar atributo matricula, ¿cómo encontramos un vehículo por matrícula en el arreglo ya ordenado
        //    usando búsqueda binaria?
        
    }
    
}
