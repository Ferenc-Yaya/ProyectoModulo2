package pe.codegym.modulo2;

import pe.codegym.modulo2.carnivoros.*;
import pe.codegym.modulo2.herbivoros.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        //JPanel panel = new JPanel();
        PanelImagen panel =new PanelImagen();
        panel.setLayout(new GridLayout(maxFilas, maxColumnas));

        JLabel[][] jLabels = new JLabel[maxFilas][maxColumnas];
        Font font = new Font("Segoe UI Emoji", Font.BOLD, 15);

        for (int i = 0; i < maxFilas; i++) {
            for (int j = 0; j < maxColumnas; j++) {
                jLabels[i][j] = new JLabel();
                jLabels[i][j].setFont(font);
                panel.add(jLabels[i][j]);
            }
        }

        JButton creaHerbivoro = new JButton("Crea herbivoro");
        JButton creaCarnivoro = new JButton("Crea carnivoro");
        JButton creaPlanta = new JButton("Crea Planta");
        JPanel panelnorte=new JPanel();
        panelnorte.setLayout(new BorderLayout());
        panelnorte.add(creaPlanta,BorderLayout.CENTER);
        panelnorte.add(creaCarnivoro,BorderLayout.EAST);
        panelnorte.add(creaHerbivoro,BorderLayout.WEST);
        frame.add(panelnorte,BorderLayout.NORTH);

        JTextArea jTextArea=new JTextArea();
        jTextArea.setFont(font);
        jTextArea.setRows(10);
        JScrollPane scrollPane = new JScrollPane(jTextArea);

        frame.add(scrollPane,BorderLayout.SOUTH);
        PosicionStrategy positionStrategy = new PosicionSwing(jLabels,jTextArea);

        creaPlanta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaActual = ThreadLocalRandom.current().nextInt(maxFilas);
                int columnaActual = ThreadLocalRandom.current().nextInt(maxColumnas);
                Planta planta=new Planta(filaActual,columnaActual,positionStrategy);
                jLabels[filaActual][columnaActual].setText(planta.getEmoji());
                Thread thread = new Thread(planta);
                thread.start();
            }
        });
        creaHerbivoro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaActual = ThreadLocalRandom.current().nextInt(maxFilas);
                int columnaActual = ThreadLocalRandom.current().nextInt(maxColumnas);
                Herbivoro herbivoro=switch (ThreadLocalRandom.current().nextInt(10)){
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
                    default -> throw new IllegalStateException("Unexpected value: " + ThreadLocalRandom.current().nextInt(2));
                };
                jLabels[filaActual][columnaActual].setText(((Animal)herbivoro).getEmoji());
                Thread thread= new Thread((Animal)herbivoro);
                thread.start();
            }
        });
        creaCarnivoro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaActual = ThreadLocalRandom.current().nextInt(maxFilas);
                int columnaActual = ThreadLocalRandom.current().nextInt(maxColumnas);

                Carnivoro carnivoro=switch (ThreadLocalRandom.current().nextInt(5)){
                    case 0->new Aguila(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                    case 1->new Boa(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                    case 2->new Lobo(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                    case 3->new Oso(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                    case 4->new Zorro(filaActual, columnaActual, maxFilas, maxColumnas,positionStrategy);
                    default -> throw new IllegalStateException("Unexpected value: " + ThreadLocalRandom.current().nextInt(2));
                };
                jLabels[filaActual][columnaActual].setText(((Animal)carnivoro).getEmoji());
                Thread thread= new Thread((Animal)carnivoro);
                thread.start();
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}






