package puzzleN.controller.botoes;

import puzzleN.model.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class BotaoVoltarMenu implements ActionListener {

    private JButton voltarMenu;
    private Cronometro cronometro;
    private SaveGame saveGame;
    private JFrame mainFrame;
    private JPanel painelMenu;
    private Usuario player;

    public BotaoVoltarMenu(JButton voltarMenu, Cronometro cronometro, SaveGame saveGame, JFrame mainFrame, JPanel painelMenu, Usuario player){
        this.voltarMenu = voltarMenu;
        this.cronometro = cronometro;
        this.saveGame = saveGame;
        this.mainFrame = mainFrame;
        this.painelMenu = painelMenu;
        this.player = player;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.voltarMenu){
            this.cronometro.pararCronometro();

            if (new File("./src/resources/save/SaveGame.txt").isFile()){
                new File("./src/resources/save/SaveGame.txt").delete();
            }

            if (this.player.getPuzzleNMaluco()){
                mainFrame.setTitle("Puzzle-N");
            }

            this.player.playerReset();

            this.mainFrame.removeWindowListener(this.saveGame);
            this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.mainFrame.setSize(500, 430);
            this.mainFrame.setLocationRelativeTo(null);
            this.mainFrame.setContentPane(this.painelMenu);
            this.painelMenu.revalidate();
        }
    }
}
