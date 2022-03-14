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

class TelaDeAlterarTrib extends JFrame {
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
			cadastrados = new JButton("VARAS E PROCESSOS");

	JTextField bDesTribunal, bEndTribunal, barraConsulta;

	public TelaDeAlterarTrib() {
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
		

		textoCadastro.add(new JLabel("ConsultarTribunal"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o nome do Tribunal:   "));
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
				Tribunal trib = tribunalDao.buscar(barraConsulta.getText());
				bEndTribunal.setText(trib.getEndTrib());
				bDesTribunal.setText(trib.getDesTrib());
				JOptionPane.showMessageDialog(null, "Tribunal consultado com sucesso!", "",
						JOptionPane.INFORMATION_MESSAGE);

			}
			if (e.getSource() == limpar) {
				bEndTribunal.setText("");
				bDesTribunal.setText("");
				barraConsulta.setText("");

			}

			if (e.getSource() == alterar) {
				Tribunal trib = tribunalDao.buscar(barraConsulta.getText());
				trib.setDesTrib(bDesTribunal.getText());
				trib.setEndTrib(bEndTribunal.getText());
				tribunalDao.atualizar(trib);
				JOptionPane.showMessageDialog(null, "Tribunal alterado com sucesso!", "",
						JOptionPane.INFORMATION_MESSAGE);

				
			}
		}

	}

}
