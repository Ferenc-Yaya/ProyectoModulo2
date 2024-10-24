package pe.codegym.modulo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prueba {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo de Botón");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Crear un botón
        JButton boton = new JButton("Haz clic aquí!");

        // Añadir acción al botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("¡Botón presionado!");
            }
        });

        // Añadir el botón al marco
        frame.getContentPane().add(boton);

        // Hacer visible el marco
        frame.setVisible(true);
    }
}
