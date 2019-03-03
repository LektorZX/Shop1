package root.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import root.DTO.BasketDto;
import root.DTO.BasketDtoId;
import root.DTO.ProductDto;
import root.DTO.UserDto;
import root.entity.Basket;
import root.entity.Product;
import root.entity.Role;
import root.entity.User;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtoConverter {
    private static final DtoConverter INSTANCE = new DtoConverter();

    public static DtoConverter getInstance() {
        return INSTANCE;
    }

    public UserDto UserConvertToUserDto(User user) {
        return UserDto.builder().name(user.getName()).
                id(user.getId()).password(user.getPassword())
                .role(user.getRole())
                .build();
    }
    public User UserDtoConvertToUser(UserDto userDto) {
        return User.builder().name(userDto.getName())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }
    public Product ProductDtoConvertToProduct(ProductDto productDto){
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .remark(productDto.getRemark())
                .origin(productDto.getOrigin())
                .amount(productDto.getAmount())
                .build();
    }
    public ProductDto ProductConvertToProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .remark(product.getRemark())
                .origin(product.getOrigin())
                .amount(product.getAmount())
                .build();
    }
    public Basket BasketDtoConvertToBasket(BasketDto basketDto){
       return Basket.builder()
                .userId(basketDto.getUserId())
                .localDate(basketDto.getLocalDate())
                .sum(basketDto.getSum())
                .build();
    }

    public BasketDtoId BasketConvertToBasketDtoId(Basket basket, List<Long> productId){
        return BasketDtoId.builder()
                .id(basket.getId())
                .productId(productId)
                .userId(basket.getUserId())
                .localDate(basket.getLocalDate())
                .sum(basket.getSum())
                .build();
    }




}
