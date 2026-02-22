package sn.data_ia_fx.repository;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sn.data_ia_fx.entity.Assurance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AssuranceRepository implements IRepository<Assurance> {
    private EntityManager entityManager;

    public AssuranceRepository() {
        //Usine gestionnaire entity
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
        this.entityManager = emf.createEntityManager(); //gestionnaire entite
    }

    public void insert(Assurance assurance) {
        entityManager.getTransaction().begin(); // demarrage
        entityManager.persist(assurance);
        entityManager.getTransaction().commit(); // persister les modifications*/
    }

    public void delete(int id){
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public Assurance findById(int id){
        return  entityManager.find(Assurance.class, id);
    }

    public void update(Assurance assurance) {
        entityManager.getTransaction().begin();
        entityManager.merge(assurance);
        entityManager.getTransaction().commit();
    }

    public ObservableList<Assurance> findAll(){
       List<Assurance> assurances =  entityManager.createQuery("select a from Assurance a").getResultList();
       return FXCollections.observableArrayList(assurances);

    }


}
