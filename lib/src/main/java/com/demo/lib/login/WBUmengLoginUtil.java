package com.demo.lib.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.demo.lib.Utils.AppUtils;
import com.demo.lib.model.ShareConfig;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class WBUmengLoginUtil {
    private static UMShareAPI mUmShareAPI;
    private static ShareConfig mShareConfig;

    public static void init(Context context1, ShareConfig shareConfig) {
        AppUtils.init(context1);
        mUmShareAPI = UMShareAPI.get(context1);
        mShareConfig = shareConfig;
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(context1).setShareConfig(config);
        UMConfigure.init(context1, shareConfig.getUmAppKey(), "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        WBUmengLoginUtil.setKeySecretQQ();
        WBUmengLoginUtil.setKeySecretWeixin();
        WBUmengLoginUtil.setKeySecretSina();
    }

    public static void setKeySecretWeixin() {
        PlatformConfig.setWeixin(mShareConfig.getWxId(), mShareConfig.getWxSecret());
    }

    public static void setKeySecretQQ() {
        PlatformConfig.setQQZone(mShareConfig.getQqId(), mShareConfig.getQqSecret());
    }

    public static void setKeySecretSina() {
        PlatformConfig.setSinaWeibo(mShareConfig.getWeiboId(), mShareConfig.getWeiboSecret(), mShareConfig.getWeiboRedirectUrl());
    }


    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);
    }

    public static void onDestroy(Activity activity) {
        UMShareAPI.get(activity).release();
    }


    public static void loginBySina(Activity activity) {
        login(activity, SHARE_MEDIA.SINA);
    }

    public static void loginByWeixin(Activity activity) {
        login(activity, SHARE_MEDIA.WEIXIN);
    }

    public static void loginByQQ(Activity activity) {
        login(activity, SHARE_MEDIA.QQ);
    }

    private static void login(Activity activity, final SHARE_MEDIA platform) {
        mUmShareAPI.getPlatformInfo(activity, platform, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
            }
        });
    }

    public static void loginBySinaLoginDoOauthVerify(Activity activity, OnSocialSinaLoginListener onSocialSinaLoginListener) {
        loginSinaDoOauthVerify(activity, onSocialSinaLoginListener);
    }

    public static void loginByWeixinLoginDoOauthVerify(Activity activity, OnSocialWXLoginListener onSocialWXLoginListener) {
        loginWXDoOauthVerify(activity, onSocialWXLoginListener);
    }

    public static void loginByQQLoginDoOauthVerify(Activity activity, OnSocialQQLoginListener onSocialQQLoginListener) {
        loginQQDoOauthVerify(activity, onSocialQQLoginListener);
    }

    private static void loginQQDoOauthVerify(final Activity activity, final OnSocialQQLoginListener onSocialQQLoginListener) {
        mUmShareAPI.doOauthVerify(activity, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                onSocialQQLoginListener.qqOnStart(share_media);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, final Map<String, String> map1) {
                mUmShareAPI.getPlatformInfo(activity, share_media, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        onSocialQQLoginListener.qqOnStart(share_media);
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map2) {
                        onSocialQQLoginListener.qqOnComplete(share_media, i, map1, map2);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        onSocialQQLoginListener.qqOnError(share_media, i, throwable);
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        onSocialQQLoginListener.qqOnCancel(share_media, i);
                    }
                });

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                onSocialQQLoginListener.qqOnError(share_media, i, throwable);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                onSocialQQLoginListener.qqOnCancel(share_media, i);
            }
        });
    }

    private static void loginWXDoOauthVerify(final Activity activity, final OnSocialWXLoginListener onSocialWXLoginListener) {
        mUmShareAPI.doOauthVerify(activity, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                onSocialWXLoginListener.wxOnStart(share_media);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, final Map<String, String> map1) {
                mUmShareAPI.getPlatformInfo(activity, share_media, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        onSocialWXLoginListener.wxOnStart(share_media);
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map2) {
                        onSocialWXLoginListener.wxOnComplete(share_media, i, map1, map2);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        onSocialWXLoginListener.wxOnError(share_media, i, throwable);
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        onSocialWXLoginListener.wxOnCancel(share_media, i);
                    }
                });
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                onSocialWXLoginListener.wxOnError(share_media, i, throwable);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                onSocialWXLoginListener.wxOnCancel(share_media, i);
            }
        });
    }

    private static void loginSinaDoOauthVerify(final Activity activity, final OnSocialSinaLoginListener onSocialSinaLoginListener) {
        mUmShareAPI.doOauthVerify(activity, SHARE_MEDIA.SINA, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                onSocialSinaLoginListener.sinaOnStart(share_media);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, final Map<String, String> map1) {
                mUmShareAPI.getPlatformInfo(activity, share_media, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        onSocialSinaLoginListener.sinaOnStart(share_media);
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map2) {
                        onSocialSinaLoginListener.sinaOnComplete(share_media, i, map1, map2);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        onSocialSinaLoginListener.sinaOnError(share_media, i, throwable);
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        onSocialSinaLoginListener.sinaOnCancel(share_media, i);
                    }
                });

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                onSocialSinaLoginListener.sinaOnError(share_media, i, throwable);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                onSocialSinaLoginListener.sinaOnCancel(share_media, i);
            }
        });
    }

    public interface OnSocialQQLoginListener {

        /**
         * @desc 授权开始的回调
         */
        void qqOnStart(SHARE_MEDIA share_media);

        /**
         * @param platform           平台名称
         * @param action             行为序号，开发者用不上
         * @param authorizationMap   获取到的权鉴信息
         * @param qqAuthorizationMap qq返回的权鉴信息
         */
        void qqOnComplete(SHARE_MEDIA platform, int action, Map<String, String> authorizationMap, Map<String, String> qqAuthorizationMap);

        /**
         * @param platform 平台名称
         * @param action   行为序号，开发者用不上
         * @param t        错误原因
         * @desc 授权失败的回调
         */
        void qqOnError(SHARE_MEDIA platform, int action, Throwable t);

        /**
         * @param platform 平台名称
         * @param action   行为序号，开发者用不上
         * @desc 授权取消的回调
         */
        void qqOnCancel(SHARE_MEDIA platform, int action);
    }

    public interface OnSocialWXLoginListener {

        void wxOnStart(SHARE_MEDIA share_media);

        void wxOnComplete(SHARE_MEDIA share_media, int i, Map<String, String> authorizationMap, Map<String, String> wxAuthorizationMap);

        void wxOnError(SHARE_MEDIA share_media, int i, Throwable throwable);

        void wxOnCancel(SHARE_MEDIA share_media, int i);
    }

    public interface OnSocialSinaLoginListener {

        void sinaOnStart(SHARE_MEDIA share_media);

        void sinaOnComplete(SHARE_MEDIA share_media, int i, Map<String, String> authorizationMap, Map<String, String> sinaAuthorizationMap);

        void sinaOnError(SHARE_MEDIA share_media, int i, Throwable throwable);

        void sinaOnCancel(SHARE_MEDIA share_media, int i);
    }
}
