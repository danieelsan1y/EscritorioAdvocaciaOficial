package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa {
	private Integer idPes;
	private String nomePes;
	private String endPes;
	private String telPes;
	private String cepPes;
	private String baiPes;
	private String cidPes;
	private String ufPes;
	private String emailPes;
	protected List<Processo> processosAutor = new ArrayList<>();

	
	public Pessoa() {
	}
	
	public Pessoa(String nomePes, String endPes, String telPes, String cepPes, String baiPes,
			String cidPes, String ufPes, String emailPes) {
		this.nomePes = nomePes;
		this.endPes = endPes;
		this.telPes = telPes;
		this.cepPes = cepPes;
		this.baiPes = baiPes;
		this.cidPes = cidPes;
		this.ufPes = ufPes;
		this.emailPes = emailPes;
	}
	

	

	public Integer getIdPes() {
		return idPes;
	}

	public void setIdPes(Integer idPes) {
		this.idPes = idPes;
	}

	public String getNomePes() {
		return nomePes;
	}

	public void setNomePes(String nomePes) {
		this.nomePes = nomePes;
	}

	public String getEndPes() {
		return endPes;
	}

	public void setEndPes(String endPes) {
		this.endPes = endPes;
	}

	public String getTelPes() {
		return telPes;
	}

	public void setTelPes(String telPes) {
		this.telPes = telPes;
	}

	public String getCepPes() {
		return cepPes;
	}

	public void setCepPes(String cepPes) {
		this.cepPes = cepPes;
	}

	public String getBaiPes() {
		return baiPes;
	}

	public void setBaiPes(String baiPes) {
		this.baiPes = baiPes;
	}

	public String getCidPes() {
		return cidPes;
	}

	public void setCidPes(String cidPes) {
		this.cidPes = cidPes;
	}

	public String getUfPes() {
		return ufPes;
	}

	public void setUfPes(String ufPes) {
		this.ufPes = ufPes;
	}

	public String getEmailPes() {
		return emailPes;
	}

	public void setEmailPes(String emailPes) {
		this.emailPes = emailPes;
	}

	public void addProcessosAutor(Processo processo) {
		processosAutor.add(processo);
	}

	public void removeProcessosAutor(Processo processo) {
		processosAutor.remove(processo);
	}

	public abstract String conPes();

	public abstract String regPes();

}
