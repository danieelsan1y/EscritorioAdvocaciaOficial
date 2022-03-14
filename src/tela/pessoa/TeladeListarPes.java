package tela.pessoa;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import dao.model.PessoaDao;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import entities.Pessoa;
import entities.PessoaFisica;
import entities.PessoaJuridica;

public class TeladeListarPes extends JFrame {
	PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
	PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
	PessoaDao pessoaDao = DaoFactory.criarPessoa();

	JPanel divisaoPrincipal = new JPanel();
	JPanel divisaoCentro = new JPanel();
	JPanel sairTela = new JPanel();
	JPanel texto = new JPanel();
	JPanel atributos = new JPanel();
	JPanel atributosConsultados = new JPanel();
	JTextField barraConsulta;
	JButton consultar = new JButton("CONSULTAR"), sair = new JButton("SAIR");

	public TeladeListarPes() {
		super("Tela de Consulta");
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
		iniciarLista();

	}

	public void adicionarNasDivisoes() {
		add("Center", divisaoCentro);
		add("North", texto);
		divisaoCentro.add("North", divisaoPrincipal);
		divisaoPrincipal.add("North", atributos);
		divisaoPrincipal.add("Center", atributosConsultados);
		divisaoPrincipal.add("South", sairTela);

	}

	public void iniciarLista() {
		JTable tabela_pessoas;
		DefaultTableModel modelo_tabela;

		JPanel tabela = new JPanel();
		tabela.setLayout(new BoxLayout(tabela, BoxLayout.Y_AXIS));
		tabela.add(new JLabel("                                                             LISTA PESSOAS"));

		String[] colunas = { "Nome", "Telefone", "E-Mail", "Endereço", "Bairro", "CEP", "Cidade", "UF" };
		modelo_tabela = new DefaultTableModel(colunas, 0);
		List<Pessoa> lista = new ArrayList<>();
		lista = pessoaDao.listarPessoas();
		for (Pessoa pessoas : lista) {

			modelo_tabela.addRow(new Object[] { pessoas.getNomePes(), pessoas.getTelPes(), pessoas.getEmailPes(),
					pessoas.getEndPes(), pessoas.getBaiPes(), pessoas.getBaiPes(), pessoas.getCepPes(),
					pessoas.getCidPes(), pessoas.getUfPes() });

		}

		tabela_pessoas = new JTable(modelo_tabela);
		JScrollPane barraRolagem = new JScrollPane(tabela_pessoas);
		tabela.add(barraRolagem);
		tabela.add(Box.createRigidArea(new Dimension(10, 10)));
		atributosConsultados.add(tabela);

	}

}