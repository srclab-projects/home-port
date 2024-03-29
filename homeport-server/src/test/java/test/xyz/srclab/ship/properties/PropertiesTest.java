package test.xyz.srclab.ship.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import xyz.srclab.common.codec.aes.AesKeys;
import xyz.srclab.ship.properties.EncodeString;

import javax.annotation.Resource;
import javax.crypto.SecretKey;

@SpringBootTest(classes = Starter.class)
//@ContextConfiguration(classes = {TestStarter.class})
public class PropertiesTest extends AbstractTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesTest.class);

    @Value("${test.properties.encode-string}")
    private EncodeString encodeString;

    @Resource
    private TestProperties testProperties;

    @Test
    public void testEncodeString() {
        logger.info("encodeString: {}", encodeString);
        SecretKey key = AesKeys.newKey("123");
        Assert.assertEquals(encodeString.decodeString(key), "some password");

        logger.info("testProperties.getEncodeString(): {}", testProperties.getEncodeString());
        Assert.assertEquals(testProperties.getEncodeString().decodeString(key), "some password");
    }
}
