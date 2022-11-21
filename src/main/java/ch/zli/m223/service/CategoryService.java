package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager categoryManager;

    @Transactional
    public Category createEntry(Category category) {
        categoryManager.persist(category);
        return category;
    }

    public List<Category> findAll() {
        var query = categoryManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }
}
