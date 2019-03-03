package root.service.basket;

import root.DTO.BasketDto;
import root.DTO.BasketDtoId;
import root.entity.Product;

import java.util.List;

public interface BasketService {

    List<BasketDtoId> findByIdUserInBasket(Long primaryKey);
    List<Product>findOneListProdutsByOrderId(Long idOrder);
    <S extends BasketDto> S save(S entity);
    void deleteById(Long primaryKey);
}
