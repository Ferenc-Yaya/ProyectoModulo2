package pe.codegym.modulo2;

import pe.codegym.modulo2.carnivoros.*;
import pe.codegym.modulo2.herbivoros.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Principal {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bioma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String input = JOptionPane.showInputDialog(frame, "Ingrese el tamaño del bioma (filas, columnas):", "Bioma", JOptionPane.QUESTION_MESSAGE);
        String[] sizes = input.split(",");

        final int maxFilas = Integer.parseInt(sizes[0].trim());
        final int maxColumnas = Integer.parseInt(sizes[1].trim());

        PanelImagen panelIsla =new PanelImagen();
        panelIsla.setLayout(new GridLayout(maxFilas, maxColumnas));

        JLabel[][] matriz = new JLabel[maxFilas][maxColumnas];
        Font font = new Font("Segoe UI Emoji", Font.BOLD, 15);

        for (int i = 0; i < maxFilas; i++) {
            for (int j = 0; j < maxColumnas; j++) {
                matriz[i][j] = new JLabel();
                matriz[i][j].setFont(font);
                panelIsla.add(matriz[i][j]);
            }
        }

        String[] carnivoros = {"Aguila","Boa","Lobo","Oso","Zorro"};
        JComboBox<String> comboBoxCarnivoro = new JComboBox<>(carnivoros);
        String[] herbivoros = {"Bufalo","Caballo","Cabra","Ciervo","Conejo","Jabali","Oruga","Oveja","Pato","Raton"};
        JComboBox<String> comboBoxHerbivoro = new JComboBox<>(herbivoros);

        JButton creaHerbivoro = new JButton("Crea herbivoro");
        JButton creaCarnivoro = new JButton("Crea carnivoro");
        JButton creaPlanta = new JButton("Crea Planta");

        JTextArea jTextAreaDatosGenerales =new JTextArea();
        jTextAreaDatosGenerales.setFont(font);
        jTextAreaDatosGenerales.setRows(10);

        JTextArea jTextAreaDatosEncuentro =new JTextArea();
        jTextAreaDatosEncuentro.setFont(font);
        jTextAreaDatosEncuentro.setRows(10);

        JPanel panelNorte=new JPanel();
        panelNorte.setLayout(new GridLayout(2,3));

        JPanel panelSur=new JPanel();
        panelSur.setLayout(new GridLayout(1,2));

        panelNorte.add(creaCarnivoro);
        panelNorte.add(creaPlanta);
        panelNorte.add(creaHerbivoro);
        panelNorte.add(comboBoxCarnivoro);
        panelNorte.add(new JLabel());
        panelNorte.add(comboBoxHerbivoro);

        panelSur.add(new JScrollPane(jTextAreaDatosGenerales));
        panelSur.add(new JScrollPane(jTextAreaDatosEncuentro));
        frame.add(panelNorte,BorderLayout.NORTH);
        frame.add(panelSur,BorderLayout.SOUTH);

        PosicionStrategy positionStrategy = new PosicionSwing(matriz,jTextAreaDatosEncuentro,jTextAreaDatosGenerales);
        Bioma bioma=new Bioma(positionStrategy);

        creaPlanta.addActionListener(e -> {
            int filaActual = ThreadLocalRandom.current().nextInt(maxFilas);
            int columnaActual = ThreadLocalRandom.current().nextInt(maxColumnas);

            Planta planta=new Planta(filaActual,columnaActual,positionStrategy);
            matriz[filaActual][columnaActual].setText(planta.getEmoji());
        });

        creaHerbivoro.addActionListener(e -> {
            int filaActual = ThreadLocalRandom.current().nextInt(maxFilas);
            int columnaActual = ThreadLocalRandom.current().nextInt(maxColumnas);

            Herbivoro herbivoro=switch (comboBoxHerbivoro.getSelectedIndex()){
                case 0->new Bufalo(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 1->new Caballo(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 2->new Cabra(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 3->new Ciervo(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 4->new Conejo(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 5->new Jabali(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 6->new Oruga(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 7->new Oveja(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 8->new Pato(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 9->new Raton(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                default -> throw new IllegalStateException("Unexpected value: " + ThreadLocalRandom.current().nextInt(10));
            };
            matriz[filaActual][columnaActual].setText(((Animal)herbivoro).getEmoji());
        });

        creaCarnivoro.addActionListener(e -> {
            int filaActual = ThreadLocalRandom.current().nextInt(maxFilas);
            int columnaActual = ThreadLocalRandom.current().nextInt(maxColumnas);

            Carnivoro carnivoro=switch (comboBoxCarnivoro.getSelectedIndex()){
                case 0->new Aguila(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 1->new Boa(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 2->new Lobo(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 3->new Oso(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                case 4->new Zorro(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                default -> throw new IllegalStateException("Unexpected value: " + ThreadLocalRandom.current().nextInt(5));
            };
            matriz[filaActual][columnaActual].setText(((Animal)carnivoro).getEmoji());
        });

        frame.add(panelIsla, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}






