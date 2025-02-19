public class OrdenamientosObjetos {


       
    public static void main(String[] args)
    {
        OrdenamientoObjetos pruebaOrdenamiento = new OrdenamientosObjetos();
        pruebaOrdenamiento.probarOrdenamientos();
    }

    public OrdenamientosObjetos(){}

    public void probarOrdenamientos()
    {
        Persona persona1 = new Persona(62,"Diego");
        Persona persona2 = new Persona(63, "Fabiola");

        //cómo comparar los dos objetos usando comparator y comparable?:
        if(persona1.compareTo(persona2) > 0)
            System.out.println("la persona 1 es mayor que la persona 2 (por el atributo que se compara)");
        else 
        {
            if(persona1.compareTo(persona2) < 0)
                System.out.println("la persona 1 es mayor que la persona 2 (por el atributo que se compara)");
            else
                System.out.println("la persona 1 es igual que la persona 2 (por el atributo que se compara)");
        }


        //crear arreglo desordenado de 10 objetos de la clase Persona:

        //ordenar usando burbuja:
    }
}
