package one.wangwei.blockchain;

import com.zhijl.common.encryptor.bytetool.Base58;
import com.zhijl.common.encryptor.bytetool.Hex;
import one.wangwei.blockchain.util.BtcAddressUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 〈尝试抽取代码未工具类〉
 *
 * @author Administrator
 * @date 2018/11/14
 * @since 1.0.0
 */
public class GenUtilsTest {


    public static String initWallet(String pubKey) throws IOException {

        byte[] pubKeyArr = Hex.decode(pubKey);

        byte[] ripemaHashArr = ripeMD160Hash(pubKeyArr);

        // 2. 添加版本 0x00
        ByteArrayOutputStream addrStream = new ByteArrayOutputStream();
        addrStream.write((byte) 0);
        addrStream.write(ripemaHashArr);
        byte[] versionedPayload = addrStream.toByteArray();

        // 3. 计算校验码
        byte[] checksum = BtcAddressUtils.checksum(versionedPayload);

        // 4. 得到 version + paylod + checksum 的组合
        addrStream.write(checksum);
        byte[] binaryAddress = addrStream.toByteArray();

        // 5. 执行Base58转换处理
        return Base58.encode(binaryAddress);

    }


    public static byte[] ripeMD160Hash(byte[] pubKey) {
        //1. 先对公钥做 sha256 处理
        byte[] shaHashedKey = DigestUtils.sha256(pubKey);
        RIPEMD160Digest ripemd160 = new RIPEMD160Digest();
        ripemd160.update(shaHashedKey, 0, shaHashedKey.length);
        byte[] output = new byte[ripemd160.getDigestSize()];
        ripemd160.doFinal(output, 0);
        return output;
    }

    public static void main(String[] args) throws IOException {
        /**
         * 0443203f2b0777b35e04bec75225571bc5160d4a54ffbab0c9de94cee3b088ffb34031084c314063cf999c5d01f6f3a5693543c0a139327c814fbf31e307c7faf6
         1CrLqnCzy8dZ3TG37ET89SMKFYyqMrEetn
         */
        String pubKey = "b77232a2047e59445c1c10ea3e6aeffb2aa21951fe62ef39dbe928298e36f0ad933806c8c72ae32313e075f60bc2c335737e01064b0c27f2037b1d0c4481ea0320f2aa36dc";
        System.out.println(initWallet(pubKey));
    }



}
