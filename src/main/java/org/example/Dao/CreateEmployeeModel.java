package org.example.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeModel {


    private String firstName;

    private String lastName;

    private String emailId;

    private Long department;

    private Long certificate;

    private List<Long> skill;
}
