package mapfood;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;


public class Main {

	public static void main(String[] args) {


		CSVHandle CsvEscrever = new CSVHandle();

		//REcebe o caminho onde estara os CSVs e os nomes dos arquivos
		String csvCaminho = "C:\\Users\\Daniel\\codenation\\mapfood\\";
		String csvNomeClientes = "clientes.csv";
		String csvNomeEstabelecimentoMunicipo = "estabelecimento-por-municipio.csv";
		String csvNomeMotoboys = "motoboys.csv";
		String csvNomeProdutosEstabelcimento = "produtos-por-estabelecimento.csv";

		//Criara um objeto da classe CSVFile para cada CSV
		mapfood.CSVfile csvClientes = new mapfood.CSVfile(csvCaminho, csvNomeClientes);
		mapfood.CSVfile csvEstabelecimentoMunicipo = new mapfood.CSVfile(csvCaminho, csvNomeEstabelecimentoMunicipo);
		mapfood.CSVfile csvMotoboys = new mapfood.CSVfile(csvCaminho, csvNomeMotoboys);
		mapfood.CSVfile csvProdutosEstabelcimento = new mapfood.CSVfile(csvCaminho, csvNomeProdutosEstabelcimento);

		String horario = LocalDateTime.now().toString();
		//System.out.println(horario.toString());



		//Horarios para colcoar
		List<String> horarios = new ArrayList<>();
		horarios.add(horario);
		horarios.add(horario);
		//horarios.add(horario);
		//horarios.add(horario);


		//Recebe as informacoes necessarias
		//Precisaria deixar mais Random
		//Os IDs dos PEdidos precisa ser melhorado, porque eu estou buscando de ums String[], trasformando um ima LIst<String>
		//PAra depois TRasnformar essa lsita em String para depois voltar para List<String>
		List<String> informacaoIDRestaurante = csvEstabelecimentoMunicipo.RecebeInformacaoCSV("restaurant_id", 2);
		List<String> informacaoIDCliente = csvClientes.RecebeInformacaoCSV("ID Cliente", 2);
		List<String> informacaoIDPedidoaux = new ArrayList<>();
		informacaoIDPedidoaux = csvProdutosEstabelcimento.RecebeInformacaoCSV("item_id", 2);
		String aux = CsvEscrever.TransformaIDPedidos(informacaoIDPedidoaux);
		List<String> informacaoIDPedido = new ArrayList<>();
		//System.out.println(aux);
		informacaoIDPedido.add(aux);

		informacaoIDPedidoaux = csvProdutosEstabelcimento.RecebeInformacaoCSV("item_id", 2);
		aux = CsvEscrever.TransformaIDPedidos(informacaoIDPedidoaux);
		//System.out.println(aux);
		informacaoIDPedido.add(aux);


		List<String> linhas = CsvEscrever.CriaLinhas(horarios, informacaoIDRestaurante, informacaoIDCliente, informacaoIDPedido);
		//System.out.println(CsvEscrever.TransformaIDPedidos(informacaoIDPedido));

		CsvEscrever.CsvWriter(csvCaminho, "teste.csv", linhas);



		/*
		Iterator var = csvClientes.getDados().iterator();

		while (var.hasNext()) {
			String[] aux = (String[]) var.next();
			System.out.println((aux[0]));
		}
		*/

	}

}
