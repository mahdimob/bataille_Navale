package vue;
import java.io.File;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class PhotoEcran extends JFrame {

    private JLabel imageLabel;
    private JProgressBar progressBar;
    private int progress = 0;
    
    public PhotoEcran() {
        // Creer une image pour la splash screen
        String filePath=new File("").getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(filePath+"/image/Bataille.png");
        imageLabel = new JLabel(imageIcon);

        // Cr er une barre de progression
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setForeground(Color.green);
        
       
        
        // Cr er un conteneur pour l'image et la barre de progression
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.SOUTH);
        
        
        

        // Ajouter le conteneur la fenetre
        add(panel, BorderLayout.CENTER);
        

        // Taille de la fenetre
        setSize(500, 300);

        // Centrer la fenetre sur l'ecran
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);

        // Afficher la fenetre
        setUndecorated(true);
        pack();
        setVisible(true);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        
        // Simuler une tache longue
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setProgress(i);
        }

        // Fermer la fenetre
        dispose();
    }

    public void setProgress(int value) {
        progress = value;
        progressBar.setValue(progress);
        progressBar.setString(progress + "%");
    }
}
