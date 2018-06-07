import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import models.Member;
import models.Message;
import play.data.Form;
import play.mvc.Content;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

	List<Member> dummy_mems = null;
	List<Message> dummy_msgs = null;

	public ApplicationTest() {
		initialData();
	}

	// ダミーデータの用意
	public void initialData() {
		dummy_mems = new ArrayList();
		Member mem = new Member();
		mem.id = 10001L;
		mem.name = "dummy_name";
		mem.mail = "dummy@mail";
		mem.tel = "00000";
		dummy_mems.add(mem);
		dummy_msgs = new ArrayList();
		Message msg = new Message();
		msg.id = 10002L;
		msg.name = mem.name;
		msg.members = mem;
		msg.message = "dummy message";
		msg.postdate = new Date();
		mem.messages = new ArrayList<Message>();
		mem.messages.add(msg);
		dummy_msgs.add(msg);
	}

    @Test
    public void renderTemplate1() {
    	String msg = "テストメッセージ";
    	Content add = views.html.add.render(msg,new Form(Message.class));
    	assertThat(contentAsString(add)).contains(msg);
    }

    @Test
    public void renderTemplate2() {
    	String msg = "テストメッセージ";
    	Content index = views.html.index.render(msg,dummy_msgs);
    	assertThat(contentType(index)).isEqualTo("text/html");
    	assertThat(contentAsString(index)).contains(msg);
    	assertThat(contentAsString(index)).contains(dummy_msgs.get(0).message);
    }

}
