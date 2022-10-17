package puzzleN.view.jogo;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.File;

import puzzleN.controller.movimentos.MovJogo;
import puzzleN.model.*;
import puzzleN.controller.movimentos.MovJogoNumero;

public class GUIJogoNumero extends GUIJogo{
    private Integer[] valorRandom;//POSICAO DOS BOTOES
    private Integer[][] resposta;//GABARITO DO JOGO

    public GUIJogoNumero(Usuario player, JFrame mainFrame, JPanel painelMenu){
        super(player, mainFrame, painelMenu);

        Processos processos = new Processos(super.getPlayer().getNivel());
        this.valorRandom = new Integer[super.getPlayer().getNivel()*super.getPlayer().getNivel()];
        this.resposta = new Integer[super.getPlayer().getNivel()][super.getPlayer().getNivel()];

        if (new File("./src/resources/save/SaveGame.txt").isFile()){
            for (int i = 0; i<super.getPlayer().getNivel()*super.getPlayer().getNivel() ;i++){
                this.valorRandom[i] = Integer.valueOf(super.getPlayer().getArraySalvo()[i]);
            }
        }else {
            this.valorRandom = processos.randomResolvivel();
        }
        this.resposta = processos.gabarito();
    }

    public void parteCima(Usuario player){
        super.parteCima(player);
    }

    public void parteBaixo() {
        super.parteBaixo();
    }

    public void parteMeio() {
        JPanel meio = new JPanel(new GridLayout(super.getPlayer().getNivel(),super.getPlayer().getNivel()));
        meio.setBackground(fundo);
        meio.setBorder(new EmptyBorder(0,100,0,100));
        MovJogo controles = new MovJogoNumero(super.getBotoes(), this.resposta, super.getPlayer(), super.getTentativas(), super.getCronometro(), super.getMainFrame(), super.getPainelMenu(), super.getSaveGame());
        super.configBotoes(this.valorRandom, this.resposta, controles, meio);
    }
}