package org.example.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    SkillRepository skillRepository;


    public List<EmployeeResponseModel> getAllEmployee(){
        List<EmployeeEntity> employees=employeeRepository.findAll();
        List<EmployeeResponseModel> empList=new ArrayList<>();
        for(EmployeeEntity emp:employees){
            EmployeeResponseModel empModel=new EmployeeResponseModel();
            empModel.setId(emp.getId());
            empModel.setFirstName(emp.getFirstName());
            empModel.setLastName(emp.getLastName());
            empModel.setEmailId(emp.getEmailId());
            empModel.setDepartment(emp.getDepartment());
            empModel.setCertificate(emp.getCertificate());
            List<Long> skillIdList=skillRepository.getSkillByEmployeeId(emp.getId());
            List<String> skillList=new ArrayList<>();
            for(Long id:skillIdList){
                skillList.add(skillRepository.getSkillNameBySkillId(id));

            }
            empModel.setSkills(skillList);
            empList.add(empModel);
        }

        return empList;
    }
}
