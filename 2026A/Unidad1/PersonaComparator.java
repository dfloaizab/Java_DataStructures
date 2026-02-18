import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class PersonaComparator {

    private String nombre;
    private int edad;
    private LocalDate fechaNacimiento;
    private float Salario;

    public PersonaComparator(String nombre, int edad, LocalDate fechaNacimiento, float Salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.salario = Salario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public float getSalario()
    {    
        return salario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return nombre + " - Edad: " + edad + " - Nacimiento: " + fechaNacimiento;
    }

    public static void main(String[] args) {

        PersonaComparator[] personas = {
            new PersonaComparator("Ana", 28, LocalDate.of(1996, 3, 15),1500000.0f),
            new PersonaComparator("Carlos", 35, LocalDate.of(1989, 7, 20),1800000.0f),
            new PersonaComparator("Beatriz", 22, LocalDate.of(2002, 1, 10),2500000.0f)
        };

        // 🔹 Comparator por nombre
        Comparator<PersonaComparator> porNombre =
                (p1, p2) -> p1.getNombre().compareTo(p2.getNombre());

        Arrays.sort(personas, porNombre);

        System.out.println("Ordenadas por nombre:");
        for (PersonaComparator p : personas) {
            System.out.println(p);
        }

        // 🔹 Comparator por edad
        Comparator<PersonaComparator> porEdad =
                (p1, p2) -> Integer.compare(p1.getEdad(), p2.getEdad());

        Arrays.sort(personas, porEdad);

        System.out.println("\nOrdenadas por edad:");
        for (PersonaComparator p : personas) {
            System.out.println(p);
        }

        // 🔹 Comparator por fecha de nacimiento
        Comparator<PersonaComparator> porFecha =
                (p1, p2) -> p1.getFechaNacimiento().compareTo(p2.getFechaNacimiento());

        Arrays.sort(personas, porFecha);

        //este comparador permite comparar por el atributo "Salario":
        //el compareTo funciona así:
        // si el atributo de p1 es > atributo p2, retorna 1
        // si son iguales, retorna 0
        // si el atributo de p1 es menor, retorna -1
        Comparator<PersonaComparator> porSalario =
            (p1,p2) -> p1.getSalario().compareTo(p2.getSalario());

        //vamos a comparar si la persona en la posición 1 del arreglo, tiene un salario  mayor al de la persona en la posición 2:
        if(porSalario.compare( personas[1], pesonas[2])  >   0)
        {
            //
        }

        //cómo usar un comparator en un algoritmo?:
        //Comparator tiene el método compare:
        //porSalario.compare(persona1, persona2) > 0   : el salario de persona1 es mayor que el de persona 2
        //porSalario.compare(persona1, persona2) == 0 : los salarios son iguales
        //porSalario.compare(persona1, persona2) < 0 : el salario de persona1 es menor que el de persona 2

        System.out.println("\nOrdenadas por fecha de nacimiento:");
        for (PersonaComparator p : personas) {
            System.out.println(p);
        }
    }
}
