package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Message extends Model {

	@Id
	public Long id;

	@Required(message = "必須項目です。")
	public String name;

	@Email(message = "メールアドレスを記入ください。")
	public String mail;

	//@MaxLength(200)
	@Pattern(message = "半角英数字のみにしてください。", value="[a-zA-Z]+")
	public String message;

	@CreatedTimestamp
	public Date postdate;

	public static Finder<Long, Message> find =
			new Finder<Long, Message>(Long.class, Message.class);

	@Override
	public String toString() {
		return ("[id:"+id+", name:"+name+", mail:"+mail+", message:"+message+", date:"+postdate+"]");
	}

}
