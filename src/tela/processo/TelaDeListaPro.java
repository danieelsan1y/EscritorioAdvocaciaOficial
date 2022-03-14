package tela.processo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.model.AudienciaDao;
import dao.model.CustoDao;
import dao.model.DaoFactory;
import dao.model.ProcessoDao;
import entities.Audiencia;
import entities.Custo;
import entities.PessoaJuridica;
import entities.Processo;
import tarefa.ThreadAudiencia;

class TelaDeListaPro extends JFrame {
	ProcessoDao processoDao = DaoFactory.criarProcesso();
	CustoDao custoDao = DaoFactory.criarCusto();
	AudienciaDao audienciaDao = DaoFactory.criarAudiencia();
	JPanel tabelaVara = new JPanel();
	JPanel tabelaPro = new JPanel();
	JPanel divisaoPrincipal = new JPanel();
	JPanel divisaoCentro = new JPanel();
	JPanel sairTela = new JPanel();
	JPanel texto = new JPanel();
	JPanel atributos = new JPanel();
	JPanel atributosConsultados = new JPanel();
	JTextField barraConsulta;
	JButton consultar = new JButton("CONSULTAR"), sair = new JButton("SAIR");

	public TelaDeListaPro() {
		super("Tela de Listar");
		getContentPane().add(divisaoCentro);
		montarTela();
		setSize(500, 500);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
	}

	public void definirLayouts() {
		setLayout(new BorderLayout());
		divisaoCentro.setLayout(new BorderLayout());
		divisaoPrincipal.setLayout(new BorderLayout());
		atributos.setLayout(new BoxLayout(atributos, BoxLayout.Y_AXIS));
		atributosConsultados.setLayout(new BoxLayout(atributosConsultados, BoxLayout.Y_AXIS));
	}

	public void montarTela() {
		definirLayouts();
		adicionarNasDivisoes();
		consultarPessoa();
		acaoBotoes();

	}

	public void adicionarNasDivisoes() {
		add("Center", divisaoCentro);
		add("North", texto);
		divisaoCentro.add("North", divisaoPrincipal);
		divisaoPrincipal.add("North", atributos);
		divisaoPrincipal.add("East", tabelaVara);
		divisaoPrincipal.add("Center", tabelaPro);
		divisaoPrincipal.add("South", sairTela);

	}

	public void consultarPessoa() {
		// consulta da pessoa
		int tamanho = 0;

		texto.add(new JLabel("Consultar Processo"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o numero do Processo:   "));
		barraConsulta = new JTextField(14);
		consulta.add(barraConsulta);
		consulta.add(consultar);
		atributos.add(consulta);

	}

	public void ResultadoProcessos(Processo pro) {

		JTable tabela_audi;
		DefaultTableModel modelo_audi;

		tabelaVara.setLayout(new BoxLayout(tabelaVara, BoxLayout.Y_AXIS));

		JPanel textoaudi = new JPanel();
		atributos.add(textoaudi);
		String[] colunas = { "Numero", "Data Audiencia", "Parecer"};
		modelo_audi = new DefaultTableModel(colunas, 0);

		List<Audiencia> lista = new ArrayList<>();
		processoDao.listaDeAudiencias(pro);
		List<Processo> listapro = new ArrayList<Processo>();
		lista = pro.getAudiencias();
		for (Audiencia audi : lista) {

			modelo_audi.addRow(new Object[] { audi.getNroAudi(), audi.getDataAudi(), audi.getParecer() });

			
		}

		tabela_audi = new JTable(modelo_audi);
		JScrollPane barraRolagem = new JScrollPane(tabela_audi);
		tabelaVara.add(barraRolagem);
		tabelaVara.add(Box.createRigidArea(new Dimension(10, 10)));
		

		
		JTable tabela_custo;
		DefaultTableModel modelo_custo;
		processoDao.listaDeCustos(pro);
		List <Custo> listaCusto = pro.getCustos();
		
		JPanel tabela = new JPanel();
		tabela.setLayout(new BoxLayout(tabela, BoxLayout.Y_AXIS));
		JPanel textopro = new JPanel();
		textopro.add(new JLabel("Processos"));
		//atributos.add(textoVara);
		String[] colunasCusto = { "Número", "Data", "Descrição", "Valor"};
		modelo_custo = new DefaultTableModel(colunasCusto, 0);

		
			
		for (Custo custo : listaCusto) {
			
			modelo_custo.addRow(new Object[] {custo.getNroCusto(), custo.getDataCusto(), custo.getDesCusto(), custo.getValCusto()});
		}

		tabela_custo = new JTable(modelo_custo);
		JScrollPane barraRolagemPro = new JScrollPane(tabela_custo);
		tabelaPro.add(barraRolagemPro);
		tabelaPro.add(Box.createRigidArea(new Dimension(10, 10)));
		
		

	}

	public void ResultadoPJ(PessoaJuridica obj) {

	}

	public void acaoBotoes() {
		ActionListenerTelaPrincipal albotoes = new ActionListenerTelaPrincipal();
		consultar.addActionListener(albotoes);
		sair.addActionListener(albotoes);
	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == consultar) {
			
				Processo pro = processoDao.buscar(barraConsulta.getText());
				ResultadoProcessos(pro);
				
				ThreadAudiencia t01 = new ThreadAudiencia("Thread01", "0001");
				//ThreadAudiencia t02 = new ThreadAudiencia("Thread02", 10);
				t01.setPriority(Thread.NORM_PRIORITY - 1);
				//t02.setPriority(Thread.NORM_PRIORITY + 5);
				t01.start();
				//t02.start();
		
			}
			if (e.getSource() == sair) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		}
	}
}