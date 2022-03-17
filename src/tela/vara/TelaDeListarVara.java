package tela.vara;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.model.DaoFactory;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import dao.model.VaraDao;
import entities.Pessoa;
import entities.PessoaJuridica;
import entities.Processo;
import entities.Vara;

public class TelaDeListarVara extends JFrame {
	PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
	PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
	VaraDao varaDao = DaoFactory.criarVara();

	JPanel divisaoPrincipal = new JPanel();
	JPanel divisaoCentro = new JPanel();
	JPanel sairTela = new JPanel();
	JPanel texto = new JPanel();
	JPanel atributos = new JPanel();
	JPanel atributosConsultados = new JPanel();
	JTextField barraConsulta;
	JButton consultar = new JButton("CONSULTAR"), sair = new JButton("SAIR");

	public TelaDeListarVara() {
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
		divisaoPrincipal.add("Center", atributosConsultados);
		divisaoPrincipal.add("South", sairTela);

	}

	public void consultarPessoa() {
		// consulta da pessoa
		int tamanho = 0;

		texto.add(new JLabel("Consultar Vara"));
		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o nome da Vara:   "));
		barraConsulta = new JTextField(14);
		consulta.add(barraConsulta);
		consulta.add(consultar);
		atributos.add(consulta);

	}

	public void ResultadoProcessos(Vara obj) {

		JTable tabela_pessoas;
		DefaultTableModel modelo_tabela;

		JPanel tabela = new JPanel();
		tabela.setLayout(new BoxLayout(tabela, BoxLayout.Y_AXIS));

		JPanel textoVara = new JPanel();
		textoVara.add(new JLabel(obj.getDesVara()));
		textoVara.add(new JLabel(" do "));
		textoVara.add(new JLabel(obj.getTribunal().getDesTrib()));
		atributos.add(textoVara);
		String[] colunas = { "Número", "Data de Abertura", "Data de Conclusão", "Situação", "Autor", "Réu" };
		modelo_tabela = new DefaultTableModel(colunas, 0);

		List<Processo> lista = new ArrayList<>();
		varaDao.listaDeProcessos(obj);
		lista = obj.getProcessos();
		for (Processo processo : lista) {

			modelo_tabela.addRow(new Object[] { processo.getNroProcesso(), processo.getDataAbertura(),
					processo.getDataConclusao(), processo.getSitucacao(), processo.getPessoaAutor().getNomePes(),
					processo.getPessoaReu().getNomePes() });
		}

		tabela_pessoas = new JTable(modelo_tabela);
		JScrollPane barraRolagem = new JScrollPane(tabela_pessoas);
		tabela.add(barraRolagem);
		tabela.add(Box.createRigidArea(new Dimension(10, 10)));
		atributosConsultados.add(tabela);

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
				if (barraConsulta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo em branco!", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Vara obj = varaDao.buscar(barraConsulta.getText());
					if (obj == null) {
						JOptionPane.showMessageDialog(null, "Vara não foi encontrada!", "", JOptionPane.INFORMATION_MESSAGE);
					}

					else {
						ResultadoProcessos(obj);
						JOptionPane.showMessageDialog(null, "Vara consultada com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
			if (e.getSource() == sair) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		}
	}
}