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

class TelaDeAlterarAudi extends JFrame {
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

	JTextField bNroAudi, bDataAudi, bParecerAudi, BExclusao, bProcessoAudi, bConsulta, barraConsulta;

	public TelaDeAlterarAudi() {
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
		consulta.add(new JLabel("Digite o numero da Audiencia:   "));
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
				Audiencia audi = audienciaDao.buscar(barraConsulta.getText());
				
				bNroAudi.setText(audi.getNroAudi());
				bParecerAudi.setText(audi.getParecer());
				bProcessoAudi.setText(audi.getProcesso().getNroProcesso());
				bDataAudi.setText(String.valueOf(sdf.format(audi.getDataAudi())));
				JOptionPane.showMessageDialog(null, "Audiencia consultada com sucesso!", "",
						JOptionPane.INFORMATION_MESSAGE);

			}
			if (e.getSource() == limpar) {
				bDataAudi.setText("");
				bNroAudi.setText("");
				bParecerAudi.setText("");
				BExclusao.setText("");
				bProcessoAudi.setText("");

			}

			if (e.getSource() == alterar) {
				Audiencia audi = audienciaDao.buscar(barraConsulta.getText());
				Processo pro = processoDao.buscar((bProcessoAudi.getText()));
				audi.setNroAudi(bNroAudi.getText());
				audi.setParecer(bParecerAudi.getText());
				audi.setProcesso(pro);
				System.out.println(audi.conAud());
				try {
					audi.setDataAudi(sdf.parse(bDataAudi.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				} finally {
					audienciaDao.atualizar(audi);
					JOptionPane.showMessageDialog(null, "Audiencia alterada com sucesso!", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
				

				
			}
		}

	}

}
