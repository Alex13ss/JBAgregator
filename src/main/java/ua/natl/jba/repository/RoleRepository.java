package ua.natl.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.natl.jba.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String string);

}
