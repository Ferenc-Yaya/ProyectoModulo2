package pe.codegym.modulo2.herbivoros;

import pe.codegym.modulo2.Animal;
import pe.codegym.modulo2.Planta;
import pe.codegym.modulo2.PosicionStrategy;

public class Bufalo extends Animal implements Herbivoro{

    public Bufalo(int filaActual, int columnaActual, int maxFilas, int maxColumnas, PosicionStrategy posicionStrategy) {
        super(filaActual, columnaActual, maxFilas, maxColumnas, posicionStrategy);
    }

    @Override
    public String getEmoji() {
        return "🐃";
    }

    @Override
    public double getPeso() {
        return 700;
    }

    @Override
    public double getPesoPerdidoPorMovimiento() {
        return 110;
    }

    @Override
    public String pastar(Planta planta) {
        vidaAnimal+=planta.getPeso();
        planta.detenerHiloPlanta();
        planta.eliminarPlanta();
        return getEmoji()+" El búfalo se comio una planta";
    }
}
