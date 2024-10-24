package pe.codegym.modulo2.herbivoros;

import pe.codegym.modulo2.Animal;
import pe.codegym.modulo2.Planta;
import pe.codegym.modulo2.PosicionStrategy;

public class Raton extends Animal implements Herbivoro {

    public Raton(int filaActual, int columnaActual, int maxFilas, int maxColumnas, PosicionStrategy posicionStrategy) {
        super(filaActual, columnaActual, maxFilas, maxColumnas, posicionStrategy);
    }

    @Override
    public String getEmoji() {
        return "🐁";
    }

    @Override
    public String pastar(Planta planta) {
        planta.borrar();
        planta.eliminar();
        return getEmoji()+" Un ratón se comio una planta";
    }
}