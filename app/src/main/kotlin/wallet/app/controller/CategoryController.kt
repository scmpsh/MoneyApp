package wallet.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import wallet.app.dto.CategoryDto
import wallet.app.entity.dictionary.Category
import wallet.app.service.CategoryService

@RestController
class CategoryController(
    private val categoryService: CategoryService
) {

    @PostMapping("/category/save")
    fun saveCategory(categoryDto: CategoryDto): ResponseEntity<Category> {
        return ResponseEntity.ok( categoryService.saveCategory(categoryDto))
    }

    @GetMapping("/category/show")
    fun showCategory() {

    }
}