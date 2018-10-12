package com.guidosit.gamexchange.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(Integer id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException());
    }

    public Category saveCategory(Category category) {
        category.setName(category.getName().toLowerCase());
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName.toLowerCase());
    }
}
