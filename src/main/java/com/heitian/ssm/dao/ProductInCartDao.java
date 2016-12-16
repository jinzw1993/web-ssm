package com.heitian.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heitian.ssm.model.ProductInCart;

@Repository
public interface ProductInCartDao {
	
	public List<ProductInCart> searchProductInCartByCartId(Long cartId);
	
	public int updateProductInCart(@Param("productInCart") ProductInCart productInCart);
	
	public ProductInCart searchProductInCartByCartIdAndProductId(@Param("cartId")Long cartId, @Param("productId")Long productId);
	
	public int insertProductInCart(@Param("productInCart") ProductInCart productInCart);
	
	public int deleteProductInCart(@Param("productInCart") ProductInCart productInCart);

}
