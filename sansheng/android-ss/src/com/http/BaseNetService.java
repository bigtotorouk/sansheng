package com.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpRequestBase;

public class BaseNetService {
	public static Map<String, HttpRequestBase> httpRequestBases = Collections
			.synchronizedMap(new HashMap<String, HttpRequestBase>());

	public static final String HOST_PORT = "http://cloud.yofoto.cn/index.php?gr=Oauthserver&mr=Shwuczb&ar=";
	// http://cloud.yofoto.cn/index.php?g=appserver&m=index&cmd=
	// http://cloud.yofoto.cn/index.php?gr=oauthserver&mr=Shwuczb&ar=devdoc
	// 其他
	public static final int startup = 1001;
	// HOT
	public static final int HOT_GET_HOT_POSITION_NAME_LIST = 1101;
	public static final int HOT_SAVE_HOT_WORDS = 1102;

	/*** job ***/
	public static final int SHOP_PRBC_LIST = 1200;
	public static final int SHOP_PRBC_SERIZAL = 1200;
	public static final int SHOP_BRANDINO = 1200;
	public static final int SHOP_PRODUCT_BASE = 1200;

	/* 公司咨询 */
	public static final String Announcement = HOST_PORT + "notices";
	public static final String NEW = HOST_PORT + "newss";
	public static final String Promotion = HOST_PORT + "promotion";
	public static final String Introduce = HOST_PORT + "company&comid=9";
	public static final String Produce = HOST_PORT + "company&comid=10";
	public static final String Quality = HOST_PORT + "company&comid=11";
	public static final String Cultural = HOST_PORT + "company&comid=12";
	public static final String Brands = HOST_PORT + "company&comid=13";
	public static final String Historical = HOST_PORT + "company&comid=15";
	public static final String Honour = HOST_PORT + "company&comid=16";
	public static final String Communit = HOST_PORT + "lovenewss";
	public static final String CommunitGG = HOST_PORT + "lovenewsadsopen";

	public static final String URL_SHOP_PRBC_LIST = HOST_PORT + "prbclist";
	public static final String URL_SHOP_PRBC_SERIZAL = HOST_PORT
			+ "queryresaleform";
	public static final String URL_SHOP_BRAND = HOST_PORT + "querybrandform";
	public static final String URL_SHOP_PRODUCT_BASE = HOST_PORT
			+ "queryresalebaseinfo";
	public static final String URL_SHOP_PRODUCT_DETAIL = HOST_PORT
			+ "queryresaledetailinfo";
	public static final String URL_SHOP_COMMENT = HOST_PORT
			+ "queryresaleappraise";
	public static final String URL_SHOPER_PAY = HOST_PORT
			+ "toconfirmsaleorderok";
	public static final String URL_NOTIFY = HOST_PORT + "tihuopushmsgdo";

	public static final String URL_KEY_WORDS = HOST_PORT + "productkeysword";

	public static final String URL_SEARCH = HOST_PORT + "productsearch";

	/* 添加到购物车 */
	public static final String URL_SHOP_ADD_CART = HOST_PORT
			+ "addshoppingcart";
	/* 查看购物车列表 */
	public static final String URL_SHOP_CART_LIST = HOST_PORT
			+ "queryshoppingcart";

	/* 购物车编辑接口 */
	public static final String URL_EDIT_SHOP_CART = HOST_PORT
			+ "editshoppingcart";

	/** 购物车删除接口 **/
	public static final String URL_DELETE_SHOP_CART = HOST_PORT
			+ "delshoppingcart";

	/** 提交订单接口 **/
	public static final String URL_SUMBIT_ORDER = HOST_PORT
			+ "submitordersopen";

	/** 支付订单接口 **/
	public static final String URL_PAY = HOST_PORT + "confirmsaleorder";

	/** 收获地址接口 **/
	public static final String URL_ADDRESS = HOST_PORT + "openfindadds";

	/** 添加收货地址接口 **/
	public static final String URL_ADDRESS_ADD = HOST_PORT + "adduseraddsopen";

	/** 编辑收货地址接口 **/
	public static final String URL_ADDRESS_EDIT = HOST_PORT + "edituseradds";
	/** 更新收货地址接口 **/
	public static final String URL_ADDRESS_UPDATE = HOST_PORT
			+ "edituseraddsdo";

	/** 删除收货地址接口 **/
	public static final String URL_ADDRESS_DELETE = HOST_PORT + "deluseraddsdo";

	/** 默认收货地址接口 **/
	public static final String URL_ADDRESS_DEFAULT = HOST_PORT
			+ "sbituserdesint";

	/** 工作室接口 **/
	public static final String URL_ROOM_QUERY = HOST_PORT + "shopvalidationck";

	/** 默认收货地址接口 **/
	public static final String URL_ADDRESS_DEAFULT = HOST_PORT
			+ "sbituserdesint";

	/** 工作室查询接口 **/
	public static final String URL_SHOP_ROOM_DEAFULT = HOST_PORT
			+ "shopvalidationck";

	/** 报单查询接口 **/
	public static final String URL_FORM_QUERY = HOST_PORT + "queryform";

	/** 报单详情接口 **/
	public static final String URL_FORM_DETAIL = HOST_PORT + "queryformdetail";

	/** 报单评价 **/
	public static final String URL_FROM_COMMENT = HOST_PORT
			+ "publishresaleappraise";

	/** 报单删除 **/
	public static final String URL_FROM_DELETE = HOST_PORT
			+ "deltoqueryformdetail";

	/** 报单删除 **/
	public static final String URL_FROM_SURE = HOST_PORT + "tihuockintopen";

	/** 添加提醒 **/
	public static final String URL_REMINDS_ADD = HOST_PORT + "addremindsdo";

	/** 查询提醒 **/
	public static final String URL_REMINDS_QUERY = HOST_PORT + "remindslist";

	/** 删除提醒 **/
	public static final String URL_REMINDS_DELETE = HOST_PORT + "delremindsdo";
	/** 添加客户 **/
	public static final String URL_CUSTOME_ADD = HOST_PORT + "addcustomerinfo";

	/** 客户编辑 **/
	public static final String URL_CUSTOME_EDIT = HOST_PORT
			+ "editcustomerinfo";

	/** 提交编辑 **/
	public static final String URL_CUSTOME_COMMIT_EDIT = HOST_PORT
			+ "delremindsdo";

	/** 删除客户 **/
	public static final String URL_CUSTOME_DELETE = HOST_PORT
			+ "delcustomerinfodo";

	/** 编辑客户 **/
	public static final String CUSTOME_EDIT = HOST_PORT + "editcustomerinfo";

	/** 详情客户 **/
	public static final String URL_CUSTOME_INFO = HOST_PORT
			+ "editcustomerinfo";

	/** 客户列表 **/
	public static final String URL_CUSTOME_QUERY = HOST_PORT
			+ "querycustomerinfo";
	/** 编辑客户 **/
	public static final String CUSTOME_EDIT_COMMIT = HOST_PORT
			+ "editcustomerinfodo";

	/** 用户回馈接口 **/
	public static final String URL_FEED_BACK = HOST_PORT + "feedbackdo";

	/** 版本更新接口 **/
	public static final String URL_VISION = HOST_PORT + "versionsaveopen";

	/** 业绩期数 **/
	public static final String URL_ACH_LIST = HOST_PORT
			+ "queryevaluationperiods";
	/** 我的业绩 **/
	public static final String URL_ACH_MY = HOST_PORT + "queryevaluation";
	/** 零售池 **/
	public static final String URL_ACH_SALE_POOL = HOST_PORT
			+ "queryresalepool";
	/** 复消池 **/
	public static final String URL_ACH_FUXIAO_POOL = HOST_PORT
			+ "queryrepeatepool";
	public static final String CHECK_USER = HOST_PORT + "findcgouserinfodo";
	public static final String NEW_AD = HOST_PORT + "newsadsopen";

	public static final String BENEFIT_AD = HOST_PORT + "lovenewsadsopen";

}
