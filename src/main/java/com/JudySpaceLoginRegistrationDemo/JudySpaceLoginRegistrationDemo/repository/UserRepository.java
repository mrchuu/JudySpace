package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("Select u from Users u where u.userName = :userName")
    Optional<Users> findByUserName(@Param("userName") String userName);

    @Query("Select u from Users u where u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);
    @Query("Select u from Users u where u.email = :email")
    Users findByEmailNotOptional(@Param("email") String email);
}
