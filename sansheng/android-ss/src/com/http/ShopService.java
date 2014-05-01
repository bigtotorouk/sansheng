package com.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Evaluate;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.sansheng.model.Address;
import com.sansheng.model.Adverst;
import com.sansheng.model.Brand;
import com.sansheng.model.MuilPorduct;
import com.sansheng.model.Product;
import com.sansheng.model.Room;
import com.sansheng.model.pr;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 下午5:55:55 declare:
 */
public class ShopService {
	private ViewCommonResponse response = new ViewCommonResponse();;
	/** 系列列表 **/
	public static final int SHOP_PRBC_LIST = 1001;
	/** 系列产品 **/
	public static final int SHOP_PRBC_SERIZAL = 1002;
	/** 产品品牌 **/
	public static final int SHOP_BRAND = 1003;
	/** 产品信息 **/
	public static final int PRODUCT_INFO = 1004;
	/** 产品详情 **/
	public static final int PRODUCT_DETAIL = 1005;

	/** 产品评价 **/
	public static final int PRODUCT_COMMENT = 1006;

	/** 关键字搜索 **/
	public static final int PRODUCT_WORD_SEARCH = 1007;

	/** 产品搜索 搜索 **/
	public static final int PRODUCT_SEARCH = 1008;

	/** 添加购物车 **/
	public static final int PRODUCT_ADD = 1009;
	/** 购物车列表 **/
	public static final int SHOP_CAR_FUXIAO_LIST = 1010;
	public static final int SHOP_CAR_LINGSHOU_LIST = 2010;
	/** 购物车编辑 **/
	public static final int SHOP_CAR_LIST_EDIT = 1011;

	/** 购物车删除 **/
	public static final int SHOP_CAR_LIST_DELETE = 1012;

	/** 订单提交 **/
	public static final int ORDER_SUBMIT = 1013;

	/** 支付订单 **/
	public static final int ORDER_PAY = 1014;

	/** 收获地址 接口 **/
	public static final int ADDRESS_LIST = 1015;

	/** 添加地址接口 **/
	public static final int ADDRESS_ADD = 1016;

	/** 编辑地址接口 **/
	public static final int ADDRESS_EDIT = 1017;

	/** 提交地址接口 **/
	public static final int ADDRESS_SUMBIT = 1018;

	/** 删除地址接口 **/
	public static final int ADDRESS_DELETE = 1019;

	/** 默认地址接口 **/
	public static final int ADDRESS_DEFAULT = 1020;

	/** 工作室地址接口 **/
	public static final int ROOM_ADDRESS = 1021;

	public static final int SHOPCAR_COUNT = 1900;

	/**
	 * 系列列表接口
	 * 
	 * @return
	 */
	public ViewCommonResponse getPrbsList() {
		HttpCommonResponse httpCommonResponse = HttpUtil.get(
				BaseNetService.URL_SHOP_PRBC_LIST, null);
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());
			List<pr> prlist = new ArrayList<pr>();
			prlist = (ArrayList<pr>) JsonUtil.json2ObList(json.toString(),
					"clist", pr.class);
			response.setHttpCode(httpCommonResponse.getStateCode());
			response.setData(prlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 2.4.2、系列产品接口
	 * 
	 * @param params
	 * @return
	 */
	public ViewCommonResponse getSerizleProduct(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_SHOP_PRBC_SERIZAL, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());

			JSONObject dataJson = json.getJSONObject("clist");
			List<Adverst> prlist = new ArrayList<Adverst>();
			prlist = (ArrayList<Adverst>) JsonUtil.json2ObList(
					dataJson.toString(), "adslist", Adverst.class);
			System.out.print(prlist);
			List<Brand> brands = new ArrayList<Brand>();
			brands = (ArrayList<Brand>) JsonUtil.json2ObList(
					dataJson.toString(), "brandlist", Brand.class);
			System.out.print(brands);
			BrandAndAdverst brandAndAdverst = new BrandAndAdverst();
			brandAndAdverst.setAdversts(prlist);
			brandAndAdverst.setBrands(brands);
			response.setHttpCode(httpCommonResponse.getStateCode());
			response.setData(brandAndAdverst);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * 2.4.3、品牌产品接口
	 * 
	 * @return
	 */
	public ViewCommonResponse getBrandInfo(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_SHOP_BRAND, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());
			List<Brand> brands = (ArrayList<Brand>) JsonUtil.json2ObList(
					json.toString(), "list", Brand.class);
			response.setHttpCode(httpCommonResponse.getStateCode());
			response.setData(brands);
			System.out.print(brands);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 2.4.4、产品基本信息接口
	 * 
	 * @return
	 */
	public ViewCommonResponse getProductSimpleInfo(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_SHOP_PRODUCT_BASE, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		Product product = null;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());

			Gson gson = new Gson();
			product = gson.fromJson(json.toString(), Product.class);
			System.out.print(product);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());
		response.setData(product);
		return response;
	}

	/**
	 * 2.4.5、产品详细信息接口
	 * 
	 * @param params
	 * @return
	 */
	public ViewCommonResponse getProductDetail(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_SHOP_PRODUCT_DETAIL, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		String content = "";
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());
			content = json.getString("url");
			System.out.print(content);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		response.setData(content);
		return response;
	}

	/**
	 * 2.4.6、产品评价信息接口
	 * 
	 * @return
	 */
	public ViewCommonResponse getProductComment(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_SHOP_COMMENT, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		List<Evaluate> evaluates = null;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());

			evaluates = new ArrayList<Evaluate>();
			evaluates = (ArrayList<Evaluate>) JsonUtil.json2ObList(
					json.toString(), "list", Evaluate.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		response.setData(evaluates);
		return response;
	}

	/**
	 * 2.4.7、关键词搜索接口
	 * 
	 * @param params
	 * @return
	 */
	public ViewCommonResponse getKeyWord(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_KEY_WORDS, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		String[] keywords = null;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());
			JSONArray jArray = json.getJSONArray("list");

			if (jArray.length() != 0) {
				keywords = new String[jArray.length()];
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject jObj = jArray.getJSONObject(i);
					keywords[i] = jObj.getString("title");
				}
			}
			System.out.print(keywords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		response.setData(keywords);
		return response;
	}

	/**
	 * 2.4.8、产品搜索接口
	 * 
	 * @param params
	 * @return
	 */
	public ViewCommonResponse Search(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_SEARCH, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		List<Product> products = null;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());

			products = new ArrayList<Product>();
			products = (ArrayList<Product>) JsonUtil.json2ObList(
					json.toString(), "list", Product.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		response.setData(products);
		return response;
	}

	/**
	 * 2.4.9、添加购物车接口
	 * 
	 * @param params
	 * @return
	 */
	public ViewCommonResponse addShop(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_SHOP_ADD_CART, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		List<Product> products = null;
		int newscartmun = 0;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			if (json.has("newscartmun")) {
				newscartmun = json.getInt("newscartmun");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		response.setData(newscartmun);
		return response;
	}

	/**
	 * 2.4.10、购物车列表接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse quryShop(Map<String, String> params) {

		List<Product> data = new ArrayList<Product>();

		boolean hasnext = true;
		int pageNum = 0;
		while (hasnext) {
			params.put("pageno", "" + pageNum);
			HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
					BaseNetService.URL_SHOP_CART_LIST, params);
			response.setHttpCode(httpCommonResponse.getStateCode());
			List<Product> productList = null;
			pageNum++;

			try {
				JSONObject json = new JSONObject(
						httpCommonResponse.getResponse());
				response = JsonUtil.commonParser(json.toString());
				response.setHttpCode(httpCommonResponse.getStateCode());
				if (response.getHttpCode() == 200 && response.getMsgCode() == 0) {
					productList = new ArrayList<Product>();
					productList = (ArrayList<Product>) JsonUtil.json2ObList(
							json.toString(), "list", Product.class);
					data.addAll(productList);
				} else {
					hasnext = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(productList);

		}
		response.setData(data);

		return response;
	}

	public ViewCommonResponse queryCount(Map<String, String> params) {

		List<Product> data = new ArrayList<Product>();

		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_SHOP_CART_LIST, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		List<Product> productList = null;

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());

			if (json.has("cartcount")) {
				int count = json.getInt("cartcount");
				response.setData(count);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(productList);

		return response;
	}

	/**
	 * 2.4.11、购物车编辑接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse editShop(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_EDIT_SHOP_CART, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		List<Product> products = null;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());

			products = new ArrayList<Product>();
			products = (ArrayList<Product>) JsonUtil.json2ObList(
					json.toString(), "list", Product.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		response.setData(products);
		return response;
	}

	/**
	 * 2.4.12、购物车删除接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse deleteShop(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_DELETE_SHOP_CART, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		response = JsonUtil.commonParser(httpCommonResponse.getResponse());

		if (response.getMsgCode() == 0) {
			System.out.print("删除成功");
		} else {
			System.out.print("删除失败");
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		return response;
	}

	/**
	 * 2.4.12、购物车删除接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse sumbitOrder(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_SUMBIT_ORDER, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.commonParser(httpCommonResponse.getResponse());

		String orderCode = "-1";
		try {
			JSONObject jObj = new JSONObject(httpCommonResponse.getResponse());
			orderCode = jObj.getString("ordercode");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		response.setData(orderCode);

		return response;
	}

	/**
	 * 2.4.12、购物车删除接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse payOrder(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_PAY, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.commonParser(httpCommonResponse.getResponse());
		response.setHttpCode(httpCommonResponse.getStateCode());

		return response;
	}

	/**
	 * 2.4.15、收货地址接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse getAddress(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_ADDRESS, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.commonParser(httpCommonResponse.getResponse());
		List<Address> products = null;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());

			products = new ArrayList<Address>();
			products = (ArrayList<Address>) JsonUtil.json2ObList(
					json.toString(), "list", Address.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setData(products);
		response.setHttpCode(httpCommonResponse.getStateCode());

		return response;
	}

	/**
	 * 2.4.16、添加收货地址接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse addAddress(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_ADDRESS_ADD, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.commonParser(httpCommonResponse.getResponse());
		response.setHttpCode(httpCommonResponse.getStateCode());

		return response;
	}

	/**
	 * 2.4.17、编辑收货地址接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse editAddress(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_ADDRESS_EDIT, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.commonParser(httpCommonResponse.getResponse());
		List<Address> products = null;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());

			products = new ArrayList<Address>();
			products = (ArrayList<Address>) JsonUtil.json2ObList(
					json.toString(), "list", Address.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpCode(httpCommonResponse.getStateCode());

		return response;
	}

	/**
	 * 2.4.18、更新收货地址接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse updateAddress(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_ADDRESS_UPDATE, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.commonParser(httpCommonResponse.getResponse());
		response.setHttpCode(httpCommonResponse.getStateCode());

		return response;
	}

	/**
	 * 2.4.18、删除收货地址接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse deleteAddress(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_ADDRESS_DELETE, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.commonParser(httpCommonResponse.getResponse());
		response.setHttpCode(httpCommonResponse.getStateCode());

		return response;
	}

	/**
	 * 2.4.20、默认收货地址接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse defaultAddress(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_ADDRESS_DEFAULT, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.commonParser(httpCommonResponse.getResponse());
		response.setHttpCode(httpCommonResponse.getStateCode());

		return response;
	}

	/**
	 * 2.4.21、 工作室查询接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryRoom(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_ROOM_QUERY, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		response = JsonUtil.json2Object(httpCommonResponse.getResponse(),
				Room.class, "shopinfo");
		Room room = (Room) response.getData();
		response.setHttpCode(httpCommonResponse.getStateCode());

		System.out.println(room);
		return response;
	}
}
