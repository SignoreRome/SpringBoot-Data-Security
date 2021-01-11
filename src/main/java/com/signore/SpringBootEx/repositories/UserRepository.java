package com.signore.SpringBootEx.repositories;

import com.signore.SpringBootEx.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
}
