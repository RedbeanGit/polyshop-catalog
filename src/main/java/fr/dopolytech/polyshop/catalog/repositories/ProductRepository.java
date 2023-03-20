package fr.dopolytech.polyshop.catalog.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import fr.dopolytech.polyshop.catalog.documents.Product;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, String> {
    public Flux<Product> findByInventoryId(String inventoryId);
}
