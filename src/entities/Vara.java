package entities;

import java.util.ArrayList;
import java.util.List;

public class Vara {

	private Integer idVara;
	private String desVara;
	private List<Processo> processos = new ArrayList<Processo>();
	private Tribunal tribunal;

	public Vara() {
	}

	public Vara(String desVara, Tribunal tribunal) {
		this.desVara = desVara;
		this.tribunal = tribunal;
	}

	public Integer getIdVara() {
		return idVara;
	}

	public void setIdVara(Integer idVara) {
		this.idVara = idVara;
	}

	public String getDesVara() {
		return desVara;
	}

	public void setDesVara(String desVara) {
		this.desVara = desVara;
	}

	public Tribunal getTribunal() {
		return tribunal;
	}

	public void setTribunal(Tribunal tribunal) {
		this.tribunal = tribunal;
	}

	public void addProcessos(Processo processo) {
		processos.add(processo);
	}

	public void removeProcessos(Processo processo) {
		processos.remove(processo);
	}

	public List<Processo> getProcessos() {
		return processos;
	}



	public String conVara() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vara\n");
		sb.append("ID:");
		sb.append(getIdVara() + "\n");
		sb.append("Descrição:");
		sb.append(getDesVara() + "\n");
		sb.append("-------Lista de processos-------\n");
		for (Processo processo : processos) {
			sb.append(processo.conPro() + "\n");
		}
		return sb.toString();
	}
}