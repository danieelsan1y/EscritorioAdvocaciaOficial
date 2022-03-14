package entities;

import java.util.List;

public class PessoaJuridica extends Pessoa {
	private String cpnjPes;

	public PessoaJuridica () {
		super();
	}



	public PessoaJuridica(String nomePes, String endPes, String telPes, String cepPes, String baiPes, String cidPes,
			String ufPes, String emailPes, String cpnjPes) {
		super(nomePes, endPes, telPes, cepPes, baiPes, cidPes, ufPes, emailPes);
		this.cpnjPes = cpnjPes;
	}




	public String getCpnjPes() {
		return cpnjPes;
	}



	public void setCpnjPes(String cpnjPes) {
		this.cpnjPes = cpnjPes;
	}



	@Override
	public String conPes() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome:");
		sb.append(getNomePes() + "\n");
		sb.append("CNPJ:");
		sb.append(getCpnjPes() + "\n");
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
		// TODO Auto-generated method stub
		return null;
	}

}
