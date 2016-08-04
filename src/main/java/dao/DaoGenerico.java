package dao;

import org.springframework.transaction.annotation.Transactional;

import excecao.ObjetoNaoEncontradoException;

/**
 * A interface GenericDao b�sica com os m�todos CRUD. Os m�todos
 * de busca s�o adicionados por heran�a de interface.
 */
public interface DaoGenerico<T, PK>
{
	@Transactional
	T inclui(T obj);

	@Transactional
    void altera(T obj);

	@Transactional
    void exclui(T obj);

    T getPorId(PK id) throws ObjetoNaoEncontradoException;

    T getPorIdComLock(PK id) throws ObjetoNaoEncontradoException;
}
