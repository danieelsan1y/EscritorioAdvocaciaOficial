package tela.processo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import dao.model.ProcessoDao;
import dao.model.TribunalDao;
import dao.model.VaraDao;
import entities.Pessoa;
import entities.Processo;
import entities.Tribunal;
import entities.Vara;
import entities.enums.SituacaoStatus;

class TelaDeCadastroPro extends JFrame {
	// atributos

	PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
	PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
	VaraDao varaDao = DaoFactory.criarVara();
	ProcessoDao processoDao = DaoFactory.criarProcesso();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

	JButton inserir = new JButton("INSERIR"), consultar = new JButton("CONSULTAR"), alterar = new JButton("CONSULTAR E ALTERAR"),
			excluir = new JButton("EXCLUIR"), limpar = new JButton("LIMPAR"),
			cadastrados = new JButton("AUDIENCIAS E CUSTOS");

	JTextField bNroProcesso, bDataAberturaPro, BExclusao, bSituacao, bCpfAutor, bCpfReu, bDesVara;

	public TelaDeCadastroPro() {
		super("Processo");
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
		botoes.add(alterar);
		botoes.add(limpar);
		botoes.add(cadastrados);

	}

	public void inserirCampos() {

		textoCadastro.add(new JLabel("Cadastrar Processo"));
		JPanel nroProcesso = new JPanel();
		nroProcesso.setLayout(new BoxLayout(nroProcesso, BoxLayout.X_AXIS));
		nroProcesso.add(Box.createHorizontalStrut(8));
		nroProcesso.add(new JLabel("Numero do Processo:    "));
		bNroProcesso = new JTextField(5);
		bNroProcesso.setMaximumSize(new Dimension(800, 20));
		nroProcesso.add(bNroProcesso);
		nroProcesso.add(Box.createHorizontalStrut(5));
		caixas.add(nroProcesso);

		JPanel dataAberturaPro = new JPanel();
		dataAberturaPro.setLayout(new BoxLayout(dataAberturaPro, BoxLayout.X_AXIS));
		dataAberturaPro.add(Box.createHorizontalStrut(8));
		dataAberturaPro.add(new JLabel("Data de abertura:            "));
		bDataAberturaPro = new JTextField(5);
		bDataAberturaPro.setMaximumSize(new Dimension(800, 20));
		dataAberturaPro.add(bDataAberturaPro);
		dataAberturaPro.add(Box.createHorizontalStrut(5));
		caixas.add(dataAberturaPro);

		JPanel situacaoPro = new JPanel();
		situacaoPro.setLayout(new BoxLayout(situacaoPro, BoxLayout.X_AXIS));
		situacaoPro.add(Box.createHorizontalStrut(8));
		situacaoPro.add(new JLabel("Situação:                           "));
		bSituacao = new JTextField(5);
		bSituacao.setMaximumSize(new Dimension(800, 20));
		situacaoPro.add(bSituacao);
		situacaoPro.add(Box.createHorizontalStrut(5));
		caixas.add(situacaoPro);

		JPanel cpfAutor = new JPanel();
		cpfAutor.setLayout(new BoxLayout(cpfAutor, BoxLayout.X_AXIS));
		situacaoPro.add(Box.createHorizontalStrut(8));
		cpfAutor.add(new JLabel("CPF/CNPJ Autor:                 "));
		bCpfAutor = new JTextField(5);
		bCpfAutor.setMaximumSize(new Dimension(800, 20));
		cpfAutor.add(bCpfAutor);
		cpfAutor.add(Box.createHorizontalStrut(5));
		caixas.add(cpfAutor);

		JPanel cpfReu = new JPanel();
		cpfReu.setLayout(new BoxLayout(cpfReu, BoxLayout.X_AXIS));
		cpfReu.add(Box.createHorizontalStrut(8));
		cpfReu.add(new JLabel("CPF/CNPJ Réu:                 "));
		bCpfReu = new JTextField(5);
		bCpfReu.setMaximumSize(new Dimension(800, 20));
		cpfReu.add(bCpfReu);
		cpfReu.add(Box.createHorizontalStrut(5));
		caixas.add(cpfReu);

		JPanel desVara = new JPanel();
		desVara.setLayout(new BoxLayout(desVara, BoxLayout.X_AXIS));
		desVara.add(Box.createHorizontalStrut(8));
		desVara.add(new JLabel("Nome da Vara:                 "));
		bDesVara = new JTextField(5);
		bDesVara.setMaximumSize(new Dimension(800, 20));
		desVara.add(bDesVara);
		desVara.add(Box.createHorizontalStrut(5));
		caixas.add(desVara);

		caixas.add(inserir);
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("Excluir Processo"));

		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o numero do processo:  "));
		BExclusao = new JTextField(14);
		consulta.add(BExclusao);
		consulta.add(excluir);
		divisaoEspecificacoes.add("West", consulta);
		caixas.add(caixas.add(Box.createGlue()));

	}

	public void acaoBotoes() {
		ActionListenerTelaPrincipal albotoes = new ActionListenerTelaPrincipal();
		inserir.addActionListener(albotoes);
		consultar.addActionListener(albotoes);
		cadastrados.addActionListener(albotoes);
		alterar.addActionListener(albotoes);
		excluir.addActionListener(albotoes);
		limpar.addActionListener(albotoes);
	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Pessoa pesAutor = null, pesReu = null;
			if (e.getSource() == inserir) {
				Vara vara = varaDao.buscar(bDesVara.getText());
				int tamAutor = bCpfAutor.getText().length();
				int tamReu = bCpfReu.getText().length();

				if (!(bNroProcesso.getText().isEmpty() && bCpfAutor.getText().isEmpty() && bCpfReu.getText().isEmpty()
						&& bDesVara.getText().isEmpty())) {

					if (tamAutor == 14) {
						pesAutor = pessoaJuridicaDao.buscar(bCpfAutor.getText());

					}
					if (tamAutor == 11) {
						pesAutor = pessoaFisicaDao.buscar(bCpfAutor.getText());
						System.out.println("passei 1");
					}
					if (tamReu == 14) {
						pesReu = pessoaJuridicaDao.buscar(bCpfReu.getText());
						System.out.println("passei 2");

					}
					if (tamReu == 11) {
						pesReu = pessoaFisicaDao.buscar(bCpfAutor.getText());

					}
					try {
						Processo pro = new Processo(bNroProcesso.getText(), sdf.parse(bDataAberturaPro.getText()),
								SituacaoStatus.valueOf(bSituacao.getText()), pesAutor, pesReu, vara);
						processoDao.inserir(pro);
					} catch (ParseException e1) {
						e1.printStackTrace();
					} finally {
						JOptionPane.showMessageDialog(null, "Processo excluido com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

			}

			if (e.getSource() == excluir) {
				Processo pro = processoDao.buscar(BExclusao.getText());
				processoDao.deletar(pro);
				JOptionPane.showMessageDialog(null, "Processo excluido com sucesso!", "",
						JOptionPane.INFORMATION_MESSAGE);

			}
			if (e.getSource() == limpar)
			{
				bCpfAutor.setText("");
				bCpfReu.setText("");
				bDataAberturaPro.setText("");
				bDesVara.setText("");
				bNroProcesso.setText("");
				bSituacao.setText("");
				excluir.setText(getName());
			}
			if (e.getSource() == cadastrados) {

			}
			if (e.getSource() == alterar) {

			}

		}
	}
}
