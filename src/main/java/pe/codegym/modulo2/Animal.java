package pe.codegym.modulo2;

import pe.codegym.modulo2.carnivoros.Carnivoro;
import pe.codegym.modulo2.herbivoros.Herbivoro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Runnable{

    private static final List<Animal> animales = new ArrayList<>();
    private int filaActual;
    private int columnaActual;
    private int maxFilas;
    private int maxColumnas;
    private PosicionStrategy posicionStrategy;
    private volatile boolean caminando=true;

    public Animal(int filaActual, int columnaActual, int maxFilas, int maxColumnas,PosicionStrategy posicionStrategy) {
        this.filaActual = filaActual;
        this.columnaActual = columnaActual;
        this.maxFilas = maxFilas;
        this.maxColumnas = maxColumnas;
        this.posicionStrategy = posicionStrategy;

        synchronized (animales) {
            animales.add(this);
        }
    }

    public static List<Animal> obtenerAnimales() {
        synchronized (animales) {
            return new ArrayList<>(animales);
        }
    }

    public void eliminar() {
        synchronized (animales) {
            animales.remove(this);
        }
    }

    public void detener() {
        this.caminando=false;
    }

    public void run() {
        while (caminando) {
            int filaAnterior = this.filaActual;
            int columnaAnterior = this.columnaActual;

            int[] nuevaPosicion = mover();
            int nuevaFila = nuevaPosicion[0];
            int nuevaColumna = nuevaPosicion[1];
            synchronized (Animal.class) {
                for (Animal otro : obtenerAnimales()) {
                    if (otro != this && otro.getFilaActual() == nuevaFila && otro.getColumnaActual() == nuevaColumna) {
                        if (this instanceof Carnivoro && otro instanceof Herbivoro) {
                            String mensaje=((Carnivoro) this).cazar((Herbivoro) otro);
                            this.posicionStrategy.resultados(mensaje);
                        } else if (this instanceof Herbivoro && otro instanceof Carnivoro) {
                            String mensaje=((Carnivoro) otro).cazar((Herbivoro) this);
                            this.posicionStrategy.resultados(mensaje);
                        }
                        if(this.getClass()==otro.getClass()){
                            this.posicionStrategy.resultados("Nacio un animal "+this.getEmoji());
                            //new Thread(this).start();
                        }
                    }
                }
                for (Planta planta : Planta.obtenerPlantas()) {
                    if (planta.getFila() == nuevaFila && planta.getColumna() == nuevaColumna) {
                        if (this instanceof Herbivoro) {
                            String mensaje = ((Herbivoro) this).pastar(planta);
                            this.posicionStrategy.resultados(mensaje);
                        }
                    }
                }
                this.posicionStrategy.actualizarPosicionAnimal(filaAnterior, columnaAnterior, nuevaFila, nuevaColumna, this);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public int[] mover() {
        int[][] directions = {
                {-1, 0}, // arriba
                {1, 0},  // abajo
                {0, -1}, // izquierda
                {0, 1}   // derecha
        };
        int moveDirecion;
        int newRow, newCol;

        do {
            moveDirecion = ThreadLocalRandom.current().nextInt(directions.length);
            newRow = this.filaActual + directions[moveDirecion][0];
            newCol = this.columnaActual + directions[moveDirecion][1];
        } while (newRow < 0 || newRow >= this.maxFilas || newCol < 0 || newCol >= this.maxColumnas);

        this.filaActual=newRow;
        this.columnaActual=newCol;
        return new int[]{newRow, newCol};
    }

    public abstract String getEmoji();

    public int getFilaActual() {
        return filaActual;
    }

    public void setFilaActual(int filaActual) {
        this.filaActual = filaActual;
    }

    public int getColumnaActual() {
        return columnaActual;
    }

    public void setColumnaActual(int columnaActual) {
        this.columnaActual = columnaActual;
    }
}
