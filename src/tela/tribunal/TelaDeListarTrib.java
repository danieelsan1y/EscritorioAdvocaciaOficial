package tela.tribunal;

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

import dao.model.DaoFactory;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import dao.model.TribunalDao;
import dao.model.VaraDao;
import entities.PessoaJuridica;
import entities.Processo;
import entities.Tribunal;
import entities.Vara;
import tarefa.ThreadAudiencia;

class TelaDeListarTrib extends JFrame {
	PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
	PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
	VaraDao varaDao = DaoFactory.criarVara();
	TribunalDao tribunalDao = DaoFactory.criarTribunal();
	
	JPanel tabelaVara = new JPanel();
	//JPanel tabelaPro = new JPanel();
	JPanel divisaoPrincipal = new JPanel();
	JPanel divisaoCentro = new JPanel();
	JPanel sairTela = new JPanel();
	JPanel texto = new JPanel();
	JPanel atributos = new JPanel();
	JPanel atributosConsultados = new JPanel();
	JTextField barraConsulta;
	JButton consultar = new JButton("CONSULTAR"), sair = new JButton("SAIR");

	public TelaDeListarTrib() {
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
		divisaoPrincipal.add("Center", tabelaVara);
		//divisaoPrincipal.add("Center", tabelaPro);
		divisaoPrincipal.add("South", sairTela);

	}

	public void consultarPessoa() {
		// consulta da pessoa
		int tamanho = 0;

		texto.add(new JLabel("Consultar Tribunal"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o nome do Tribunal:   "));
		barraConsulta = new JTextField(14);
		consulta.add(barraConsulta);
		consulta.add(consultar);
		atributos.add(consulta);

	}

	public void ResultadoProcessos(Tribunal obj) {

		JTable tabela_Vara;
		DefaultTableModel modelo_Vara;

		tabelaVara.setLayout(new BoxLayout(tabelaVara, BoxLayout.X_AXIS));

		JPanel textoVara = new JPanel();
		atributos.add(textoVara);
		String[] colunas = { "Nome da vara"};
		modelo_Vara = new DefaultTableModel(colunas, 0);

		List<Vara> lista = new ArrayList<>();
		tribunalDao.listaDeVaras(obj);
		List<Processo> listapro = new ArrayList<Processo>();
		lista = obj.getVaras();
		for (Vara varas : lista) {

			modelo_Vara.addRow(new Object[] { varas.getDesVara() });
			listapro.addAll(varas.getProcessos());
			
		}

		tabela_Vara = new JTable(modelo_Vara);
		JScrollPane barraRolagem = new JScrollPane(tabela_Vara);
		tabelaVara.add(barraRolagem);
		tabelaVara.add(Box.createRigidArea(new Dimension(10, 10)));
		

		
		JTable tabela_pro;
		DefaultTableModel modelo_pro;

		JPanel tabela = new JPanel();
		tabela.setLayout(new BoxLayout(tabela, BoxLayout.Y_AXIS));
		JPanel textopro = new JPanel();
		textopro.add(new JLabel("Processos"));
		atributos.add(textoVara);
		String[] colunasPro = { "Número", "Data de Abertura", "Data de Conclusão", "Situação",
				"Autor", "Réu", "Nome da vara"};
		modelo_pro = new DefaultTableModel(colunasPro, 0);

		
			
		for (Processo processo : listapro) {
			
			modelo_pro.addRow(new Object[] { processo.getNroProcesso(), processo.getDataAbertura(), processo.getDataConclusao(),
					processo.getSitucacao(), processo.getPessoaAutor().getNomePes(), processo.getPessoaReu().getNomePes(), processo.getVara().getDesVara()});
		}

		tabela_pro = new JTable(modelo_pro);
		JScrollPane barraRolagemPro = new JScrollPane(tabela_pro);
		tabelaVara.add(barraRolagemPro);
		tabelaVara.add(Box.createRigidArea(new Dimension(10, 10)));
		
		
		
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
			
				Tribunal obj= tribunalDao.buscar(barraConsulta.getText());
				ResultadoProcessos(obj);
				

				
		
			}
			if (e.getSource() == sair) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		}
	}
}