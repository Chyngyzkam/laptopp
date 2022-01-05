package peaksoft.laptop.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.laptop.entity.Laptop;
import peaksoft.laptop.repository.LaptopRepository;
import peaksoft.laptop.repository.UserRepository;
import peaksoft.laptop.security.User;
import peaksoft.laptop.service.LaptopService;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.laptopRepository = laptopRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addLaptop(Laptop laptop) {
        laptopRepository.save(laptop);
    }

    @Override
    public Laptop getById(Long id) {
        return laptopRepository.findById(id)
                .orElseThrow(()->new RuntimeException(
                        "not found "+id +" id"
                ));
    }

    @Override
    public List<Laptop> getLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        laptopRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void addUser(User user) {
        Optional<User> optionaluser =
                userRepository.getApplicationByUserName(user.getUsername());
        if (optionaluser.isPresent()) {
            throw new LaptopExcepton(
                    String.format("User with usename" + user.getUsername() + "has already exest")
            );
        }
        String password = user.getPassword();
        password = passwordEncoder.encode(password);
        user.setPassword(password);
        userRepository.save(user);

    }
}
