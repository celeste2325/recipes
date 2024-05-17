package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Category;
import com.distribuidas.recipe.repository.CategoryRepository;
import com.distribuidas.recipe.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoryByPartialName(String categoryPartialName) {
        if (!Objects.equals(categoryPartialName, "")) {
            return this.categoryRepository.findByDescriptionLikeIgnoreCase(categoryPartialName + "%");
        }
        return null;
    }
}
