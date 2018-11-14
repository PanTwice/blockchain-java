package one.wangwei.blockchain;


import com.zhijl.common.encryptor.bytetool.Base58;
import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBase58 {

    @Test
    public void t58() {
        String originBase64 = "dO6GCEbBYyCFmu3QdLMFexxy0dybSjH2Gq9IqB57xrs=";

        byte[] originBytes = Base64.getDecoder().decode(originBase64);

        String base58 = Base58.encodeCheck(originBytes);
        System.out.println("base58 code: " + base58);

        assertTrue(Base58.check(base58));

        byte[] dbytes = Base58.decodeCheck(base58);
        assertEquals(Base64.getEncoder().encodeToString(dbytes),originBase64);
    }

}
