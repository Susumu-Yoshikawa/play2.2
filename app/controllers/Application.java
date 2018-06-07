package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Member;
import models.Message;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	// フォーム管理用クラス
	public static class SampleForm{
		public List<String> inputs;
	}

	// ルートにアクセスした際のAction
	public static Result index() {
		List<Message> msgs = Message.find.all();
		return ok(index.render("please set form.", msgs));
	}

	// JSONデータの作成
	public static Result ajax() {
		String input = request().body().asFormUrlEncoded().get("input")[0];
		ObjectNode result = Json.newObject();
		if(input == null) {
			result.put("status", "BAD");
			result.put("message", "Can't get sending data...");
			return badRequest(result);
		} else {
			result.put("status", "OK");
			result.put("message", input);
			return ok(result);
		}
	}

	// dummy
	public static Result create() {
		return redirect("/");
	}

	// 表示用のAction
	public static Result add() {
		/*
		Form<Message> f = new Form(Message.class);
		List<Member> mems = Member.find.select("name").findList();
		List<Tuple2<String, String>> opts = new ArrayList<Tuple2<String, String>>();
		for(Member mem : mems) {
			opts.add(new Tuple2(mem.name, mem.name));
		}
		return ok(add.render("投稿フォーム", f, opts));
		*/
		return redirect("/");
	}

}
