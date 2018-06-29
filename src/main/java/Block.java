import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SerializationUtils;

public class Block {

	private String BlockHash;

	public String getBlockHash() {
		return BlockHash;
	}
	
	public Block() {
		// TODO Auto-generated constructor stub
	}

	public Block(String previusHash, String transaction) {
		
		Object[] content = {previusHash, transaction};
		String hashing = Hash(content);
		this.BlockHash = hashing;
		
	}
	
	private String Hash(Object Value) {
		byte[] converter = SerializationUtils.serialize((Serializable) Value);
		String code = DigestUtils.sha256Hex(converter);
		return code;
	}

	
	
}
