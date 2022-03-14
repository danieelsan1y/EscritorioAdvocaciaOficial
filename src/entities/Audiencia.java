package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Audiencia {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Integer idAudi;
	private String nroAudi;
	private Date dataAudi;
	private String parecer;
	Processo processo;
	
	public Audiencia () {
		
	}
	
	public Audiencia(Date dataAudi, String parecer, Processo processo, String nroAudi) {
		
		this.parecer = parecer;
		this.processo = processo;
		this.idAudi =idAudi;
		this.nroAudi = nroAudi;
		this.dataAudi = dataAudi;
	}

	public Integer getIdAudi() {
		return idAudi;
	}

	public void setIdAudi(Integer idAudi) {
		this.idAudi = idAudi;
	}
	

	public String getNroAudi() {
		return nroAudi;
	}

	public void setNroAudi(String nroAudi) {
		this.nroAudi = nroAudi;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}



	public Date getDataAudi() {
		return dataAudi;
	}

	public void setDataAudi(Date dataAudi) {
		this.dataAudi = dataAudi;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Processo getProcesso() {
		return processo;
	}

	public String conAud() {
		StringBuilder sb = new StringBuilder();
		sb.append("Audiência\n");
		sb.append("Data:");
		sb.append(sdf.format(getDataAudi()) + "\n");
		sb.append("Parecer:");
		sb.append(getParecer() + "\n");
		return sb.toString();
	}

}
