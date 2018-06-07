package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	// ルートにアクセスした際のAction
	public static Result index() {
		return ok(index.render("これはテンプレートのテストです。"));
	}

}
