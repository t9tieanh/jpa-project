package com.jpa.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("persistence-data");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
