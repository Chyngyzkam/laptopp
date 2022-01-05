package peaksoft.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.laptop.entity.Laptop;
import peaksoft.laptop.security.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select a from User a where a.username =?1")
     Optional<User> getApplicationByUserName(String username);
}
