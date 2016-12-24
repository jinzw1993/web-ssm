package com.heitian.ssm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.heitian.ssm.dao.*;
import com.heitian.ssm.util.ResultResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.PageCondition;
import com.heitian.ssm.bo.ProductInOrderBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.model.Cart;
import com.heitian.ssm.model.Customer;
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
    private ShopDao shopDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private ShopIncomeDao shopIncomeDao;
    @Resource
    private MallIncomeDao mallIncomeDao;
    
    private Result result = new Result();

    public Result changeProcessStatus(Long orderId, Long status) {
        int i = orderDao.changeOrderProcessStatus(orderId, status);
        if(i == 4) {
            shopIncomeDao.insertIncome(orderId);
            mallIncomeDao.insertIncome(orderId);
        }
        return ResultResolver.returnRes(i);
    }

    public OrderBo getOrderBoById(Long orderId) {
        OrderBo order = orderDao.getOrderById(orderId);
        if(order == null)
            return new OrderBo();
        else
            return order;
    }

    public List<OrderBo> getOrderByTime(Long id, TimeCondition time, int kind) {
        setTimeCon(time);
        return orderDao.getOrdersTime(id, time, kind);
    }

    public Result getOrderByTimeNum(Long ownerId, TimeCondition time, int kind) {
        setTimeCon(time);
        result.setStatus(1);
        result.setMessage(String.valueOf(orderDao.getOrdersTimeNum(ownerId, time, kind)));
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

    public Result deliver(Long expressId, String number, Long orderId) {
        return ResultResolver.returnRes(orderDao.setExpress(expressId, number, orderId));
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
        if(time.getDay() == null)
            time.setDay(now.get(Calendar.DAY_OF_MONTH));
    }

    
	@Override
	public List<OrderBo> addOrder(Long cartId) {
		Cart cart = cartDao.searchCartById(cartId);
		int x = 0;
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
				
				x = ownerIds.size();
				
				for(Long ownerId : ownerIds) {
					
					Double orderPrice = 0.0;
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
					order.setExpressId((long)0);
					order.setPrice(orderPrice);
					order.setAmount(orderAmount);
					order.setCommissionRate(Double.valueOf(mallConfigDao.getMallConfigByKey("1").getValue()));
					order.setCommission(orderPrice * Double.valueOf(mallConfigDao.getMallConfigByKey("1").getValue()));
					order.setStatus((long)0); 
					order.setProcessStatus((long)1);
					order.setCreatedAt(new Timestamp(new Date().getTime()));
					order.setAddressId((long)0);
					order.setExpressPrice((long)0);
					//添加订单信息
					orderDao.insertOrder(order);
					
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
							
							productInOrder.setName(p.getName());
							productInOrder.setCategory(p.getCategoryId());
							//添加订单产品信息
							productInOrderDao.insertProductInOrder(productInOrder);
							
						}
					}					
				}				
			}
			productInCartDao.cleanCart(cart.getId());
			cartDao.updateCartAmount((long)0, cart.getCustomerId());
		}
		//System.out.println("最大id" + orderDao.getMaxOrderId() + "订单数量" + x);
		List<OrderBo> list = new ArrayList<OrderBo>();
		for(int i = orderDao.getMaxOrderId() - x + 1; i <= orderDao.getMaxOrderId(); i++) {
			list.add(orderDao.getOrderById((long)i));
		}
				/*orderDao.getListOrderBoLimit(, x);*/
		return list;
	}	

	@Override
	public Result confirmOrder(Long orderId, Long addressId) {
		
		OrderBo orderBo = orderDao.getOrderById(orderId);
		Customer customer = customerDao.getCustomerByEmail(orderBo.getCustomerEmail());
		if(orderBo != null && customer != null) {
			if(0 == orderBo.getStatus()) {
				if(customer.getBalance() < orderBo.getAmount()) {
					Result r = new Result();
					r.setStatus(0);
					r.setMessage("Not sufficient funds");
					return r;
				} else {
					customerDao.updateBalance(customer.getBalance() - orderBo.getPrice(), customer.getEmail());
					orderDao.changeOrderAddress(orderId, addressId);
					orderDao.changeOrderStatus(orderId, (long) 1);
					Result r = new Result();
					r.setStatus(1);
					r.setMessage("Pay for success");
					return r;
				}
			} else {
				Result r = new Result();
				r.setStatus(0);
				r.setMessage("Account paid");
				return r;
			}
		} else {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("wrong");
			return r;
		}
				
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
	
}
