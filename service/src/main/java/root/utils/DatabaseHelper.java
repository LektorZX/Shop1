package root.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.DTO.BasketDto;
import root.DTO.BasketDtoId;
import root.DTO.ProductDto;
import root.DTO.UserDto;
import root.converter.DtoConverter;
import root.entity.Product;
import root.entity.Role;
import root.entity.User;
import root.service.basket.BasketService;
import root.service.basket.BasketServiceImpl;
import root.service.product.ProductService;
import root.service.user.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseHelper {

    private final EntityManagerFactory entityManagerFactory;
    private final UserService userService;
    private final ProductService productService;
    private final BasketService basketService;


    @Autowired
    public DatabaseHelper(EntityManagerFactory entityManagerFactory, UserService userService,
                          ProductService productService, BasketService basketService) {
        this.entityManagerFactory = entityManagerFactory;
        this.userService = userService;
        this.productService = productService;
        this.basketService = basketService;
    }


    public void cleanDatabase() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User").executeUpdate();
        entityManager.createQuery("delete from Basket ").executeUpdate();
        entityManager.createQuery("delete from Product").executeUpdate();
        entityManager.createQuery("delete from Order").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void prepareData() {
        for (int i = 0; i < 3; i++) {
            productService.save(Product.of("product" + i, BigDecimal.valueOf(123.11 + i),
                    "Милкавита", "RB", 107l - i));
        }
//
//        for (int i = 0; i <5 ; i++) {
//            userService.save(User.builder().name("Ivan5" + i)
//                    .password("126345" + i).role(Role.CLIENT).build());
//        }
//
//        userService.save(User.builder().name("Ivan3" )
//                .password("126345").role(Role.ADMIN).build());

    }

    public void userTest() {
        User ivansave = User.builder()
                .name("Ivansave")
                .password("126345")
                .role(Role.CLIENT)
                .build();
        //save
////        Long save1 = userService.save(ivansave);
//        //update
//        ivansave.setRole(Role.ADMIN);
//        userService.update(ivansave);
//        //find
//        User byId1 = userService.findById(save1);
//        System.out.println();
//        Optional<User> ivansave1 = userService.findByName("Ivansave");
//        User user1 = ivansave1.get();
//        System.out.println();
//        List<User> all = userService.findAll();
//        System.out.println();
//        //del
//        userService.delete(byId1);
//        System.out.println();
//        userService.deleteById(save1);
    }


    public void productTest() {
        //save
        Product save = productService.save(Product.of("product111", BigDecimal.valueOf(123.11),
                "Милкавита", "RB", 10l));
        //update
        save.setAmount(9L);
        productService.update(save);
        //find
        List<Product> all = productService.findAll();
        Product id = productService.findId(save.getId());
        //dell
        productService.del(save);
        productService.deleteById(1l);

       try{ productService.deleteById(1l);}catch (Exception e){
           System.out.println(e.getStackTrace());
       }

    }


    public void TestConvertDTO() {
        DtoConverter instance = DtoConverter.getInstance();
        Product id = productService.findId(6l);
        System.out.println(id);
        ProductDto productDto1 = instance.ProductConvertToProductDto(id);
        System.out.println(productDto1+"+++++++++++++++++++++++++++");
        ProductDto productDto = ProductDto.builder()
                .name("333")
                .price(BigDecimal.valueOf(333))
                .remark("333")
                .origin("333")
                .amount(333l)
                .build();
        System.out.println(productDto);
        Product product1 = instance.ProductDtoConvertToProduct(productDto);
        System.out.println(product1+"+++++++++++++++++++++++++++");
        ////////////////////////////////////
        UserDto clientDto = UserDto.builder()
                .name("333")
                .password("333")
                .role(Role.CLIENT)
                .build();
        User byId = userService.findById(3l);
        UserDto userDto = instance.UserConvertToUserDto(byId);
        System.out.println(userDto);
        User user1 = instance.UserDtoConvertToUser(clientDto);
        System.out.println(user1+"-------------------------------------------");
    }


    public void testBasket() {

        //проверка создание ордера+++
        for (int i = 0; i < 5; i++) {
            List<Long> productId2=new ArrayList<>();
            productId2.add(5L);
            productId2.add(6L);
            productId2.add(12L);
            productId2.add(13L);
            productId2.add(14L);
            productId2.add(2L);
            productId2.add(3L);
            productId2.add(4L);

            basketService.save(BasketDto.builder()
                    .userId(4L)
                    .localDate(LocalDate.now())
                    .sum(BigDecimal.valueOf(999))
                    .productId(productId2)
                    .build());
        }


        //вывод списка товаров по одному оредеру+++
        List<Product> oneListProdutsByOrderId = basketService.findOneListProdutsByOrderId(38l);
        for (Product product : oneListProdutsByOrderId) {
            System.out.println(product);
        }

//удаление работает+++
//        basketService.deleteById(41L);

        //список всех заказов юзера по айди юзера+++
        List<BasketDtoId> byIdUserInBasket = basketService.findByIdUserInBasket(4l);
        for (BasketDtoId basketDtoId : byIdUserInBasket) {
            System.out.println(basketDtoId);
        }
    }


}
//byId.ifPresent(val -> products.add(byId.get())); добавление в коллекцию если опшинал