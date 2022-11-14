package br.com.fintech.dao;

import java.util.List;

public interface DefaultDAO {
	/**
	 * Insere no banco de dados 
	 * @param user O objeto do contexto em questão
	 */
	public void insert(Object obj);
	
	/**
	 * Atualiza um registro no banco de dados
	 * @param user O objeto do contexto em questão
	 */
	public void update(Object obj);
	
	/**
	 * Deleta um registro no banco de dados
	 * @param id O codigo identificador do objeto
	 */
	public void delete(int id);
	
	/**
	 * Seleciona todos os registros no banco de dados ordenado crescentemente por id, instancia um objeto para cada registro e armazena em uma List
	 * @return Uma lista de objetos 
	 */
	public List selectAll();
	
	/**
	 * Seleciona um registro específico no banco de dados
	 * @param id O codigo identificador do registro/objeto
	 * @return O objeto do contexto em questão
	 */
	public Object selectById(int id);	

}
