package com.http.task;

import android.os.AsyncTask;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;

public class ShopAsyncTask extends
		AsyncTask<BaseRequest, Void, ViewCommonResponse> {
	private CommonActivity activity;
	ShopService shopService = new ShopService();

	public ShopAsyncTask(CommonActivity activity) {
		this.activity = activity;
	}

	@Override
	protected ViewCommonResponse doInBackground(BaseRequest... params) {
		ViewCommonResponse viewCommonResponse = null;

		if (params[0] != null) {
			int action = params[0].getAction();
			switch (action) {
			case ShopService.SHOP_PRBC_LIST:
				viewCommonResponse = shopService.getPrbsList();
				break;
			case ShopService.SHOP_PRBC_SERIZAL:
				viewCommonResponse = shopService.getSerizleProduct(params[0]
						.getParams());
				break;
			case ShopService.SHOP_BRAND:
				viewCommonResponse = shopService.getBrandInfo(params[0]
						.getParams());
				break;
			case ShopService.PRODUCT_INFO:
				viewCommonResponse = shopService.getProductSimpleInfo(params[0]
						.getParams());
				break;
			case ShopService.PRODUCT_DETAIL:
				viewCommonResponse = shopService.getProductDetail(params[0]
						.getParams());
				break;
			case ShopService.PRODUCT_COMMENT:
				viewCommonResponse = shopService.getProductComment(params[0]
						.getParams());

				break;
			case ShopService.PRODUCT_ADD:
				viewCommonResponse = shopService.addShop(params[0].getParams());

				break;
			case ShopService.SHOP_CAR_FUXIAO_LIST:
				viewCommonResponse = shopService
						.quryShop(params[0].getParams());
				break;
			case ShopService.SHOP_CAR_LINGSHOU_LIST:
				viewCommonResponse = shopService
						.quryShop(params[0].getParams());
				break;
			case ShopService.SHOP_CAR_LIST_EDIT:
				viewCommonResponse = shopService
						.editShop(params[0].getParams());

				break;
			case ShopService.SHOP_CAR_LIST_DELETE:
				viewCommonResponse = shopService.deleteShop(params[0]
						.getParams());
				break;

			case ShopService.ADDRESS_LIST:
				viewCommonResponse = shopService.getAddress(params[0]
						.getParams());
				break;
			case ShopService.ADDRESS_ADD:
				viewCommonResponse = shopService.addAddress(params[0]
						.getParams());
				break;
			case ShopService.ADDRESS_DELETE:
				viewCommonResponse = shopService.deleteAddress(params[0]
						.getParams());
				break;
			case ShopService.ADDRESS_DEFAULT:
				viewCommonResponse = shopService.defaultAddress(params[0]
						.getParams());
				break;
			case ShopService.ADDRESS_SUMBIT:
				viewCommonResponse = shopService.updateAddress(params[0]
						.getParams());
				break;
			case ShopService.ROOM_ADDRESS:
				viewCommonResponse = shopService.queryRoom(params[0]
						.getParams());
				break;

			case ShopService.PRODUCT_WORD_SEARCH:
				viewCommonResponse = shopService.getKeyWord(params[0]
						.getParams());
				break;

			case ShopService.PRODUCT_SEARCH:
				viewCommonResponse = shopService.Search(params[0].getParams());
				break;
			case ShopService.ORDER_SUBMIT:
				viewCommonResponse = shopService.sumbitOrder(params[0]
						.getParams());
				break;
			case ShopService.ORDER_PAY:
				viewCommonResponse = shopService
						.payOrder(params[0].getParams());
				break;

			case ShopService.SHOPCAR_COUNT:
				viewCommonResponse = shopService.queryCount(params[0]
						.getParams());
				break;

			}

			viewCommonResponse.setAction(action);
		}
		return viewCommonResponse;
	}

	@Override
	protected void onPostExecute(ViewCommonResponse result) {
		activity.refresh(result);
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

}