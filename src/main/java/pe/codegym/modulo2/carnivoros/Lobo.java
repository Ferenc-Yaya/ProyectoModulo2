package pe.codegym.modulo2.carnivoros;

import pe.codegym.modulo2.Animal;
import pe.codegym.modulo2.Encuentro;
import pe.codegym.modulo2.PosicionStrategy;

import java.util.concurrent.ThreadLocalRandom;

public class Lobo extends Animal implements Carnivoro {

    public Lobo(int filaActual, int columnaActual, int maxFilas, int maxColumnas, PosicionStrategy posicionStrategy) {
        super(filaActual, columnaActual, maxFilas, maxColumnas, posicionStrategy);
    }

    @Override
    public String getEmoji() {
        return "🐺";
    }

    @Override
    public double getPeso() {
        return 50;
    }

    @Override
    public double getPesoPerdidoPorMovimiento() {
        return 8;
    }

    @Override
    public String cazar(Animal animal) {
        Encuentro encuentro= new Encuentro();
        int porcentaje=encuentro.porcentajeEncuentro(this.getClass().getSimpleName(),animal.getClass().getSimpleName());

        int numeroAleatorio= ThreadLocalRandom.current().nextInt(100);
        if(porcentaje!=0) {
            if (numeroAleatorio < porcentaje) {
                vidaAnimal+=animal.getPeso();
                animal.detenerHiloAnimal();
                animal.eliminarAnimal();
                return this.getEmoji() + " "+porcentaje+"%"+" ha atrapado su presa. " + animal.getEmoji()+" "+numeroAleatorio+"%";
            } else {
                return this.getEmoji() + " "+porcentaje+"%"+" se le escapo la presa. " + animal.getEmoji()+" "+numeroAleatorio+"%";
            }
        }else{
            return "("+this.getEmoji()+","+animal.getEmoji()+")"+"no paso nada ";
        }
    }
}


