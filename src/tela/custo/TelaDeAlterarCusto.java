package tela.custo;

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

import dao.model.AudienciaDao;
import dao.model.CustoDao;
import dao.model.DaoFactory;
import dao.model.ProcessoDao;
import dao.model.TribunalDao;
import entities.Custo;
import entities.Processo;

class TelaDeAlterarCusto extends JFrame {
	// atributos
	ProcessoDao processoDao = DaoFactory.criarProcesso();
	CustoDao custoDao = DaoFactory.criarCusto();
	AudienciaDao audienciaDao = DaoFactory.criarAudiencia();
	TribunalDao tribunalDao = DaoFactory.criarTribunal();
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
			excluir = new JButton("EXCLUIR"), limpar = new JButton("LIMPAR"), cadastrados = new JButton("CONSULTAR");

	JTextField bNroCusto, bDataCusto, bDesCusto, BExclusao, bValCusto, bConsulta, bProcessoCusto, barraConsulta;

	public TelaDeAlterarCusto() {
		super("Alterar");
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

		textoCadastro.add(new JLabel("ConsultarT"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o numero do custo:   "));
		barraConsulta = new JTextField(14);
		consulta.add(barraConsulta);
		consulta.add(consultar);
		caixas.add(consulta);
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel(" "));
		caixas.add(new JLabel("Escreva as alterações se for necessário"));
		caixas.add(new JLabel(" "));

		textoCadastro.add(new JLabel("Alterar custo"));
		JPanel nroAudiencia = new JPanel();
		nroAudiencia.setLayout(new BoxLayout(nroAudiencia, BoxLayout.X_AXIS));
		nroAudiencia.add(Box.createHorizontalStrut(8));
		nroAudiencia.add(new JLabel("Numero:          "));
		bNroCusto = new JTextField(5);
		bNroCusto.setMaximumSize(new Dimension(800, 20));
		nroAudiencia.add(bNroCusto);
		nroAudiencia.add(Box.createHorizontalStrut(5));
		caixas.add(nroAudiencia);

		JPanel dataCusto = new JPanel();
		dataCusto.setLayout(new BoxLayout(dataCusto, BoxLayout.X_AXIS));
		dataCusto.add(Box.createHorizontalStrut(8));
		dataCusto.add(new JLabel("Data:                "));
		bDataCusto = new JTextField(5);
		bDataCusto.setMaximumSize(new Dimension(800, 20));
		dataCusto.add(bDataCusto);
		dataCusto.add(Box.createHorizontalStrut(5));
		caixas.add(dataCusto);

		JPanel desCusto = new JPanel();
		desCusto.setLayout(new BoxLayout(desCusto, BoxLayout.X_AXIS));
		desCusto.add(Box.createHorizontalStrut(8));
		desCusto.add(new JLabel("Parecer:         "));
		bDesCusto = new JTextField(5);
		bDesCusto.setMaximumSize(new Dimension(800, 20));
		desCusto.add(bDesCusto);
		desCusto.add(Box.createHorizontalStrut(5));
		caixas.add(desCusto);

		JPanel valorCusto = new JPanel();
		valorCusto.setLayout(new BoxLayout(valorCusto, BoxLayout.X_AXIS));
		valorCusto.add(Box.createHorizontalStrut(8));
		valorCusto.add(new JLabel("Valor:              "));
		bValCusto = new JTextField(5);
		bValCusto.setMaximumSize(new Dimension(800, 20));
		valorCusto.add(bValCusto);
		valorCusto.add(Box.createHorizontalStrut(5));
		caixas.add(valorCusto);

		JPanel processoCusto = new JPanel();
		processoCusto.setLayout(new BoxLayout(processoCusto, BoxLayout.X_AXIS));
		processoCusto.add(Box.createHorizontalStrut(8));
		processoCusto.add(new JLabel("Nº Processo:         "));
		bProcessoCusto = new JTextField(5);
		bProcessoCusto.setMaximumSize(new Dimension(800, 20));
		processoCusto.add(bProcessoCusto);
		processoCusto.add(Box.createHorizontalStrut(5));
		caixas.add(processoCusto);
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
				Custo custo = custoDao.buscar(barraConsulta.getText());
				bDataCusto.setText(sdf.format(custo.getDataCusto()));
				bDesCusto.setText(custo.getDesCusto());
				bNroCusto.setText(custo.getNroCusto());
				bValCusto.setText(String.valueOf(custo.getValCusto()));
				bProcessoCusto.setText(custo.getProcesso().getNroProcesso());
				JOptionPane.showMessageDialog(null, "Custo consultado com sucesso!", "",
						JOptionPane.INFORMATION_MESSAGE);

			}
			if (e.getSource() == limpar) {

			}

			if (e.getSource() == alterar) {
				if (bDataCusto.getText().isEmpty() || bDesCusto.getText().isEmpty()
						|| bProcessoCusto.getText().isEmpty() || bNroCusto.getText().isEmpty()
						|| bValCusto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo(s) em branco!", "", JOptionPane.INFORMATION_MESSAGE);
				} else {

					Custo custo = custoDao.buscar(barraConsulta.getText());
					Processo pro = processoDao.buscar(bProcessoCusto.getText());

					custo.setDesCusto(bDesCusto.getText());
					custo.setNroCusto(bNroCusto.getText());
					custo.setValCusto(Double.valueOf(bValCusto.getText()));
					custo.setProcesso(pro);

					try {
						custo.setDataCusto(sdf.parse(bDataCusto.getText()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					} finally {
						custoDao.atualizar(custo);
						JOptionPane.showMessageDialog(null, "Custo alterado com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

			}
		}

	}

}
