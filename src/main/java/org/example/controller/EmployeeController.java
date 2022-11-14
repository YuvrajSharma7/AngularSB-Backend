package org.example.controller;


import org.example.Dao.*;
import org.example.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    EmpService empService;

    @GetMapping("/employees")
    public List<EmployeeResponseModel> getAllEmployee(){
return empService.getAllEmployee();
    }
    @PostMapping("/employees")
    public EmployeeEntity createEmployee(@RequestBody CreateEmployeeModel employee){
        EmployeeEntity employee1=new EmployeeEntity();
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmailId(employee.getEmailId());
        employee1.setDepartment(departmentRepository.findById(employee.getDepartment()).get());
        employee1.setCertificate(certificateRepository.findById(employee.getCertificate()).get());
        try {
            List<SkillEntity> skills = new ArrayList<>();
            for (Long id : employee.getSkill()) {
                skills.add(skillRepository.findById(id).get());
            }
            employee1.setSkill(skills);
        }catch (Exception e){e.printStackTrace();}
        return employeeRepository.save(employee1);    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id){

        EmployeeEntity employee= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exists with id:"+id));
        return ResponseEntity.ok(employee);
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id,@RequestBody CreateEmployeeModel employeeDetails){

        EmployeeEntity employee= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exists with id:"+id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setDepartment(departmentRepository.findById(employeeDetails.getDepartment()).get());
        employee.setCertificate(certificateRepository.findById(employeeDetails.getCertificate()).get());
        try {
            List<SkillEntity> skills = new ArrayList<>();
            for (Long id1 : employeeDetails.getSkill()) {
                skills.add(skillRepository.findById(id1).get());
            }
            employee.setSkill(skills);
        }catch (Exception e){e.printStackTrace();}
        EmployeeEntity updatedEmployee =employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        EmployeeEntity employee= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exists with id:"+id));
        employeeRepository.delete(employee);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/department")
    public List<DepartmentEntity> getAllDepartment(){
        return departmentRepository.findAll();
    }
    @GetMapping("/certificate")
    public List<CertificateEntity> getAllCertificate(){
        return certificateRepository.findAll();
    }
    @GetMapping("/skill")
    public List<SkillEntity> getAllSkill(){
        return skillRepository.findAll();
    }
    }
