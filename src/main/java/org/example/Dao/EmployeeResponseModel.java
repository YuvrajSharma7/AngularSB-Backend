package org.example.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseModel {
    private Long id;

    private String firstName;

    private String lastName;

    private String emailId;

    private DepartmentEntity department;

    private CertificateEntity certificate;

    private List<SkillEntity> skill;


    private List<String> skills;
}
