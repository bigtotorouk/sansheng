package com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import com.tencent.weibo.oauthv1.OAuthV1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-6 下午4:05:39 declare:
 */
public class DateKeeper {

	private static String FILE_NAME = "base_info";

	public static void saveData(Context context, String Name, Object obj) {
		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME,
				0);
		// 创建字节输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 创建对象输出流，并封装字节流
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			// 将对象写入字节流
			oos.writeObject(obj);
			// 将字节流编码成base64的字符窜
			String oAuth_Base64 = new String(Base64.encode(baos.toByteArray(),
					Base64.DEFAULT));
			Editor editor = preferences.edit();
			editor.putString(Name, oAuth_Base64);
			editor.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("ok", "存储成功");
	}

	public static Object getData(Context context, String Name) {
		Object object_1 = null;
		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME,
				0);
		String productBase64 = preferences.getString(Name, "");

		// 读取字节
		byte[] base64 = Base64.decode(productBase64.getBytes(), Base64.DEFAULT);

		// 封装到字节流
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		try {
			// 再次封装
			ObjectInputStream bis = new ObjectInputStream(bais);
			try {
				// 读取对象
				object_1 =  bis.readObject();
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
		return object_1;
	}

}
