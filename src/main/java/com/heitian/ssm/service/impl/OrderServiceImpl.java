package com.heitian.ssm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.PageCondition;
import com.heitian.ssm.bo.ProductInOrderBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.dao.CartDao;
import com.heitian.ssm.dao.CustomerAddressDao;
import com.heitian.ssm.dao.MallConfigDao;
import com.heitian.ssm.dao.OrderDao;
import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.dao.ProductInCartDao;
import com.heitian.ssm.dao.ProductInOrderDao;
import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.Cart;
import com.heitian.ssm.model.Order;
import com.heitian.ssm.model.Product;
import com.heitian.ssm.model.ProductInCart;
import com.heitian.ssm.model.ProductInOrder;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.OrderService;

/**
 * Created by unname on 2016/12/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private ProductInOrderDao productInOrderDao;
    @Resource
    private CartDao cartDao;
    @Resource
    private ProductInCartDao productInCartDao;
    @Resource
    private ProductDao productDao;
    @Resource
    private MallConfigDao mallConfigDao;
    @Resource
    private CustomerAddressDao customerAddressDao;
    @Resource
    private ShopDao shopDao;
    
    private Result result = new Result();

    public Result changeProcessStatus(Long orderId, Long status) {
        int i = orderDao.changeOrderProcessStatus(orderId, status);
        if (i > 0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setStatus(0);
            result.setMessage("failed");
        }
        return result;
    }

    public OrderBo getOrderBoById(Long orderId) {
        OrderBo order = orderDao.getOrderById(orderId);
        if(order == null)
            return new OrderBo();
        else
            return order;
    }


    public List<OrderBo> getOwnOrderByTime(Long id, TimeCondition time) {
        setTimeCon(time);
        return orderDao.getOwnerOrders(id, time);
    }

    public Result getOwnOrderByTimeNum(Long ownerId, TimeCondition time) {
        setTimeCon(time);
        result.setStatus(1);
        result.setMessage(String.valueOf(orderDao.getOwnOrderCompleteNum(ownerId, time)));
        return result;
    }

    public List<OrderBo> getOwnerOrderBoByPStatus(Long processStatus, Long ownerId, int page, int pageNum) {
        return orderDao.getOwnerOrderBoByProcessStatus(processStatus, ownerId, (page - 1) * pageNum, pageNum);
    }

    public Result getOwnerOrderBoByPStatusNum(Long processStatus, Long ownerId) {
        result.setStatus(1);
        result.setMessage(String.valueOf(orderDao.getOwnerOrderBoByProcessStatusNum(processStatus, ownerId)));
        return result;
    }

    public List<ProductInOrderBo> getProductInOrder(Long orderId) {
        return productInOrderDao.getProductByOrderId(orderId);
    }

    private void setTimeCon(TimeCondition time) {
        if(time == null)
            time = new TimeCondition();
        //默认按天搜索
        if(time.getId() == null)
            time.setId(0);

        //默认设为当前日期
        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);
        if(time.getYear() == null)
            time.setYear(now.get(Calendar.YEAR));
        if(time.getMonth() == null)
            time.setMonth(now.get(Calendar.MONTH) +1);
        if(time.getWeek() == null)
            time.setWeek(now.get(Calendar.WEEK_OF_YEAR) -1);
        else
            time.setWeek(now.get(Calendar.WEEK_OF_YEAR) -time.getWeek());
        if(time.getDay() == null)
            time.setDay(now.get(Calendar.DAY_OF_MONTH));
    }

    
	@Override
	public Result addOrder(Long cartId, Long expressId, Long addressId) {
		int i = 0;
		Cart cart = cartDao.searchCartById(cartId);
		if(cart != null) {
			List<ProductInCart> productInCarts = productInCartDao.searchProductInCartByCartId(cart.getId());
			if(productInCarts != null && productInCarts.size() > 0) {
				
				List<Product> products = new ArrayList<Product>();
				for(ProductInCart pic : productInCarts) {
					products.add(productDao.searchProductById(pic.getProductId()));
				}
				Set<Long> ownerIds = new HashSet<Long>();
				for(Product p : products) {
					ownerIds.add(p.getOwnId());
				}
				
				for(Long ownerId : ownerIds) {
					
					Long orderPrice = (long)0;
					Long orderAmount = (long)0;
					for(Product p : products) {
						if(p.getOwnId() == ownerId) {
							ProductInCart pic = productInCartDao.searchProductInCartByCartIdAndProductId(cart.getId(), p.getId());
							orderAmount += pic.getAmount();
							orderPrice += p.getPrice() * pic.getAmount();
						}
					}
					
					Order order = new Order();
					
					order.setCustomerId(cart.getCustomerId());					
					Shop shop = shopDao.selectShopByOwnerId(ownerId);
					order.setShopId(shop.getId());
					order.setOwnerId(ownerId);
					order.setExpressId(expressId);
					order.setPrice(orderPrice);
					order.setAmount(orderAmount);
					order.setCommission((long) 1);
					order.setCommissionRate((long)(orderPrice * Double.valueOf(mallConfigDao.getMallConfigByKey("1").getValue())));
					order.setStatus((long)0); 
					order.setProcessStatus((long)0);
					order.setCreatedAt(new Timestamp(new Date().getTime()));
					order.setAddressId(addressId);
					
					//添加订单信息
					i = orderDao.insertOrder(order);
					
					for(Product p : products) {
						if(p.getOwnId() == ownerId) {
							int orderId = orderDao.getMaxOrderId();
							ProductInCart pic = productInCartDao.searchProductInCartByCartIdAndProductId(cart.getId(), p.getId());
							ProductInOrder productInOrder = new ProductInOrder();
							productInOrder.setProductId(p.getId());
							productInOrder.setOrderId((long)orderId);
							productInOrder.setPrice(pic.getAmount() * p.getPrice());
							productInOrder.setAmount(pic.getAmount());
							productInOrder.setShopId(p.getShopId());
							productInOrder.setCreatedAt(new Timestamp(new Date().getTime()));
							//添加订单产品信息
							i = productInOrderDao.insertProductInOrder(productInOrder);
							
						}
					}					
				}				
			}
			productInCartDao.cleanCart(cart.getId());
			cartDao.updateCartAmount((long)0, cart.getCustomerId());
		} else {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("You can't avtive!");
			return r;
		}
		return returnRes(i);
	}
	
	@Override
	public Result changeStatus(Long orderId, Long status) {
        int i = orderDao.changeOrderStatus(orderId, status);
        if (i > 0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setStatus(0);
            result.setMessage("failed");
        }
        return result;
    }
	
	@Override
	public List<OrderBo> search(PageCondition page, Long customerId) {
		return orderDao.search(page, customerId);
	}
	
	private Result returnRes(int i) {
        Result result = new Result();
        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;
    }

	
}
