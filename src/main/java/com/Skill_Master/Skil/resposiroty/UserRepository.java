package com.Skill_Master.Skil.resposiroty;

import com.Skill_Master.Skil.Enums.UserRole;
import com.Skill_Master.Skil.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByRole(UserRole role);
    User findFirtstByEmail(String email);

    Optional<User> findByEmail(String email);


    //buscar por ficha
    @Query("SELECT u FROM User u WHERE u.ficha = :ficha")
    List<User> findByFicha(@Param("ficha") Long ficha);


}
