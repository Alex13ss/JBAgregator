package ua.natl.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.natl.jba.entity.Blog;
import ua.natl.jba.entity.User;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findByUser(User user);

}
