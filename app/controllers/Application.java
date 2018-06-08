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

	// ルートにアクセスした際のAction
    public static Result index() {
    	List<Message> msgs = Message.find.all();
        return ok(index.render("投稿メッセージ",msgs));
    }

    // Message Action =====================================================

    // 新規作成フォームのAction
    public static Result add() {
    	Form<Message> f = new Form(Message.class);
    	return ok(add.render("投稿フォーム", f));
    }

    // createにアクセスした際のAction
    public static Result create() {
    	Form<Message> f = new Form(Message.class).bindFromRequest();
        if(!f.hasErrors()) {
        	Message data = f.get();
        	data.member = Member.findByName(data.name);
        	data.save();
        	return redirect("/");
        } else {
        	return ok(add.render("ERROR",f));
        }
    }

    // Member Action =====================================================
    // メンバー作成フォームのAction
    public static Result add2() {
    	Form<Member> f = new Form(Member.class);
    	return ok(add2.render("メンバー登録フォーム", f));
    }

    // create2にアクセスした際のAction
    public static Result create2() {
    	Form<Member> f = new Form(Member.class).bindFromRequest();
        if(!f.hasErrors()) {
        	Member data = f.get();
        	data.save();
        	return redirect("/");
        } else {
        	return badRequest(add2.render("ERROR",f));
        }
    }

}
