package pe.codegym.modulo2;

public interface PosicionStrategy {
    void actualizarPosicionAnimal(int filaAnterior, int columnaAnterior, int nuevaFila, int nuevaColumna, Animal animal);
    void actualizarPosicionPlanta(int fila, int columna, Planta planta);
    void resultados(String mensaje);
}