package ru.geekbrains.controller;

import ru.geekbrains.persist.*;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        categories = categoryRepository.findAll();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String createCategory() {
        this.category = new Category();
        return "/category_form.xhtml?faces-redirect-true";
    }

    public List<Category> getAllCategory() {
        return categories;
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect-true";
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteById(category.getId());
    }

    public String saveCategory() {
        categoryRepository.saveOrUpdate(category);
        return "/category.xhtml?faces-redirect-true";
    }
}
