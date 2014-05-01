package com.activity.achievement;

import java.util.List;

import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.schedule.CommonFragment;
import com.http.AchivementService;
import com.http.BaseRequest;
import com.http.ViewCommonResponse;
import com.http.task.AchivementAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.AchList;
import com.sansheng.model.Achivement;
import com.sansheng.model.SalePool;
import com.util.ProgressDialogUtil;
import com.view.OnWheelChangedListener;
import com.view.WheelAdapter;
import com.view.WheelView;

public class SalePollFragment extends CommonFragment implements OnClickListener {

	protected View view;
	protected LayoutInflater layoutInflater;
	private static Achivement achivement;
	static TextView Tv_One;

	static TextView Tv_Two;
	static TextView Tv_Three;
	static TextView Tv_Four;
	private CommonActivity activity;
	List<AchList> achLists;
	RatingBar rateMax;
	RatingBar rateOk;

	private WheelView wheelView;
	private WheelAdapter wheelAdapter;

	private Button btnCancel;
	private Button btnOk;
	private int currentItem;
	private RelativeLayout pickerLayout;
	Button resultsview;
	private String priod;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.activity = (CommonActivity) getActivity();
		view = (View) inflater.inflate(R.layout.layout_achievement_sale, null);
		layoutInflater = inflater;
		pickerLayout = (RelativeLayout) view.findViewById(R.id.Layout_Picker);
		resultsview = (Button) view.findViewById(R.id.btn_periods);
		resultsview.setOnClickListener(this);
		// resultsview.setWidth(320);
		// 字体加粗
		Tv_One = (TextView) view.findViewById(R.id.Tv_One);
		TextPaint tp_Group_Num = Tv_One.getPaint();
		tp_Group_Num.setFakeBoldText(true);

		Tv_Two = (TextView) view.findViewById(R.id.Tv_Two);
		TextPaint tp_Current_Num = Tv_Two.getPaint();
		tp_Current_Num.setFakeBoldText(true);

		Tv_Three = (TextView) view.findViewById(R.id.Tv_Three);
		tp_Current_Num = Tv_Two.getPaint();
		tp_Current_Num.setFakeBoldText(true);

		Tv_Four = (TextView) view.findViewById(R.id.Tv_Four);
		tp_Current_Num = Tv_Two.getPaint();
		tp_Current_Num.setFakeBoldText(true);

		wheelView = (WheelView) view.findViewById(R.id.WheelView);

		btnCancel = (Button) view.findViewById(R.id.Btn_Cancel);
		btnOk = (Button) view.findViewById(R.id.Btn_Sure);

		btnCancel.setOnClickListener(this);
		btnOk.setOnClickListener(this);
		initDataList();
		return view;
	}

	public void initDataList() {
		if (achLists == null) {
			BaseRequest requert = activity
					.createRequestWithUserId(AchivementService.ACHI_LIST);
			new AchivementAsyncTask(null, this).execute(requert);
			// ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);
		}

	}

	public void initData(AchList achList) {

		BaseRequest requert = activity
				.createRequestWithUserId(AchivementService.ACHI_SALE);
		requert.add("qsid", achList.getPeriodsid());
		new AchivementAsyncTask(null, this).execute(requert);
		ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);

	}

	public void updata(SalePool salePool) {
		if (salePool.getLastresaleevaluation() != null) {
			Tv_One.setText(salePool.getLastresaleevaluation());
		}
		if (salePool.getResaleevaluation() != null) {
			Tv_Two.setText(salePool.getResaleevaluation());
		}
		if (salePool.getUseresaleevaluation() != null) {
			Tv_Three.setText(salePool.getUseresaleevaluation());
		}
		if (salePool.getResalesurplusevaluation() != null) {
			Tv_Four.setText(salePool.getResalesurplusevaluation());
		}
	}

	// public void initWidget() {
	// lvEvaluate = (ListView) view.findViewById(R.id.Lv_Evaluate);
	// lvEvaluate.setDivider(null);
	// EvaluateAdapter adapter = new EvaluateAdapter(commonActivity, getData());
	// lvEvaluate.setAdapter(adapter);
	// }

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case AchivementService.ACHI_LIST:
			ProgressDialogUtil.close();
			achLists = (List<AchList>) viewCommonResponse.getData();
			AchievementActivity.achLists = achLists;
			if (achLists != null && achLists.size() >= 1) {
				initData(achLists.get(0));
			} else {
				ProgressDialogUtil.close();
			}
			wheelAdapter = new WheelAdapter() {

				@Override
				public int getMaximumLength() {
					// TODO Auto-generated method stub
					return achLists.size();
				}

				@Override
				public int getItemsCount() {
					return achLists.size();
				}

				@Override
				public String getItem(int index) {
					// TODO Auto-generated method stub
					AchList achList = achLists.get(index);
					String content = "";
					if (achList.getTitle() != null) {
						content += achList.getTitle();
					}
					if (achList.getTimes() != null) {
						content += achList.getTimes();
					}

					return content;
				}
			};
			wheelView.setAdapter(wheelAdapter);
			wheelView.addChangingListener(new OnWheelChangedListener() {

				@Override
				public void onChanged(WheelView wheel, int oldValue,
						int newValue) {
					// TODO Auto-generated method stub
					Log.e("de bug",
							"value" + newValue + " item:"
									+ wheel.getCurrentItem());
					currentItem = wheel.getCurrentItem();
				}
			});
			break;

		case AchivementService.ACHI_SALE:
			AchList achList = achLists.get(0);
			ProgressDialogUtil.close();
			resultsview.setText(achList.getTitle());
			SalePool salePool = (SalePool) viewCommonResponse.getData();
			if (salePool != null)
				updata(salePool);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Cancel:
			pickerLayout.setVisibility(View.GONE);
			break;

		case R.id.Btn_Sure:
			initData(achLists.get(currentItem));
			resultsview.setText(achLists.get(currentItem).getTitle());
			pickerLayout.setVisibility(View.GONE);
			break;
		case R.id.btn_periods:
			pickerLayout.setVisibility(View.VISIBLE);
			break;
		}
	}

}
