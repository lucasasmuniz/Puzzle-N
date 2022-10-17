package puzzleN.controller.movimentos;

import puzzleN.model.Cronometro;
import puzzleN.model.Ranking;
import puzzleN.model.SaveGame;
import puzzleN.model.Usuario;
import puzzleN.view.Ganhou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public abstract class MovJogo <T> implements ActionListener{
    private Usuario player;
    private JButton[][] botao;
    private JLabel movimentos;
    private Cronometro cronometro;
    private JFrame mainFrame;
    private JPanel painelMenu;
    private SaveGame saveGame;
    Font fonteBotao = new Font("", Font.BOLD, 50);
    Font botaoBranco = new Font("", Font.BOLD,0);
    Color fundo = new Color(253,184,39);

    public MovJogo(Usuario player, JButton[][] botao, JLabel movimentos, Cronometro cronometro, JFrame mainFrame, JPanel painelMenu, SaveGame saveGame) {
        this.player = player;
        this.botao = botao;
        this.movimentos = movimentos;
        this.cronometro = cronometro;
        this.mainFrame = mainFrame;
        this.painelMenu = painelMenu;
        this.saveGame = saveGame;
    }
    public void misturarBotoesMaluco(){
        if (this.player.getPuzzleNMaluco()){
            if ((new Random().nextInt(100) + 1) <= this.player.getRandomMaluco()*100){
                Random random = new Random();
                int posicaoI1 = getIBotao();
                int posicaoJ1 = getJBotao();
                int posicaoI2 = getIBotao();
                int posicaoJ2 = getJBotao();
                while ((posicaoI1 == getIBotao()) && (posicaoJ1 == getJBotao()) && (posicaoI2 == getIBotao()) && (posicaoJ2 == getJBotao()) && (posicaoI1 == posicaoI2) && (posicaoJ1 == posicaoJ2)){
                    posicaoI1 = random.nextInt(getPlayer().getNivel());
                    posicaoJ1 = random.nextInt(getPlayer().getNivel());
                    posicaoI2 = random.nextInt(getPlayer().getNivel());
                    posicaoJ2 = random.nextInt(getPlayer().getNivel());
                }
                String temp = (this.botao[posicaoI1][posicaoJ1].getText());
                this.botao[posicaoI1][posicaoJ1].setText(this.botao[posicaoI2][posicaoJ2].getText());
                this.botao[posicaoI2][posicaoJ2].setText(temp);
                this.botao[posicaoI1][posicaoJ1].setBackground(new Color(84,37,131));
                this.botao[posicaoI1][posicaoJ1].setFont(fonteBotao);
                this.botao[posicaoI1][posicaoJ1].setForeground(Color.white);
                this.botao[posicaoI2][posicaoJ2].setBackground(new Color(84,37,131));
                this.botao[posicaoI2][posicaoJ2].setFont(fonteBotao);
                this.botao[posicaoI2][posicaoJ2].setForeground(Color.white);
            }
        }
    }
    public int getIBotao(){
        for(int i = 0; i<this.player.getNivel() ;i++){
            for(int j = 0; j<this.player.getNivel() ;j++){
                if(botao[i][j].getText().equals("0")){
                    return i;
                }
            }
        }
        return 0;
    }
    public int getJBotao(){
        for(int i = 0; i<this.player.getNivel() ;i++){
            for(int j = 0; j<this.player.getNivel() ;j++){
                if(botao[i][j].getText().equals("0")){
                    return j;
                }
            }
        }
        return 0;
    }

    public void trocarTelaGanhou(){
        Ranking ranking = new Ranking();
        this.cronometro.pararCronometro();
        ranking.salvarRanking(this.player);
        Ganhou telaGanhou = new Ganhou(this.mainFrame, this.painelMenu, this.player, this.saveGame);
        this.mainFrame.setContentPane(telaGanhou);
        telaGanhou.revalidate();
    }

    public void trocarBotoes(int i, int j){
        if (this.player.getTipoJogo() == 1 || this.player.getTipoJogo() == 2) {
            misturarBotoesMaluco();
        }
        this.botao[getIBotao()][getJBotao()].setBackground(new Color(84,37,131));
        if (this.player.getTipoJogo() == 1 || this.player.getTipoJogo() == 2) {
            this.botao[getIBotao()][getJBotao()].setFont(fonteBotao);
        }
        if (this.player.getTipoJogo() == 3){
            this.botao[getIBotao()][getJBotao()].setIcon(this.botao[i][j].getIcon());
        }
        this.botao[getIBotao()][getJBotao()].setForeground(Color.white);
        this.botao[getIBotao()][getJBotao()].setText(getBotao()[i][j].getText());
        this.botao[i][j].setBackground(fundo);
        this.botao[i][j].setFont(botaoBranco);
        this.botao[i][j].setText("0");
        this.player.setMovimento(this.player.getMovimento() + 1);
        this.movimentos.setText("Movimentos: "+this.player.getMovimento()+ " |");
    }

    public void casaCorreta(T[][] gabarito) {
        for(int i=0; i<this.botao.length ;i++) { //caso o botao estiver na posicao certa ele troca de cor
            for (int j = 0; j < this.botao[i].length; j++) {
                if (String.valueOf(gabarito[i][j]).equals(this.botao[i][j].getText())) {
                    if (this.botao[i][j].getText().equals("0")) { //se o botão que esta na posição correta é o branco então não faz nada
                        continue;
                    } else {
                        if (this.player.getTipoJogo() == 3){
                            this.botao[i][j].setForeground(new Color(23,197,0));
                        } else {
                            this.botao[i][j].setBackground(new Color(153, 50, 204));
                        }
                    }
                }
            }
        }
    }

    public abstract void actionPerformed(ActionEvent e);

    public JButton[][] getBotao(){
        return this.botao;
    }

    public Usuario getPlayer(){
        return this.player;
    }

    public JLabel getMovimentos() {
        return movimentos;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getPainelMenu() {
        return painelMenu;
    }

}
