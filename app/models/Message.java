package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Message extends Model {

	@Id
	public Long id;

	@Required(message = "必須項目です。")
	public String name;

	@Required(message = "必須項目です。")
	public String message;

	@CreatedTimestamp
	public Date postdate;

	@ManyToOne(cascade=CascadeType.ALL)
	public Member member;

	public static Finder<Long, Message> find =
			new Finder<Long, Message>(Long.class, Message.class);

	@Override
	public String toString() {
		return ("[id:"+id+", message:"+message+", members:"+name+
				", date:"+postdate+"]");
	}

	public static Message findByName(String input) {
		return Message.find.where().eq("name", input).findList().get(0);
	}

}
