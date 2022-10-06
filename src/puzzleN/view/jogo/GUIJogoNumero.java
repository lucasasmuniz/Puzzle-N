package puzzleN.view.jogo;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.File;

import puzzleN.controller.movimentos.MovJogo;
import puzzleN.model.*;
import puzzleN.controller.movimentos.MovJogoNumero;

public class GUIJogoNumero extends GUIJogo{
    private int[] numerosRandom;//POSICAO DOS BOTOES
    private int[][] resposta;
    Color fundo = new Color(253,184,39);
    Font fonteBotao = new Font("", Font.BOLD, 50);
    Font botaoBranco = new Font("", Font.BOLD,0);

    public GUIJogoNumero(Usuario player, JFrame mainFrame, JPanel painelMenu){
        super(player, mainFrame, painelMenu);

        this.numerosRandom = new int[super.getPlayer().getNivel()*super.getPlayer().getNivel()];
        this.resposta = new int[super.getPlayer().getNivel()][super.getPlayer().getNivel()];

        Processos processosPlayer = new Processos(super.getPlayer().getNivel());
        if (new File("./src/resources/save/SaveGame.txt").isFile()){
            for (int i = 0; i<super.getPlayer().getNivel()*super.getPlayer().getNivel() ;i++){
                this.numerosRandom[i] = Integer.valueOf(super.getPlayer().getArraySalvo()[i]);
            }
        }else {
            this.numerosRandom = processosPlayer.randomResolvivel();
        }
        this.resposta = processosPlayer.gabarito();
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
        int k = 0;
        MovJogo controles = new MovJogoNumero(super.getBotoes(), this.resposta, super.getPlayer(), super.getTentativas(), super.getCronometro(), super.getMainFrame(), super.getPainelMenu(), super.getSaveGame());
        for(int i = 0; i<super.getBotoes().length; i++) {
            for(int j = 0; j<super.getBotoes()[i].length ;j++) {
                if(String.valueOf(numerosRandom[k]).equals("0")) {
                    super.getBotoes()[i][j] = new JButton();
                    super.getBotoes()[i][j].setBackground(fundo);
                    super.getBotoes()[i][j].addActionListener(controles);
                    super.getBotoes()[i][j].setFont(botaoBranco);
                    super.getBotoes()[i][j].setText(String.valueOf(numerosRandom[k]));
                    meio.add(super.getBotoes()[i][j]);
                } else {
                    super.getBotoes()[i][j] = new JButton();
                    super.getBotoes()[i][j].addActionListener(controles);
                    super.getBotoes()[i][j].setBackground(new Color(84,37,131));
                    super.getBotoes()[i][j].setFont(fonteBotao);
                    super.getBotoes()[i][j].setForeground(Color.white);
                    super.getBotoes()[i][j].setText(String.valueOf(numerosRandom[k]));
                    if(String.valueOf(resposta[i][j]).equals(super.getBotoes()[i][j].getText())) {
                        super.getBotoes()[i][j].setBackground(new Color(153,50,204));
                    }
                }
                k++;
                meio.add(super.getBotoes()[i][j]);
            }
        }
        add(meio);
    }
}