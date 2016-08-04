package servico;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.LivroDAO;
import excecao.LivroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Livro;

//@Service
public class LivroService
{	
	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}

	
	private LivroDAO livroDAO;
	
	@Transactional
	public long inclui(Livro umLivro) 
	{	return livroDAO.inclui(umLivro).getNumero();
	}

	@Transactional
	public void altera(Livro umLivro)
		throws LivroNaoEncontradoException
	{	try
		{	livroDAO.getPorIdComLock(umLivro.getNumero()); 
			livroDAO.altera(umLivro);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new LivroNaoEncontradoException("Livro não encontrado");
		}
	}

	@Transactional
	public void exclui(Livro umLivro) 
		throws LivroNaoEncontradoException
	{	try
		{	Livro livro = livroDAO.recuperaUmLivro(umLivro.getNumero());
			livroDAO.exclui(livro);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new LivroNaoEncontradoException("Livro não encontrado");
		}
	}

	public Livro recuperaUmLivro(long numero) 
		throws LivroNaoEncontradoException
	{	try
		{	return livroDAO.recuperaUmLivro(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new LivroNaoEncontradoException("Livro não encontrado");
		}
	}

	public List<Livro> recuperaLivros()
	{	return livroDAO.recuperaLivros();
	}

	public long recuperaQtdPeloTitulo(String nome) 
	{	
		return livroDAO.recuperaQtdPeloTitulo(nome + "%");
	}
	
	public List<Livro> recuperaPeloTitulo(String nome, int deslocamento, int linhasPorPagina) 
	{	
		List<Livro> livros = livroDAO.recuperaPeloTitulo(nome + "%", deslocamento, linhasPorPagina);

		return livros;
	}
}