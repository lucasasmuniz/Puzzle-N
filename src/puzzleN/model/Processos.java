package puzzleN.model;

import puzzleN.controller.movimentos.MovJogo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Processos <T>{ //Essa classe depois herdará de usuarios

    private int tamanho;
    private Integer[] randomPosicoes;

    Color fundo = new Color(253,184,39);

    public Processos(int tamanho){
        this.tamanho = tamanho;
    }

    public Integer[][] gabarito() { //Array para a resposta
        Integer[][] array = new Integer[this.tamanho][this.tamanho];
        for(int i = 0; i<array.length; i++) {
            for(int j = 0; j<array[i].length; j++) {
                array[i][j] = j +(i*tamanho) + 1;
            }
        }
        array[this.tamanho-1][this.tamanho-1] = 0;
        return array;
    }

    public Integer[] random(){ //Randomiza os numeros, mas aqui não se sabe se é possivel resolver o jogo
        Random rand = new Random();
        Integer[] array = new Integer[this.tamanho*this.tamanho];
        for(int i=0; i<this.tamanho*this.tamanho; i++) {
            array[i] = i+1;
        }
        array[(this.tamanho*this.tamanho)-1] = 0;
        for(int i=0; i<this.tamanho*this.tamanho ;i++) {
            int index = rand.nextInt(this.tamanho*this.tamanho);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }
    public int numeroInversoes(Integer array[]){ //Um dos processos para saber se é possivel resolver o jogo
        int nInversoes = 0;
        for(int i=0; i<this.tamanho*this.tamanho-1 ;i++){
            for(int j=i+1; j<this.tamanho*this.tamanho ;j++){
                if (array[j] != 0 && array[i] != 0 && array[i] > array[j]) {
                    nInversoes++;
                }
            }
        }
        return nInversoes;
    }
    public int acharPecaBrancaInversao(Integer array[]){ //Outro processo para saber se é possivel resolver o jogo
        int linha = this.tamanho - 1;
        for(int i = array.length - 1; array[i] != 0 ; i--){
            if (i % this.tamanho == 0){
                linha--;
            }
        }
        return this.tamanho - linha;
    }
    public boolean jogoResolvivel(Integer array[]){ //Aqui é onde sera verificado se os numeros que foram randomizados são possiveis de serem resolvidos
        int nInversoes = numeroInversoes(array);
        if (this.tamanho%2 !=0 ){
            return (nInversoes%2 == 0);
        }else{
            int posicaoBranco = acharPecaBrancaInversao(array);
            if (posicaoBranco % 2 != 0){
                return (nInversoes%2 == 0);
            }else{
                return (nInversoes%2 !=0 );
            }
        }
    }
    public Integer[] randomResolvivel(){ //Aqui sera sorteado os numeros ate que uma sequencia seja possivel
        boolean resolvivel = false;
        while(resolvivel == false){
            Integer[] randomPosicoes = random();
            this.randomPosicoes = randomPosicoes;
            resolvivel = jogoResolvivel(this.randomPosicoes);
        }
        return this.randomPosicoes;
    }
    public boolean foiResolvido(JButton[][] botoes){
        Integer array[][] = gabarito();
        for (int i = 0; i<this.tamanho; i++){
            for(int j = 0; j<this.tamanho ;j++){
                if (String.valueOf(array[i][j]).equals(botoes[i][j].getText()) == false){
                    return false;
                }
            }
        }
        return true;
    }
    public Character trocarIntPorChar(int i) { //método que transforma um valor int passado no parametro em seu char correspondente, ex: 1 = a
        return i > 0 && i < 27 ? Character.valueOf((char)(i + 'A' - 1)) : null;
    }
    public Character[] randomResolvivelChar(){
        Integer[] randomNumero = randomResolvivel();
        Character[] randomChar = new Character[this.tamanho*this.tamanho];
        for(int i = 0; i<randomChar.length ;i++){
            if (randomNumero[i] == 0){
                randomChar[i] = '0';
            }else{
                randomChar[i] = trocarIntPorChar(randomNumero[i]);
            }
        }
        return randomChar;
    }
    public Character[][] gabaritoChar(){
        Integer[][] respostaNumero = gabarito();
        Character[][] respostaChar = new Character[this.tamanho][this.tamanho];
        for(int i = 0; i<this.tamanho ;i++){
            for (int j = 0; j<this.tamanho ;j++){
                if(respostaNumero[i][j] == 0){
                    respostaChar[i][j] = '0';
                }else{
                    respostaChar[i][j] = trocarIntPorChar(respostaNumero[i][j]);
                }
            }
        }
        return respostaChar;
    }
    public boolean foiResolvidoChar(JButton[][] botoes){
        Character array[][] = gabaritoChar();
        for (int i = 0; i<this.tamanho; i++){
            for(int j = 0; j<this.tamanho ;j++){
                if (String.valueOf(array[i][j]).equals(botoes[i][j].getText()) == false){
                    return false;
                }
            }
        }
        return true;
    }
    public String calcularTempo(long tempoDecorrido){ //método para pegar um valor long (tempo em milissegundos) e retornar uma string de horario
        long hora = (tempoDecorrido/3600000) % 24;
        long minutos = (tempoDecorrido/60000) % 60;
        long segundos = (tempoDecorrido/1000) % 60;
        long centisegundos = (tempoDecorrido/10) % 100;
        return (String.format("%02d:%02d:%02d:%02d", hora, minutos, segundos, centisegundos));
    }
}
