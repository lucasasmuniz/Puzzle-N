package puzzleN.controller.botoes;

import puzzleN.model.Cronometro;
import puzzleN.model.SaveGame;
import puzzleN.model.Usuario;
import puzzleN.view.jogo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BotaoRecomecar implements ActionListener {

    private JButton recomeco;
    private JFrame mainFrame;
    private Usuario player;
    private JPanel painelMenu;
    private Cronometro cronometro;
    private SaveGame saveGame;

    public BotaoRecomecar(JButton recomeco, JFrame mainFrame, JPanel painelMenu, Usuario player, Cronometro cronometro, SaveGame saveGame){
        this.recomeco = recomeco;
        this.mainFrame = mainFrame;
        this.painelMenu = painelMenu;
        this.player = player;
        this.cronometro = cronometro;
        this.saveGame = saveGame;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recomeco){
            if (new File("./src/resources/save/SaveGame.txt").isFile()){
                new File("./src/resources/save/SaveGame.txt").delete();
            }
            this.cronometro.pararCronometro();
            this.player.setMovimento(0);
            this.player.setTempo(0);
            if (this.player.getPuzzleNMaluco()){
                this.player.setRandomMaluco(0);
            }
            this.mainFrame.removeWindowListener(this.saveGame);

            if(this.player.getTipoJogo() == 1){
                GUIJogo numeroJogo = new GUIJogoNumero(this.player, this.mainFrame, this.painelMenu);
                mainFrame.setContentPane(numeroJogo);
                numeroJogo.revalidate();
                numeroJogo.parteCima(this.player);
                numeroJogo.parteMeio();
                numeroJogo.parteBaixo();
            } else if (this.player.getTipoJogo() == 2) {
                GUIJogo caractereJogo = new GUIJogoCaractere(this.player, this.mainFrame, this.painelMenu);
                mainFrame.setContentPane(caractereJogo);
                caractereJogo.revalidate();
                caractereJogo.parteCima(this.player);
                caractereJogo.parteMeio();
                caractereJogo.parteBaixo();
            } else {
                GUIJogo imagemJogo = new GUIJogoImagem(this.player, this.mainFrame, this.painelMenu);
                mainFrame.setContentPane(imagemJogo);
                imagemJogo.revalidate();
                imagemJogo.parteCima(this.player);
                imagemJogo.parteMeio();
                imagemJogo.parteBaixo();
            }
        }
    }
}
