package mapfood;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class CSVfile {

    Map<String, Integer> colunas = new HashMap<>(); //Recebe o nome das colunas
    List<String[]> dados = new ArrayList<>(); //recebe os atributos do csv

    public CSVfile(String csvFileCaminho, String csvFileNome) {
        CriaListaPeloCSV(csvFileCaminho, csvFileNome);
    }

    public Map<String, Integer> getColunas() {
        return colunas;
    }

    public List<String[]> getDados() {
        return dados;
    }

    //Cria o mapa de colunas
    //Cria uma lista com o que o CSV possui dentro
    public void CriaListaPeloCSV(String csvFileCaminho, String csvFileNome) {
        String line = "";
        String cvsSplitBy = ",";
        String csvPath = csvFileCaminho + csvFileNome;
        int PrimeiraLinha = 0; // faz saber se eh a primeira linha ou nao


        try (BufferedReader file = new BufferedReader(new FileReader(csvPath))) {

            while ((line = file.readLine()) != null) {

                // use comma as separator
                String[] linha = line.split(cvsSplitBy);

                //Cria o mapa para os nomes dos indices do vetor de string do jogadores
                if (PrimeiraLinha == 0) {
                    for (String s : linha) {
                        colunas.put(s, PrimeiraLinha);
                        PrimeiraLinha++;
                    }
                } else {
                    dados.add(linha);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Integer BuscaIndiceColuna(String NomeColuna) {
        return this.colunas.get(NomeColuna);
    }

    //Envia uma coluna para o pedinte, podendo selecionar quantos itens deseja no maximo
    //Se Pedir mais de uma  nao for -1 (todos), vira como uma String e sera separado por - cada item
    // se enviar -1 eh porque quer tudo
    public List RecebeInformacaoCSV(String NomeColuna, Integer quantidade) {
        List<String> pedido = new ArrayList<>();
        int indice = BuscaIndiceColuna(NomeColuna);

        Iterator var = this.dados.iterator();

        if (quantidade == -1) {
            while (var.hasNext()) {
                String[] aux = (String[]) var.next();
                //System.out.println(aux[indice]);
                if (aux[indice].isEmpty() == false) {
                    pedido.add(aux[indice]);
                }
            }
        } else {
            for (int i = 0; i < quantidade; i++) {
                if (var.hasNext()) {
                    String[] aux = (String[]) var.next();
                    //System.out.println(aux[indice]);
                    if (aux[indice].isEmpty() == false) {
                        pedido.add(aux[indice]);
                    }
                } else {
                    break;
                }
            }
        }
        return pedido;
    }

    //Envia X colunas para o pedinte, sendo X os indices pedidos
    public List RecebeInformacaoJogadores_maior(String[] NomeColuna) {
        List<String[]> pedido = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (String s : NomeColuna) {
            indices.add(BuscaIndiceColuna(s));
        }

        Iterator var = this.dados.iterator();

        while (var.hasNext()) {
            String[] aux = (String[]) var.next();
            String[] auxS = new String[indices.size()];
            int i = 0;
            for (Integer index : indices) {
                auxS[i] = aux[index];
                i++;
            }
            int nulo = 0;
            for (String s : auxS) {
                if (s.isEmpty()) {
                    nulo = 1;
                }
            }

            if (nulo == 0) {
                pedido.add(auxS);
            }
        }

        return pedido;
    }

    public Map CriaTuplaFloat(String[] NomeColuna) {

        List<String> informacao_lista = RecebeInformacaoJogadores_maior(NomeColuna);
        Map<String, Float> mapa_clausula = new HashMap<>();

        //Transformo em mapa com o nome como key
        Iterator var = informacao_lista.iterator();
        while(var.hasNext()) {
            String[] aux = (String[]) var.next();
            mapa_clausula.put(aux[0], Float.parseFloat(aux[1]));
        }
        return mapa_clausula;

    }

    public Map CriaTuplaDate(String[] NomeColuna) {

        List<String> informacao_lista = RecebeInformacaoJogadores_maior(NomeColuna);
        Map<String, LocalDate> mapa_clausula = new HashMap<>();

        //Transformo em mapa com o nome como key
        Iterator var = informacao_lista.iterator();
        while(var.hasNext()) {
            String[] aux = (String[]) var.next();
            mapa_clausula.put(aux[0], LocalDate.parse(aux[1]));
        }
        return mapa_clausula;

    }








}
