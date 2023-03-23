package program;

import java.io.Serializable;

public class Transicao implements Serializable {
	private static final long serialVersionUID = 1L;

	private String origem;
	private String simbolo;
	private String destino;
	
	public Transicao() {
	}

	public Transicao(String origem, String simbolo, String destino) {
		super();
		this.origem = origem;
		this.simbolo = simbolo;
		this.destino = destino;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "[origem=" + origem + ", simbolo=" + simbolo + ", destino=" + destino+ "]";
	}
}
