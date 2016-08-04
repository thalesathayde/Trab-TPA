package servico;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import dao.AutorDAO;
import excecao.AutorNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Autor;



//@Service
public class AutorService
{	
	
	public void setAutorDAO(AutorDAO autorDAO) {
		this.autorDAO = autorDAO;
	}

	private AutorDAO autorDAO;
	

	public long inclui(Autor umAutor) 
	{	return autorDAO.inclui(umAutor).getNumero();
	}


	public void altera(Autor umAutor)
		throws AutorNaoEncontradoException
	{	try
		{	autorDAO.getPorIdComLock(umAutor.getNumero());
			autorDAO.altera(umAutor);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new AutorNaoEncontradoException("Autor não encontrado");
		}
	}

	@Transactional
	public void exclui(Autor umAutor) 
		throws AutorNaoEncontradoException
	{	try
		{	Autor autor = autorDAO.recuperaUmAutor(umAutor.getNumero());
			autorDAO.exclui(autor);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new AutorNaoEncontradoException("Autor não encontrado");
		}
	}

	public Autor recuperaUmAutor(long numero) 
		throws AutorNaoEncontradoException
	{	try
		{	return autorDAO.recuperaUmAutor(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new AutorNaoEncontradoException("Autor não encontrado");
		}
	}

	public List<Autor> recuperaAutor()
	{	return autorDAO.recuperaAutores();
	}

	public long recuperaQtdPeloNome(String nome) 
	{	
		return autorDAO.recuperaQtdPeloNome(nome + "%");
	}
	
	public List<Autor> recuperaPeloNome(String nome, int deslocamento, int linhasPorPagina) 
	{	
		List<Autor> autores = autorDAO.recuperaPeloNome(nome + "%", deslocamento, linhasPorPagina);

		return autores;
	}
}