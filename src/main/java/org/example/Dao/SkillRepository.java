package org.example.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@EnableJpaRepositories
public interface SkillRepository extends JpaRepository<SkillEntity ,Long> {

//    @Query(value = "SELECT skill_name FROM skill t1 INNER JOIN employee_skill t2 ON t1.id = t2.skill_id WHERE t2.skill_id = ?1 ")
//    List<String> findAllByEmployeeId(long empId);

    @Query(value = "SELECT skill_name FROM skill WHERE id = ?1 " , nativeQuery=true)
    String getSkillNameBySkillId(long skillId);
    @Query(value = "SELECT skill_id FROM employee_skill WHERE employee_entity_id = ?1 ", nativeQuery=true)
    List<Long> getSkillByEmployeeId(long empId);
}
