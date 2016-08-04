package dao.impl;

import java.util.List;

import dao.LivroDAO;
import modelo.Livro;

public abstract class LivroDAOImpl
       extends JPADaoGenerico<Livro, Long> implements LivroDAO 
{   
    public LivroDAOImpl()
    { 	super(Livro.class); 
    }
    
	@SuppressWarnings("unchecked")
	final public List<Livro> recuperaPeloTitulo(String nome, 
            							  int deslocamento, 
            							  int linhasPorPagina)
	{	
		List<Livro> livros = em
			.createQuery("select l from Livro l "
					   + "where l.titulo like :titulo order by l.titulo asc")
			.setParameter("titulo", nome.toUpperCase())
			.setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return livros;
	}
	
}
