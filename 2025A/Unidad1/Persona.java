public class Persona implements Comparable<Persona>
    {
        private int edad;
        private String nombre;

        public Persona(int edad, String nombre)
        {
            this.edad = edad;
            this.nombre = nombre;
        }

        public String getNombre()
        {
            return nombre;
        }

        public int getEdad()
        {
            return edad;
        }

        //Modificar para que compare por nombre:
        @Override
        public int compareTo(Persona o) {
            if(this.getEdad() < o.getEdad())
                return 1;
            else 
            {
                if(this.getEdad() > o.getEdad())
                {
                    return -1;
                }
                else{
                    return 0;
                }
            }
        }




    } 