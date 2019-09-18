package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JpaUtil;

public class DaoGeneric <ClasseGenerica> {
	public void salvar(ClasseGenerica entidade) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(entidade);
		entityTransaction.commit();
		entityManager.close();
	}
	
	//Merge salva ou atualiza o objeto, retornando o mesmo com os dados salvos em banco 
	public ClasseGenerica merge(ClasseGenerica entidade) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ClasseGenerica retorno = entityManager.merge(entidade);
		entityTransaction.commit();
		entityManager.close();
		
		return retorno;
	}
	
	public void deletar(ClasseGenerica entidade) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(entidade);
		entityTransaction.commit();
		entityManager.close();
	}
	
	public void deletePorId(ClasseGenerica entidade) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		//recebe o id enccontrado no getPrimaryKey
		Object id = JpaUtil.getPrimaryKey(entidade);
		//cria uma query para remover o objeto com base no ID encontrado por getPrimaryKey(entidade)
		entityManager.createQuery("delete from "+ entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		//executeUpdate() faz tanto delete quanto update
		entityTransaction.commit();
		entityManager.close();
	}
	
	//Lista Usuarios
	public List <ClasseGenerica> getListEntity(Class<ClasseGenerica> entidade){
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<ClasseGenerica> retorno = entityManager.createQuery("from "+ entidade.getName()).getResultList();
		entityTransaction.commit();
		entityManager.close();
		return retorno;
	}
	
}
