import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 密码加密方式MD5
 * @author huang
 *
 */
public class MessageDigestTest {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String [] passwords= { "123456","000000","aaaaaa"};
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		for(int i=0;i<passwords.length;i++) {
			
			byte[] mdBytes=md.digest(passwords[i].getBytes());
			
			for(int j=0;j<mdBytes.length;j++) {
				
			}
			
		}
		
	}

}
