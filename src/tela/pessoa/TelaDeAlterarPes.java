package tela.pessoa;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.model.DaoFactory;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import entities.PessoaFisica;
import entities.PessoaJuridica;

public class TelaDeAlterarPes extends JFrame {
	// atributos

	PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
	PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();

	JPanel divisaoPrincipal = new JPanel();
	JPanel divisaoCentral = new JPanel();
	JPanel divisaoOeste = new JPanel();
	JPanel divisao1 = new JPanel();
	JPanel divisaoEspecificacoes = new JPanel();

	JLabel imageLogo = new JLabel(new ImageIcon("oficial.png"));

	JPanel botoes = new JPanel();
	JPanel caixas = new JPanel();
	JPanel textoEquipamentos = new JPanel();
	JPanel textoCadastro = new JPanel();
	JPanel texto = new JPanel();
	JButton alterar = new JButton("ALTERAR"), consultar = new JButton("CONSULTAR"), limpar = new JButton("LIMPAR");
	JTextField bNomePessoa, bEndereco, bCpfPessoa, bRgPessoa, bCnpjPes, bTelPessoa, bCepPessoa, bEmailPessoa,
			bBairroPessoa, bCidadePessoa, bUfPessoa, barraConsulta;

	public TelaDeAlterarPes() {
		super("TelaDeAlterar");
		montarTela();
		getContentPane().add(divisaoPrincipal);
		setSize(800, 700);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void montarTela() {
		definirLayouts();
		adicionarNasDivisoes();
		inserirBotoes();
		inserirCampos();
		acaoBotoes();

	}

	public void definirLayouts() {
		setLayout(new BorderLayout());
		divisao1.setLayout(new BorderLayout());
		divisaoCentral.setLayout(new BorderLayout());
		divisaoOeste.setLayout(new BorderLayout());
		divisaoEspecificacoes.setLayout(new BorderLayout());
		caixas.setLayout(new BoxLayout(caixas, BoxLayout.Y_AXIS));
	}

	public void adicionarNasDivisoes() {
		add("Center", divisaoPrincipal);
		divisaoPrincipal.add("North", imageLogo);
		divisaoPrincipal.add("Center", divisaoCentral);
		divisaoPrincipal.add("West", divisao1);
		divisaoOeste.add("South", botoes);
		divisao1.add("Center", divisaoOeste);
		divisaoOeste.add("North", caixas);
		divisaoOeste.add("Center", divisaoEspecificacoes);
		divisao1.add("North", textoCadastro);

	}

	public void inserirBotoes() {
		botoes.add(alterar);
		botoes.add(limpar);

	}

	public void inserirCampos() {

		textoCadastro.add(new JLabel("Alterar Pessoa"));
		texto.add(new JLabel("Consultar Pessoa"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o CPF ou CNPJ:   "));
		barraConsulta = new JTextField(14);
		consulta.add(barraConsulta);
		consulta.add(consultar);
		caixas.add(consulta);

		int tamanho = barraConsulta.getText().length();

		// Campo Nome
		JPanel nomePessoa = new JPanel();
		nomePessoa.setLayout(new BoxLayout(nomePessoa, BoxLayout.X_AXIS));
		nomePessoa.add(Box.createHorizontalStrut(8));
		nomePessoa.add(new JLabel("Nome:                     "));
		bNomePessoa = new JTextField(5);
		nomePessoa.add(bNomePessoa);
		nomePessoa.add(Box.createHorizontalStrut(5));
		caixas.add(nomePessoa);

		// Campo CPF
		JPanel cpfPessoa = new JPanel();
		cpfPessoa.setLayout(new BoxLayout(cpfPessoa, BoxLayout.X_AXIS));
		cpfPessoa.add(Box.createHorizontalStrut(8));
		cpfPessoa.add(new JLabel("CPF:                         "));
		bCpfPessoa = new JTextField(5);
		cpfPessoa.add(bCpfPessoa);
		cpfPessoa.add(Box.createHorizontalStrut(5));
		cpfPessoa.setMaximumSize(new Dimension(800, 20));
		caixas.add(cpfPessoa);

		// Campo RG
		JPanel rgPessoa = new JPanel();
		rgPessoa.setLayout(new BoxLayout(rgPessoa, BoxLayout.X_AXIS));
		rgPessoa.add(Box.createHorizontalStrut(8));
		rgPessoa.add(new JLabel("RG:                           "));
		bRgPessoa = new JTextField(5);
		bRgPessoa.setMaximumSize(new Dimension(800, 20));
		rgPessoa.add(bRgPessoa);
		rgPessoa.add(Box.createHorizontalStrut(5));
		caixas.add(rgPessoa);

		// Campo cnpj
		JPanel cnpjPessoa = new JPanel();
		cnpjPessoa.setLayout(new BoxLayout(cnpjPessoa, BoxLayout.X_AXIS));
		cnpjPessoa.add(Box.createHorizontalStrut(8));
		cnpjPessoa.add(new JLabel("CNPJ:                      "));
		bCnpjPes = new JTextField(5);
		bCnpjPes.setMaximumSize(new Dimension(800, 20));
		cnpjPessoa.add(bCnpjPes);
		cnpjPessoa.add(Box.createHorizontalStrut(5));
		caixas.add(cnpjPessoa);

		// campo Telefone
		JPanel telPessoa = new JPanel();
		telPessoa.setLayout(new BoxLayout(telPessoa, BoxLayout.X_AXIS));
		telPessoa.add(Box.createHorizontalStrut(8));
		telPessoa.add(new JLabel("Telefone:                "));
		bTelPessoa = new JTextField(5);
		bTelPessoa.setMaximumSize(new Dimension(800, 20));
		telPessoa.add(bTelPessoa);
		telPessoa.add(Box.createHorizontalStrut(5));
		caixas.add(telPessoa);

		// campo e-mail
		JPanel emailPessoa = new JPanel();
		emailPessoa.setLayout(new BoxLayout(emailPessoa, BoxLayout.X_AXIS));
		emailPessoa.add(Box.createHorizontalStrut(8));
		emailPessoa.add(new JLabel("E-mail:                     "));
		bEmailPessoa = new JTextField(5);
		bEmailPessoa.setMaximumSize(new Dimension(800, 20));
		emailPessoa.add(bEmailPessoa);
		emailPessoa.add(Box.createHorizontalStrut(5));
		caixas.add(emailPessoa);

		// Campo endereço
		JPanel endereco = new JPanel();
		endereco.setLayout(new BoxLayout(endereco, BoxLayout.X_AXIS));
		endereco.add(Box.createHorizontalStrut(8));
		endereco.add(new JLabel("Endereço:              "));
		bEndereco = new JTextField(5);
		bEndereco.setMaximumSize(new Dimension(800, 20));
		endereco.add(bEndereco);
		endereco.add(Box.createHorizontalStrut(5));
		caixas.add(endereco);

		// campo cep
		JPanel bairroPessoa = new JPanel();
		bairroPessoa.setLayout(new BoxLayout(bairroPessoa, BoxLayout.X_AXIS));
		bairroPessoa.add(Box.createHorizontalStrut(8));
		bairroPessoa.add(new JLabel("Bairro:                     "));
		bBairroPessoa = new JTextField(5);
		bBairroPessoa.setMaximumSize(new Dimension(800, 20));
		bairroPessoa.add(bBairroPessoa);
		bairroPessoa.add(Box.createHorizontalStrut(5));
		caixas.add(bairroPessoa);

		// campo cep
		JPanel cepPessoa = new JPanel();
		cepPessoa.setLayout(new BoxLayout(cepPessoa, BoxLayout.X_AXIS));
		cepPessoa.add(Box.createHorizontalStrut(8));
		cepPessoa.add(new JLabel("CEP:                        "));
		bCepPessoa = new JTextField(5);
		bCepPessoa.setMaximumSize(new Dimension(800, 20));
		cepPessoa.add(bCepPessoa);
		cepPessoa.add(Box.createHorizontalStrut(5));
		caixas.add(cepPessoa);

		// campo cidade
		JPanel cidadePessoa = new JPanel();
		cidadePessoa.setLayout(new BoxLayout(cidadePessoa, BoxLayout.X_AXIS));
		cidadePessoa.add(Box.createHorizontalStrut(8));
		cidadePessoa.add(new JLabel("Cidade:                   "));
		bCidadePessoa = new JTextField(5);
		bCidadePessoa.setMaximumSize(new Dimension(800, 20));
		cidadePessoa.add(bCidadePessoa);
		cidadePessoa.add(Box.createHorizontalStrut(5));
		caixas.add(cidadePessoa);

		// campo uf
		JPanel ufPessoa = new JPanel();
		ufPessoa.setLayout(new BoxLayout(ufPessoa, BoxLayout.X_AXIS));
		ufPessoa.add(Box.createHorizontalStrut(8));
		ufPessoa.add(new JLabel("UF:                           "));
		bUfPessoa = new JTextField(5);
		bUfPessoa.setMaximumSize(new Dimension(800, 20));
		ufPessoa.add(bUfPessoa);
		ufPessoa.add(Box.createHorizontalStrut(5));
		caixas.add(ufPessoa);

		caixas.add(caixas.add(Box.createGlue()));

	}

	public void acaoBotoes() {
		ActionListenerTelaPrincipal albotoes = new ActionListenerTelaPrincipal();
		alterar.addActionListener(albotoes);
		consultar.addActionListener(albotoes);
		limpar.addActionListener(albotoes);

	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PessoaFisica pes;
			PessoaJuridica pesJu;
			if (e.getSource() == consultar) {
				int tamanho = barraConsulta.getText().length();
				if(tamanho == 11) {
					 pes = pessoaFisicaDao.buscar(barraConsulta.getText());
					bNomePessoa.setText(pes.getNomePes());
					bEndereco.setText(pes.getEndPes());
					bCpfPessoa.setText(pes.getCpfPes());
					bRgPessoa.setText(pes.getCpfPes());
					bTelPessoa.setText(pes.getTelPes());
					bCepPessoa.setText(pes.getCepPes());
					bEmailPessoa.setText(pes.getEmailPes());
					bBairroPessoa.setText(pes.getBaiPes());
					bCidadePessoa.setText(pes.getCidPes());
					bUfPessoa.setText(pes.getUfPes());
					
				}
				if(tamanho == 14) {
					pesJu = pessoaJuridicaDao.buscar(barraConsulta.getText());
					bNomePessoa.setText(pesJu.getNomePes());
					bEndereco.setText(pesJu.getEndPes());
					bCpfPessoa.setText(pesJu.getCpnjPes());
					bTelPessoa.setText(pesJu.getTelPes());
					bCepPessoa.setText(pesJu.getCepPes());
					bEmailPessoa.setText(pesJu.getEmailPes());
					bBairroPessoa.setText(pesJu.getBaiPes());
					bCidadePessoa.setText(pesJu.getCidPes());
					bUfPessoa.setText(pesJu.getUfPes());
				}
			
			}

			if (e.getSource() == limpar) {
				bNomePessoa.setText("");
				barraConsulta.setText("");
				bCpfPessoa.setText("");
				bRgPessoa.setText("");
				bCnpjPes.setText("");
				bTelPessoa.setText("");
				bEmailPessoa.setText("");
				bEndereco.setText("");
				bBairroPessoa.setText("");
				bCepPessoa.setText("");
				bCidadePessoa.setText("");
				bUfPessoa.setText("");
			}
			if(e.getSource() == alterar) {
				int tamanho =0;
				tamanho = barraConsulta.getText().length();
				
				if(tamanho == 11) {
					pes = pessoaFisicaDao.buscar(barraConsulta.getText());
					pes.setNomePes(bNomePessoa.getText());
					pes.setEndPes(bEndereco.getText());
					pes.setCpfPes(bCpfPessoa.getText());
					pes.setRgPes(bRgPessoa.getText());
					pes.setCepPes(bCepPessoa.getText());
					pes.setEmailPes(bEmailPessoa.getText());
					pes.setBaiPes(bBairroPessoa.getText());
					pes.setCidPes(bCidadePessoa.getText());
					pes.setUfPes(bUfPessoa.getText());
					pes.setEmailPes(bEmailPessoa.getText());
					pes.setNomePes(bNomePessoa.getText());
					pessoaFisicaDao.atualizar(pes);
					
				}
				if(tamanho == 14) {
					pesJu = pessoaJuridicaDao.buscar(barraConsulta.getText());
					pesJu.setNomePes(bNomePessoa.getText());
					pesJu.setEndPes(bEndereco.getText());
					pesJu.setCpnjPes(bCnpjPes.getText());
					pesJu.setCepPes(bCepPessoa.getText());
					pesJu.setEmailPes(bEmailPessoa.getText());
					pesJu.setBaiPes(bBairroPessoa.getText());
					pesJu.setCidPes(bCidadePessoa.getText());
					pesJu.setUfPes(bUfPessoa.getText());
					pesJu.setEmailPes(bEmailPessoa.getText());
					pesJu.setNomePes(bNomePessoa.getText());
					pessoaJuridicaDao.atualizar(pesJu);
				}
			}
		}

	}

}
