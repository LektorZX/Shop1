package root.service.basket;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.DTO.BasketDto;
import root.DTO.BasketDtoId;
import root.dao.basket.BasketDao;
import root.dao.order.OrderDao;
import root.entity.Basket;
import root.entity.Product;
import root.service.product.ProductService;
import root.service.base.BaseService;
import root.converter.DtoConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BasketServiceImpl implements BaseService<BasketDto, Long>, BasketService {

    private final BasketDao basketDao;
    private final ProductService productService;
    private final OrderDao orderDao;


    @Override
    public <S extends BasketDto> S save(S entity) {
        Basket basket = DtoConverter.getInstance().BasketDtoConvertToBasket(entity);
        List<Long> productId = entity.getProductId();
        System.out.println();
        basketDao.save(basket);
        System.out.println(basket);
        System.out.println();
        for (Long aLong : productId) {
            orderDao.save(basket.getId(), aLong);
        }
        return null;
    }


    @Override
    public void deleteById(Long primaryKey) {
        orderDao.delete(primaryKey);
        basketDao.deleteById(primaryKey);
    }


    //поиск всех заказов юзера
    @Override
    public List<BasketDtoId> findByIdUserInBasket(Long primaryKey) {
        List<BasketDtoId> basketDtoIds = new ArrayList<>();
        List<Basket> basketsByUserId = basketDao.findBasketsByUserId(primaryKey);//нашли заказы юзера
        for (Basket basket : basketsByUserId) {
            List<Long> productId = orderDao.customFindAllByBasket(basket.getId());//нашли продукты по заказам
            basketDtoIds.add(DtoConverter.getInstance().BasketConvertToBasketDtoId(basket, productId));
        }
        return basketDtoIds;
    }

    //поиск продуктов одного заказа
    @Override
    public List<Product> findOneListProdutsByOrderId(Long idOrder) {
        List<Long> productId = orderDao.customFindAllByBasket(idOrder);
        List<Product> products = new ArrayList<>();
        String s = productId.toString();
        s = s.replaceAll(" ", "");
        s = s.substring(1, s.length() - 1);
        String s1[] = s.split(",");
        for (int i = 0; i < s1.length; i++) {
            Product id = productService.findId(Long.parseLong(s1[i]));
            products.add(id);
        }
        return products;
    }


    @Override
    public List<BasketDto> findAll() {
        return null;
    }


    @Override
    public void delete(BasketDto entity) {
        Basket basket = DtoConverter.getInstance().BasketDtoConvertToBasket(entity);
        basketDao.delete(basket);
    }

    @Override
    public Optional<BasketDto> findById(Long primaryKey) {
        return null;
    }
}
