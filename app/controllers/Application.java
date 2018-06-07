package controllers;

import static play.data.Form.*;

import java.util.List;

import models.Message;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	// Form用の内部クラス
	public static class SampleForm{
		public String message;
	}

	// ルートにアクセスした際のAction
    public static Result index() {
    	List<Message> datas = Message.find.all();
        return ok(index.render("データベースのサンプル",datas));
    }
}
