package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LZW {

	private static BufferedReader ReadH;
	private static BufferedWriter WriteH;
	private static ArrayList<String> dicionario;
	private static FileOutputStream arquivoSaida;
	private static DataOutputStream dataSaida;

	public static void criaDicionario() {
		dicionario = new ArrayList<String>();
		String aux = "";
		for (short i = 0; i < 256; i++) {
			aux = Character.toString((char) i);
			dicionario.add(aux);
		}
	}

	public static short pesquisaNoDicionario(String seq) {
		short i = 256;
		while (i < dicionario.size()) {
			if (seq.equals((dicionario.get(i)))) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static void addAoDicionario(String seq) {
		dicionario.add(seq);
	}

	public static void compactar(String nomeArquivo, File arquivo, String local) {

		LZW.criaDicionario();

		if (nomeArquivo != null) {

			String seq = null;
			short Codigo;
			short codigoAntes;
			short resultado;
			try {
				arquivo = new File(nomeArquivo.replaceAll(".pgm", ".jft"));
				arquivoSaida = new FileOutputStream(arquivo);
				dataSaida = new DataOutputStream(arquivoSaida);

			} catch (IOException ex) {
				ex.printStackTrace();
			}

			try {
				ReadH = new BufferedReader(new FileReader(nomeArquivo));

				codigoAntes = Codigo = (short) ReadH.read();
				seq = Character.toString((char) Codigo);

				while (seq != null) {

					if (seq.length() > 1) {
						resultado = pesquisaNoDicionario(seq);
						if (resultado == -1) {

							addAoDicionario(seq);

							seq = Character.toString(seq.charAt(seq.length() - 1));

							dataSaida.writeShort(codigoAntes);
							codigoAntes = (short) ((char) seq.charAt(0));

						} else {
							codigoAntes = resultado;
						}
					}
					Codigo = (short) ReadH.read();
					seq += Character.toString((char) Codigo);

					if (Codigo == -1) {
						dataSaida.writeShort(codigoAntes);
						break;
					}
				}

				ReadH.close();
				dataSaida.flush();
				dataSaida.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void descompactar(String nomeArquivo, File arquivo, String local, DataInputStream dataEntrada) {

		if (nomeArquivo != null) {
			String seq = null, nextseq = null, todic = null;
			criaDicionario();
			short Code;
			short resultado;
			try {

				WriteH = new BufferedWriter(new FileWriter(nomeArquivo.replaceAll(".jft", "Descompactado.pgm")));

			} catch (IOException ex) {
				ex.printStackTrace();
			}

			try {

				Code = dataEntrada.readShort();
				seq = Character.toString((char) Code);

				while (true) {
					Code = dataEntrada.readShort();
					nextseq = getTheString(Code);
					resultado = pesquisaNoDicionario(seq + nextseq);

					if (resultado != -1) {
						seq += nextseq;
						todic = null;
					} else {
						if (!nextseq.equals("?")) {
							todic = seq + nextseq.substring(0, 1);

							addAoDicionario(todic);
							WriteH.write(seq);

							seq = nextseq;
						} else {

							todic = seq + seq.substring(0, 1);
							addAoDicionario(todic);
							WriteH.write(seq);

							seq = nextseq = todic;
						}
					}
				}

			} catch (IOException ex) {
				try {

					if (seq != null) {
						WriteH.write(seq);
					}
				} catch (IOException ex1) {
					ex.printStackTrace();
				}
			}
			try {

				WriteH.flush();
				WriteH.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String getTheString(short code) {

		if (code < 256)
			return Character.toString((char) code);
		else if (code < dicionario.size()) {
			return (dicionario.get(code));
		} else
			return "?";
	}
}
