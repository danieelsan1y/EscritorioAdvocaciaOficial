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

public class TeladeCadastroPes extends JFrame {
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

	JButton inserir = new JButton("INSERIR"), consultar = new JButton("CONSULTAR"), alterar = new JButton("ALTERAR"),
			excluir = new JButton("EXCLUIR"), limpar = new JButton("LIMPAR"),
			cadastrados = new JButton("LISTA DE PESSOAS");

	JTextField bNomePessoa, bEndereco, bCpfPessoa, bRgPessoa, bCnpjPes, bTelPessoa, bCepPessoa, bEmailPessoa,
			bBairroPessoa, bCidadePessoa, bUfPessoa;

	public TeladeCadastroPes() {
		super("SISTEMA DE GERENCIAMENTO DE ESCRITÓRIO DE ADVOCACIA");
		montarTela();
		getContentPane().add(divisaoPrincipal);
		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		botoes.add(inserir);
		botoes.add(consultar);
		botoes.add(alterar);
		botoes.add(excluir);
		botoes.add(limpar);
		botoes.add(cadastrados);

	}

	public void inserirCampos() {
		textoCadastro.add(new JLabel("Cadastrar Pessoa"));
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
		inserir.addActionListener(albotoes);
		consultar.addActionListener(albotoes);
		excluir.addActionListener(albotoes);
		limpar.addActionListener(albotoes);
		cadastrados.addActionListener(albotoes);
		alterar.addActionListener(albotoes);
	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == inserir) {
				if (bCpfPessoa.getText().equals("")) {

					PessoaJuridica pes = new PessoaJuridica(bNomePessoa.getText(), bEndereco.getText(),
							bTelPessoa.getText(), bCepPessoa.getText(), bBairroPessoa.getText(),
							bCidadePessoa.getText(), bUfPessoa.getText(), bEmailPessoa.getText(), bCnpjPes.getText());
					pessoaJuridicaDao.inserir(pes);
					JOptionPane.showMessageDialog(null, "Pessoa inserida com sucesso!", "",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					PessoaFisica pes = new PessoaFisica(bNomePessoa.getText(), bEndereco.getText(),
							bTelPessoa.getText(), bCepPessoa.getText(), bBairroPessoa.getText(),
							bCidadePessoa.getText(), bUfPessoa.getText(), bEmailPessoa.getText(), bCpfPessoa.getText(),
							bRgPessoa.getText());
					pessoaFisicaDao.inserir(pes);

					JOptionPane.showMessageDialog(null, "Pessoa inserida com sucesso!", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (e.getSource() == consultar) {
				TeladeConsultaPes tela = new TeladeConsultaPes();
			}
			if (e.getSource() == excluir) {
				TelaDeExcluirPes tela = new TelaDeExcluirPes();
			}
			if (e.getSource() == limpar) {
				bNomePessoa.setText("");
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
			if (e.getSource() == cadastrados) {
				TeladeListarPes tela = new TeladeListarPes();
			}
			if (e.getSource() == alterar) {
				TelaDeAlterarPes tela = new TelaDeAlterarPes();
			}
		}

	}

}
