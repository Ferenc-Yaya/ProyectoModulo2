package pe.codegym.modulo2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

class PanelImagen extends JPanel {
    public static final Path RUTA= Paths.get("ficheros","isla.png");
    private BufferedImage fondo;

    public PanelImagen() {
        try {
            File imagenFile = RUTA.toFile();
            fondo = ImageIO.read(imagenFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

