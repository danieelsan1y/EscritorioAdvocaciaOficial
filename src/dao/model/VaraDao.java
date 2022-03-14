package dao.model;

import java.util.List;

import entities.Vara;

public interface VaraDao {
	public void inserir (Vara obj);

	public void atualizar(Vara obj);

	public void deletar(Vara obj);
	
	Vara buscar (String nome);
	
	List<Vara> listar();
	
	public void listaDeProcessos (Vara obj) ;
}
