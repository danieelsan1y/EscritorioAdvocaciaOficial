package tela.pessoa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.model.DaoFactory;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import entities.PessoaFisica;
import entities.PessoaJuridica;

public class TelaDeExcluirPes extends JFrame {
	PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
	PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
	JPanel divisaoCentro = new JPanel();
	JPanel divisaoPrincipal = new JPanel();
	JPanel sairTela = new JPanel();
	JPanel texto = new JPanel();
	JPanel atributos = new JPanel();
	JTextField barraExcluir;
	JButton excluir = new JButton("EXCLUIR"), sair = new JButton("SAIR");

	public TelaDeExcluirPes() {

		getContentPane().add(divisaoCentro);
		montarTela();
		setSize(500, 300);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void montarTela() {
		definirLayouts();
		adicionarNasDivisoes();
		excluirPessoa();
		acaoBotoes();

	}

	public void definirLayouts() {
		setLayout(new BorderLayout());
		divisaoCentro.setLayout(new BorderLayout());
		divisaoPrincipal.setLayout(new BorderLayout());
		atributos.setLayout(new BoxLayout(atributos, BoxLayout.Y_AXIS));

	}

	public void adicionarNasDivisoes() {
		add("Center", divisaoCentro);
		add("North", texto);
		divisaoCentro.add("North", divisaoPrincipal);
		divisaoPrincipal.add("North", atributos);
		divisaoPrincipal.add("South", sairTela);

	}

	public void excluirPessoa() {

		texto.add(new JLabel("Excluir Pessoa"));

		JPanel consulta = new JPanel();
		consulta.add(new JLabel("Digite o CPF ou CNPJ:   "));
		barraExcluir = new JTextField(14);
		consulta.add(barraExcluir);
		consulta.add(excluir);
		atributos.add(consulta);

		sairTela.add(sair);
	}

	public void acaoBotoes() {
		ActionListenerTelaPrincipal albotoes = new ActionListenerTelaPrincipal();
		excluir.addActionListener(albotoes);

	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == excluir) {
				int tamanho = 0;
				if (barraExcluir.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo em branco!", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					tamanho = barraExcluir.getText().length();
					if (tamanho == 11) {
						PessoaFisica pes = pessoaFisicaDao.buscar(barraExcluir.getText());
						if (pes == null) {
							JOptionPane.showMessageDialog(null, "Pessoa não encontrada!", "",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							pessoaFisicaDao.deletar(pes);
							JOptionPane.showMessageDialog(null, "Pessoa excluida com sucesso!", "",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						if (tamanho == 14) {
							PessoaJuridica pes = pessoaJuridicaDao.buscar(barraExcluir.getText());
							if (pes == null) {
								JOptionPane.showMessageDialog(null, "Pessoa não encontrada!", "",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								pessoaJuridicaDao.deletar(pes);
								JOptionPane.showMessageDialog(null, "Pessoa excluida com sucesso!", "",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Tamanho inválido!", "",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}

				}

			}
		}
	}
}