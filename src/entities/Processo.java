package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.SituacaoStatus;

public class Processo {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer idProcesso;
	private String nroProcesso;
	private Date dataAbertura;
	private Date dataConclusao;
	private SituacaoStatus situcacao;
	private Pessoa pessoaAutor;
	private Pessoa pessoaReu;
	private Vara vara;
	private List<Custo> custos = new ArrayList<>();
	private List<Audiencia> audiencias = new ArrayList<>();

	public Processo() {

	}

	public Processo(String nroProcesso, Date dataAbertura, SituacaoStatus situcacao,
			Pessoa pessoaAutor, Pessoa pessoaReu, Vara vara) {
		super();
		this.nroProcesso = nroProcesso;
		this.dataAbertura = dataAbertura;
		this.situcacao = situcacao;
		this.pessoaAutor = pessoaAutor;
		this.pessoaReu = pessoaReu;
		this.vara = vara;
	}

	
	
	
	public Integer getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getNroProcesso() {
		return nroProcesso;
	}

	public void setNroProcesso(String nroProcesso) {
		this.nroProcesso = nroProcesso;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public SituacaoStatus getSitucacao() {
		return situcacao;
	}

	public void setSitucacao(SituacaoStatus situcacao) {
		this.situcacao = situcacao;
	}

	public Pessoa getPessoaAutor() {
		return pessoaAutor;
	}

	public void setPessoaAutor(Pessoa pessoaAutor) {
		this.pessoaAutor = pessoaAutor;
	}

	public Pessoa getPessoaReu() {
		return pessoaReu;
	}

	public void setPessoaReu(Pessoa pessoaReu) {
		this.pessoaReu = pessoaReu;
	}

	public Vara getVara() {
		return vara;
	}

	public void setVara(Vara vara) {
		this.vara = vara;
	}

	public void addCusto(Custo custo) {
		custos.add(custo);

	}

	public void removeCusto(Custo custo) {
		custos.remove(custo);

	}
	

	public List<Custo> getCustos() {
		return custos;
	}


	public List<Audiencia> getAudiencias() {
		return audiencias;
	}

	public Double totalCusto() {
		double soma = 0.0;
		for (Custo custo : custos) {
			soma += custo.getValCusto();
		}
		return soma;

	}

	public void addAudiencia(Audiencia audiencia) {
		audiencias.add(audiencia);
	}

	public void removeAudiencia(Audiencia audiencia) {
		audiencias.add(audiencia);

	}

	public String conPro() {
		StringBuilder sb = new StringBuilder();
		sb.append("Processo\n");
		sb.append("Número:");
		sb.append(getNroProcesso() + "\n");
		sb.append("Data de abertura:");
		sb.append(sdf.format(getDataAbertura()) + "\n");
		if(dataConclusao != null ) {
			sb.append("Data de conclusao:");
			sb.append(sdf.format(getDataConclusao()) + "\n");
		}

		sb.append("Situação:");
		sb.append(getSitucacao() + "\n");
		sb.append("-------Lista de custos-------\n");
		for (Custo custo : custos) {
			sb.append(custo.conCusto() + "\n");
		}
		sb.append("Total dos Custos:");
		sb.append(totalCusto() + "\n");
		sb.append("-------Lista de Audiências-------\n");
		for (Audiencia audiencia : audiencias) {
			sb.append(audiencia.conAud() + "\n");
		}
		return sb.toString();
	}

}