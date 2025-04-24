package com.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.scm.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User,String> {
    //extra methods db relatedopeerations
    //custom query methods
    //custom finder methods
    
    // Optional<User> findByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByeEmailAndPassword(String email,String password);

    Optional<User> findByEmailTOken(String id);

}
