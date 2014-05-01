package com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;

import com.tencent.weibo.oauthv1.OAuthV1;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-12 上午11:19:48 declare:
 */
public class OauthKeeper {
	public static void saveOAuth(Context context, OAuthV1 oAuth_1) {
		SharedPreferences preferences = context
				.getSharedPreferences("oauth", 0);
		// 创建字节输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 创建对象输出流，并封装字节流
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			// 将对象写入字节流
			oos.writeObject(oAuth_1);
			// 将字节流编码成base64的字符窜
			String oAuth_Base64 = new String(Base64.encode(baos.toByteArray(),
					Base64.DEFAULT));
			Editor editor = preferences.edit();
			editor.putString("oAuth_1", oAuth_Base64);

			editor.commit();
		} catch (IOException e) {
			// TODO Auto-generated
		}
		Log.i("ok", "存储成功");
	}

	public static OAuthV1 readOAuth(Context context) {
		OAuthV1 oAuth_1 = null;
		SharedPreferences preferences = context
				.getSharedPreferences("oauth", 0);
		String productBase64 = preferences.getString("oAuth_1", "");

		// 读取字节
		byte[] base64 = Base64.decode(productBase64.getBytes(), Base64.DEFAULT);

		// 封装到字节流
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		try {
			// 再次封装
			ObjectInputStream bis = new ObjectInputStream(bais);
			try {
				// 读取对象
				oAuth_1 = (OAuthV1) bis.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oAuth_1;
	}

}
