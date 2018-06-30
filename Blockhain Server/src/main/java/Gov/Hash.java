package Gov;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SerializationUtils;

public class Hash {
	
	private String hashCode;
	
	public String getHashCode() {
		return hashCode;
	}

	public Hash(String string) {
		byte[] converter = SerializationUtils.serialize((Serializable) string);
		String code = DigestUtils.sha256Hex(converter);
		this.hashCode = code;
	}

}

