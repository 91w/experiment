package org.wgh.handshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2021-11-07 10:25
 */
@Configuration
@Component
public class AliPayConfig {
    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    private String appId = "9021000136668268";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥  填上你的私钥
     */
    private String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFJGtFiJSE2J+7AkZwHjoKxEsPBzc4jNY4iAKl5feLcu2voNYENfB0KDpJZPm55I8KAjs4u8HPKcBCeUZk9g4XrNZkRbhKzXafQooqFtKCJR5+VuYnkSCbdSySpT6oMfJHtR+vzelUM8rqCBxJplBsDIh6/XgikJEacL7Zsh+ODuCxYeZUzNoZ8+4+EivQoq8tgVTqGDqIIFwysdJYE8cTPlOgZOAse48EYvPHO2U05EGA5S432OaYj8A9bw5QNjK1Q9dsiwzWix74iivmrbkKHDKXCP2RT18BPMf1g2SIT1e+79v5bFHXIKw6CmRQ9hqhqIc5KxP2HeE4aOm6HBVBAgMBAAECggEAbJpibhuUmjBhSpf2gOoHClVHrICESLqI2yAGmPBW1gbvXr1hFZoyA2307X1SPpHzI4TibcqpsqQp0Y8/Hoeewy0WzEAardmfExWhwedP2x0QHJohrtCwul3/97aWv3fOWzOhHzRlSWvd7ryi5milXR10Dm7HGdq2ocypwMWM+8B1cjIm2bAlHeCLAF69oKc6bqWudqAC0+ZKee4YdUuMPyM9rgIvmeD4ERU8TpqDoNgvalNhvPLkG+nmzxVXgaR3Q8xE4wYLI9/6Ugzw1sb4/nIwhY4MopaPz/aH01gdA4YV4sJtncII3wXxBjaqH8n4+3ybtK6ptZW6AS+HO6W6SQKBgQDCHwsC34kU4MxFli6Q7eC2/c/6pn2VkrVH0fcrf8Q7ieIbtdBzXxRXk1mtIHleIb0qo0M4zizdZbC3GF0TTn820WhOwnssEHAfGdOOBoJDocX+34tHzrqIdb2wGPYqaUfD7aU5d/EklgTzgtuWoCiumofmLa/kyGUMh/u7wys/DwKBgQCvlUga8SMFTCE6X/PvUyz0rnYl5tyeGf1lHbY5mwru6RwtuEJ4o9VoQiXIKR/bv69IHvehTPSP52szW7NBTTmNGsSrNtHaRWL2+OR6yN5K+0VSVm3Fnhgz4sQZ1cpIWH/k6q4nadXw1+ALECkLtBi/B69W4RRPLRyaTysGZNRmrwKBgH9LnAzmSR5Hy6xWL3MaiqF1D01uoCvVaMsQBOWhgy0H3h4NvAPr9Yr4NXYyNWRk+4daSFqX2RPV+2wVrfNbJlDsQVpkbiEXJm8+oFwiKInxK8VXEh6kke2qSBmSRNrK7ySsUgy9eoRjXBvTU+wSWpsk+rkWfKGqR5lQEuOtuE5BAoGAMu2I0Y+5ybKQPL9SIMuuUXY8/LderFj7f5XDoifPXwasTrKQTS+wGaJ+FPWgsPuDWtbompeYpBjKpkXl034TwFLQUpz+92GC2AHPO7IBkBwbukspYdV+TaZrnNa+6ocI6ZwMUZBIJqhm6q6QST2+o8IV4Iw+eMyr7DIkV6FtXi8CgYBiekIMWhZwe00yUiGItOfJlkLcHNU1mIKSRT9uVrpwdid4I34NkinuCgOu9JW7KHueBEAVBxDUOayqvID2TZgzQMWSVUoNOpd4cABeeABcpuNHDOJEVJluuxE7Zvo1UzWJWskDasHL/vN4Lgxk4VzBkTUS/HQVb0h4jtr//Z9FSw==";

    /**
     * 支付宝公钥,  填上你的公钥
     */
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4jRqxU86WGMI0ZpOeEh83XKVwDtDpWmBsXv64sx9DQ85BnjCr3O+6ibRO75NRBaxKgRAILUXPS/kNvQJbw1zQzwa9yFQwm6Qstk/50/f+3HKOkP/AOddsR5X5YRZbhB4zIFoXURgayFwwqwHEy84VDWO0L3jHl0A3+bKBMk8WSq3S/jmC+q/YuIlOywa9fdXx4sX8Y0Qm+osoaWM6O5X12PH9h5DV97SSuAJIDweIhVSvEaGeWX9MeAMS/yGOGwedizllHSC/9FVntIAuC4aiQpngup/TG+EGUDSrLtdpznSAOS8UpCgfdOMiGGkmi8Iup35rHJKdZfVa0Y6syOmxQIDAQAB";

    /**
     * 服务器异步通知页面路径需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    private String notifyUrl = "http://localhost:8081/order";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    private String returnUrl = "http://localhost:8081/order";

    /**
     * 签名方式
     */
    private String signType = "RSA2";

    /**
     * 字符编码格式
     */
    private String charset = "UTF-8";

    /**
     * 支付宝网关
     */
    private String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

    /**
     * 支付宝网关
     */
    private String logPath;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}
