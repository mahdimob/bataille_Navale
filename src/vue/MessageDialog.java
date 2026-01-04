package vue;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe permettant d'afficher des boîtes de dialogue avec des messages personnalisés.
 */
public class MessageDialog {
    /** Message à afficher */
    private String message;
    
    /** Type de message (erreur, information, avertissement) */
    private int type;

    public MessageDialog(String message, int type) {
        // Chargement de la police pour les emojis
        Font font = new Font("Segoe UI Emoji", Font.PLAIN, 12);
        UIManager.put("OptionPane.messageFont", font);

        this.message = message;
        this.type = type;
    }

    /**
     * Affiche une boîte de dialogue simple avec le message et le type spécifié.
     */
    public void showMessageDialog() {
        switch (type) {
            case JOptionPane.ERROR_MESSAGE:
                JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case JOptionPane.INFORMATION_MESSAGE:
                JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
                break;
            case JOptionPane.WARNING_MESSAGE:
                JOptionPane.showMessageDialog(null, message, "Avertissement", JOptionPane.WARNING_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, message);
                break;
        }
        loadDefaultFont();
    }

    /**
     * Affiche une boîte de dialogue de confirmation avec des boutons personnalisés.
     * @param okButtonLabel Texte du bouton de confirmation.
     * @param cancelButtonLabel Texte du bouton d'annulation.
     * @return Résultat de l'option sélectionnée par l'utilisateur.
     */
    public int showConfirmationMessageDialog(String okButtonLabel, String cancelButtonLabel) {
        int res = JOptionPane.showOptionDialog(null, message, "",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[] { okButtonLabel, cancelButtonLabel }, JOptionPane.OK_OPTION);

        loadDefaultFont();
        return res;
    }

    /**
     * Réinitialise la police et l'apparence par défaut du système.
     */
    public void loadDefaultFont() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        UIManager.put("OptionPane.messageFont", new Font(Font.DIALOG, Font.PLAIN, 12));
    }
}
