public class Cliente {

    private String nombre, documento;
    private int edad;
    private float total_compras;

    public Cliente(String documento, int edad, String nombre, float total_compras) {
        this.documento = documento;
        this.edad = edad;
        this.nombre = nombre;
        this.total_compras = total_compras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getTotal_compras() {
        return total_compras;
    }

    public void setTotal_compras(float total_compras) {
        this.total_compras = total_compras;
    }


    
}
