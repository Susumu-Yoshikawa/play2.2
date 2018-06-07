package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.ValidateWith;
import play.data.validation.Constraints.Validator;
import play.db.ebean.Model;
import play.libs.F;

@Entity
public class Message extends Model {

	@Id
	public Long id;

	@Required(message = "必須項目です。")
	public String name;

	@Email(message = "メールアドレスを記入ください。")
	public String mail;

	//@MaxLength(200)
	//@Pattern(message = "半角英数字のみにしてください。", value="[a-zA-Z]+")
	@Required(message = "必須項目です。")
	@ValidateWith(value = IsUrl.class, message = "URLで始まるメッセージを記述ください。")
	public String message;

	@CreatedTimestamp
	public Date postdate;

	public static Finder<Long, Message> find =
			new Finder<Long, Message>(Long.class, Message.class);

	@Override
	public String toString() {
		return ("[id:"+id+", name:"+name+", mail:"+mail+", message:"+message+", date:"+postdate+"]");
	}

	public static class IsUrl extends Validator<String>{
		public boolean isValid(String s) {
			return s.toLowerCase().startsWith("http://");
		}

		@Override
		public F.Tuple<String, Object[]> getErrorMessageKey(){
			return new F.Tuple<String, Object[]>("error.invalid", new String[] {});
		}
	}

}
