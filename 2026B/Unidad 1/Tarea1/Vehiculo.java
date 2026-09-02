public class Vehiculo implements Comparable<Object> {

    int modelo;
    float valorMercado;
    String marca;

    public Vehiculo(int pModelo, float pValor, String pMarca)
    {
        modelo = pModelo;
        valorMercado = pValor;
        marca = pMarca;
    }

    @Override
    /**
     * retorna > 0, sí este objeto es mayor al objeto o, en el atributo
     * 0, si son iguales
     * < 0 si el atributo de este objeto es menor al atributo del objeto o
     */
    public int compareTo(Object o) {
        //Implementar método de comparación de esta clase de objetos.
        //Aquí defino por qué atributo voy a comparar:
        if(this.valorMercado > ((Vehiculo) o).valorMercado )
            return 1;
        else if(this.valorMercado == ((Vehiculo) o).valorMercado)
            return 0;
        else 
            return -1;
    }
    
}
