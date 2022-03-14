package entities;

import java.util.ArrayList;
import java.util.List;

public class Tribunal {
	private Integer idTribunal;
	private String desTrib;
	private String endTrib;
	List<Vara> varas = new ArrayList<Vara>();

	public Tribunal() {
	}

	public Tribunal(String desTrib, String endTrib) {
		this.desTrib = desTrib;
		this.endTrib = endTrib;
	}

	public Integer getIdTribunal() {
		return idTribunal;
	}

	public void setIdTribunal(Integer idTribunal) {
		this.idTribunal = idTribunal;
	}

	public String getDesTrib() {
		return desTrib;
	}

	public void setDesTrib(String desTrib) {
		this.desTrib = desTrib;
	}

	public String getEndTrib() {
		return endTrib;
	}

	public void setEndTrib(String endTrib) {
		this.endTrib = endTrib;
	}

	public void addVara(Vara vara) {
		varas.add(vara);
	}

	public void removerVara(Vara vara) {
		varas.add(vara);
	}
	
	public List<Vara> getVaras() {
		return varas;
	}


	public String conTrib() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tribunal\n");
		sb.append("Nome:");
		sb.append(getDesTrib() + "\n");
		sb.append("Endereço:");
		sb.append(getEndTrib() + "\n");
		sb.append("-------Lista de varas-------\n");
		for (Vara vara : varas) {
			sb.append(vara.conVara() + "\n");
		}
		return sb.toString();
	}

}
