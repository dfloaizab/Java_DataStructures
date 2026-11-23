
public class MainClass {

    public static void main(String[] args) {

        // Crear nodos
        Nodo<String> cali =
            new Nodo<>(0, "Cali");

        Nodo<String> palmira =
            new Nodo<>(1, "Palmira");

        Nodo<String> buga =
            new Nodo<>(2, "Buga");

        Nodo<String> tulua =
            new Nodo<>(3, "Tulua");

        Nodo<String> armenia =
            new Nodo<>(4, "Armenia");


        // =====================================
        // GRAFO CON LISTAS DE ADYACENCIA
        // =====================================

        Grafo_LA<String> grafoLA =
            new Grafo_LA<>(false);

        // Agregar nodos
        grafoLA.agregarNodo(cali);
        grafoLA.agregarNodo(palmira);
        grafoLA.agregarNodo(buga);
        grafoLA.agregarNodo(tulua);
        grafoLA.agregarNodo(armenia);

        // Agregar aristas ponderadas
        grafoLA.agregarArista(cali, palmira, 30);
        grafoLA.agregarArista(cali, buga, 70);
        grafoLA.agregarArista(palmira, buga, 40);
        grafoLA.agregarArista(buga, tulua, 25);
        grafoLA.agregarArista(tulua, armenia, 65);
        grafoLA.agregarArista(buga, armenia, 90);

        grafoLA.mostrarGrafo();


        // =====================================
        // GRAFO CON MATRIZ DE ADYACENCIA
        // =====================================

        Grafo_MA<String> grafoMA =
            new Grafo_MA<>(false);

        // Agregar nodos
        grafoMA.agregarNodo(cali);
        grafoMA.agregarNodo(palmira);
        grafoMA.agregarNodo(buga);
        grafoMA.agregarNodo(tulua);
        grafoMA.agregarNodo(armenia);

        // Agregar aristas ponderadas
        grafoMA.agregarArista(cali, palmira, 30);
        grafoMA.agregarArista(cali, buga, 70);
        grafoMA.agregarArista(palmira, buga, 40);
        grafoMA.agregarArista(buga, tulua, 25);
        grafoMA.agregarArista(tulua, armenia, 65);
        grafoMA.agregarArista(buga, armenia, 90);

        grafoMA.mostrarGrafo();

    }
}
