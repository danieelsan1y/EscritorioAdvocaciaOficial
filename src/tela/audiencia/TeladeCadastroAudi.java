package tela.audiencia;

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
import entities.Audiencia;
import entities.Processo;

public class TeladeCadastroAudi extends JFrame {
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

	JTextField bNroAudi, bDataAudi, bParecerAudi, BExclusao, bProcessoAudi, bConsulta;

	public TeladeCadastroAudi() {
		super("Cadastro Audiência");
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

		textoCadastro.add(new JLabel("Cadastrar Audiência"));
		JPanel nroAudiencia = new JPanel();
		nroAudiencia.setLayout(new BoxLayout(nroAudiencia, BoxLayout.X_AXIS));
		nroAudiencia.add(Box.createHorizontalStrut(8));
		nroAudiencia.add(new JLabel("Numero:          "));
		bNroAudi = new JTextField(5);
		bNroAudi.setMaximumSize(new Dimension(800, 20));
		nroAudiencia.add(bNroAudi);
		nroAudiencia.add(Box.createHorizontalStrut(5));
		caixas.add(nroAudiencia);

		JPanel dataAudi = new JPanel();
		dataAudi.setLayout(new BoxLayout(dataAudi, BoxLayout.X_AXIS));
		dataAudi.add(Box.createHorizontalStrut(8));
		dataAudi.add(new JLabel("Data:                "));
		bDataAudi = new JTextField(5);
		bDataAudi.setMaximumSize(new Dimension(800, 20));
		dataAudi.add(bDataAudi);
		dataAudi.add(Box.createHorizontalStrut(5));
		caixas.add(dataAudi);

		JPanel parecerAudi = new JPanel();
		parecerAudi.setLayout(new BoxLayout(parecerAudi, BoxLayout.X_AXIS));
		parecerAudi.add(Box.createHorizontalStrut(8));
		parecerAudi.add(new JLabel("Parecer:         "));
		bParecerAudi = new JTextField(5);
		bParecerAudi.setMaximumSize(new Dimension(800, 20));
		parecerAudi.add(bParecerAudi);
		parecerAudi.add(Box.createHorizontalStrut(5));
		caixas.add(parecerAudi);

		JPanel processoAudi = new JPanel();
		processoAudi.setLayout(new BoxLayout(processoAudi, BoxLayout.X_AXIS));
		processoAudi.add(Box.createHorizontalStrut(8));
		processoAudi.add(new JLabel("Numero do Processo:         "));
		bProcessoAudi = new JTextField(5);
		bProcessoAudi.setMaximumSize(new Dimension(800, 20));
		processoAudi.add(bProcessoAudi);
		processoAudi.add(Box.createHorizontalStrut(5));
		caixas.add(processoAudi);

		caixas.add(inserir);
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));

		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o numero da audiencia:  "));
		BExclusao = new JTextField(14);
		consulta.add(BExclusao);
		consulta.add(excluir);
		divisaoEspecificacoes.add("West", consulta);
		caixas.add(caixas.add(Box.createGlue()));

		JPanel consultaAudi = new JPanel();
		consulta.add(consultar);
		divisaoEspecificacoes.add("West", consulta);
		caixas.add(caixas.add(Box.createGlue()));

	}

	public void resultadoConsulta(Audiencia audi) {

		JPanel rNroAudi = new JPanel();
		rNroAudi.add(new JLabel("Numero Audiencia: "));
		rNroAudi.add(new JLabel(audi.getNroAudi()));
		caixas.add(rNroAudi);

		JPanel rdataAudi = new JPanel();
		rdataAudi.add(new JLabel("Data: "));
		rdataAudi.add(new JLabel(String.valueOf(audi.getDataAudi())));
		caixas.add(rdataAudi);

		JPanel rParecer = new JPanel();
		rParecer.add(new JLabel("Parecer: "));
		rParecer.add(new JLabel(audi.getParecer()));
		caixas.add(rParecer);

		JPanel rNroProcesso = new JPanel();
		rNroProcesso.add(new JLabel("nroProcesso: "));
		rNroProcesso.add(new JLabel(audi.getProcesso().getNroProcesso()));
		caixas.add(rNroProcesso);
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));

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
				if (bProcessoAudi.getText().isEmpty() && bNroAudi.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo(s) em branco!", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Processo pro = processoDao.buscar(bProcessoAudi.getText());
					Audiencia audi = audienciaDao.buscar(bNroAudi.getText());
					if (pro == null) {
						JOptionPane.showMessageDialog(null, "Processo não encontrado!", "",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						if (audi == null) {
							try {
								audi = new Audiencia(sdf.parse(bDataAudi.getText()), bParecerAudi.getText(), pro,
										bNroAudi.getText());
							} catch (ParseException e1) {
								e1.printStackTrace();
							} finally {
								JOptionPane.showMessageDialog(null, "Audiencia adicionada com sucesso!", "",
										JOptionPane.INFORMATION_MESSAGE);
								audi.setProcesso(pro);
								audienciaDao.inserir(audi);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Numero da audiencia já existe!", "",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}

				}

			}
			if (e.getSource() == consultar) {

				if (BExclusao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo em branco!", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Audiencia audi = audienciaDao.buscar(BExclusao.getText());
					if (audi == null) {
						JOptionPane.showMessageDialog(null, "Audiencia não encontrada!", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						resultadoConsulta(audi);
						JOptionPane.showMessageDialog(null, "Audiencia consultada com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
			if (e.getSource() == excluir) {

				if (BExclusao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo em branco!", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Audiencia audi = audienciaDao.buscar(BExclusao.getText());
					if (audi == null) {
						JOptionPane.showMessageDialog(null, "Audiencia não encontrada!", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						audienciaDao.deletar(audi);
						JOptionPane.showMessageDialog(null, "Audiencia excluida com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
		
					}
				}

			}
			if (e.getSource() == limpar) {
				bDataAudi.setText("");
				bNroAudi.setText("");
				bParecerAudi.setText("");
				BExclusao.setText("");
				bProcessoAudi.setText("");
			}
			if (e.getSource() == cadastrados) {
			}
			if (e.getSource() == alterar) {
				TelaDeAlterarAudi audi = new TelaDeAlterarAudi();

			}
		}

	}

}
