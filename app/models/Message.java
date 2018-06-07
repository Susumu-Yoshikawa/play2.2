package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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

	@ManyToMany(mappedBy="messages", cascade=CascadeType.ALL)
	public List<Member> members;

	public static Finder<Long, Message> find =
			new Finder<Long, Message>(Long.class, Message.class);

	@Override
	public String toString() {
		String mems = "{";
		for(Member m : members) {
			mems += " " + m.name;
		}
		mems += "}";
		return ("[id:"+id+", message:"+message+", members:"+mems+
				", date:"+postdate+"]");
	}

	public static Message findByName(String input) {
		return Message.find.where().eq("name", input).findList().get(0);
	}

}
