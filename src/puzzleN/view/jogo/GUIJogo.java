package puzzleN.view.jogo;
import puzzleN.model.*;
import puzzleN.controller.botoes.BotaoRecomecar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public abstract class GUIJogo extends JPanel implements IGUIParteMeio {
    private Usuario player;
    private Cronometro cronometro;
    private JFrame mainFrame;
    private JPanel painelMenu;
    private JLabel tentativas;
    private JLabel dificuldade;
    private JLabel puzzleMaluco;
    private JLabel tempo;
    private JPanel painelSul;
    private JButton[][] botoes;
    private SaveGame saveGame;
    JButton reset = new JButton("Recomeçar");
    Color fundo = new Color(253,184,39);
    Font fontePainel = new Font("", Font.BOLD, 15);

    public GUIJogo(Usuario player, JFrame mainFrame, JPanel painelMenu){
        this.player = player;
        this.mainFrame = mainFrame;
        this.painelMenu = painelMenu;
        if (this.player.getPuzzleNMaluco() && !new File("./src/resources/save/SaveGame.txt").isFile()){
            this.player.setRandomMaluco(0);
        }
        this.tempo = new JLabel();
        this.botoes = new JButton[this.player.getNivel()][this.player.getNivel()];

        setLayout(new BorderLayout());
        setVisible(true);
    }
    public void parteCima(Usuario player){
        JPanel norte = new JPanel();
        norte.setBackground(fundo);
        norte.setBorder(new EmptyBorder(30,0,0,0));
        norte.setLayout(new FlowLayout());
        norte.setPreferredSize(new Dimension(700,100));
        JLabel usuario = new JLabel(player.getNome()+" |");
        if(this.player.getPuzzleNMaluco()){
            if (this.player.getRandomMaluco() <= 0.03){
                this.puzzleMaluco = new JLabel("Maluquice leve |");
            } else if ((this.player.getRandomMaluco() > 0.03) && (this.player.getRandomMaluco() <= 0.06)) {
                this.puzzleMaluco = new JLabel("Maluquice média |");
            } else if ((this.player.getRandomMaluco() > 0.06) && (this.player.getRandomMaluco() <= 0.09)){
                this.puzzleMaluco = new JLabel("Maluquice forte |");
            } else {
                this.puzzleMaluco = new JLabel("Maluquice EXTREMA |");
            }
        }
        if(this.player.getNivel()==2){
            this.dificuldade = new JLabel("Fácil |");
        } else if (player.getNivel()==3) {
            this.dificuldade = new JLabel("Médio |");
        } else if (player.getNivel()==4) {
            this.dificuldade = new JLabel("Difícil |");
        }
        this.tentativas = new JLabel("Movimentos: " +player.getMovimento()+" |");
        usuario.setFont(fontePainel);
        this.dificuldade.setFont(fontePainel);
        this.tentativas.setFont(fontePainel);
        this.tempo.setFont(fontePainel);
        this.tempo.setText("00:00:00:00");
        norte.add(usuario);
        if (this.player.getPuzzleNMaluco()){
            norte.add(this.puzzleMaluco);
            this.puzzleMaluco.setFont(fontePainel);
        }
        norte.add(this.dificuldade);
        norte.add(this.tentativas);
        norte.add(tempo);
        add(norte,BorderLayout.NORTH);
        this.cronometro = new Cronometro(this.player, this.tempo);
        this.cronometro.comecarCronometro();

        this.saveGame = new SaveGame(this.player, this.botoes, this.cronometro);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.mainFrame.addWindowListener(this.saveGame);
    }

    public void parteBaixo(){
        this.painelSul = new JPanel(new FlowLayout());
        this.painelSul.setPreferredSize(new Dimension(700,100));
        this.painelSul.setBackground(fundo);
        BotaoRecomecar botaoRecomecar = new BotaoRecomecar(this.reset, this.mainFrame, this.painelMenu, this.player, this.cronometro, this.saveGame);
        reset.addActionListener(botaoRecomecar);
        this.painelSul.add(reset);
        add(this.painelSul,BorderLayout.SOUTH);
    }

    public Usuario getPlayer(){
        return this.player;
    }
    public JFrame getMainFrame(){
        return this.mainFrame;
    }
    public JPanel getPainelMenu(){
        return this.painelMenu;
    }
    public JLabel getTentativas(){
        return this.tentativas;
    }
    public Cronometro getCronometro(){
        return this.cronometro;
    }
    public JPanel getPainelSul(){
        return this.painelSul;
    }
    public JButton[][] getBotoes() {
        return botoes;
    }
    public SaveGame getSaveGame() {
        return saveGame;
    }
}
