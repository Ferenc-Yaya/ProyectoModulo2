package pe.codegym.modulo2;
import pe.codegym.modulo2.carnivoros.Carnivoro;
import pe.codegym.modulo2.herbivoros.Herbivoro;

import javax.swing.*;

public class PosicionSwing implements PosicionStrategy {
    private JLabel[][] jLabels;
    private JTextArea jTextArea;

    public PosicionSwing(JLabel[][] jLabels,JTextArea jTextArea) {
        this.jLabels = jLabels;
        this.jTextArea= jTextArea;
    }

    @Override
    public synchronized void actualizarPosicionAnimal(int filaAnterior, int columnaAnterior, int filaNueva, int columnaNueva, Animal animal){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    String contenidoActual = jLabels[filaNueva][columnaNueva].getText();
                    String emojiNuevo = animal.getEmoji();
                    String nuevoContenido = contenidoActual + emojiNuevo;
                    jLabels[filaNueva][columnaNueva].setText(nuevoContenido);
                    jLabels[filaAnterior][columnaAnterior].setText("");
               }
            });
    }
    public synchronized void actualizarPosicionPlanta(int fila, int columna, Planta planta){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jLabels[fila][columna].setText(planta.getEmoji());
            }
        });
    }

    @Override
    public void resultados(String mensaje) {
        String textoActual = jTextArea.getText();
        textoActual += mensaje + "\n";
        jTextArea.setText(textoActual);
    }
}




