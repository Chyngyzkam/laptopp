package peaksoft.laptop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
@Getter
@Setter
@ToString
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String brand;
    String color;
    double weight;

    public Laptop() {
    }

    public Laptop(Long id, String brand, String color, int weight) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.weight = weight;
    }
}
