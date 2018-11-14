package one.wangwei.blockchain;

import one.wangwei.blockchain.cli.CLI;
import one.wangwei.blockchain.wallet.Wallet;

/**
 * 测试
 *
 * @author wangwei
 * @date 2018/02/05
 */
public class BlockchainTest {

    public static void main(String[] args) {
        Wallet wallet = new Wallet();
        System.out.println(wallet.getAddress());
    }
}
