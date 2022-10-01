package puzzleN.view.GUIRanking;

import puzzleN.model.Ranking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIRanking extends JPanel implements ActionListener {
    private JFrame mainFrame;
    private JPanel painelMenu;
    private JLabel dificuldade;
    private Ranking ranking;
    private int dificuldadeRanking;

    JButton voltarMenu = new JButton("Voltar ao início");
    Color fundo = new Color(253,184,39);
    Font fonteT = new Font("", Font.BOLD,25);
    Font fonteTRanking = new Font("", Font.BOLD, 20);
    Font fonteRecords = new Font("", Font.BOLD, 10);

    public GUIRanking(JFrame mainMenu, JPanel painelMenu, int dificuldadeRanking) {
        this.mainFrame = mainMenu;
        this.painelMenu = painelMenu;
        this.dificuldadeRanking = dificuldadeRanking;

        setSize(500,430);
        setLayout(new BorderLayout());
        setVisible(true);
        this.ranking = new Ranking();
        this.ranking.organizarRanking(this.dificuldadeRanking);
        parteCima();
        painelLadoLeste();
        painelLadoOeste();
        painelBaixo();
        painelMeio();
    }
    public void parteCima(){
        JPanel cima = new JPanel();
        cima.setLayout(new BoxLayout(cima, BoxLayout.PAGE_AXIS));
        cima.setBackground(fundo);
        cima.setPreferredSize(new Dimension(500, 84));

        JLabel titulo = new JLabel("Ranking");
        titulo.setFont(fonteT);
        titulo.setForeground(new Color(54, 54, 54));
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        if(this.dificuldadeRanking == 2){
            this.dificuldade = new JLabel("Fácil");
        } else if (this.dificuldadeRanking == 3) {
            this.dificuldade = new JLabel("Médio");
        } else {
            this.dificuldade = new JLabel("Difícil");
        }
        this.dificuldade.setFont(fonteTRanking);
        this.dificuldade.setForeground(new Color(54, 54, 54));
        this.dificuldade.setAlignmentX(CENTER_ALIGNMENT);

        cima.add(Box.createRigidArea(new Dimension(0,10)));
        cima.add(titulo);
        cima.add(this.dificuldade);
        add(cima, BorderLayout.NORTH);
    }
    public void painelLadoLeste(){
        JPanel lado = new JPanel();
        lado.setBackground(fundo);
        lado.setPreferredSize(new Dimension(100, 430));
        add(lado, BorderLayout.EAST);
    }
    public void painelLadoOeste(){
        JPanel lado = new JPanel();
        lado.setBackground(fundo);
        lado.setPreferredSize(new Dimension(100, 430));
        add(lado, BorderLayout.WEST);
    }
    public void painelBaixo(){
        JPanel baixo = new JPanel();
        baixo.setLayout(new BoxLayout(baixo, BoxLayout.PAGE_AXIS));
        baixo.setBackground(fundo);
        baixo.setPreferredSize(new Dimension(500, 84));

        voltarMenu.setAlignmentX(CENTER_ALIGNMENT);
        voltarMenu.setMaximumSize(new Dimension(140,40));
        voltarMenu.addActionListener(this);

        baixo.add(Box.createRigidArea(new Dimension(0,15)));
        baixo.add(voltarMenu);
        add(baixo, BorderLayout.SOUTH);
    }
    public void painelMeio() {
        JPanel meio = new JPanel();
        meio.setLayout(new GridLayout(1,1));
        meio.setBackground(new Color(0, 0, 0));
        this.ranking.mostrarRecordes(meio, this.dificuldadeRanking);
        add(meio, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == voltarMenu) {
            this.mainFrame.setContentPane(this.painelMenu);
            this.painelMenu.revalidate();
        }
    }
}
