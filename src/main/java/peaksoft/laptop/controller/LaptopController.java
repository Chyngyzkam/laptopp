package peaksoft.laptop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.laptop.entity.Laptop;
import peaksoft.laptop.repository.UserRepository;
import peaksoft.laptop.security.User;
import peaksoft.laptop.service.LaptopService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/laptops")
public class LaptopController {
    private final LaptopService laptopService;

    @Autowired
    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/save")
    public void addLaptop(@RequestBody Laptop laptop) {
        laptopService.addLaptop(laptop);
    }

    @GetMapping("/{laptopId}")
    public Laptop getById(@PathVariable("laptopId") Long id) {
        return laptopService.getById(id);
    }

    @GetMapping
    public List<Laptop> getAllLaptops() {
        List<Laptop> laptops = laptopService.getLaptops();
        return laptops;
    }

    @DeleteMapping("/delete/{laptopId}")
    public void deleteById(@PathVariable("laptopId") Long id) {
        laptopService.deleteById(id);
    }
    @GetMapping("/users")
    public List<User> getAlllUsers(){
        return laptopService.getAllUsers();
    }
    @PostMapping("/save/user")
    public void addUser(@RequestBody User user){
        laptopService.addUser(user);
    }
}
