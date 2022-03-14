package dao.model;

import java.util.List;

import entities.Pessoa;

public interface PessoaDao {
	List<Pessoa> listarPessoas();
	public Pessoa ListarProcessosAutor (String numero); 
}
