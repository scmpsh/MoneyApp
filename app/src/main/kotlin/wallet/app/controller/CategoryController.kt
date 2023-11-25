package wallet.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wallet.app.dto.CategoryDto
import wallet.app.entity.dictionary.Category
import wallet.app.service.CategoryService

@RestController
@RequestMapping("/category")
class CategoryController(
    private val categoryService: CategoryService
) {

    @PostMapping("/save")
    fun saveCategory(categoryDto: CategoryDto): ResponseEntity<Category> {
        return ResponseEntity.ok( categoryService.saveCategory(categoryDto))
    }

    @GetMapping("/show")
    fun showCategory(): List<Category> {
        return categoryService.showCategories()
    }
}