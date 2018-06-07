import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;

import controllers.Application;
import models.Member;
import models.Message;
import play.GlobalSettings;
import play.libs.Yaml;

public class Global extends GlobalSettings {

	//@Override
	public void onStart(Application app) {
		insert(app);
	}

	@SuppressWarnings("unchecked")
	public void insert(Application app) {
		Map<String, List<Object>> all = (Map<String,List<Object>>)Yaml.load("test-data.yml");
		Ebean.save(all.get("members"));
		Ebean.save(all.get("messages"));
		for(Object message : all.get("messages")){
			Message target = Message.find.byId(((Message)message).id);
			target.members = Member.findByName(target.name);
			target.update();
		}
	}

}
