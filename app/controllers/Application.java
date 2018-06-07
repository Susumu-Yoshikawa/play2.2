package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Member;
import models.Message;
import play.data.Form;
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

	// dummy
	public static Result create() {
		return redirect("/");
	}

	// 表示用のAction
	public static Result add() {
		Form<Message> f = new Form(Message.class);
		List<Member> mems = Member.find.select("name").findList();
		return ok(add.render("投稿フォーム", f, mems));
	}

}
