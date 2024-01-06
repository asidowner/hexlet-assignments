package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findAllByPriceGreaterThanEqual(Integer minPrice, Sort sort);
    List<Product> findAllByPriceLessThanEqual(Integer maxPrice, Sort sort);
    List<Product> findAllByPriceBetween(Integer minPrice, Integer maxPrice, Sort sort);
    // END
}
