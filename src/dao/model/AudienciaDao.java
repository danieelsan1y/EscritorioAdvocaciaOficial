package dao.model;

import java.util.List;

import entities.Audiencia;

public interface AudienciaDao {
	public void inserir (Audiencia obj); 
	public void atualizar (Audiencia obj);
	public void deletar (Audiencia obj);
	Audiencia buscar (String numero);
	List<Audiencia> listar();
}
