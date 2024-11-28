package pe.codegym.modulo2;

public class Encuentro {

    private static final String[] animales = {
            "Lobo", "Boa", "Zorro", "Oso", "Aguila", "Caballo", "Ciervo", "Conejo",
            "Raton", "Cabra", "Oveja", "Jabali", "Bufalo", "Pato", "Oruga", "Planta"
    };


    private static final int[][] encuentros = {
            {0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0},  // Lobo
            {0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0, 0},  // Boa
            {0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40, 0},  // Zorro
            {0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0, 0},  // Oso
            {0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0, 0},  // Aguila
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},  // Caballo
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},  // Ciervo
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},  // Conejo
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},  // Raton
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},  // Cabra
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},  // Oveja
            {0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90, 100},  // Jabali
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},  // Bufalo
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},  // Pato
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}   // Oruga
    };


    public int obtenerIndiceAnimal(String animal) {
        for (int i = 0; i < animales.length; i++) {
            if (animales[i].equals(animal)) {
                return i;
            }
        }
        return -1;
    }

    public int porcentajeEncuentro(String animal1, String animal2) {
        int indice1 = obtenerIndiceAnimal(animal1);
        int indice2 = obtenerIndiceAnimal(animal2);

        if (indice1 != -1 && indice2 != -1) {
            return encuentros[indice1][indice2];
        }
        return -1;
    }
}