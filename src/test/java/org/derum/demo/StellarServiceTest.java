package org.derum.demo;

import org.derum.demo.services.StellarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.stellar.sdk.KeyPair;
import java.io.IOException;

@SpringBootTest
public class StellarServiceTest {

    @Mock
    StellarService stellarService;

    private final String destinataire =   "GC6GMX46NAKLFGQEEPG2C5343LVOJASIL7BTXRTMRCUFZDE76ICGRRGI";

    KeyPair source = KeyPair.random();

    @Test
    public void testFundAccount(){
        stellarService.fundAccount(source.getAccountId());
    }

    @Test
    public void testTransfert() throws Exception {
        stellarService.transfertMoney(source,destinataire,"5000");
    }

    @Test
    public void testGetAccountBalance() throws IOException {
        stellarService.getAccountBalance(destinataire);
    }
}