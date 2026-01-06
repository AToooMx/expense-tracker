package com.rsa.expense.tracker.mapper;

import com.rsa.expense.tracker.dto.UserDto;
import com.rsa.expense.tracker.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

}
