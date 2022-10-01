package puzzleN.view.GUIRanking;

import puzzleN.model.Usuario;
import puzzleN.view.Dificuldade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUIDificuldadeRanking extends JPanel implements ActionListener {

    private JFrame mainFrame;
    private JPanel painelMenu;
    private Usuario player;
    private int rankingDificuldade;
    Color fundo = new Color(253,184,39);
    Font fonteT = new Font("", Font.BOLD,30);

    JButton facil = new JButton("Fácil");
    JButton medio = new JButton("Médio");
    JButton dificil = new JButton("Dificil");
    JButton voltar = new JButton("Voltar");
    public GUIDificuldadeRanking(JFrame mainFrame, JPanel painelMenu, Usuario player) {
        this.mainFrame = mainFrame;
        this.painelMenu = painelMenu;
        this.player = player;

        setSize(500,430);
        setLayout(new BorderLayout());
        setVisible(true);
        painelDificuldade();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==facil){
            if (new File("./src/ranking/RankingFacil.txt").isFile()){
                rankingDificuldade = 2;
                GUIRanking guiRanking = new GUIRanking(this.mainFrame, this.painelMenu, rankingDificuldade);
                mainFrame.setContentPane(guiRanking);
                guiRanking.revalidate();
            }else {
                JOptionPane.showMessageDialog(null,"Ainda não há registro de ranking nesta dificuldade.", "Ranking inexistente", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource()==medio) {
            if (new File("./src/ranking/RankingMedio.txt").isFile()){
                rankingDificuldade = 3;
                GUIRanking guiRanking = new GUIRanking(this.mainFrame, this.painelMenu, rankingDificuldade);
                mainFrame.setContentPane(guiRanking);
                guiRanking.revalidate();
            }else {
                JOptionPane.showMessageDialog(null,"Ainda não há registro de ranking nesta dificuldade.", "Ranking inexistente", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource()==dificil) {
            if (new File("./src/ranking/RankingDificil.txt").isFile()){
                rankingDificuldade = 4;
                GUIRanking guiRanking = new GUIRanking(this.mainFrame, this.painelMenu, rankingDificuldade);
                mainFrame.setContentPane(guiRanking);
                guiRanking.revalidate();
            }else {
                JOptionPane.showMessageDialog(null,"Ainda não há registro de ranking nesta dificuldade.", "Ranking inexistente", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource()==voltar){
            this.mainFrame.setContentPane(this.painelMenu);
            this.painelMenu.revalidate();
        }
    }
        public void painelDificuldade(){
            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.PAGE_AXIS));
            painel.setBackground(fundo);
            JLabel titulo = new JLabel("Ranking de qual dificuldade?");
            titulo.setFont(fonteT);

            titulo.setAlignmentX(CENTER_ALIGNMENT);
            facil.setAlignmentX(CENTER_ALIGNMENT);
            medio.setAlignmentX(CENTER_ALIGNMENT);
            dificil.setAlignmentX(CENTER_ALIGNMENT);
            voltar.setAlignmentX(CENTER_ALIGNMENT);

            facil.setMaximumSize(new Dimension(150,60));
            medio.setMaximumSize(new Dimension(150,60));
            dificil.setMaximumSize(new Dimension(150,60));
            voltar.setMaximumSize(new Dimension(100,40));

            painel.add(Box.createRigidArea(new Dimension(0,10)));
            painel.add(titulo);
            painel.add(Box.createRigidArea(new Dimension(0,30)));
            facil.addActionListener(this);
            painel.add(facil);
            painel.add(Box.createRigidArea(new Dimension(0,10)));
            medio.addActionListener(this);
            painel.add(medio);
            painel.add(Box.createRigidArea(new Dimension(0,10)));
            dificil.addActionListener(this);
            painel.add(dificil);
            painel.add(Box.createRigidArea(new Dimension(0,20)));
            voltar.addActionListener(this);
            painel.add(voltar);

            add(painel, BorderLayout.CENTER);
        }
}
