package controllers;

import java.util.Date;
import java.util.Map;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	// フォーム管理用クラス
	public static class SampleForm{
		public String input;
		public String pass;
		public boolean check;
		public String radio;
		public String sel;
		public String area;
		public Date date;
	}

	// ルートにアクセスした際のAction
	public static Result index() {
		SampleForm sf = new SampleForm();
		sf.radio = "windows";
		sf.check = true;
		sf.input = "default value";
		sf.sel = "uk";
		Form<SampleForm> form = new Form(SampleForm.class).fill(sf);

		return ok(index.render("please set form.", form));

	}

	// POST送信された際のAction
	public static Result send() {
		Form<SampleForm> f = new Form(SampleForm.class).bindFromRequest();
		if(!f.hasErrors()) {
			SampleForm sf = f.get();
			String res = "value: ";
			res += "input="+sf.input+", ";
			res += "pass="+sf.pass+", ";
			res += "check="+sf.check+", ";
			res += "radio="+sf.radio+", ";
			res += "sel="+sf.sel+", ";
			res += "area="+sf.area+", ";
			res += "date="+sf.date+", ";
			return ok(index.render(res,f));
		} else {
			return badRequest(index.render("ERROR",f));
		}
	}

}
