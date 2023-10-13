package com.excelr.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.entity.Employee;
import com.excelr.exception.IdNotFoundException;

import com.excelr.repository.EmployeeRepository;
import com.excelr.utility.AppConstant;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	
	@Override
	public Employee addEmployee(Employee employee) {
		
		
		
	    Employee employee1=employeeRepository.save(employee);
		return employee1;
	}

	
	@Override
	public List<Employee> getAllEmployee() {
		 
		return employeeRepository.findAll();
	}
	
	

	@Override
	public Employee getEmployeeById(int id) {
	   Optional<Employee> optionalEmployee =  employeeRepository.findById(id);
	  Employee employee=null;
	  
	  if(optionalEmployee.isPresent())
	   {
		  employee= optionalEmployee.get();
	   }
	  else
	  {
		  throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
	  }
		
		return employee;
	}

	
	
	@Override
	public String deleteEmployee(int id) {
		String msg="";
		   if(employeeRepository.existsById(id))
		   {
			   employeeRepository.deleteById(id);
			   msg="employee deleted";
		   }
		   else
		   {
			   throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
		   }
		
		return msg;
	}

	
	
	@Override
	public String updateEmployee(int id, Employee employee) {
		String msg="";
		   if(employeeRepository.existsById(id))
		   {
			    Employee employee2 = employeeRepository.findById(id).get();
			    employee2.setName(employee.getName());
			    employee2.setDept(employee.getDept());
			    employee2.setSalary(employee.getSalary());
			    
			    employeeRepository.save(employee2);
			    msg="employee successfull updated";
			    
		   }
		   else
		   {
			   throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
		   }
		return msg;
	}

	

}
