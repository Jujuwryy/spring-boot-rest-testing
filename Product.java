
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
                            //Lombok did not function properly so I manually implemented the constructors/get,set
@Entity
@Table(name = "product_record")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NonNull
    private String name;

    @NonNull
    private String description;  // Changed from summary to description for a product

    private int price;  // Adjusted to reflect product price instead of rating

    public Product(long productId, @NonNull String name, @NonNull String description, int price) {
        super();
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {
        // Default constructor
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", price=" + price + "]";
    }
}
