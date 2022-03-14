package dao.model;

import java.util.List;

import entities.PessoaJuridica;



public interface PessoaJuridicaDao {
	public void inserir(PessoaJuridica obj);

	public void atualizar(PessoaJuridica obj);

	public void deletar(PessoaJuridica obj);

	PessoaJuridica buscar(String cnpj);

	List<PessoaJuridica> listar();
	
	public void listaDeProcessosPJAutor (PessoaJuridica obj);
	
	public void listaDeProcessosPJReu ();
}
