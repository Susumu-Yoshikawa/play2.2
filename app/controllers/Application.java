package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
		Form<SampleForm> form = new Form(SampleForm.class);
		return ok(index.render("please set form.", form));
	}

	// POST送信された際のAction
	public static Result send() {
		Form<SampleForm> f = new Form(SampleForm.class).bindFromRequest();
		if(!f.hasErrors()) {
			SampleForm sf = f.get();
			String res = "Value: ";
			for(String s : sf.inputs) {
				res += " " + s;
			}
			sf.inputs.add("");
			return ok(index.render(res,f));
		} else {
			return badRequest(index.render("ERROR",f));
		}
	}

}
