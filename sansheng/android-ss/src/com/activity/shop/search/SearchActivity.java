package com.activity.shop.search;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Brand;
import com.sansheng.model.Product;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.SearchView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午2:17:49 declare:
 */
public class SearchActivity extends CommonActivity implements OnClickListener {

	private SearchView searchView;
	private ListView lvShop;
	ShopSearchAdapter shopSearchAdapter;
	private LayoutInflater layoutInflater;

	private TextSearchAdapter textAdapter;
	PopupWindow popView;
	EditText etSearch;
	HeadBar headBar;
	CommonActivity activity;
	// ListView listView;
	ListView lvWord;
	SearchAdapter shopCarAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		activity = this;
		setContentView(R.layout.activity_shop_search_activity);
		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("搜索");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		layoutInflater = getLayoutInflater();
		lvShop = (ListView) findViewById(R.id.Lv_Shop);
		lvWord = (ListView) findViewById(R.id.Lv_Word);
		searchView = (SearchView) findViewById(R.id.SearchView);
		searchView.btnSearchView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("debug", "search" + searchView.getContent());
				shopSearchAdapter.setProducts(getTempData());
				shopSearchAdapter.notifyDataSetChanged();
			}
		});

		View btnSearch = (View) searchView.getBtnSearchView();
		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SearchShop(etSearch.getText().toString());
			}
		});
		shopCarAdapter = new SearchAdapter(this);

		shopSearchAdapter = new ShopSearchAdapter(this);
		lvShop.setAdapter(shopSearchAdapter);

		textAdapter = new TextSearchAdapter();
		lvWord.setAdapter(textAdapter);
		lvShop.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Product product = shopCarAdapter.getProducts().get(position);
				Intent intent = new Intent(activity, ShopDetailActivity.class);
				Bundle bundle = new Bundle();
				Brand brand = new Brand();
				brand.setId(product.getPid());
				bundle.putSerializable(ShopDetailActivity.INTNET_PRODUCT, brand);
				intent.putExtras(bundle);
				activity.startActivity(intent);
			}
		});

		lvWord.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String word = textAdapter.getWords()[position];
				// Toast.makeText(activity, "" + word,
				// Toast.LENGTH_SHORT).show();
				Log.e("debug", "word" + word);
				SearchShop(word);
			}
		});
		etSearch = searchView.getEtSearch();
		searchView.getEtSearch().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// if (popView == null) {
				//
				// popView = new PopupWindow(listView,
				// LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				// popView.showAsDropDown(searchView);
				// // popView.showAtLocation(etSearch, Gravity.BOTTOM, 0, 0);
				// } else if (popView.isShowing() == true) {
				// popView.dismiss();
				//
				// } else {
				// popView.showAsDropDown(searchView);
				// }
			}
		});

		etSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Log.e("text", "change" + s);
				// BaseRequest baseRequest =
				// createRequest(ShopService.PRODUCT_WORD_SEARCH);
				// baseRequest.add("keysword", s.toString());
				// new ShopAsyncTask(activity).execute(baseRequest);
				SearchShop(s.toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		etSearch.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				// if (hasFocus) {
				// show();
				// } else {
				// dismissPopupWindow();
				// }
			}
		});

	}

	public void SearchShop(String shop) {
		if (!shop.equals("")) {
			lvWord.setVisibility(View.INVISIBLE);
			lvShop.setVisibility(View.VISIBLE);
			BaseRequest baseRequest = createRequest(ShopService.PRODUCT_SEARCH);
			baseRequest.add("keysword", shop.toString());
			new ShopAsyncTask(activity).execute(baseRequest);
		}

	}

	public void searchWord(String shop) {
		if (!shop.equals("")) {
			BaseRequest baseRequest = createRequest(ShopService.PRODUCT_WORD_SEARCH);
			baseRequest.add("keysword", shop);
			new ShopAsyncTask(activity).execute(baseRequest);
		}
	}

	public void dismissPopupWindow() {
		if (popView != null && popView.isShowing()) {
			popView.dismiss();
		}
	}

	// private void show() {
	// popView = new PopupWindow(listView, LayoutParams.FILL_PARENT,
	// LayoutParams.WRAP_CONTENT, false);
	// popView.setFocusable(true);
	// popView.setOutsideTouchable(true);
	// popView.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg));
	// }

	class TextSearchAdapter extends BaseAdapter {
		public String[] words;

		public String[] getWords() {
			return words;
		}

		public void setWords(String[] words) {
			this.words = words;
		}

		@Override
		public int getCount() {
			if (words == null) {
				return 0;
			} else {
				return words.length;
			}
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = (View) layoutInflater.inflate(
						R.layout.layout_text_search_item, null);
			}
			TextView tvTitle = (TextView) convertView
					.findViewById(R.id.Tv_Word);
			tvTitle.setText(words[position]);
			return convertView;
		}

	}

	public List<Product> getTempData() {
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 0; i++) {
			Product product = new Product();
			product.setName("舒伯赖氨基酸洁面皂");
			product.setNumber("1");
			product.setPrice("120");
			product.setPv("89");
			products.add(product);
		}
		return products;
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case ShopService.PRODUCT_WORD_SEARCH:
			lvWord.setVisibility(View.VISIBLE);
			lvShop.setVisibility(View.INVISIBLE);
			String[] words = (String[]) viewCommonResponse.getData();
			textAdapter.setWords(words);
			textAdapter.notifyDataSetChanged();
			break;
		case ShopService.PRODUCT_SEARCH:
			ProgressDialogUtil.close();
			List<Product> products = (List<Product>) viewCommonResponse
					.getData();
			shopCarAdapter.setProducts(products);
			lvShop.setAdapter(shopCarAdapter);
			shopCarAdapter.notifyDataSetChanged();
			break;

		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();

			break;

		default:
			break;
		}
	}
	

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		InputMethodManager mInputMethodManager;
		mInputMethodManager = (InputMethodManager) this
				.getSystemService(this.INPUT_METHOD_SERVICE);
		mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus()
				.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

}
