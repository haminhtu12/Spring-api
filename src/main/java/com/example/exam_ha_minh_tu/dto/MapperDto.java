package com.example.exam_ha_minh_tu.dto;

import com.example.exam_ha_minh_tu.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MapperDto {
    Employee convertToEntity(EmployeeDto userDto);
    EmployeeDto convertToDto(Employee userEntity);

}
