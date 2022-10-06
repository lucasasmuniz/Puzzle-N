package puzzleN.model;

import puzzleN.view.jogo.GUIJogo;
import puzzleN.view.jogo.GUIJogoCaractere;
import puzzleN.view.jogo.GUIJogoImagem;
import puzzleN.view.jogo.GUIJogoNumero;

import javax.swing.*;
import java.io.*;

public class ChecarSave {

    private Usuario player;
    private JFrame mainFrame;
    private JPanel painelMenu;
    private File save;

    public ChecarSave(Usuario player, JFrame mainFrame, JPanel painelMenu){
        this.player = player;
        this.mainFrame = mainFrame;
        this.painelMenu = painelMenu;
        checarSaveGame();
    }

    public void checarSaveGame() {
        this.save = new File("./src/resources/save/SaveGame.txt");
        if (save.isFile()) {
            int resposta = JOptionPane.showConfirmDialog(null, "Há um jogo salvo, gostaria de terminá-lo?", "Jogo salvo", JOptionPane.YES_NO_OPTION);
            if (resposta == 0) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader("./src/resources/save/SaveGame.txt"));
                    String currentLine = reader.readLine();
                    while (currentLine != null) {
                        String[] usuarioDetalhe = currentLine.split(",");
                        this.player.setNome(usuarioDetalhe[0]);
                        this.player.setTempo(Long.parseLong(usuarioDetalhe[1]));
                        this.player.setMovimento(Integer.parseInt(usuarioDetalhe[2]));
                        this.player.setNivel(Integer.parseInt(usuarioDetalhe[3]));
                        this.player.setPuzzleNMaluco(Boolean.parseBoolean(usuarioDetalhe[4]));
                        this.player.setAjudaAtiva(Boolean.parseBoolean(usuarioDetalhe[5]));
                        this.player.setRandomMaluco(Double.parseDouble(usuarioDetalhe[6]));
                        this.player.setTipoJogo(Integer.parseInt(usuarioDetalhe[7]));
                        String temp[] = new String[this.player.getNivel()*this.player.getNivel()];
                        for (int i = 0; i < this.player.getNivel()*this.player.getNivel(); i++) {
                            temp[i] = usuarioDetalhe[i+8];
                        }
                        this.player.setArraySalvo(temp);
                        currentLine = reader.readLine();
                        abrirJogoSalvo();
                    }
                    reader.close();
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                } catch (NomeDeUsuarioException e) {
                }
            } else {
                this.save.delete();
            }
        }
    }
    public void abrirJogoSalvo(){
        if(this.player.getTipoJogo() == 1) {
            mainFrame.setSize(700,700);
            mainFrame.setLocationRelativeTo(null);
            GUIJogo numeroJogo = new GUIJogoNumero(this.player, this.mainFrame, this.painelMenu);
            mainFrame.setContentPane(numeroJogo);
            numeroJogo.parteCima(this.player);
            numeroJogo.parteMeio();
            numeroJogo.parteBaixo();
            numeroJogo.revalidate();
        }else if(this.player.getTipoJogo() == 2){
            mainFrame.setSize(700,700);
            mainFrame.setLocationRelativeTo(null);
            GUIJogo caractereJogo = new GUIJogoCaractere(this.player, this.mainFrame, this.painelMenu);
            mainFrame.setContentPane(caractereJogo);
            caractereJogo.parteCima(this.player);
            caractereJogo.parteMeio();
            caractereJogo.parteBaixo();
            caractereJogo.revalidate();
        }else if(this.player.getTipoJogo() == 3){
            mainFrame.setSize(700,700);
            mainFrame.setLocationRelativeTo(null);
            GUIJogo imagemJogo = new GUIJogoImagem(this.player, this.mainFrame, this.painelMenu);
            mainFrame.setContentPane(imagemJogo);
            imagemJogo.parteCima(this.player);
            imagemJogo.parteMeio();
            imagemJogo.parteBaixo();
            imagemJogo.revalidate();
        }
    }
}
