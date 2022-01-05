package peaksoft.laptop.service;

import peaksoft.laptop.entity.Laptop;
import peaksoft.laptop.security.User;

import java.util.List;

public interface LaptopService {
    void addLaptop(Laptop laptop);
    Laptop getById(Long id);
    List<Laptop> getLaptops();
    void deleteById(Long id);

    List<User> getAllUsers();
    void addUser(User user);

}
