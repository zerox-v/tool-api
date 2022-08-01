package fun.tiga.toolapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class WxConfig {
    public static String appId;
    public static String secret;

    @Value("${wx.appId}")
    private void setAppId(String appId) {
        WxConfig.appId = appId;
    }

    @Value("${wx.secret}")
    private void setSecret(String secret) {
        WxConfig.secret = secret;
    }
}
