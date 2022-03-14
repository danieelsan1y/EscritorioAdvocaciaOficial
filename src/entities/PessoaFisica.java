package entities;

import dao.model.PessoaFisicaDao;

public class PessoaFisica extends Pessoa {
	private String cpfPes;
	private String rgPes;

	public PessoaFisica() {
		super();
	}
	public PessoaFisica(String nomePes, String endPes, String telPes, String cepPes, String baiPes, String cidPes,
			String ufPes, String emailPes, String cpfPes, String rgPes) {
		super(nomePes, endPes, telPes, cepPes, baiPes, cidPes, ufPes, emailPes);
		this.cpfPes = cpfPes;
		this.rgPes = rgPes;
	}
	



	public String getCpfPes() {
		return cpfPes;
	}
	public void setCpfPes(String cpfPes) {
		this.cpfPes = cpfPes;
	}
	public String getRgPes() {
		return rgPes;
	}
	public void setRgPes(String rgPes) {
		this.rgPes = rgPes;
	}
	@Override
	public String conPes() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome:");
		sb.append(getNomePes() + "\n");
		sb.append("CPF:");
		sb.append(getCpfPes() + "\n");
		sb.append("RG:");
		sb.append(getRgPes() + "\n");
		sb.append("Telefone:");
		sb.append(getTelPes() + "\n");
		sb.append("E-mail:");
		sb.append(getEmailPes() + "\n");
		sb.append("CEP:");
		sb.append(getCepPes() + "\n");
		sb.append("Endereço:");
		sb.append(getEndPes() + "\n");
		sb.append("Bairo:");
		sb.append(getBaiPes() + "\n");
		sb.append("Cidade:");
		sb.append(getCidPes() + "\n");
		sb.append("UF:");
		sb.append(getUfPes() + "\n");
		sb.append("-------Lista de processos-------\n");
		for (Processo processo : processosAutor) {
			sb.append(processo.conPro() + "\n");
		}

		return sb.toString();
	}

	@Override
	public String regPes() {
		return null;
	}

}