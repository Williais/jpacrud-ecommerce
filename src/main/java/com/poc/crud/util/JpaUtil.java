package com.poc.crud.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("jpa-app-pu");
    }
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
