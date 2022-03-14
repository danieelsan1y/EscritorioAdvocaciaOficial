package tela.pessoa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.model.DaoFactory;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import entities.Pessoa;
import entities.PessoaFisica;
import entities.PessoaJuridica;

public class TeladeConsultaPes extends JFrame {
	PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
	PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();

	JPanel divisaoPrincipal = new JPanel();
	JPanel divisaoCentro = new JPanel();
	JPanel sairTela = new JPanel();
	JPanel texto = new JPanel();
	JPanel atributos = new JPanel();
	JPanel atributosConsultados = new JPanel();
	JTextField barraConsulta;
	JButton consultar = new JButton("CONSULTAR"), sair = new JButton("SAIR");

	public TeladeConsultaPes() {
		super("Tela de Consulta");
		getContentPane().add(divisaoCentro);
		montarTela();
		setSize(500, 500);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void definirLayouts() {
		setLayout(new BorderLayout());
		divisaoCentro.setLayout(new BorderLayout());
		divisaoPrincipal.setLayout(new BorderLayout());
		atributos.setLayout(new BoxLayout(atributos, BoxLayout.Y_AXIS));
		atributosConsultados.setLayout(new BoxLayout(atributosConsultados, BoxLayout.Y_AXIS));
	}

	public void montarTela() {
		definirLayouts();
		adicionarNasDivisoes();
		consultarPessoa();
		acaoBotoes();

	}

	public void adicionarNasDivisoes() {
		add("Center", divisaoCentro);
		add("North", texto);
		divisaoCentro.add("North", divisaoPrincipal);
		divisaoPrincipal.add("North", atributos);
		divisaoPrincipal.add("Center", atributosConsultados);
		divisaoPrincipal.add("South", sairTela);

	}

	public void consultarPessoa() {
		// consulta da pessoa
		int tamanho = 0;

		texto.add(new JLabel("Consultar Pessoa"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o CPF ou CNPJ:   "));
		barraConsulta = new JTextField(14);
		consulta.add(barraConsulta);
		consulta.add(consultar);
		atributos.add(consulta);

	}

	public void ResultadoPF(PessoaFisica obj) {
		// Resultado nome
		JPanel nomePessoa = new JPanel();
		nomePessoa.add(new JLabel("Nome: "));
		nomePessoa.add(new JLabel(obj.getNomePes()));
		atributosConsultados.add(nomePessoa);

		// Resultado cpf
		JPanel cpfPessoa = new JPanel();
		cpfPessoa.add(new JLabel("CPF: "));
		cpfPessoa.add(new JLabel(obj.getCpfPes()));
		atributosConsultados.add(cpfPessoa);

		// Resultado rg
		JPanel rgPessoa = new JPanel();
		rgPessoa.add(new JLabel("RG: "));
		rgPessoa.add(new JLabel(obj.getRgPes()));
		atributosConsultados.add(rgPessoa);

		// Resultado telefone
		JPanel telefonePessoa = new JPanel();
		telefonePessoa.add(new JLabel("Telefone: "));
		telefonePessoa.add(new JLabel(obj.getTelPes()));
		atributosConsultados.add(telefonePessoa);

		// Resultado email
		JPanel emailPessoa = new JPanel();
		emailPessoa.add(new JLabel("E-Mail: "));
		emailPessoa.add(new JLabel(obj.getEmailPes()));
		atributosConsultados.add(emailPessoa);

		// Resultado endereco
		JPanel enderecoPessoa = new JPanel();
		enderecoPessoa.add(new JLabel("Endereço: "));
		enderecoPessoa.add(new JLabel(obj.getEndPes()));
		atributosConsultados.add(enderecoPessoa);

		// Resultado bairro
		JPanel bairroPessoa = new JPanel();
		bairroPessoa.add(new JLabel("Bairro: "));
		bairroPessoa.add(new JLabel(obj.getBaiPes()));
		atributosConsultados.add(bairroPessoa);

		// Resultado cep
		JPanel cepPessoa = new JPanel();
		cepPessoa.add(new JLabel("CEP: "));
		cepPessoa.add(new JLabel(obj.getCepPes()));
		atributosConsultados.add(cepPessoa);

		// Resultado cidade
		JPanel cidadePes = new JPanel();
		cidadePes.add(new JLabel("Cidade: "));
		cidadePes.add(new JLabel(obj.getCidPes()));
		atributosConsultados.add(cidadePes);

		// Resultado uf
		JPanel ufPessoa = new JPanel();
		ufPessoa.add(new JLabel("UF: "));
		ufPessoa.add(new JLabel(obj.getUfPes()));
		atributosConsultados.add(ufPessoa);
		sairTela.add(sair);

	}

	public void ResultadoPJ (PessoaJuridica obj) {
		// Resultado nome
		JPanel nomePessoa = new JPanel();
		nomePessoa.add(new JLabel("Nome: "));
		nomePessoa.add(new JLabel(obj.getNomePes()));
		atributosConsultados.add(nomePessoa);

		// Resultado cpf
		JPanel cnpjPessoa = new JPanel();
		cnpjPessoa.add(new JLabel("CNPJ: "));
		cnpjPessoa.add(new JLabel(obj.getCpnjPes()));
		atributosConsultados.add(cnpjPessoa);


		// Resultado telefone
		JPanel telefonePessoa = new JPanel();
		telefonePessoa.add(new JLabel("Telefone: "));
		telefonePessoa.add(new JLabel(obj.getTelPes()));
		atributosConsultados.add(telefonePessoa);

		// Resultado email
		JPanel emailPessoa = new JPanel();
		emailPessoa.add(new JLabel("E-Mail: "));
		emailPessoa.add(new JLabel(obj.getEmailPes()));
		atributosConsultados.add(emailPessoa);

		// Resultado endereco
		JPanel enderecoPessoa = new JPanel();
		enderecoPessoa.add(new JLabel("Endereço: "));
		enderecoPessoa.add(new JLabel(obj.getEndPes()));
		atributosConsultados.add(enderecoPessoa);

		// Resultado bairro
		JPanel bairroPessoa = new JPanel();
		bairroPessoa.add(new JLabel("Bairro: "));
		bairroPessoa.add(new JLabel(obj.getBaiPes()));
		atributosConsultados.add(bairroPessoa);

		// Resultado cep
		JPanel cepPessoa = new JPanel();
		cepPessoa.add(new JLabel("CEP: "));
		cepPessoa.add(new JLabel(obj.getCepPes()));
		atributosConsultados.add(cepPessoa);

		// Resultado cidade
		JPanel cidadePes = new JPanel();
		cidadePes.add(new JLabel("Cidade: "));
		cidadePes.add(new JLabel(obj.getCidPes()));
		atributosConsultados.add(cidadePes);

		// Resultado uf
		JPanel ufPessoa = new JPanel();
		ufPessoa.add(new JLabel("UF: "));
		ufPessoa.add(new JLabel(obj.getUfPes()));
		atributosConsultados.add(ufPessoa);
		sairTela.add(sair);
		
	}
	
	public void acaoBotoes() {
		ActionListenerTelaPrincipal albotoes = new ActionListenerTelaPrincipal();
		consultar.addActionListener(albotoes);
		sair.addActionListener(albotoes);
	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == consultar) {
				int tamanho = 0;

				tamanho = barraConsulta.getText().length();
				if (tamanho == 11) {
					PessoaFisica pes = pessoaFisicaDao.buscar(barraConsulta.getText());
					ResultadoPF(pes);
					JOptionPane.showMessageDialog(null, "Pessoa consultada com sucesso!", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
				if (tamanho == 14) {
					PessoaJuridica pes = pessoaJuridicaDao.buscar(barraConsulta.getText());
					ResultadoPJ(pes);
					JOptionPane.showMessageDialog(null, "Pessoa consultada com sucesso!", "",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
			if(e.getSource() == sair) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
		}
	}
}