package mapfood;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVHandle {

    //Cria uma lista de linhas para inserir no CSV
    //Todas as listas terao o mesmo tamanho
    public List CriaLinhas(List horario, List IDRestaurante, List IDCliente, List IDPedidos){
        List<String> Linha = new ArrayList<>();
        String AuxLinha = "";
        String separador = ",";

       Iterator varHorario = horario.iterator();
        Iterator varIDRestaurante = IDRestaurante.iterator();
        Iterator varIDCliente = IDCliente.iterator();
        Iterator varIDPedidos = IDPedidos.iterator();
       while (varHorario.hasNext()) {
           String IDjuntos = "";
           String auxHorario = (String) varHorario.next();
           String auxIDRestaurante = (String) varIDRestaurante.next();
           String auxIDCliente = (String) varIDCliente.next();
           String auxIDPedidos = (String) varIDPedidos.next();

           IDjuntos = auxHorario + separador + auxIDRestaurante + separador +
                   auxIDCliente + separador + auxIDPedidos +"\n";
          // System.out.println(IDjuntos);
           Linha.add(IDjuntos);
       }

       return Linha;


    }



   //Transforma String[] para String podendo sselecionar qual vai ser o tipo de separacao entre eles
   public String TransformaIDPedidos(List IDPedidos){
       String IDjuntos ="";
       String separador = "-";
       Integer aux_tamanho = 1;


       Iterator var = IDPedidos.iterator();
       while (var.hasNext()) {
           String aux = (String) var.next();
           IDjuntos = IDjuntos + aux;
           //System.out.println(IDjuntos);
           if (IDPedidos.size() > aux_tamanho){
               IDjuntos = IDjuntos + separador;
               aux_tamanho++;
           }
       }
       return IDjuntos;
   }


   //Cria o arquivo CSV para teste
    public void CsvWriter(String csvFileCaminho, String csvFileNome, List Linhas) {

        String csvPath = csvFileCaminho + csvFileNome;
        try (PrintWriter writer = new PrintWriter(new File(csvPath))) {

            Iterator var = Linhas.iterator();
            while (var.hasNext()) {
                String aux = (String) var.next();
                writer.write(aux.toString());

            }



            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
