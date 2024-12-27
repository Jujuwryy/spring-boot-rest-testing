
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product") // Updated path to '/product' for consistency with tests
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping
    public List<Product> getAllProductRecords() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
	public Product getProductById(@PathVariable(value = "id") Long productId){
		return productRepo.findById(productId).get();
	}

    @PostMapping
    public Product createProductRecord(@RequestBody @Valid Product productRecord) {
        return productRepo.save(productRecord);
    }

    @PutMapping("/{id}") // Include ID in path for update
    public Product updateProductRecord(@PathVariable(value = "id") Long productId, @RequestBody @Valid Product productRecord) throws NotFoundException {

        if (productRecord == null || productRecord.getProductId() == null) {
            throw new NotFoundException();
        }

        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new NotFoundException();
        }

        Product existingProductRecord = optionalProduct.get();
        existingProductRecord.setName(productRecord.getName());
        existingProductRecord.setDescription(productRecord.getDescription());
        existingProductRecord.setPrice(productRecord.getPrice());

        return productRepo.save(existingProductRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteProductRecord(@PathVariable(value = "id") Long productId) throws NotFoundException {
        if (!productRepo.findById(productId).isPresent()) {
            throw new NotFoundException();
        }
        productRepo.deleteById(productId);
    }
}
