package root.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import root.model.dto.BankBookDto;
import root.model.entity.BankBookEntity;

@Mapper(componentModel = "spring")
public interface BankBookMapper {
    @Mapping(target = "currency", source = "currency.name")
    BankBookDto mapToDto(BankBookEntity bankBookEntity);

    @Mapping(target = "currency.name", source = "currency")
    BankBookEntity mapToEntity(BankBookDto bankBookDto);
}
