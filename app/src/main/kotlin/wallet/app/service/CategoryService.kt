package wallet.app.service

import org.springframework.stereotype.Service
import wallet.app.dto.CategoryDto
import wallet.app.entity.dictionary.Category
import wallet.app.mapper.IMapper
import wallet.app.repository.CategoryRepository

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val mapper: IMapper<Category, CategoryDto>
) {

    fun saveCategory(categoryDto: CategoryDto): Category {
        return categoryRepository.save(mapper.fromDto(categoryDto))
    }
}