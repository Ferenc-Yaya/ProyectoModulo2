package pe.codegym.modulo2.herbivoros;

import pe.codegym.modulo2.Animal;
import pe.codegym.modulo2.Planta;
import pe.codegym.modulo2.PosicionStrategy;

public class Oveja extends Animal implements Herbivoro {

    public Oveja(int filaActual, int columnaActual, int maxFilas, int maxColumnas, PosicionStrategy posicionStrategy) {
        super(filaActual, columnaActual, maxFilas, maxColumnas, posicionStrategy);
    }

    @Override
    public String getEmoji() {
        return "🐑";
    }

    @Override
    public double getPeso() {
        return 70;
    }

    @Override
    public double getPesoPerdidoPorMovimiento() {return 15;}

    @Override
    public String pastar(Planta planta) {
        vidaAnimal+=planta.getPeso();
        planta.detenerHiloPlanta();
        planta.eliminarPlanta();
        return getEmoji()+" Una oveja se comio una planta";
    }
}
