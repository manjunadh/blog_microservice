package se.callista.microservices.composite.product.model;

import se.callista.microservices.model.Product;
import se.callista.microservices.model.Recommendation;
import se.callista.microservices.model.Review;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by magnus on 04/03/15.
 */
public class ProductAggregated {
    private int productId;
    private String name;
    private String description;
    private List<RecommendationSummary> recommendations;
    private List<ReviewSummary> reviews;

    public ProductAggregated(Product product, List<Recommendation> recommendations, List<Review> reviews) {

        // 1. Setup product info
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getName();

        // 2. Copy summary recommendation info, if available
        if (recommendations != null)
            this.recommendations = recommendations.stream()
                .map(r -> new RecommendationSummary(r.getRecommendationId(), r.getAuthor(), r.getRate()))
                .collect(Collectors.toList());

        // 3. Copy summary review info, if available
        if (reviews != null)
            this.reviews = reviews.stream()
                .map(r -> new ReviewSummary(r.getReviewId(), r.getAuthor(), r.getSubject()))
                .collect(Collectors.toList());
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<RecommendationSummary> getRecommendations() {
        return recommendations;
    }

    public List<ReviewSummary> getReviews() {
        return reviews;
    }
}
