package quixada.ufc.br.repository.jpa;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import quixada.ufc.br.repository.GenericRepository;


@Named
public abstract class JpaGenericRepositoryImpl<T> implements
		GenericRepository<T> {

	private static Logger logger = LoggerFactory
			.getLogger(JpaGenericRepositoryImpl.class);

	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.ufc.quixada.npi.repository.jpa.GenericRepository#setEntityManager(
	 * javax.persistence.EntityManager)
	 */
	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		logger.debug("Setting EntityManager: {} {} ", this.getClass(), em);
		this.em = em;
	}

	public enum QueryType {
		JPQL, NATIVE, NAMED
	}

	protected Class<T> persistentClass;

	/**
		 * 
		 */
	public JpaGenericRepositoryImpl() {
	}

	@Override
	@Transactional
	public void save(T entity) {
		this.em.persist(entity);
	}

	@Override
	@Transactional
	public void update(T entity) {
		this.em.merge(entity);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public T find(Object id) {
		T result = null;
		result = em.find(this.persistentClass, id);
		return result;
	}

	@Override
	public List<T> find() {
		return find(-1, -1);

	}

	@Override
	public List<T> find(int firstResult, int maxResults) {
		List<T> result = null;
		Query q = em.createQuery("select obj from "
				+ this.persistentClass.getSimpleName() + " obj");
		if (firstResult >= 0 && maxResults >= 0) {
			q = q.setFirstResult(firstResult).setMaxResults(maxResults);
		}
		result = q.getResultList();
		return result;
	}

	@Override
	public List<T> find(String queryName, Map<String, Object> namedParams) {
		return find(QueryType.NAMED, queryName, namedParams);
	}

	@Override
	public List<T> find(QueryType type, String query,
			Map<String, Object> namedParams) {
		return find(type, query, namedParams, -1, -1);
	}

	@Override
	public List<T> find(String queryName, Map<String, Object> namedParams,
			int firstResult, int maxResults) {
		return find(QueryType.NAMED, queryName, namedParams, firstResult,
				maxResults);
	}

	@Override
	public List<T> find(QueryType type, String query,
			Map<String, Object> namedParams, int firstResult, int maxResults) {
		List<T> result = null;
		Query q;
		if (type == QueryType.JPQL) {
			q = em.createQuery(query);
		} else if (type == QueryType.NATIVE) {
			q = em.createNativeQuery(query);
		} else if (type == QueryType.NAMED) {
			q = em.createNamedQuery(query);
		} else {
			throw new IllegalArgumentException("Tipo de Query inválido: "
					+ type);
		}

		if (namedParams != null) {
			Set<String> keys = namedParams.keySet();
			for (String key : keys) {
				q.setParameter(key, namedParams.get(key));
			}
		}

		if (firstResult >= 0 && maxResults >= 0) {
			q = q.setFirstResult(firstResult).setMaxResults(maxResults);
		}

		result = q.getResultList();

		return result;
	}

	@Override
	public T findFirst(String query, Map<String, Object> namedParams) {
		return findFirst(query, namedParams, -1, -1);
	}

	@Override
	public T findFirst(String query, Map<String, Object> namedParams,
			int firstResult, int maxResults) {
		return findFirst(QueryType.NAMED, query, namedParams, firstResult,
				maxResults);
	}

	@Override
	public T findFirst(QueryType type, String query,
			Map<String, Object> namedParams, int firstResult, int maxResults) {

		List<T> result = find(type, query, namedParams, firstResult, maxResults);
		return result == null || result.size() == 0 ? null : result.get(0);
	}

}
