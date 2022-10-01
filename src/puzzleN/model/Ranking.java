package puzzleN.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking implements Comparator<Usuario> {

    public void salvarRanking(Usuario player) {
        File diretorio = new File("./src/ranking");
        if (!diretorio.isDirectory()){
            diretorio.mkdirs();
        }
        BufferedWriter writer = null;
        try {
            if (player.getNivel() == 2){
                writer = new BufferedWriter(new FileWriter("./src/ranking/RankingFacil.txt", true));
            } else if (player.getNivel() == 3) {
                writer = new BufferedWriter(new FileWriter("./src/ranking/RankingMedio.txt", true));
            } else {
                writer = new BufferedWriter(new FileWriter("./src/ranking/RankingDificil.txt", true));
            }
            writer.write(player.getNome()+ "," +player.getTempo()+ "," +player.getMovimento());
            writer.newLine();
        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {

            }
        }
    }
    public void organizarRanking(int dificuldadeRanking){
        BufferedReader reader = null;
        try{
            if (dificuldadeRanking == 2){
                reader = new BufferedReader(new FileReader("./src/ranking/RankingFacil.txt"));
            } else if (dificuldadeRanking == 3) {
                reader = new BufferedReader(new FileReader("./src/ranking/RankingMedio.txt"));
            } else {
                reader = new BufferedReader(new FileReader("./src/ranking/RankingDificil.txt"));
            }
            ArrayList<Usuario> gravacaoUsuario = new ArrayList<Usuario>();
            String currentLine = reader.readLine();
            while(currentLine != null){
                String[] usuarioDetalhe = currentLine.split(",");
                String nome = usuarioDetalhe[0];
                long tempo = Integer.valueOf(usuarioDetalhe[1]);
                int movimento = Integer.valueOf(usuarioDetalhe[2]);
                gravacaoUsuario.add(new Usuario(nome,tempo,movimento));
                currentLine = reader.readLine();
            }
            Collections.sort(gravacaoUsuario, this);
            BufferedWriter writer = null;
            if (dificuldadeRanking == 2){
                writer = new BufferedWriter(new FileWriter("./src/ranking/RankingFacil.txt", false));
            } else if (dificuldadeRanking == 3) {
                writer = new BufferedWriter(new FileWriter("./src/ranking/RankingMedio.txt", false));
            } else {
                writer = new BufferedWriter(new FileWriter("./src/ranking/RankingDificil.txt", false));
            }
            int i = 1;
            for (Usuario usuario : gravacaoUsuario){
                writer.write(usuario.getNome());
                writer.write(","+ usuario.getTempo());
                writer.write(","+ usuario.getMovimento());
                writer.newLine();
                i++;
                if(i>10){
                    break;
                }
            }
            writer.close();
            reader.close();
        }catch (FileNotFoundException e){
        }catch (IOException e) {
        }
    }

    public int compare(Usuario player1, Usuario player2) {
        return (int) (player1.getTempo() - player2.getTempo());
    }

    public void mostrarRecordes(JPanel painelMeio, int dificuldadeRanking){
        BufferedReader reader = null;
        try {
            if (dificuldadeRanking == 2){
                reader = new BufferedReader(new FileReader("./src/ranking/RankingFacil.txt"));
            } else if (dificuldadeRanking == 3) {
                reader = new BufferedReader(new FileReader("./src/ranking/RankingMedio.txt"));
            } else {
                reader = new BufferedReader(new FileReader("./src/ranking/RankingDificil.txt"));
            }

            String currentLine = reader.readLine();
            Processos processos = new Processos(dificuldadeRanking);

            DefaultTableModel tableModel = new DefaultTableModel(){

                public boolean isCellEditable(int row, int column) {
                    return false;
                }

            };

            tableModel.addColumn("Usuário");
            tableModel.addColumn("Tempo");
            tableModel.addColumn("Movimentos");
            int i = 0;

            while(currentLine != null){
                String[] usuarioDetalhe = currentLine.split(",");
                String nome = usuarioDetalhe[0];
                long tempo = Integer.valueOf(usuarioDetalhe[1]);
                int movimento = Integer.valueOf(usuarioDetalhe[2]);
                Object[] linha = {nome,processos.calcularTempo(tempo),movimento};
                tableModel.insertRow(i, linha);
                currentLine = reader.readLine();
                i++;
            }

            JTable table = new JTable(tableModel);
            table.setFocusable(false);
            table.setRowSelectionAllowed(false);
            table.setFont(new Font("", Font.BOLD, 10));
            table.setRowHeight(20);
            table.setBackground(Color.BLACK);
            table.setForeground(Color.WHITE);

            JTableHeader tableHeader = table.getTableHeader();
            tableHeader.setBackground(new Color(37, 37, 37));
            tableHeader.setFont(new Font("", Font.BOLD, 12));
            tableHeader.setForeground(Color.WHITE);
            tableHeader.setReorderingAllowed(false);

            TableColumnModel columnModel = table.getColumnModel();
            for(i = 0; i<3 ;i++){
                TableColumn tableColumn = columnModel.getColumn(i);
                tableColumn.setResizable(false);
            }

            painelMeio.add(new JScrollPane(table));

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }
}
