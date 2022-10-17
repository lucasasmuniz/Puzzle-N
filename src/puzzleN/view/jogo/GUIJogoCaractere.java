package puzzleN.view.jogo;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.File;

import puzzleN.model.*;
import puzzleN.controller.movimentos.*;

public class GUIJogoCaractere extends GUIJogo{

    private Character[] valorRandom;//POSICAO DOS BOTOES
    private Character[][] resposta;//GABARITO DO JOGO

    public GUIJogoCaractere(Usuario player, JFrame mainFrame, JPanel painelMenu){
        super(player, mainFrame, painelMenu);

        Processos processos = new Processos(super.getPlayer().getNivel());
        this.valorRandom = new Character[super.getPlayer().getNivel()*super.getPlayer().getNivel()];
        this.resposta = new Character[super.getPlayer().getNivel()][super.getPlayer().getNivel()];

        if (new File("./src/resources/save/SaveGame.txt").isFile()){
            for (int i = 0; i<super.getPlayer().getNivel()*super.getPlayer().getNivel() ;i++){
                this.valorRandom[i] = super.getPlayer().getArraySalvo()[i].charAt(0);
            }
        }else {
            this.valorRandom = processos.randomResolvivelChar();
        }
        this.resposta = processos.gabaritoChar();
    }
    public void parteCima(Usuario player) {
        super.parteCima(player);
    }
    public void parteBaixo() {
        super.parteBaixo();
    }

    public void parteMeio() {
        JPanel meio = new JPanel(new GridLayout(super.getPlayer().getNivel(),super.getPlayer().getNivel()));
        meio.setBackground(fundo);
        meio.setBorder(new EmptyBorder(0,100,0,100));
        MovJogoCaractere controles = new MovJogoCaractere(super.getBotoes(), this.resposta, super.getPlayer(), super.getTentativas(), super.getCronometro(), super.getMainFrame(), super.getPainelMenu(), super.getSaveGame());
        super.configBotoes(this.valorRandom, this.resposta, controles, meio);
    }
}
