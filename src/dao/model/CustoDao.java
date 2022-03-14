package dao.model;

import java.util.List;

import entities.Custo;

public interface CustoDao {
	public void inserir(Custo obj);

	public void atualizar(Custo obj);

	public void deletar(Custo obj);

	Custo buscar(String numero);

	List<Custo> listar();
	
	
}
