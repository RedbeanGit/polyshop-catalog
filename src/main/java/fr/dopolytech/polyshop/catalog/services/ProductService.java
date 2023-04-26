package fr.dopolytech.polyshop.catalog.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.dopolytech.polyshop.catalog.dtos.UpdateProductDto;
import fr.dopolytech.polyshop.catalog.events.InventoryUpdateEvent;
import fr.dopolytech.polyshop.catalog.events.InventoryUpdateEventProduct;
import fr.dopolytech.polyshop.catalog.models.Product;
import fr.dopolytech.polyshop.catalog.repositories.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final QueueService queueService;

    public ProductService(ProductRepository repository, QueueService queueService) {
        this.repository = repository;
        this.queueService = queueService;
    }

    public Flux<Product> getProducts() {
        return repository.findAll();
    }

    public Mono<Product> getProduct(String productId) {
        return repository.findByProductId(productId);
    }

    public Mono<Product> updateProduct(String productId, UpdateProductDto dto) {
        return repository.findByProductId(productId).flatMap(product -> {
            product.name = dto.name;
            product.description = dto.description;
            product.price = dto.price;
            return repository.save(product);
        });
    }

    public Mono<Product> createProduct(Product product) {
        return repository.save(product);
    }

    @RabbitListener(queues = "catalogQueue")
    public void onInventoryUpdate(String message) {
        try {
            InventoryUpdateEvent inventoryUpdate = this.queueService.parse(message, InventoryUpdateEvent.class);

            if (inventoryUpdate == null) {
                return;
            }

            for (InventoryUpdateEventProduct product : inventoryUpdate.products) {
                if (product.newQuantity <= 0) {
                    repository.deleteByProductId(product.productId);
                } else if (product.oldQuantity <= 0) {
                    repository.findByProductId(product.productId)
                            .switchIfEmpty(repository.save(new Product(product.productId, null, null, 0)));
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
