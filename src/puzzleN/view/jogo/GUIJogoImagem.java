package puzzleN.view.jogo;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.File;

import puzzleN.model.*;
import puzzleN.controller.botoes.*;
import puzzleN.controller.movimentos.*;

public class GUIJogoImagem extends GUIJogo{
    private Integer[] valorRandom;//POSICAO DOS BOTOES
    private Integer[][] resposta;//GABARITO DO JOGO

    JButton ajuda = new JButton("Ajuda");


    public GUIJogoImagem(Usuario player, JFrame mainFrame, JPanel painelMenu){
        super(player, mainFrame, painelMenu);
        super.getPlayer().setAjudaAtiva(false);

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
    public void parteCima(Usuario player) {
        super.parteCima(player);
    }

    public void parteBaixo() {
        super.parteBaixo();
        BotaoAjuda botaoAjuda = new BotaoAjuda(super.getBotoes(),this.ajuda, super.getPlayer());
        ajuda.setPreferredSize(new Dimension(120,30));
        ajuda.addActionListener(botaoAjuda);
        super.getPainelSul().add(ajuda);
        add(super.getPainelSul(),BorderLayout.SOUTH);
    }
    public void parteMeio() {
        JPanel meio = new JPanel(new GridLayout(super.getPlayer().getNivel(),super.getPlayer().getNivel()));
        meio.setBackground(fundo);
        meio.setBorder(new EmptyBorder(0,100,0,100));
        MovJogo controles = new MovJogoImagem(super.getBotoes(), this.resposta, super.getPlayer(), super.getTentativas(), super.getCronometro(), super.getMainFrame(), super.getPainelMenu(), super.getSaveGame());
        super.configBotoes(this.valorRandom, this.resposta, controles, meio);
    }
}
