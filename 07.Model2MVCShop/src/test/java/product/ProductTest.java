package product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({	"classpath:config/context-aspect.xml",
						"classpath:config/context-common.xml",
						"classpath:config/context-mybatis.xml",
						"classpath:config/context-transaction.xml"})
public class ProductTest {

	@Test
	public void productTest() {
		
	}
	
}
