package solutions.stein.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.stein.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}