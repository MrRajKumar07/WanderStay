package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.UserDTO;
import com.l2p.WanderStay.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userId", source = "id")
    UserDTO toDTO(User user);
}