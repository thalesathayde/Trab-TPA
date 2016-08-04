package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Autor;

public interface AutorDAO extends DaoGenerico<Autor, Long>
{	
	@RecuperaObjeto
	Autor recuperaUmAutor(long numero) 
		throws ObjetoNaoEncontradoException; 
	
	@RecuperaLista
	List<Autor> recuperaAutores();
	
	@RecuperaObjeto
	long recuperaQtdPeloNome(String nome);
	
	@RecuperaLista
	List<Autor> recuperaPeloNome(String nome, 
         						   int deslocamento, 
            					   int linhasPorPagina);
}