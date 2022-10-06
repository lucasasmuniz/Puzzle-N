package puzzleN.controller.movimentos;

import puzzleN.model.*;
import puzzleN.view.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class MovJogoNumero extends MovJogo{
    private int[][] gabarito;

    Font fonteBotao = new Font("", Font.BOLD, 50);
    Font botaoBranco = new Font("", Font.BOLD,0);
    Color fundo = new Color(253,184,39);

    public MovJogoNumero(JButton[][] botao, int[][] gabarito, Usuario player, JLabel movimentos, Cronometro cronometro, JFrame mainFrame, JPanel painelMenu,SaveGame saveGame) {
        super(player, botao, movimentos, cronometro, mainFrame, painelMenu, saveGame);
        this.gabarito = gabarito;
    }
    public void actionPerformed(ActionEvent e) {
        Processos processo = new Processos(super.getPlayer().getNivel());
        Ranking ranking = new Ranking();
        for(int i = 0; i<super.getBotao().length ;i++) { //nesses for daqui ele vai olhar cada botao pra saber qual foi apertado
            for(int j = 0; j<super.getBotao()[i].length ;j++) {
                if(e.getSource()==super.getBotao()[i][j]) {
                    if(((i+1 == super.getIBotao() && j == super.getJBotao()) || (i-1 == super.getIBotao() && j == super.getJBotao()) || (i == super.getIBotao() && j+1 == super.getJBotao()) || (i == super.getIBotao() && j-1 == super.getJBotao()))) { //ele checa se o botao realmente esta na posicao certa para fazer o movimento
                        super.misturarBotoesMaluco();
                        super.getBotao()[super.getIBotao()][super.getJBotao()].setBackground(new Color(84,37,131));
                        super.getBotao()[super.getIBotao()][super.getJBotao()].setFont(fonteBotao);
                        super.getBotao()[super.getIBotao()][super.getJBotao()].setForeground(Color.white);
                        super.getBotao()[super.getIBotao()][super.getJBotao()].setText(getBotao()[i][j].getText());
                        super.getBotao()[i][j].setBackground(fundo);
                        super.getBotao()[i][j].setFont(botaoBranco);
                        super.getBotao()[i][j].setText("0");
                        super.getPlayer().setMovimento(super.getPlayer().getMovimento() + 1);
                        super.getMovimentos().setText("Movimentos: "+super.getPlayer().getMovimento()+ " |");
                        if(processo.foiResolvido(super.getBotao())){
                            super.getCronometro().pararCronometro();
                            ranking.salvarRanking(super.getPlayer());
                            super.getMainFrame().setSize(500,430);
                            super.getMainFrame().setLocationRelativeTo(null);
                            super.getMainFrame().removeWindowListener(super.getSaveGame());
                            super.getMainFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            Ganhou telaGanhou = new Ganhou(super.getMainFrame(), super.getPainelMenu(), super.getPlayer());
                            super.getMainFrame().setContentPane(telaGanhou);
                            telaGanhou.revalidate();
                        }
                    }
                }
            }
        }
        for(int i=0; i<super.getBotao().length ;i++) { //caso o botao estiver na posicao certa ele troca de cor
            for(int j=0; j<super.getBotao()[i].length ; j++) {
                if(String.valueOf(gabarito[i][j]).equals(super.getBotao()[i][j].getText())) {
                    if(super.getBotao()[i][j].getText().equals("0")) { //se o botão que esta na posição correta é o branco então não faz nada
                        continue;
                    }else {
                        super.getBotao()[i][j].setBackground(new Color(153,50,204));
                    }
                }
            }
        }
    }
}