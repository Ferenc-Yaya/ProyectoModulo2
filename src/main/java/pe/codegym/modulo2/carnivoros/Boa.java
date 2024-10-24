package pe.codegym.modulo2.carnivoros;

import pe.codegym.modulo2.Animal;
import pe.codegym.modulo2.PosicionStrategy;
import pe.codegym.modulo2.herbivoros.Herbivoro;

public class Boa extends Animal implements Carnivoro {

    public Boa(int filaActual, int columnaActual, int maxFilas, int maxColumnas, PosicionStrategy posicionStrategy) {
        super(filaActual, columnaActual, maxFilas, maxColumnas, posicionStrategy);
    }

    @Override
    public String getEmoji() {
        return "🐍";
    }

    @Override
    public String cazar(Herbivoro herbivoro) {
        ((Animal) herbivoro).detener();
        ((Animal) herbivoro).eliminar();
        return "La boa ha atrapado a un herbívoro. " + ((Animal) herbivoro).getEmoji();
    }
}
