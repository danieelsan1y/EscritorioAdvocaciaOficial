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

public class TelaDeAlterarVara extends JFrame {
	// atributos

	VaraDao varaDao = DaoFactory.criarVara();
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
			cadastrados = new JButton("VARAS E PROCESSOS");

	JTextField bDesTribunal, bDesVara, barraConsulta;

	public TelaDeAlterarVara() {
		super("Alterar vara");
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

		textoCadastro.add(new JLabel("Consultar Vara"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o nome da Vara:   "));
		barraConsulta = new JTextField(14);
		consulta.add(barraConsulta);
		consulta.add(consultar);
		caixas.add(consulta);
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel("Escreva as alterações"));
		caixas.add(new JLabel(" "));

		JPanel desVara = new JPanel();
		desVara.setLayout(new BoxLayout(desVara, BoxLayout.X_AXIS));
		desVara.add(Box.createHorizontalStrut(8));
		desVara.add(new JLabel("Nome da Vara:         "));
		bDesVara = new JTextField(5);
		bDesVara.setMaximumSize(new Dimension(800, 20));
		desVara.add(bDesVara);
		desVara.add(Box.createHorizontalStrut(5));
		caixas.add(desVara);

		JPanel desTribunal = new JPanel();
		desTribunal.setLayout(new BoxLayout(desTribunal, BoxLayout.X_AXIS));
		desTribunal.add(Box.createHorizontalStrut(8));
		desTribunal.add(new JLabel("Nome do Tribunal:   "));
		bDesTribunal = new JTextField(5);
		bDesTribunal.setMaximumSize(new Dimension(800, 20));
		desTribunal.add(bDesTribunal);
		desTribunal.add(Box.createHorizontalStrut(5));
		caixas.add(desTribunal);
		caixas.add(alterar);

	}

	public void acaoBotoes() {
		ActionListenerTelaPrincipal albotoes = new ActionListenerTelaPrincipal();
		consultar.addActionListener(albotoes);
		alterar.addActionListener(albotoes);
	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == consultar) {
				Tribunal trib;
				Vara vara = varaDao.buscar(barraConsulta.getText());
				if (barraConsulta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo em branco!", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (vara == null) {
						JOptionPane.showMessageDialog(null, "Vara não foi encontrada!", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (!(vara.getDesVara().equals(null))) {
							bDesVara.setText(vara.getDesVara());
							bDesTribunal.setText(vara.getTribunal().getDesTrib());
						}

					}
				}
			}
			if (e.getSource() == limpar)

			{
				bDesVara.setText("");
				bDesTribunal.setText("");
				barraConsulta.setText("");

			}

			if (e.getSource() == alterar) {
				if (barraConsulta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo em branco!", "", JOptionPane.INFORMATION_MESSAGE);

				} else {
					Vara vara = varaDao.buscar(barraConsulta.getText());
					if (vara == null) {
						JOptionPane.showMessageDialog(null, "Vara não foi encontrada!", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (bDesVara.getText().isEmpty() || bDesTribunal.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Campos em branco!", "",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							Tribunal trib = tribunalDao.buscar(bDesTribunal.getText());
							if (trib == null) {
								JOptionPane.showMessageDialog(null, "Tribunal não foi encontrado!", "",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								vara.setDesVara(bDesVara.getText());
								vara.setTribunal(trib);
								varaDao.atualizar(vara);
								JOptionPane.showMessageDialog(null, "Vara alterada com sucesso!", "",
										JOptionPane.INFORMATION_MESSAGE);
							}

						}
					}
				}
			}

		}
	}

}
