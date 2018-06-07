package controllers;

import java.util.Map;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	// ルートにアクセスした際のAction
	public static Result index() {
		String method = request().method();
		if("GET".equals(method)) {
			return ok(index.render("please type:"));
		} else {
			Map<String, String[]> form =
					request().body().asFormUrlEncoded();
			String[] input = form.get("input");
			return ok(index.render("posted:" + input[0]));
		}
	}

}
