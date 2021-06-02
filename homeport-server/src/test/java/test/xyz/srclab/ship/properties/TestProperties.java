package test.xyz.srclab.ship.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import xyz.srclab.ship.properties.EncodeString;

@ConfigurationProperties(prefix = "test.properties")
@Component
public class TestProperties {

    private EncodeString encodeString;

    public EncodeString getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(EncodeString encodeString) {
        this.encodeString = encodeString;
    }
}
