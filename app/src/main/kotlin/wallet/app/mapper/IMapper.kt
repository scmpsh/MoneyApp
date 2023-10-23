package wallet.app.mapper

interface IMapper<E, D> {
    fun toDto(entity: E): D
    fun fromDto(dto: D): E
}