package com.demo.lib.model;


public class ShareConfig {

    private String wxId;

    private String wxSecret;

    private String qqId;

    private String weiboId;

    private String weiboRedirectUrl = "http://open.weibo.com/apps/3387986310/privilege/oauth";

    private String weiboScope = "email";

    private boolean debug;

    private String qqSecret;

    private String weiboSecret;

    private String umAppKey;

    public String getWxId() {
        return wxId;
    }

    public ShareConfig setWxId(String wxId) {
        this.wxId = wxId;
        return this;
    }

    public String getWxSecret() {
        return wxSecret;
    }

    public ShareConfig setWxSecret(String wxSecret) {
        this.wxSecret = wxSecret;
        return this;
    }

    public String getQqId() {
        return qqId;
    }

    public ShareConfig setQqId(String qqId) {
        this.qqId = qqId;
        return this;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public ShareConfig setWeiboId(String weiboId) {
        this.weiboId = weiboId;
        return this;
    }

    public String getWeiboRedirectUrl() {
        return weiboRedirectUrl;
    }

    public ShareConfig setWeiboRedirectUrl(String weiboRedirectUrl) {
        this.weiboRedirectUrl = weiboRedirectUrl;
        return this;
    }

    public String getWeiboScope() {
        return weiboScope;
    }

    public ShareConfig setWeiboScope(String weiboScope) {
        this.weiboScope = weiboScope;
        return this;
    }

    public boolean isDebug() {
        return debug;
    }

    public ShareConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public String getQqSecret() {
        return qqSecret;
    }

    public ShareConfig setQqSecret(String qqSecret) {
        this.qqSecret = qqSecret;
        return this;
    }

    public String getWeiboSecret() {
        return weiboSecret;
    }

    public ShareConfig setWeiboSecret(String weiboSecret) {
        this.weiboSecret = weiboSecret;
        return this;
    }

    public String getUmAppKey() {
        return umAppKey;
    }

    public ShareConfig setUmAppKey(String umAppKey) {
        this.umAppKey = umAppKey;
        return this;
    }
}
