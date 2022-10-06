package puzzleN.model;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class SaveGame extends WindowAdapter {
    private Usuario player;
    private JButton[][] botoes;
    private Cronometro cronometro;

    public SaveGame(Usuario player, JButton[][] botoes, Cronometro cronometro) {
        this.player = player;
        this.botoes = botoes;
        this.cronometro = cronometro;
    }

    public void windowClosing(WindowEvent e) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja mesmo fechar o programa?", "AVISO", JOptionPane.YES_NO_OPTION);
        if (resposta == 0) {
            this.cronometro.pararCronometro();
            salvarPlayer();
            System.exit(0);
        }
    }

    public void salvarPlayer() {
        BufferedWriter writer = null;
        File diretorio = new File("./src/resources/save");
        if (!diretorio.isDirectory()) {
            diretorio.mkdirs();
        }
        try {
            writer = new BufferedWriter(new FileWriter("./src/resources/save/SaveGame.txt", false));
            writer.write(this.player.getNome() + "," + this.player.getTempo() + "," + this.player.getMovimento() + "," + this.player.getNivel() + "," + this.player.getPuzzleNMaluco() + "," + this.player.getAjudaAtiva() + "," + this.player.getRandomMaluco() + "," + this.player.getTipoJogo());
            for (int i = 0; i < this.player.getNivel(); i++) {
                for (int j = 0; j < this.player.getNivel(); j++) {
                    writer.write("," +this.botoes[i][j].getText());
                }
            }
            writer.close();
        } catch (IOException e) {
        }
    }
}