import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;

public class addressTest {
	@Test
	public void testAddress() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ac.getBean("addressMapper", AddressMapper.class);
		Address address = new Address();
		address.setUid(1);
		address.setRecvName("顶顶顶");
		address.setRecvPhone("2134567");
		address.setRecvCity("苏州市");
		address.setRecvTel("2323");
		address.setRecvDistrict("大师的");
		address.setRecvAddress("asda");
		address.setRecvProvince("jj");
		address.setRecvZip("123");
		address.setModifiedUser("h");
		address.setCreatedUser("g");
		address.setRecvArea("wj");
		address.setTag("asda");
		addressMapper.insert(address);
		ac.close();
	}
}
