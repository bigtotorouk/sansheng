package com.activity.setting.bindsetting.sina;


/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-14 下午6:35:46 declare:
 */
//public class SinaLogingActivity extends CommonActivity implements
//		OnClickListener, IWeiboHandler.Response {
//	public static Oauth2AccessToken accessToken;
//	private SinaLogingActivity sinaLogingActivity;
//	private Weibo mWeibo;
//
//	@Override
//	protected void onCreate(Bundle arg0) {
//		// TODO Auto-generated method stub
//		super.onCreate(arg0);
//		sinaLogingActivity = this;
//		setContentView(R.layout.activity_setting_bind_sina);
//		mWeibo = Weibo.getInstance(ConstantS.APP_KEY, ConstantS.REDIRECT_URL,
//				ConstantS.SCOPE);
//		mWeibo.anthorize(this, new AuthDialogListener());
//		initWidget();
//	}
//
//	public void initWidget() {
//		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
//		headBar.setRightType(BtnType.empty);
//		headBar.setTitle(getStr(R.string.sina_weibo));
//
//		headBar.setWidgetClickListener(this);
//	}
//
//	class AuthDialogListener implements WeiboAuthListener {
//
//		@Override
//		public void onComplete(Bundle values) {
//
//			String code = values.getString("code");
//			if (code != null) {
//				Toast.makeText(SinaLogingActivity.this, "认证code成功",
//						Toast.LENGTH_SHORT).show(); 
//				return;
//			}
//			String token = values.getString("access_token");
//			String expires_in = values.getString("expires_in"); 
//			accessToken = new Oauth2AccessToken(token, expires_in);
//			if (accessToken.isSessionValid()) {
//				String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//						.format(new java.util.Date(accessToken.getExpiresTime()));
//				Log.e("debug", "认证成功: \r\n access_token: " + token + "\r\n"
//						+ "expires_in: " + expires_in + "\r\n有效期：" + date);
//
//				AccessTokenKeeper.keepAccessToken(sinaLogingActivity,
//						accessToken);
//				Toast.makeText(sinaLogingActivity, "认证成功", Toast.LENGTH_SHORT)
//						.show();
//			}
//		}
//
//		public String getToken(String code) {
//			String tokenStr = "";
//			try {
//				URL url = new URL("https://api.weibo.com/oauth2/access_token");
//				URLConnection connection = url.openConnection();
//				connection.setDoOutput(true);
//				OutputStreamWriter out = new OutputStreamWriter(
//						connection.getOutputStream(), "utf-8");
//				out.write("client_id=id&client_secret=secret&grant_type=authorization_code"
//						+ "&code=" + code + "&redirect_uri=uri");
//				out.flush();
//				out.close();
//				String sCurrentLine;
//				String sTotalString;
//				sCurrentLine = "";
//				sTotalString = "";
//				InputStream l_urlStream;
//				l_urlStream = connection.getInputStream();
//				BufferedReader l_reader = new BufferedReader(
//						new InputStreamReader(l_urlStream));
//				while ((sCurrentLine = l_reader.readLine()) != null) {
//					sTotalString += sCurrentLine;
//				}
//
//				JSONObject o = new JSONObject(sTotalString);
//				System.out.println(o.getString("access_token"));
//				tokenStr = o.getString("access_token");
//				return tokenStr;
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			return tokenStr;
//		}
//
//		@Override
//		public void onError(WeiboDialogError e) {
//			Toast.makeText(getApplicationContext(),
//					"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
//		}
//
//		@Override
//		public void onCancel() {
//			Toast.makeText(getApplicationContext(), "Auth cancel",
//					Toast.LENGTH_LONG).show();
//		}
//
//		@Override
//		public void onWeiboException(WeiboException e) {
//			Toast.makeText(getApplicationContext(),
//					"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
//					.show();
//		}
//
//	}
//
//	public void sendMessage() {
//		IWeiboAPI weiboAPI = WeiboSDK.createWeiboAPI(this, ConstantS.APP_KEY);
//		WeiboMessage wMsg = new WeiboMessage();
//		TextObject textObject = new TextObject();
//		textObject.text = "heh";
//		wMsg.mediaObject = textObject;
//		SendMessageToWeiboRequest req = new SendMessageToWeiboRequest();
//		req.transaction = String.valueOf(System.currentTimeMillis());
//		req.message = wMsg;
//		weiboAPI.sendRequest(this, req);
//	}
//
//	@Override
//	public void onClick(View v) {
//		int id = v.getId();
//		switch (id) {
//		case R.id.Btn_Back:
//			sendMessage();
//			break;
//
//		default:
//			break;
//		}
//	}
//
//	@Override
//	public void onResponse(BaseResponse baseResp) {
//		switch (baseResp.errCode) {
//		case com.sina.weibo.sdk.constant.Constants.ErrorCode.ERR_OK:
//			Toast.makeText(this, "成功！！", Toast.LENGTH_LONG).show();
//			break;
//		case com.sina.weibo.sdk.constant.Constants.ErrorCode.ERR_CANCEL:
//			Toast.makeText(this, "用户取消！！", Toast.LENGTH_LONG).show();
//			break;
//		case com.sina.weibo.sdk.constant.Constants.ErrorCode.ERR_FAIL:
//			Toast.makeText(this, baseResp.errMsg + ":失败！！", Toast.LENGTH_LONG)
//					.show();
//			break;
//		}
//	}
//}
