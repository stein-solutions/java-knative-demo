package solutions.stein.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.stein.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}