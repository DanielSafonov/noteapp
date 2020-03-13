package com.safonov.demo.application.model.repository;

import com.safonov.demo.application.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.HashSet;
import java.util.Set;

/**
 * Реализация примитивного репозитория с помощью Entity Manager
 */
@Component
@Slf4j
public class UserSimpleRepository {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public User save(User user){
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            log.error("UserSimpleRepository.save(): " + e.getMessage(), e);
            entityManager.getTransaction().rollback();
        } finally {
            if(entityManager != null && entityManager.isOpen()){
                entityManager.close();
            }
        }
        return user;
    }

    public Long getCount(){
        Long count = null;
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            count = entityManager.createQuery("select count(u) from User u", Long.class).getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e){
            log.error("UserSimpleRepository.getCount(): " + e.getMessage(), e);
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return count;
    }

    public User findById(Long id){
        User user = null;
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            user = entityManager.find(User.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            log.error("UserSimpleRepository.getById(): " + e.getMessage(), e);
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return user;
    }

    public void delete(User user){
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            user = entityManager.merge(user);
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            log.error("UserSimpleRepository.delete(): " + e.getMessage(), e);
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public Set<User> findAll(){
        Set<User> users = null;
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            users = new HashSet<>(
                    entityManager.createQuery("select u from User u", User.class)
                            .getResultList()
            );
            entityManager.getTransaction().commit();
        } catch (Exception e){
            log.error("UserSimpleRepository.getById(): " + e.getMessage(), e);
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return users;
    }
}
