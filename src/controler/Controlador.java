package controler;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import model.LZW;
import model.RLE;
import view.TelaInicial;

public class Controlador {

	private TelaInicial telaI;
	private boolean LZWselecao = false;
	private boolean RLEselecao = false;
	private boolean Hufselecao = false;
	private File file;
	private String filepath;
	private String localArquivo;

	public Controlador(TelaInicial telaI) {
		this.setTelaI(telaI);
	}

	public void inicia() {
		telaI.setVisible(true);
	}

	public void btnCompactarAction(ActionEvent arg0) {
		if (LZWselecao == true) {
			LZW.criaDicionario();
			LZW.compactar(filepath, file, localArquivo);
			System.out.println("compactado !!!");
		}
		if (RLEselecao == true) {
			try {
				RLE.compactar(filepath, file, localArquivo);
				System.out.println("compactado !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public TelaInicial getTelaI() {
		return telaI;
	}

	public void setTelaI(TelaInicial telaI) {
		this.telaI = telaI;
	}

}
