package model;

public class Cabecalho {

	private String extensao;
	private String dimencao;
	private String niveisCinza;

	public Cabecalho(String extensao, String dimencao, String niveisCinza) {
		this.extensao = extensao;
		this.dimencao = dimencao;
		this.niveisCinza = niveisCinza;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getDimencao() {
		return dimencao;
	}

	public void setDimencao(String dimencao) {
		this.dimencao = dimencao;
	}

	public String getNiveisCinza() {
		return niveisCinza;
	}

	public void setNiveisCinza(String niveisCinza) {
		this.niveisCinza = niveisCinza;
	}
}
