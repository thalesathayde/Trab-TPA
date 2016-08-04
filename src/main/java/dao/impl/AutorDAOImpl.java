package dao.impl;

import java.util.List;

import dao.AutorDAO;
import modelo.Autor;

public abstract class AutorDAOImpl
       extends JPADaoGenerico<Autor, Long> implements AutorDAO 
{   
    public AutorDAOImpl()
    { 	super(Autor.class); 
    }
    
	@SuppressWarnings("unchecked")
	final public List<Autor> recuperaPeloNome(String nome, 
            							  int deslocamento, 
            							  int linhasPorPagina)
	{	
		List<Autor> autores = em
			.createQuery("select a from Autor a "
					   + "where a.nome like :UPPER(nome) order by a.nome asc")
			.setParameter("nome", nome.toUpperCase())
			.setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return autores;
	}
	
	final public long recuperaQtdPeloNome(String nome) 
	{	
		long qtd = (Long) em.createQuery("select count(a) from Autor a where a.nome like :nome")
						    .setParameter("nome", nome.toUpperCase())
							.getSingleResult();
		return qtd;
	}
}