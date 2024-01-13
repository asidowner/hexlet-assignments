package exercise.specification;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

// BEGIN
@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withTitleContains(params.getTitleCont())
                .and(withCategoryId(params.getCategoryId()))
                .and(withPriceAtLt(params.getPriceLt()))
                .and(withPriceAtGt(params.getPriceGt()))
                .and(withRatingAtGr(params.getRatingGt()));

    }

    private Specification<Product> withTitleContains(String titleCont) {
        return (root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.like(criteriaBuilder.upper(root.get("title")),
                        "%" + titleCont.toUpperCase() + "%");
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withPriceAtLt(Integer priceAtLt) {
        return (root, query, criteriaBuilder) -> priceAtLt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.lessThan(root.get("price"), priceAtLt);
    }

    private Specification<Product> withPriceAtGt(Integer priceAtGt) {
        return (root, query, criteriaBuilder) -> priceAtGt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("price"), priceAtGt);
    }

    private Specification<Product> withRatingAtGr(Double ratingGt) {
        return (root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("rating"), ratingGt);
    }

}
// END
