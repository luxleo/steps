package com.steps.util;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("test")
@Component
public class DBCleaner {
    private static final String INTEGRITY_FALSE = "SET REFERENTIAL_INTEGRITY FALSE";
    private static final String INTEGRITY_TRUE = "SET REFERENTIAL_INTEGRITY TRUE";
    private static final String TRUNCATE = "TRUNCATE TABLE %s";
    private static final String CAMEL_CASE = "([a-z])([A-Z])";
    private static final String SNAKE_CASE = "$1_$2";

    @PersistenceContext
    private EntityManager em;
    private List<String> tables;

    @PostConstruct
    public void findTables() {
        tables = em.getMetamodel().getEntities().stream()
                .filter(e -> e.getJavaType().getAnnotation(Entity.class) != null)
                .map(this::convertCamelToSnake)
                .toList();
    }

    @Transactional
    public void clean() {
        em.clear();
        em.createNativeQuery(INTEGRITY_FALSE).executeUpdate();

        for(String table : tables) {
            truncateTable(table);
        }
        em.createNativeQuery(INTEGRITY_TRUE).executeUpdate();
    }

    private void truncateTable(String table) {
        em.createNativeQuery(String.format(TRUNCATE, table)).executeUpdate();
    }

    private String convertCamelToSnake(EntityType<?> e) {
        return e.getName()
                .replaceAll(CAMEL_CASE, SNAKE_CASE)
                .toLowerCase();
    }
}
