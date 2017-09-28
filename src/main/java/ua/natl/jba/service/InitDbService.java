package ua.natl.jba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.natl.jba.entity.Blog;
import ua.natl.jba.entity.Role;
import ua.natl.jba.entity.User;
import ua.natl.jba.repository.BlogRepository;
import ua.natl.jba.repository.ItemRepository;
import ua.natl.jba.repository.RoleRepository;
import ua.natl.jba.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class InitDbService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleRepository.save(roleUser);

            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleRepository.save(roleAdmin);

            User userAdmin = new User();
            userAdmin.setEnabled(true);
            userAdmin.setName("admin");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userAdmin.setPassword(encoder.encode("admin"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleAdmin);
            roles.add(roleUser);
            userAdmin.setRoles(roles);
            userRepository.save(userAdmin);

            Blog blogWestern = new Blog();
            blogWestern.setName("Western's blog");
            blogWestern
                    .setUrl("http://feeds.feedburner.com/javavids?format=xml");
            blogWestern.setUser(userAdmin);
            blogRepository.save(blogWestern);

            // Item item1 = new Item();
            // item1.setBlog(blogWestern);
            // item1.setTitle("First");
            // item1.setLink("http://www.alexblog.com");
            // item1.setPublishedDate(new Date());
            // itemRepository.save(item1);
            //
            // Item item2 = new Item();
            // item2.setBlog(blogWestern);
            // item2.setTitle("Second");
            // item2.setLink("http://www.alexblog.com");
            // item2.setPublishedDate(new Date());
            // itemRepository.save(item2);
        }
    }

}
