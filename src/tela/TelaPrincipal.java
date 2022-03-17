package tela;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.model.DaoFactory;
import dao.model.TribunalDao;
import dao.model.VaraDao;
import tela.audiencia.TeladeCadastroAudi;
import tela.custo.TeladeCadastroCusto;
import tela.pessoa.TeladeCadastroPes;
import tela.processo.TelaDeCadastroPro;
import tela.tribunal.TeladeCadastroTrib;
import tela.vara.TeladeCadastroVara;

public class TelaPrincipal extends JFrame {
	// atributos

	TribunalDao tribunalDao = DaoFactory.criarTribunal();
	VaraDao varaDao = DaoFactory.criarVara();

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

	JButton pessoa = new JButton("MANTER PESSOA     "), processo = new JButton("MANTER PROCESSO"),
			tribunal = new JButton("MANTER TRIBUNAL   "), vara = new JButton(" MANTER VARA          "),
			limpar = new JButton("LIMPAR"), cadastrados = new JButton("CONSULTA DOS PROCESSOS"),
			audiencia = new JButton("MANTER AUDIENCIAS"), custo = new JButton("MANTER CUSTOS       ");

	JTextField bDesVara, bDesTribunal, BExclusao;

	public TelaPrincipal() {
		super("Tela Principal");
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
		divisao1.add("Center", divisaoOeste);
		divisaoOeste.add("North", caixas);
		divisaoOeste.add("Center", divisaoEspecificacoes);
		divisao1.add("North", textoCadastro);
		caixas.add(botoes);
	}

	public void inserirBotoes() {
		caixas.add(pessoa);
		caixas.add(new JLabel("    "));
		caixas.add(processo);
		caixas.add(new JLabel(" "));
		caixas.add(vara);
		caixas.add(new JLabel(" "));
		caixas.add(tribunal);
		caixas.add(new JLabel(" "));
		caixas.add(audiencia);
		caixas.add(new JLabel(" "));
		caixas.add(custo);

	}

	public void acaoBotoes() {
		ActionListenerTelaPrincipal albotoes = new ActionListenerTelaPrincipal();
		pessoa.addActionListener(albotoes);
		processo.addActionListener(albotoes);
		vara.addActionListener(albotoes);
		tribunal.addActionListener(albotoes);
		audiencia.addActionListener(albotoes);
		custo.addActionListener(albotoes);
	}

	private class ActionListenerTelaPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == pessoa) {
				TeladeCadastroPes tela = new TeladeCadastroPes();
			}

			if (e.getSource() == processo) {
				TelaDeCadastroPro tela = new TelaDeCadastroPro();
			}

			if (e.getSource() == vara)

			{
				TeladeCadastroVara tela = new TeladeCadastroVara();
			}
			if (e.getSource() == tribunal) {
				TeladeCadastroTrib tela = new TeladeCadastroTrib();
			}
			if (e.getSource() == audiencia) {
				TeladeCadastroAudi audi = new TeladeCadastroAudi();
			}
			if(e.getSource() == custo) {
				TeladeCadastroCusto tela = new TeladeCadastroCusto();
			}
		}

	}

}
