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
import entities.Audiencia;
import entities.Custo;
import entities.Processo;

public class TeladeCadastroCusto extends JFrame {
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

	JTextField bNroCusto, bDataCusto, bDesCusto, BExclusao, bValCusto, bConsulta, bProcessoCusto;

	public TeladeCadastroCusto() {
		super("Cadastro Custo");
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

	}

	public void inserirCampos() {

		textoCadastro.add(new JLabel("Cadastrar Custo"));
		JPanel nroCusto = new JPanel();
		nroCusto.setLayout(new BoxLayout(nroCusto, BoxLayout.X_AXIS));
		nroCusto.add(Box.createHorizontalStrut(8));
		nroCusto.add(new JLabel("Numero:          "));
		bNroCusto= new JTextField(5);
		bNroCusto.setMaximumSize(new Dimension(800, 20));
		nroCusto.add(bNroCusto);
		nroCusto.add(Box.createHorizontalStrut(5));
		caixas.add(nroCusto);

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

		caixas.add(inserir);
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));
		caixas.add(new JLabel("      "));

		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o numero do custo:  "));
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
	public void resultadoConsulta (Custo custo) {
		
		
		JPanel rNroCusto = new JPanel();
		rNroCusto.add(new JLabel("Numero: "));
		rNroCusto.add(new JLabel(custo.getNroCusto()));
		caixas.add(rNroCusto);

		JPanel rdataCusto = new JPanel();
		rdataCusto.add(new JLabel("Data: "));
		rdataCusto.add(new JLabel(String.valueOf(sdf.format(custo.getDataCusto()))));
		caixas.add(rdataCusto);

		JPanel rDesCusto = new JPanel();
		rDesCusto.add(new JLabel("Descrição: "));
		rDesCusto.add(new JLabel(custo.getDesCusto()));
		caixas.add(rDesCusto);
		
		JPanel rValCusto = new JPanel();
		rValCusto.add(new JLabel("Valor: "));
		rValCusto.add(new JLabel(String.valueOf(custo.getValCusto())));
		caixas.add(rValCusto);

		JPanel rNroProcesso = new JPanel();
		rNroProcesso.add(new JLabel("Nº Processo: "));
		rNroProcesso.add(new JLabel(custo.getProcesso().getNroProcesso()));
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
				Processo pro = processoDao.buscar(bProcessoCusto.getText());
				Custo custo = null;
				try {
					custo = new Custo(sdf.parse(bDataCusto.getText()), bDesCusto.getText(), Double.valueOf(bValCusto.getText()), pro, bNroCusto.getText());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}  finally {
					custoDao.inserir(custo);
					JOptionPane.showMessageDialog(null, "Custo inserido com sucesso!", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				
	
			}
			if (e.getSource() == consultar) {
				Custo custo = custoDao.buscar(BExclusao.getText());
				resultadoConsulta(custo);
				JOptionPane.showMessageDialog(null, "Custo consultado com sucesso!", "",
						JOptionPane.INFORMATION_MESSAGE);


			}
			if (e.getSource() == excluir) {
				Custo custo = custoDao.buscar(BExclusao.getText());
				custoDao.deletar(custo);
				JOptionPane.showMessageDialog(null, "Custo excluido com sucesso!", "",
						JOptionPane.INFORMATION_MESSAGE);
				

				
			}
			if (e.getSource() == limpar) {
				BExclusao.setText("");
				bDataCusto.setText("");
				bDesCusto.setText("");
				bValCusto.setText("");
				bProcessoCusto.setText("");

			}
			if (e.getSource() == cadastrados) {
			}
			if (e.getSource() == alterar) {
				TelaDeAlterarCusto tela = new TelaDeAlterarCusto();

			}
		}

	}

}
