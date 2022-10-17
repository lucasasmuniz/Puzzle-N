package puzzleN.controller.movimentos;

import puzzleN.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovJogoCaractere extends MovJogo implements ActionListener {
    private Character[][] gabarito;

    public MovJogoCaractere(JButton[][] botao, Character[][] gabarito, Usuario player, JLabel movimentos, Cronometro cronometro, JFrame mainFrame, JPanel painelMenu, SaveGame saveGame) {
        super(player, botao, movimentos, cronometro, mainFrame, painelMenu, saveGame);
        this.gabarito = gabarito;
    }
    public void actionPerformed(ActionEvent e) {
        Processos processo = new Processos(super.getPlayer().getNivel());
        for(int i = 0; i<super.getBotao().length ;i++) { //nesses for daqui ele vai olhar cada botao pra saber qual foi apertado
            for(int j = 0; j<super.getBotao()[i].length ;j++) {
                if(e.getSource()==super.getBotao()[i][j]) {
                    if(((i+1 == super.getIBotao() && j == super.getJBotao()) || (i-1 == super.getIBotao() && j == super.getJBotao()) || (i == super.getIBotao() && j+1 == super.getJBotao()) || (i == super.getIBotao() && j-1 == super.getJBotao()))) { //ele checa se o botao realmente esta na posicao certa para fazer o movimento
                        trocarBotoes(i, j);

                        if(processo.foiResolvidoChar(super.getBotao())) {
                            super.trocarTelaGanhou();
                        }
                    }
                }
            }
        }
        super.casaCorreta(gabarito);
    }
}
