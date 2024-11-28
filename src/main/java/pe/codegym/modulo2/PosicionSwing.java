package pe.codegym.modulo2;

import javax.swing.*;

public class PosicionSwing implements PosicionStrategy {
    private JLabel[][] matriz;
    private JTextArea jTextAreaDatosEncuentro;
    private JTextArea jTextAreaDatosGenerales;

    public PosicionSwing(JLabel[][] matriz, JTextArea jTextAreaDatosEncuentro,JTextArea jTextAreaDatosGenerales) {
        this.matriz = matriz;
        this.jTextAreaDatosEncuentro= jTextAreaDatosEncuentro;
        this.jTextAreaDatosGenerales= jTextAreaDatosGenerales;
    }

    @Override
    public synchronized void actualizarPosicionAnimal(int filaAnterior, int columnaAnterior, int filaNueva, int columnaNueva, Animal animal) {
        SwingUtilities.invokeLater(() -> {
            matriz[filaNueva][columnaNueva].setText(matriz[filaNueva][columnaNueva].getText() + animal.getEmoji());
            matriz[filaAnterior][columnaAnterior].setText("");
        });
    }
    public synchronized void actualizarPosicionPlanta(int fila, int columna, Planta planta){
        SwingUtilities.invokeLater(() -> matriz[fila][columna].setText(planta.getEmoji()));
    }

    @Override
    public void actualizarPosicionFinal(int fila, int columna) {
        SwingUtilities.invokeLater(() -> {
            matriz[fila][columna].setText("");
        });
    }

    @Override
    public void resultadosEncuentro(String mensaje) {
        jTextAreaDatosEncuentro.append(mensaje + "\n");
    }

    @Override
    public void resultadosGenerales(String mensaje){
        jTextAreaDatosGenerales.setText(mensaje);
    }
}




