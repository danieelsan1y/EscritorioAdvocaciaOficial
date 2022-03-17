package tela.tribunal;

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
import entities.Tribunal;

public class TeladeCadastroTrib extends JFrame {
	// atributos

	TribunalDao tribunalDao = DaoFactory.criarTribunal();

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
			cadastrados = new JButton("CONSULTA DO TRIBUNAL, VARAS E PROCESSOS");

	JTextField bDesTribunal, bEndTribunal, BExclusao;

	public TeladeCadastroTrib() {
		super("Cadastro Tribunal");
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

		textoCadastro.add(new JLabel("Cadastrar Tribunal"));
		JPanel desTribunal = new JPanel();
		desTribunal.setLayout(new BoxLayout(desTribunal, BoxLayout.X_AXIS));
		desTribunal.add(Box.createHorizontalStrut(8));
		desTribunal.add(new JLabel("Nome:                "));
		bDesTribunal = new JTextField(5);
		bDesTribunal.setMaximumSize(new Dimension(800, 20));
		desTribunal.add(bDesTribunal);
		desTribunal.add(Box.createHorizontalStrut(5));
		caixas.add(desTribunal);

		JPanel endTribunal = new JPanel();
		endTribunal.setLayout(new BoxLayout(endTribunal, BoxLayout.X_AXIS));
		endTribunal.add(Box.createHorizontalStrut(8));
		endTribunal.add(new JLabel("Endereço:         "));
		bEndTribunal = new JTextField(5);
		bEndTribunal.setMaximumSize(new Dimension(800, 20));
		endTribunal.add(bEndTribunal);
		endTribunal.add(Box.createHorizontalStrut(5));
		caixas.add(endTribunal);
		caixas.add(inserir);
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("Excluir tribunal"));

		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o nome do tribunal:  "));
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
				if (bDesTribunal.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo(s) em branco!", "",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					Tribunal trib = tribunalDao.buscar(bDesTribunal.getText());

					if (trib == null) {
						trib = new Tribunal(bDesTribunal.getText(), bEndTribunal.getText());
						tribunalDao.inserir(trib);
						JOptionPane.showMessageDialog(null, "Tribunal inserido com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Tribunal já cadastrado!", "",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
			if (e.getSource() == consultar) {
				// TeladeConsulta telaCadastrados = new TeladeConsulta();
			}
			if (e.getSource() == excluir) {
				if(BExclusao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo(s) em branco!", "",
							JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					Tribunal trib = tribunalDao.buscar(BExclusao.getText());
					if(trib == null) {
						JOptionPane.showMessageDialog(null, "Tribunal não encontrado!", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						tribunalDao.deletar(trib);
						JOptionPane.showMessageDialog(null, "Tribunal excluido com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
					

				}


			}
			if (e.getSource() == limpar) {
				bEndTribunal.setText("");
				bDesTribunal.setText("");
				BExclusao.setText("");

			}
			if (e.getSource() == cadastrados) {
				TelaDeListarTrib tela = new TelaDeListarTrib();
			}
			if (e.getSource() == alterar) {
				TelaDeAlterarTrib tela = new TelaDeAlterarTrib();
			}
		}

	}

}
