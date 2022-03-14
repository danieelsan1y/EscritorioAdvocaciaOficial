package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Custo {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Integer idCusto;
	private String nroCusto;
	private Date dataCusto;
	private String desCusto;
	private Double valCusto;
	private Processo processo;
	

	public Custo() {

	}

	public Custo(Date dataCusto, String desCusto, Double valCusto, Processo processo, String nroCusto) {
		this.dataCusto = dataCusto;
		this.desCusto = desCusto;
		this.valCusto = valCusto;
		this.processo = processo;
		this.nroCusto = nroCusto;
	}

	
	
	public Integer getIdCusto() {
		return idCusto;
	}

	public void setIdCusto(Integer idCusto) {
		this.idCusto = idCusto;
	}
	

	public String getNroCusto() {
		return nroCusto;
	}

	public void setNroCusto(String nroCusto) {
		this.nroCusto = nroCusto;
	}

	public Date getDataCusto() {
		return dataCusto;
	}

	public void setDataCusto(Date dataCusto) {
		this.dataCusto = dataCusto;
	}

	public String getDesCusto() {
		return desCusto;
	}

	public void setDesCusto(String desCusto) {
		this.desCusto = desCusto;
	}

	public Double getValCusto() {
		return valCusto;
	}

	public void setValCusto(Double valCusto) {
		this.valCusto = valCusto;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public String conCusto() {
		StringBuilder sb = new StringBuilder();
		sb.append("Custo\n");
		sb.append("ID:");
		sb.append(getIdCusto() + "\n");
		sb.append("Descrição:");
		sb.append(getDesCusto() + "\n");
		sb.append("Data do Custo:");
		sb.append(sdf.format(getDataCusto()) + "\n");
		sb.append("Preço:");
		sb.append(getValCusto() + "\n");
		return sb.toString();
	}

}
