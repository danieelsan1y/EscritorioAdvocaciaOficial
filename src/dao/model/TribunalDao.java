package dao.model;

import java.util.List;

import entities.Tribunal;
import entities.Vara;

public interface TribunalDao {
	public void inserir(Tribunal obj);

	public void atualizar(Tribunal obj);

	public void deletar(Tribunal obj);

	Tribunal buscar(String descricao);

	List<Tribunal> listar();
	
	public void listaDeVaras(Tribunal obj);

}
