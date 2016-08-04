package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Livro;

public interface LivroDAO extends DaoGenerico<Livro, Long>
{	
	
	@RecuperaObjeto
	Livro recuperaUmLivro(long numero) 
		throws ObjetoNaoEncontradoException; 
	
	@RecuperaLista
	List<Livro> recuperaLivros();
	
	@RecuperaObjeto
	long recuperaQtdPeloTitulo(String nome);
	
	@RecuperaLista
	List<Livro> recuperaPeloTitulo(String nome, 
         						   int deslocamento, 
            					   int linhasPorPagina);
}