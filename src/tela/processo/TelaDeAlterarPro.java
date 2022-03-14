package tela.processo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import dao.model.VaraDao;
import entities.PessoaFisica;
import entities.PessoaJuridica;
import entities.Processo;


public class TelaDeAlterarPro extends JFrame {
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

	JButton inserir = new JButton("INSERIR"), consultar = new JButton("CONSULTAR"), alterar = new JButton("ALTERAR"),
			excluir = new JButton("EXCLUIR"), limpar = new JButton("LIMPAR"),
			cadastrados = new JButton("VARAS E PROCESSOS");

	JTextField bNroProcesso, bDataAberturaPro, BExclusao, barraConsulta, bSituacao, bCpfAutor, bCpfReu, bDesVara,
			bDataConclusaoPro;

	public TelaDeAlterarPro() {
		super("Cadastro Tribunal");
		montarTela();
		getContentPane().add(divisaoPrincipal);
		setSize(600, 500);
		setVisible(true);

	}

	public void montarTela() {
		definirLayouts();
		adicionarNasDivisoes();
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

	public void inserirCampos() {

		textoCadastro.add(new JLabel("Consultar Processo"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o numero do processo:   "));
		barraConsulta = new JTextField(14);
		consulta.add(barraConsulta);
		consulta.add(consultar);
		caixas.add(consulta);
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel("Escreva as alterações se desejar"));
		caixas.add(new JLabel(" "));

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

		JPanel dataConclusaoPro = new JPanel();
		dataConclusaoPro.setLayout(new BoxLayout(dataConclusaoPro, BoxLayout.X_AXIS));
		dataConclusaoPro.add(Box.createHorizontalStrut(8));
		dataConclusaoPro.add(new JLabel("Data de Conclusao:        "));
		bDataConclusaoPro = new JTextField(5);
		bDataConclusaoPro.setMaximumSize(new Dimension(800, 20));
		dataConclusaoPro.add(bDataConclusaoPro);
		dataConclusaoPro.add(Box.createHorizontalStrut(5));
		caixas.add(dataConclusaoPro);

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
		caixas.add(alterar);

	}

	public void acaoBotoes() {
		ActionListenerTelaPrincipal albotoes = new ActionListenerTelaPrincipal();
		consultar.addActionListener(albotoes);
	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == consultar) {
				Processo pro = processoDao.buscar(barraConsulta.getText());
				System.out.println(pro.getPessoaAutor().getNomePes());
				bNroProcesso.setText(pro.getNroProcesso());
				bDataAberturaPro.setText(sdf.format(pro.getDataAbertura()));
				if(pro.getDataConclusao()!= null) {
					bDataConclusaoPro.setText(sdf.format(pro.getDataConclusao()));
				}
				bSituacao.setText(String.valueOf(pro.getSitucacao()));
				if(((PessoaJuridica) pro.getPessoaAutor()).getCpnjPes().length() == 14) {
					bCpfAutor.setText(((PessoaJuridica) pro.getPessoaAutor()).getCpnjPes());
				}
			}

		}
	}
}
