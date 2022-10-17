package puzzleN.controller.movimentos;

import puzzleN.model.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MovJogoNumero extends MovJogo{
    private Integer[][] gabarito;

    public MovJogoNumero(JButton[][] botao, Integer[][] gabarito, Usuario player, JLabel movimentos, Cronometro cronometro, JFrame mainFrame, JPanel painelMenu,SaveGame saveGame) {
        super(player, botao, movimentos, cronometro, mainFrame, painelMenu, saveGame);
        this.gabarito = gabarito;
    }
    public void actionPerformed(ActionEvent e) {
        Processos processo = new Processos(super.getPlayer().getNivel());
        for(int i = 0; i<super.getBotao().length ;i++) {
            for(int j = 0; j<super.getBotao()[i].length ;j++) {
                if(e.getSource()==super.getBotao()[i][j]) {
                    if(((i+1 == super.getIBotao() && j == super.getJBotao()) || (i-1 == super.getIBotao() && j == super.getJBotao()) || (i == super.getIBotao() && j+1 == super.getJBotao()) || (i == super.getIBotao() && j-1 == super.getJBotao()))) { //ele checa se o botao realmente esta na posicao certa para fazer o movimento
                        super.trocarBotoes(i, j);

                        if(processo.foiResolvido(super.getBotao())){
                            super.trocarTelaGanhou();
                        }
                    }
                }
            }
        }
        super.casaCorreta(gabarito);
    }
}