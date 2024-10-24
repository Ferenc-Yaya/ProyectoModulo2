package pe.codegym.modulo2;

import java.util.ArrayList;
import java.util.List;

public class Planta implements Runnable{

    private static final List<Planta> plantas = new ArrayList<>();
    private PosicionStrategy posicionStrategy;
    private int fila;
    private int columna;
    private volatile boolean planta=true;

    public void borrar(){
        planta=false;
    }
    public String getEmoji() {
        return "🌳";
    }

    public Planta(int fila,int columna,PosicionStrategy posicionStrategy) {
        this.fila=fila;
        this.columna=columna;
        this.posicionStrategy=posicionStrategy;
        synchronized (plantas) {
            plantas.add(this);
        }
    }
    public static List<Planta> obtenerPlantas() {
        synchronized (plantas) {
            return new ArrayList<>(plantas);
        }
    }

    public void eliminar() {
        synchronized (plantas) {
            plantas.remove(this);
        }
    }

    @Override
    public void run() {
        while (planta) {
            posicionStrategy.actualizarPosicionPlanta(fila, columna, this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
