package pe.codegym.modulo2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

class PanelImagen extends JPanel {
    public static final Path RUTA= Paths.get("ficheros");
    private BufferedImage fondo;

    public PanelImagen() {
        try {
            fondo = ImageIO.read(new File(RUTA.resolve("isla.png").toString()));
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

