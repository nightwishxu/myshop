package com.api.action;

import com.alibaba.fastjson.JSONObject;
import com.api.MEnumError;
import com.api.view.pay.PayResult;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.api.annotation.ApiMethod;
import com.base.pay.PayMethod;
import com.base.pay.ali.AlipayConfig;
import com.base.util.JSONUtils;
import com.base.util.StringUtil;
import com.item.dao.model.PayLog;
import com.item.service.PayLogService;
import com.paidang.dao.model.*;
import com.paidang.service.GoodsService;
import com.paidang.service.OrderService;
import com.paidang.service.UserAddressService;
import com.paidang.service.UserCouponService;
import com.util.MPaidangPayType;
import io.rong.RongCloud;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/api/userPay", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
@Api(tags = "支付接口")
public class ApiUserPayController extends ApiBaseController{

	@Autowired
	private PayLogService payLogService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserAddressService	userAddressService;
	@Autowired
	private UserCouponService userCouponService;

	@ApiOperation(value = "下单", notes = "登陆")
	@RequestMapping("/createOrder")
	@ApiMethod(isLogin = true)
	public PayResult createOrder(MobileInfo mobileInfo,
								 @ApiParam(value = "商品id", required = true)Integer goodsId,
								 @ApiParam(value = "优惠券id", required = false)Integer couponId,
								 @ApiParam(value = "地址id", required = true)Integer addressId){
		PayResult payResult = new PayResult();

		GoodsExample goodsExample = new GoodsExample();
		goodsExample.createCriteria().andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTotalGreaterThanOrEqualTo(1).andIdEqualTo(goodsId);//.andStateEqualTo(1);
		List<Goods> list = goodsService.selectByExample(goodsExample);
		if(null == list || list.size() ==0) {
			throw new ApiException(MEnumError.GOODS_NOT_EXIST);
		}
		Goods goods = list.get(0);

		UserCoupon userCoupon = null;
		//用户使用优惠券
		if(null != couponId){
			UserCouponExample userCouponExample = new UserCouponExample();
			userCouponExample.createCriteria().andEndTimeGreaterThanOrEqualTo(new Date()).andIdEqualTo(couponId).andStateEqualTo(1);
			List<UserCoupon> userCouponList = userCouponService.selectByExample(userCouponExample);
			if(null == userCouponList || list.size() ==0){
				throw new ApiException(MEnumError.COUPON_TYPE_EXIST);
			}
			userCoupon = userCouponList.get(0);
		}


		UserAddress userAddress = userAddressService.selectByPrimaryKey(addressId);
		if(null == userAddress){
			throw new ApiException(MEnumError.ADDRESS_NOT_EXIST);
		}

		Order order = new Order();
		long code = System.currentTimeMillis();
		//订单号生成规则：时间戳加商品编号
		order.setCode(code + goodsId.toString());
		order.setUserId(mobileInfo.getUserid());
		order.setGoodsId(goods.getId());
		order.setGoodsName(goods.getName());
		order.setGoodsImg(goods.getImg());
		order.setGoodsSource(goods.getSource());
		order.setOrgId(goods.getOrgId());
		order.setGoodsPrice(goods.getPrice());
		order.setGoodsCost(goods.getCost());
		//优惠券

		BigDecimal finalPrice = null;
		if(null == userCoupon){
			//没有优惠券
			finalPrice = goods.getPrice();
		}else{
			finalPrice = goods.getPrice().subtract(userCoupon.getFull());
		}
		order.setPrice(finalPrice);
		order.setState(1);
		order.setIsBalance(0);
		order.setShipUser(userAddress.getUserName());
		order.setShipPhone(userAddress.getPhone());
		order.setShipAddress(userAddress.getArea()+userAddress.getAddress());
		order.setRefState(0);
		if(null != couponId){
			order.setCouponValue(userCoupon.getFull());
		}
		order.setCouponId(couponId);
		order.setIsDel(0);
		int result = orderService.insert(order);
		if(0 == result){
			throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
		}

		//商品数量-1
		goods.setTotal(goods.getTotal() - 1);
		goods.setSoldOut(goods.getSoldOut() + 1);
		int reuslt2 = goodsService.updateByPrimaryKeySelective(goods);

		if(0 == reuslt2){
			throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
		}

		//优惠券不可用
		if(null != userCoupon){
			userCoupon.setState(0);
			int result3 = userCouponService.updateByPrimaryKeySelective(userCoupon);
			if(0 == result3){
				throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
			}
		}

		payResult.setId(order.getId().toString());
		return payResult;

	}
	
	@ApiOperation(value = "商城支付", notes = "登陆")
	@RequestMapping("/buyPay")
	@ApiMethod(isLogin = true)
	public PayResult buyPay(MobileInfo mobileInfo,
			@ApiParam(value = "支付方式:1:支付宝 2:微信", required = true)Integer platform,
			@ApiParam(value = "订单id", required = true)Integer orderId){


		if (platform == null){
			throw new ApiException("platform");
		}
		if (orderId == null){
			throw new ApiException("orderId");
		}

		Order order = orderService.selectByPrimaryKey(orderId);
		order.setPayType(platform);
		orderService.updateByPrimaryKeySelective(order);
		if (order == null){
			throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
		}

//		if (order.getState() != 2){
//			throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
//		}
		
		PayLog log = new PayLog();
		log.setOrderId(order.getId());
		log.setUserId(mobileInfo.getUserid());
		log.setState(0);
		log.setMoney(order.getPrice());
		log.setParam(MPaidangPayType.NORMAL_BUY.name());
		payLogService.insertSelective(log);
		
		PayResult result = new PayResult();
		result.setPlatform(platform);
		switch (platform) {
		case 1:
			//支付宝
			result.setId(log.getId().toString()+"_"+ MPaidangPayType.NORMAL_BUY.name());
			result.setMoney(order.getPrice().toString());
			result.setBackUrl(PayMethod.urlToUrl(AlipayConfig.notify_url));
			break;
		case 2:
			//微信
			String wxId = PayMethod.wxPrepayId(order.getPrice(), log.getId().toString(), "订单支付",MPaidangPayType.NORMAL_BUY);
			if (StringUtil.isBlank(wxId)){
				throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
			}
			result.setId(wxId);
			break;
		default:
			throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
		}
		return result;
	}


	@ApiOperation(value = "购物车下单", notes = "登陆")
	@RequestMapping("/createShopCartOrder")
	@ApiMethod(isLogin = true)
	public List<PayResult> createShopCartOrder(MobileInfo mobileInfo,
								 @ApiParam(value = "json参数", required = true)String data,
								 @ApiParam(value = "地址id", required = true)Integer addressId){
		List<Map> mapList =JSONObject.parseArray(data, Map.class);
		long code = System.currentTimeMillis();

		UserAddress userAddress = userAddressService.selectByPrimaryKey(addressId);
		if(null == userAddress){
			throw new ApiException(MEnumError.ADDRESS_NOT_EXIST);
		}
		List<PayResult> results=new ArrayList<>();
		for (Map map:mapList){
			PayResult payResult = new PayResult();
			Integer goodsId=Integer.valueOf((String)map.get("goodsId"));
			String str =(String)map.get("couponId");
			Integer couponId=null;
			if(StringUtil.isNotBlank(str)){
				couponId=Integer.valueOf(str);
			}

			GoodsExample goodsExample = new GoodsExample();
			goodsExample.createCriteria().andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTotalGreaterThanOrEqualTo(1).andIdEqualTo(goodsId);//.andStateEqualTo(1);
			List<Goods> list = goodsService.selectByExample(goodsExample);
			if(null == list || list.size() ==0) {
				throw new ApiException(MEnumError.GOODS_NOT_EXIST);

			}
			Goods goods = list.get(0);
			UserCoupon userCoupon = null;
			Order order = new Order();

			//订单号生成规则：时间戳加商品编号
			order.setCode(code + goodsId.toString());
			order.setUserId(mobileInfo.getUserid());
			order.setGoodsId(goods.getId());
			order.setGoodsName(goods.getName());
			order.setGoodsImg(goods.getImg());
			order.setGoodsSource(goods.getSource());
			order.setOrgId(goods.getOrgId());
			order.setGoodsPrice(goods.getPrice());
			order.setGoodsCost(goods.getCost());
			BigDecimal finalPrice = null;
			if(null == userCoupon){
				//没有优惠券
				finalPrice = goods.getPrice();
			}else{
				finalPrice = goods.getPrice().subtract(userCoupon.getFull());
			}
			order.setPrice(finalPrice);
			order.setState(1);
			order.setIsBalance(0);
			order.setShipUser(userAddress.getUserName());
			order.setShipPhone(userAddress.getPhone());
			order.setShipAddress(userAddress.getArea()+userAddress.getAddress());
			order.setRefState(0);
			if(null != couponId){
				order.setCouponValue(userCoupon.getFull());
			}
			order.setCouponId(couponId);
			order.setIsDel(0);
			int result = orderService.insert(order);
			if(0 == result){
				throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
			}

			//商品数量-1
			goods.setTotal(goods.getTotal() - 1);
			goods.setSoldOut(goods.getSoldOut() + 1);
			int reuslt2 = goodsService.updateByPrimaryKeySelective(goods);

			if(0 == reuslt2){
				throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
			}

			//优惠券不可用
			if(null != userCoupon){
				userCoupon.setState(0);
				int result3 = userCouponService.updateByPrimaryKeySelective(userCoupon);
				if(0 == result3){
					throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
				}
			}

			payResult.setId(order.getId().toString());
			results.add(payResult);

		}
		return results;


			//用户使用优惠券
//			if(null != couponId){
//				UserCouponExample userCouponExample = new UserCouponExample();
//				userCouponExample.createCriteria().andEndTimeGreaterThanOrEqualTo(new Date()).andIdEqualTo(couponId).andStateEqualTo(1);
//				List<UserCoupon> userCouponList = userCouponService.selectByExample(userCouponExample);
//				if(null == userCouponList || list.size() ==0){
//					throw new ApiException(MEnumError.COUPON_TYPE_EXIST);
//				}
//				userCoupon = userCouponList.get(0);
//			}



			//优惠券


	}


	@ApiOperation(value = "商城购物车支付", notes = "登陆")
	@RequestMapping("/buyShopCartPay")
	@ApiMethod(isLogin = true)
	public PayResult buyPay(MobileInfo mobileInfo,
							@ApiParam(value = "支付方式:1:支付宝 2:微信", required = true)Integer platform,
							@ApiParam(value = "订单id,以,相隔", required = true)String orderIds){
		if (platform == null){
			throw new ApiException("platform");
		}
		if (orderIds == null){
			throw new ApiException("orderId");
		}
		String logIds="";
		List<PayLog> logList=new ArrayList<>();
		BigDecimal price=BigDecimal.ZERO;
		String[] orderArray=orderIds.split(",");
		for (String id:orderArray){
			Integer orderId=Integer.valueOf(id);
			Order order = orderService.selectByPrimaryKey(orderId);
			if (order == null){
				throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
			}
			order.setPayType(platform);
			orderService.updateByPrimaryKeySelective(order);
			PayLog log = new PayLog();
			log.setOrderId(order.getId());
			log.setUserId(mobileInfo.getUserid());
			log.setState(0);
			log.setMoney(order.getPrice());
			log.setParam(MPaidangPayType.NORMAL_BUY.name());
			payLogService.insertSelective(log);

			price.add(order.getPrice());
			logList.add(log);
			logIds+=log.getId();

		}

		PayResult result = new PayResult();
		result.setPlatform(platform);
		switch (platform) {
			case 1:
				//支付宝
				result.setId(logIds+"_"+ MPaidangPayType.NORMAL_BUY.name());
				result.setMoney(price.toString());
				result.setBackUrl(PayMethod.urlToUrl(AlipayConfig.notify_url));
				break;
			case 2:
				//微信
				String wxId = PayMethod.wxPrepayId(price, logIds, "订单支付",MPaidangPayType.NORMAL_BUY);
				if (StringUtil.isBlank(wxId)){
					throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
				}
				result.setId(wxId);
				break;
			default:
				throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
		}


		return result;





//		if (order.getState() != 2){
//			throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
//		}




	}



}
