package tela.vara;

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
import dao.model.TribunalDao;
import dao.model.VaraDao;
import entities.Tribunal;
import entities.Vara;

public class TeladeCadastroVara extends JFrame {
	// atributos

	TribunalDao tribunalDao = DaoFactory.criarTribunal();
	VaraDao varaDao = DaoFactory.criarVara();

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
			cadastrados = new JButton("CONSULTA DOS PROCESSOS");

	JTextField bDesVara, bDesTribunal, BExclusao;

	public TeladeCadastroVara() {
		super("Vara");
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
		botoes.add(cadastrados);

	}

	public void inserirCampos() {

		textoCadastro.add(new JLabel("Cadastrar Vara"));
		JPanel desVara = new JPanel();
		desVara.setLayout(new BoxLayout(desVara, BoxLayout.X_AXIS));
		desVara.add(Box.createHorizontalStrut(8));
		desVara.add(new JLabel("Nome Vara:                "));
		bDesVara = new JTextField(5);
		bDesVara.setMaximumSize(new Dimension(800, 20));
		desVara.add(bDesVara);
		desVara.add(Box.createHorizontalStrut(5));
		caixas.add(desVara);

		JPanel endDesTrib = new JPanel();
		endDesTrib.setLayout(new BoxLayout(endDesTrib, BoxLayout.X_AXIS));
		endDesTrib.add(Box.createHorizontalStrut(8));
		endDesTrib.add(new JLabel("Nome Tribunal:         "));
		bDesTribunal = new JTextField(5);
		bDesTribunal.setMaximumSize(new Dimension(800, 20));
		endDesTrib.add(bDesTribunal);
		endDesTrib.add(Box.createHorizontalStrut(5));
		caixas.add(endDesTrib);
		caixas.add(inserir);
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("Excluir Vara"));

		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o nome da Vara:  "));
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
		excluir.addActionListener(albotoes);
		limpar.addActionListener(albotoes);
		cadastrados.addActionListener(albotoes);
		alterar.addActionListener(albotoes);
	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == inserir) {
				if (bDesTribunal.getText().isEmpty() && bDesVara.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campos obrigatórios em  branco!", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Tribunal trib = tribunalDao.buscar(bDesTribunal.getText());
					Vara vara = new Vara(bDesVara.getText(), trib);
					varaDao.inserir(vara);
					JOptionPane.showMessageDialog(null, "Vara adicionada com sucesso!", "",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}

			if (e.getSource() == excluir) {
				if (BExclusao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo em branco!", "", JOptionPane.INFORMATION_MESSAGE);

				} else {
					Vara vara = varaDao.buscar(BExclusao.getText());
					if (vara == null) {
						JOptionPane.showMessageDialog(null, "Vara não foi encontrada!", "",
								JOptionPane.INFORMATION_MESSAGE);

					} else {

						varaDao.deletar(vara);
						JOptionPane.showMessageDialog(null, "Vara excluida com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
			if (e.getSource() == limpar)

			{
				bDesTribunal.setText("");
				bDesVara.setText("");
				BExclusao.setText("");
			}
			if (e.getSource() == cadastrados) {
				TelaDeListarVara tela = new TelaDeListarVara();
			}
			if (e.getSource() == alterar) {
				TelaDeAlterarVara tela = new TelaDeAlterarVara();

			}
		}

	}

}
