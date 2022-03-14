package tarefa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import dao.model.DaoFactory;
import dao.model.ProcessoDao;
import entities.Audiencia;
import entities.Processo;

public class ThreadAudiencia extends Thread {
	private static boolean parar = false;
	private String nomeThread;
	public int contador = 0;
	public int limite = 0;
	public String numeroProcesso;

	public ThreadAudiencia(String str, String numeroProcesso) {
		nomeThread = str;
		this.numeroProcesso = numeroProcesso;
	}

	public void run() {
		
		while(true) {
			ProcessoDao processoDao = DaoFactory.criarProcesso();
			Processo pro = processoDao.buscar(this.numeroProcesso);
			processoDao.listaDeAudiencias(pro);

			List<Audiencia> lista = pro.getAudiencias();
			Date dataAtual = new Date();
			List <Audiencia> audienciaRemover = new ArrayList<Audiencia>();
			
			
			for (Audiencia audi : lista) {
				if (dataAtual.compareTo(audi.getDataAudi()) > 0) {
					audienciaRemover.add(audi);

				}
			}
			
			lista.removeAll(audienciaRemover);
			
			lista.sort((d1,d2) -> d1.getDataAudi().compareTo(d2.getDataAudi()));

		

			JOptionPane.showMessageDialog(null, "Proxima audiência N:" + lista.get(0).getNroAudi() +" Data:" + lista.get(0).getDataAudi(), "",
					JOptionPane.INFORMATION_MESSAGE);
			try {
				sleep((new Random()).nextInt(300000));
			} catch (InterruptedException e) {
				System.out.println("problemas com o sleep()");
			}
		}
		

		/*
		 * contador = 0; System.out.println("Inicializando " + nomeThread); for (int i =
		 * 0; parar == false && i < limite; i++) { contador++;
		 * 
		 * try { sleep((new Random()).nextInt(7000)); } catch (InterruptedException e) {
		 * System.out.println("problemas com o sleep()"); }
		 * System.out.println("Somando em " + nomeThread + " = " + contador); } parar =
		 * true; System.out.println("Terminando " + nomeThread);
		 */
	}
}
