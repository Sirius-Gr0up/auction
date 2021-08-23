package SiriusGroup.auction;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsRepository extends CrudRepository <Products,Long> {
    public static List<Products> findProductById(Long id){
        return null;
    }
}
