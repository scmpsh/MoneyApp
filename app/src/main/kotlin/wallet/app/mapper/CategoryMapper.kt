package wallet.app.mapper

import org.springframework.stereotype.Component
import wallet.app.dto.CategoryDto
import wallet.app.entity.dictionary.Category

@Component
class CategoryMapper: IMapper<Category, CategoryDto> {
    override fun toDto(entity: Category): CategoryDto {
        return CategoryDto(
            entity.code.uppercase(),
            entity.name.uppercase()
        )
    }

    override fun fromDto(dto: CategoryDto): Category {
        return Category(
            dto.code.uppercase(),
            dto.name.uppercase()
        )
    }

}