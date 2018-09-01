import org.springframework.util.DigestUtils;
/**
 * 密码加密方式MD5
 * @author huang
 *
 */
public class DigestTest {
	public static void main(String[] args) {
		String [] passwords= { "123456","000000","aaaaaa"};
		String salt ="java";//加密 盐
		for(int i=0;i<passwords.length;i++) {
			String md5Str = DigestUtils.md5DigestAsHex(passwords[i].getBytes());
			String m = DigestUtils.md5DigestAsHex((md5Str+salt).getBytes());
			System.out.println(m);
			
		}
	}
}
