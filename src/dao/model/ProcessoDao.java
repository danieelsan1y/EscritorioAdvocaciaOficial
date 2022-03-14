package dao.model;

import java.util.List;

import entities.Custo;
import entities.Processo;

public interface ProcessoDao {
	public void inserir (Processo obj);

	public void atualizar(Processo obj);

	public void deletar(Processo obj);

	Processo buscar(String numeroProcesso);

	List<Processo> listar();
	
	public void listaDeCustos (Processo obj);
	
	public void listaDeAudiencias (Processo obj);
}
