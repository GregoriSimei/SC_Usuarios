package dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class GenericDAO<T extends EntidadeBase> {
	
	
	public void Save(T t) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();

			Session.save(t);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}


	
	public void Update(T t) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();

			Session.saveOrUpdate(t);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	public T GetById(Class<T> c, int Codigo) {
		//Iniciar nova Transa��o banco
		Transaction trans = null;

		T t = null;

		//CONTROLE DE SESS�O COM UMA SESS�O QUE VIR� DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			Session.clear();
			trans = Session.beginTransaction();

			t = Session.get(c, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return t;
	}

	public List<T> GetAll(Class<T> c) {
		Transaction trans = null;

		List<T> lista = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();


			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<T> criteryQuery = builder.createQuery(c);
			Root<T> rootEntry = criteryQuery.from(c);
			CriteriaQuery<T> all = criteryQuery.select(rootEntry);

			TypedQuery<T> allQuery = Session.createQuery(all);
			lista = allQuery.getResultList();

		} catch (Exception e) {
			trans.rollback();
		}
		return lista;
	}

	public void Delete(Class<T> c, int id) {
		Transaction trans = null;
		T t = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			t = Session.get(c, id);
			Session.remove(t);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
	}

}
