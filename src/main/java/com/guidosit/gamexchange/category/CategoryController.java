package com.guidosit.gamexchange.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getCategories(){
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        List<Category> categories = categoryService.getCategories();
        for (Category c: categories) {
            categoryResponseList.add(CategoryResponse.returnCategoryWithoutGames(c));
        }
        return categoryResponseList;
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable Integer id) throws CategoryNotFoundException {
        return CategoryResponse.returnCategory(categoryService.getCategory(id));
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category ) {
        return categoryService.saveCategory(category);
    }
}
