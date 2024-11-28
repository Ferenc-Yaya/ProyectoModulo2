package pe.codegym.modulo2;

import java.util.ArrayList;
import java.util.List;

public class Planta implements Runnable{

    private static final List<Planta> plantas = new ArrayList<>();
    private PosicionStrategy posicionStrategy;
    private int fila;
    private int columna;
    private Thread hiloPlanta;

    public Planta(int fila,int columna,PosicionStrategy posicionStrategy) {
        this.fila=fila;
        this.columna=columna;
        this.posicionStrategy=posicionStrategy;

        this.hiloPlanta =new Thread(this);
        this.hiloPlanta.start();

        synchronized (plantas) {
            plantas.add(this);
        }
    }

    public void detenerHiloPlanta(){
        if (this.hiloPlanta != null)
            this.hiloPlanta.interrupt();
    }

    public void eliminarPlanta() {
        synchronized (plantas) {
            plantas.remove(this);
        }
    }

    public String getEmoji() {
        return "🌳";
    }

    public static List<Planta> obtenerPlantas() {
        synchronized (plantas) {
            return new ArrayList<>(plantas);
        }
    }

    @Override
    public void run() {
        while (!this.hiloPlanta.isInterrupted()) {
            posicionStrategy.actualizarPosicionPlanta(fila, columna, this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                this.hiloPlanta.interrupt();
                break;
            }
        }
    }
    public int getFila() {return fila;}
    public int getColumna() {return columna;}
    public float getPeso(){return 1;}
}
