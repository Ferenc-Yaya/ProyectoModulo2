package pe.codegym.modulo2;

public interface PosicionStrategy {
    void actualizarPosicionAnimal(int filaAnterior, int columnaAnterior, int nuevaFila, int nuevaColumna, Animal animal);
    void actualizarPosicionPlanta(int fila, int columna, Planta planta);
    void actualizarPosicionFinal(int fila, int columna);
    void resultadosEncuentro(String mensaje);
    void resultadosGenerales(String mensaje);
}
