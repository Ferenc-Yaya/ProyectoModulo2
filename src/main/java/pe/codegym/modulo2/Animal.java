package pe.codegym.modulo2;

import pe.codegym.modulo2.carnivoros.Carnivoro;
import pe.codegym.modulo2.herbivoros.Herbivoro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Runnable{

    private static final List<Animal> animales = new ArrayList<>();
    private int filaActual,columnaActual,maxFilas,maxColumnas;
    private PosicionStrategy posicionStrategy;

    protected double vidaAnimal;
    private Thread hiloAnimal;

    private volatile boolean caminando=true;

    public Animal(int filaActual, int columnaActual, int maxFilas, int maxColumnas, PosicionStrategy posicionStrategy) {
        this.filaActual = filaActual;
        this.columnaActual = columnaActual;
        this.maxFilas = maxFilas;
        this.maxColumnas = maxColumnas;
        this.posicionStrategy = posicionStrategy;

        synchronized (animales) {
            animales.add(this);
        }
        vidaAnimal = getPeso();
        this.hiloAnimal=new Thread(this);
        this.hiloAnimal.start();
    }

    public static List<Animal> obtenerAnimales() {
        synchronized (animales) {
            return new ArrayList<>(animales);
        }
    }

    public void eliminarAnimal() {
        synchronized (animales) {
            animales.remove(this);
        }
    }

    public void detenerHiloAnimal() {
        caminando=false;
    }

    public void run() {
        while (caminando&&this.vidaAnimal>0) {
            int filaAnterior = this.filaActual;
            int columnaAnterior = this.columnaActual;

            int[] nuevaPosicion = mover();
            int nuevaFila = nuevaPosicion[0];
            int nuevaColumna = nuevaPosicion[1];

            synchronized (Animal.class) {
                for (Animal otro : obtenerAnimales()) {
                    if (otro != this && otro.getFilaActual() == nuevaFila && otro.getColumnaActual() == nuevaColumna) {
                        if(this.getClass()==otro.getClass()){
                            this.posicionStrategy.resultadosEncuentro("Nacio un animal "+this.getEmoji());
                            int x = ThreadLocalRandom.current().nextInt(maxFilas);
                            int y = ThreadLocalRandom.current().nextInt(maxColumnas);
                            Animal.crearInstancia(this.getClass(), x, y, this.maxFilas, this.maxColumnas, this.posicionStrategy);
                        }else{
                            if (this instanceof Carnivoro carnivoro) {
                                String mensaje=carnivoro.cazar(otro);
                                this.posicionStrategy.resultadosEncuentro(mensaje);
                            } else if (otro instanceof Carnivoro carnivoro) {
                                String mensaje=carnivoro.cazar(this);
                                this.posicionStrategy.resultadosEncuentro(mensaje);
                            }
                        }
                    }
                }
                for (Planta planta : Planta.obtenerPlantas()) {
                    if (planta.getFila() == nuevaFila && planta.getColumna() == nuevaColumna) {
                        if (this instanceof Herbivoro herbivoro) {
                            String mensaje = herbivoro.pastar(planta);
                            this.posicionStrategy.resultadosEncuentro(mensaje);
                        }
                    }
                }
                this.posicionStrategy.actualizarPosicionAnimal(filaAnterior, columnaAnterior, nuevaFila, nuevaColumna, this);
                tiempoVidaAnimal();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                this.hiloAnimal.interrupt();
                break;
            }
        }
    }
    public void tiempoVidaAnimal(){
        this.vidaAnimal-=getPesoPerdidoPorMovimiento();
        if(!(this.vidaAnimal>0)) {
            this.posicionStrategy.resultadosEncuentro(this.getEmoji() + " murio de hambre");
            this.eliminarAnimal();
            this.posicionStrategy.actualizarPosicionFinal(this.filaActual,this.columnaActual);
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
    public abstract double getPeso();
    public abstract double getPesoPerdidoPorMovimiento();

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

    public double getVidaAnimal() {
        return vidaAnimal;
    }

    public void setVidaAnimal(double vidaAnimal) {
        this.vidaAnimal = vidaAnimal;
    }

    public static Animal crearInstancia(Class<? extends Animal> animalClass, int fila, int columna, int maxFilas, int maxColumnas, PosicionStrategy posicionStrategy) {
        try {
            return animalClass.getDeclaredConstructor(int.class, int.class, int.class, int.class, PosicionStrategy.class)
                    .newInstance(fila, columna, maxFilas, maxColumnas, posicionStrategy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
