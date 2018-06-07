package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Member extends Model {

	@Id
	public Long id;

	@Required(message = "必須項目です。")
	public String name;

	@Email(message = "メールアドレスを記入ください。")
	public String mail;

	public String tel;

	public static Finder<Long, Member> find =
			new Finder<Long, Member>(Long.class, Member.class);

	@OneToMany(cascade = CascadeType.ALL)
	public List<Message> messages = new ArrayList<Message>();

	@Override
	public String toString() {
		String ids = "{id:";
		for(Message m : messages) {
			ids += " " + m.id;
		}
		return ("[id:"+id+", message:"+ids+", name:"+name+", mail:"+mail+", tel:"+tel+"]");
	}

	public static Member findByName(String input) {
		return Member.find.where().eq("name", input).findList().get(0);
	}

}
