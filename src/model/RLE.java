package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import controler.LerArquivo;

@SuppressWarnings(value = { "unused" })

public class RLE {

	private static FileWriter arquivoSaida;

	public static void descompactar(String nomeArquivo, File arquivo, String local) throws IOException {

		if (nomeArquivo != null) {
			Cabecalho cabecalho = LerArquivo.salvaCabecalho(nomeArquivo);
			arquivoSaida = new FileWriter(nomeArquivo.replaceAll(".jft", "Descompactado.pgm"));

			try {
				int[] arquivoArray = LerArquivo.pixels(nomeArquivo);
				ArrayList<Integer> arraySaida = new ArrayList<Integer>();

				int contador = 1;
				int indice = 0;
				int apontador = 0;
				while (apontador < arquivoArray.length) {
					if (apontador % 2 == 0) {
						contador = arquivoArray[apontador];
					}
					for (int i = 0; i < contador; i++) {
						arraySaida.add(arquivoArray[apontador + 1]);
					}
				}

				arquivoSaida.write(cabecalho.getExtensao() + "\r\n");
				arquivoSaida.write(cabecalho.getDimencao() + "\r\n");
				arquivoSaida.write(cabecalho.getNiveisCinza() + "\r\n");
				for (Integer i : arraySaida) {
					arquivoSaida.write(i + " ");
				}
				arquivoSaida.close();

				// System.out.println(arraySaida.toString());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void compactar(String nomeArquivo, File arquivo, String local) throws IOException {

		if (nomeArquivo != null) {
			Cabecalho cabecalho = LerArquivo.salvaCabecalho(nomeArquivo);
			arquivoSaida = new FileWriter(nomeArquivo.replaceAll(".pgm", ".jft"));

			try {
				int[] arquivoArray = LerArquivo.pixels(nomeArquivo);
				ArrayList<Integer> arraySaida = new ArrayList<Integer>();

				int contador = 1;
				int indice = 0;
				int valorA = arquivoArray[indice];
				int valorB;
				int apontador = 0;
				while (apontador < arquivoArray.length) {
					valorB = arquivoArray[indice + 1];
					if (valorA == valorB) {
						contador++;
						indice++;
					} else {
						arraySaida.add(contador);
						arraySaida.add(valorA);
						valorA = valorB;
						contador = 1;
					}
					apontador++;
				}
				arraySaida.add(contador);
				arraySaida.add(valorA);

				arquivoSaida.write(cabecalho.getExtensao() + "\r\n");
				arquivoSaida.write(cabecalho.getDimencao() + "\r\n");
				arquivoSaida.write(cabecalho.getNiveisCinza() + "\r\n");
				for (Integer i : arraySaida) {
					arquivoSaida.write(i + " ");
				}
				arquivoSaida.close();

				System.out.println(arraySaida.toString());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
