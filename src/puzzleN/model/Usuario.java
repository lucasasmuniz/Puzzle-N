package puzzleN.model;

import java.util.*;


public class Usuario {
    private String nome;
    private int movimento;
    private int nivel;
    private int tipoJogo;
    private long tempo;
    private Boolean ajudaAtiva;
    private Boolean puzzleNMaluco;
    private double randomMaluco;
    private String[] arraySalvo;
    public Usuario(String nome, long tempo, int movimento){
        this.nome = nome;
        this.tempo = tempo;
        this.movimento = movimento;
    }

    public void setNome(String nome) throws NomeDeUsuarioException {
        if(nome==null) {
            this.nome = null;
        }else if(nome.trim().length()<=12 && nome.trim().length()>=3) {
            this.nome = nome.trim();//replaceAll("\\s", "");
        }else {
            throw new NomeDeUsuarioException();
        }
    }
    public String getNome() {
        return this.nome;
    }

    public void setNivel(int dificuldade) {
        this.nivel = dificuldade;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setTipoJogo(int tipoJogo){
        this.tipoJogo = tipoJogo;
    }

    public int getTipoJogo(){
        return this.tipoJogo;
    }

    public void setMovimento(int movimento){
        this.movimento = movimento;
    }

    public int getMovimento(){
        return this.movimento;
    }

    public void setAjudaAtiva(Boolean ativo){
        this.ajudaAtiva = ativo;
    }

    public Boolean getAjudaAtiva(){
        return this.ajudaAtiva;
    }

    public void setPuzzleNMaluco(Boolean puzzleNMaluco) {
        this.puzzleNMaluco = puzzleNMaluco;
    }

    public Boolean getPuzzleNMaluco(){
        return this.puzzleNMaluco;
    }

    public void setTempo(long tempo){
        this.tempo = tempo;
    }

    public long getTempo(){
        return this.tempo;
    }

    public void setRandomMaluco(double randomMaluco){
        if (randomMaluco == 0){
            Random random = new Random();
            this.randomMaluco = (double) (random.nextInt(12) + 1)/100;
        } else {
            this.randomMaluco = randomMaluco;
        }
    }

    public double getRandomMaluco(){
        return this.randomMaluco;
    }

    public void setArraySalvo(String[] arraySalvo) {
        this.arraySalvo = arraySalvo;
    }

    public String[] getArraySalvo() {
        return arraySalvo;
    }

    public void playerReset(){
        this.nome = null;
        this.movimento = 0;
        this.nivel = 0;
        this.tipoJogo = 0;
        this.tempo = 0;
        this.ajudaAtiva = null;
        this.puzzleNMaluco = null;
        this.randomMaluco = 0;
        this.arraySalvo = null;
    }
}
