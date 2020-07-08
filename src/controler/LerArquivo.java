package controler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import model.Cabecalho;

public class LerArquivo {

	public static Cabecalho salvaCabecalho(String arquivo) throws IOException {
		BufferedReader arq = new BufferedReader(new FileReader(arquivo));
		Cabecalho cabecalho;
		String extensao = arq.readLine();
		String dimencao = arq.readLine();
		String niveisCinza = arq.readLine();
		cabecalho = new Cabecalho(extensao, dimencao, niveisCinza);

		return cabecalho;

	}

	public static int largura(String arquivo) throws IOException {

		BufferedReader arq = new BufferedReader(new FileReader(arquivo));
		@SuppressWarnings("unused")
		String primeiralinha = arq.readLine();
		String info = " ";
		do {
			info = arq.readLine();
		} while (info.charAt(0) == '#');
		StringTokenizer st = new StringTokenizer(info, " ");
		int largura = Integer.parseInt(st.nextToken());

		return largura;

	}

	public static int altura(String arquivo) throws IOException {

		BufferedReader arq = new BufferedReader(new FileReader(arquivo));
		@SuppressWarnings("unused")
		String primeiralinha = arq.readLine();
		String info = " ";
		do {
			info = arq.readLine();
		} while (info.charAt(0) == '#');
		StringTokenizer st = new StringTokenizer(info, " ");
		st.nextToken();
		int altura = Integer.parseInt(st.nextToken());

		return altura;
	}

	public static int[] pixels(String arquivo) throws IOException {

		int largura, altura;
		Cabecalho cabecalho = LerArquivo.salvaCabecalho(arquivo);
		StringTokenizer tokensImg = new StringTokenizer(cabecalho.getDimencao(), " ");

		largura = Integer.parseInt(tokensImg.nextToken());
		altura = Integer.parseInt(tokensImg.nextToken());

		int[][] pixels = new int[largura][altura];

		BufferedReader arq = new BufferedReader(new FileReader(arquivo));
		@SuppressWarnings("unused")
		String primeiralinha = arq.readLine();
		String info = " ";
		do {
			info = arq.readLine();
		} while (info.charAt(0) == '#');
		info = arq.readLine();

		for (int i = 0; i < largura; i++) {
			info = arq.readLine();
			StringTokenizer st1 = new StringTokenizer(info, " ");
			for (int j = 0; j < altura; j++) {
				pixels[i][j] = Integer.parseInt(st1.nextToken());
			}
		}

		int indice = 0;
		int[] resultado = new int[largura * altura];
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				resultado[indice] = pixels[i][j];
				indice++;
			}
		}
		return resultado;
	}
}
