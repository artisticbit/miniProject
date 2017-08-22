package user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({	"classpath:config/context-aspect.xml",
						"classpath:config/context-common.xml",
						"classpath:config/context-mybatis.xml",
						"classpath:config/context-transaction.xml"})
public class userIdsTest {
	
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	@Test
	public void getUserIds() throws Exception{
		
		List<String> list=userService.getUserIds("user");
		
		for(String s : list) {
			System.out.println(s);
		}
		
	}
}
