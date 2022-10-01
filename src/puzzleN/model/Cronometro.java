package puzzleN.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cronometro implements ActionListener {
    private Usuario player;
    private JLabel cronometro;
    private Processos processos;
    private long tempoDecorrido;
    private long tempoInicial;

    public Cronometro(Usuario player ,JLabel cronometro){
        this.player = player;
        this.cronometro = cronometro;
        this.processos = new Processos(this.player.getNivel());
    }

    public void actionPerformed(ActionEvent e) {
        this.tempoDecorrido = System.currentTimeMillis() - this.tempoInicial;
        this.cronometro.setText(processos.calcularTempo(this.tempoDecorrido));
    }

    Timer timer = new Timer(10, this);

    public void pararCronometro(){
        timer.stop();
        player.setTempo(this.tempoDecorrido);
    }

    public void comecarCronometro(){
        this.tempoInicial = System.currentTimeMillis();
        timer.start();
    }

}

