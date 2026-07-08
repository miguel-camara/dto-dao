package com.miguel.dto_dao.service;

import java.util.List;

import com.miguel.dto_dao.entity.User;

public interface IUserService {
  List<User> findAllUser();

  User saveUser(User user);

  // List<EmployeeDTO> getAllEmployees();
  // Optional<EmployeeDTO> getEmployeeById(Long id);
  // EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
  // EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
  // void deleteEmployee(Long id);
}
