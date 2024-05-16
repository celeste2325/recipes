package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getCategories();

    List<Category> getCategoryByPartialName(String categoryPartialName);
}
