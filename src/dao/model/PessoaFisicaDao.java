package dao.model;

import java.util.List;

import entities.PessoaFisica;

public interface PessoaFisicaDao {
	public void inserir(PessoaFisica pes);

	public void atualizar(PessoaFisica pes);

	public void deletar(PessoaFisica obj);

	PessoaFisica buscar(String cpf);

	List<PessoaFisica> listar();
	
	public void listarProcessosPF (PessoaFisica obj) ;
	
}
