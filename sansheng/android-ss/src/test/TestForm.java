package test;

import java.util.HashMap;
import java.util.Map;

import model.Evaluate;

import com.google.gson.Gson;
import com.http.CustomFormService;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 下午6:06:11
 * 
 *          declare:表单查询
 */
public class TestForm {
	CustomFormService customFormService;

	public void init() {
		customFormService = new CustomFormService();
	}
	/* 
	 * 报单列表
	 */
	public void testRoomQuery() {
		Map<String, String> p = new HashMap<String, String>();
		p.put("userid", "TYgQm3jJucgti1xy2Hd9zA=={");
		p.put("querytype", "0");
		p.put("shopshowid", "all");
		customFormService.queryForm(p);
	}

	/*
	 * 报单详情
	 */
	public void testRoomQueryDetail() {
		Map<String, String> p = new HashMap<String, String>();
		p.put("userid", "H7Mud3IiaWjWqdL4J4qEJA==");
		p.put("orderid", "1");
		customFormService.queryFormDetail(p);
	}

	/*
	 * 报单评论
	 */
	public void testqueryComment() {
		Map<String, String> p = new HashMap<String, String>();
		p.put("userid", "H7Mud3IiaWjWqdL4J4qEJA==");
		p.put("logisticsok", "1");
		p.put("userlevel", "4");
		Evaluate evaluate = new Evaluate();
		evaluate.setNumber("358");
		evaluate.setCommentcon("不错");
		evaluate.setQualityint("1");
		Gson gson = new Gson();
		String json = gson.toJson(evaluate);
		p.put("commentinfo", json);
		customFormService.queryFormComment(p);
	}
	
	/*
	 * 报单评论
	 */
	public void testDeleteForm() {
		Map<String, String> p = new HashMap<String, String>();
		p.put("userid", "H7Mud3IiaWjWqdL4J4qEJA==");
		p.put("balanceno", "1");
		p.put("balanqishu", "1");
		customFormService.deleteFrom(p);
	}

}
