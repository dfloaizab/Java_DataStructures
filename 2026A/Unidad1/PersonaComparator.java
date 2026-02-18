import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class PersonaComparator {

    private String nombre;
    private int edad;
    private LocalDate fechaNacimiento;

    public PersonaComparator(String nombre, int edad, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
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
            new PersonaComparator("Ana", 28, LocalDate.of(1996, 3, 15)),
            new PersonaComparator("Carlos", 35, LocalDate.of(1989, 7, 20)),
            new PersonaComparator("Beatriz", 22, LocalDate.of(2002, 1, 10))
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

        System.out.println("\nOrdenadas por fecha de nacimiento:");
        for (PersonaComparator p : personas) {
            System.out.println(p);
        }
    }
}
