package com.demo.lib.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import com.demo.lib.Utils.AppUtils;
import com.demo.lib.Utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;

import java.io.File;

public class WBUmengShareUtils {
    /**
     * WBUmengShareUtils()
     * //初始化
     * .init(this)
     * //设置平台
     * .setPlatform(SHARE_MEDIA.QQ)
     * //分享内容
     * .withText("xxxxxxxxx")
     * //分享图片
     * .withImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2802814504,1549340613&fm=27&gp=0.jpg")
     * //分享连接
     * .withUMWeb("http://www.baidu.com", "文博", "文博描述", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2802814504,1549340613&fm=27&gp=0.jpg")
     * .share(object :UMShareListener{
     * override fun onResult(p0: SHARE_MEDIA?) {
     * System.out.println("分享结果  $p0")
     * }
     * override fun onCancel(p0: SHARE_MEDIA?) {
     * System.out.println("分享取消  $p0")
     * }
     * override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
     * System.out.println("分享错误  $p0       $p1")
     * }
     * override fun onStart(p0: SHARE_MEDIA?) {
     * System.out.println("分享开始  $p0")
     * }
     * })
     */
    private ShareAction mShareAction = null;

    private SHARE_MEDIA mShareMedia = null;

    public WBUmengShareUtils(Activity activity) {
        mShareAction = new ShareAction(activity);
    }

    private UMImage withImageByUrl(String url) {
        UMImage image = new UMImage(AppUtils.getApp(), url);//网络图片
        withImage(image);
        return image;
    }

    private UMImage withImageByFile(File file) {
        UMImage image = new UMImage(AppUtils.getApp(), file);//本地文件
        withImage(image);
        return image;
    }

    private UMImage withImageByRes(int res) {
        UMImage image = new UMImage(AppUtils.getApp(), res);//资源文件
        withImage(image);
        return image;
    }

    private UMImage withImageByBitmap(Bitmap bitmap) {
        UMImage image = new UMImage(AppUtils.getApp(), bitmap);//bitmap文件
        withImage(image);
        return image;
    }

    private UMImage withImageByByte(byte[] bytes) {
        UMImage image = new UMImage(AppUtils.getApp(), bytes);//字节流
        withImage(image);
        return image;
    }


    private WBUmengShareUtils withImage(UMImage image) {
        mShareAction.withMedia(image);
        return this;
    }

    /**
     * 设置平台
     *
     * @param share_media
     * @return
     */
    public WBUmengShareUtils setPlatform(SHARE_MEDIA share_media) {
        this.mShareMedia = null;
        this.mShareMedia = share_media;
        if (null != mShareAction) {
            //传入平台
            mShareAction.setPlatform(share_media);
        }
        return this;
    }

    /**
     * 分享文本内容
     *
     * @param content
     * @return
     */
    public WBUmengShareUtils withText(String content) {
        if (null != mShareAction) {
            //分享内容
            mShareAction.withText(content);
        }
        return this;
    }

    /**
     * 通过url分享图片 带缩略图
     *
     * @param url      被分享图片url
     * @param urlThumb 被分享缩略图url
     * @return
     */
    public WBUmengShareUtils withImageUrl(String url, String urlThumb) {
        UMImage image = withImageByUrl(url);
        if (!"".equals(urlThumb)) {
            UMImage img = new UMImage(AppUtils.getApp(), urlThumb);//网络图片
            image.setThumb(img);
        }
        mShareAction.withMedia(image);
        return this;
    }

    /**
     * 通过url分享图片 缩略图为目标图
     *
     * @param url
     * @return
     */
    public WBUmengShareUtils withImageUrl(String url) {
        withImageUrl(url, url);
        return this;
    }

    /**
     * 通过File分享图片 带缩略图
     *
     * @param file     被分享图片
     * @param urlThumb 被分享缩略图
     * @return
     */
    public WBUmengShareUtils withImageFile(File file, String urlThumb) {
        UMImage image = withImageByFile(file);
        if (!"".equals(urlThumb)) {
            UMImage img = new UMImage(AppUtils.getApp(), urlThumb);//网络图片
            image.setThumb(img);
        }
        mShareAction.withMedia(image);
        return this;
    }

    /**
     * 通过File分享图片 不带缩略图
     *
     * @param file
     * @return
     */
    public WBUmengShareUtils withImageFile(File file) {
        withImage(withImageByFile(file));
        return this;
    }

    /**
     * 通过res分享图片 带缩略图
     *
     * @param res      被分享图片
     * @param urlThumb 被分享缩略图
     * @return
     */
    public WBUmengShareUtils withImageRes(int res, String urlThumb) {
        UMImage image = withImageByRes(res);
        if (!"".equals(urlThumb)) {
            UMImage img = new UMImage(AppUtils.getApp(), urlThumb);//网络图片
            image.setThumb(img);
        }
        mShareAction.withMedia(image);
        return this;
    }

    /**
     * 通过res分享图片 不带缩略图
     *
     * @param res
     * @return
     */
    public WBUmengShareUtils withImageRes(int res) {
        withImage(withImageByRes(res));
        return this;
    }

    /**
     * 通过Bitmap分享图片 不带缩略图
     *
     * @param bitmap
     * @return
     */
    public WBUmengShareUtils withImageBitmap(Bitmap bitmap) {
        withImage(withImageByBitmap(bitmap));
        return this;
    }

    public WBUmengShareUtils withImageBitmap(Bitmap bitmap, boolean isThumb) {
        UMImage image = withImageByBitmap(bitmap);
        if (isThumb && null != bitmap) {
            UMImage thumb = new UMImage(AppUtils.getApp(), bitmap);
            image.setThumb(thumb);
        }
        mShareAction.withMedia(image);
        return this;
    }

    /**
     * 通过byte[]数组分享图片 不带缩略图
     *
     * @param bytes
     * @return
     */
    public WBUmengShareUtils withImageByte(byte[] bytes) {
        withImage(withImageByByte(bytes));
        return this;
    }

    public WBUmengShareUtils withImageByte(byte[] bytes, boolean isThumb) {
        UMImage image = withImageByByte(bytes);
        if (isThumb && null != bytes) {
            UMImage thumb = new UMImage(AppUtils.getApp(), bytes);
            image.setThumb(thumb);
        }
        mShareAction.withMedia(image);
        return this;
    }

    /**
     * 分享链接
     *
     * @param targetUrl
     * @return
     */
    public WBUmengShareUtils withUMWeb(String targetUrl, String title, String description, String thumbUrl) {
        UMWeb umWeb = new UMWeb(targetUrl);
        umWeb.setTitle(title);
        umWeb.setDescription(description);
        umWeb.setThumb(withImageByUrl(thumbUrl));
        mShareAction.withMedia(umWeb);
        return this;
    }

    /**
     * 分享视频  只能分享网络视频
     *
     * @param vUrl        被分享的视频地址
     * @param thumbUrl    缩略图地址
     * @param description 分享描述
     * @return
     */
    public WBUmengShareUtils withUMVideo(String vUrl, String title, String thumbUrl, String description) {
        UMVideo umVideo = new UMVideo(vUrl);
        umVideo.setTitle(title);
        umVideo.setThumb(withImageByUrl(thumbUrl));//视频的缩略图
        umVideo.setDescription(description);//视频的描述
        mShareAction.withMedia(umVideo);
        return this;
    }

    /**
     * 分享音乐
     *
     * @param musicurl    被分享的音乐地址
     * @param title       标题
     * @param thumbUrl    缩略图地址
     * @param description 描述
     * @param skipUrl     跳转连接
     * @return
     */
    private WBUmengShareUtils withUMusic(String musicurl, String title, String thumbUrl, String description, String skipUrl) {
        UMusic uMusic = new UMusic(musicurl);
        uMusic.setTitle(title);//音乐的标题
        uMusic.setThumb(withImageByUrl(thumbUrl));//音乐的缩略图
        uMusic.setDescription(description);//音乐的描述
        uMusic.setmTargetUrl(skipUrl);//音乐的跳转链接
        mShareAction.withMedia(uMusic);
        return this;
    }

    /**
     * 分享表情
     *
     * @param emojiUrl
     * @param title
     * @param thumbUrl
     * @param description
     * @return
     */
    private WBUmengShareUtils withUMEmoji(String emojiUrl, String title, String thumbUrl, String description) {
        UMEmoji umEmoji = new UMEmoji(AppUtils.getApp(), emojiUrl);
        umEmoji.setTitle(title);
        umEmoji.setThumb(withImageByUrl(thumbUrl));
        umEmoji.setDescription(description);
        mShareAction.withMedia(umEmoji);
        return this;
    }

    private WBUmengShareUtils withUMMin(UMMin umMin) {
        mShareAction.withMedia(umMin);
        return this;
    }


    private WBUmengShareUtils setCallback(final SHARE_MEDIA sm, final IShareListener shareListener) {
        if (null != mShareAction) {
            //分享内容
            mShareAction.setCallback(new UMShareListener() {
                @Override
                public void onStart(com.umeng.socialize.bean.SHARE_MEDIA share_media) {
                    if (share_media.equals(sm)) {
                        if (null != shareListener) {
                            shareListener.onStart(share_media);
                        }
                    }
                }

                @Override
                public void onResult(com.umeng.socialize.bean.SHARE_MEDIA share_media) {
                    if (share_media.equals(sm)) {
                        shareListener.onResult(share_media);
                    }
                }

                @Override
                public void onError(com.umeng.socialize.bean.SHARE_MEDIA share_media, Throwable throwable) {
                    if (share_media.equals(sm)) {
                        shareListener.onError(share_media, throwable);
                    }
                }

                @Override
                public void onCancel(com.umeng.socialize.bean.SHARE_MEDIA share_media) {
                    if (share_media.equals(sm)) {
                        shareListener.onCancel(share_media);
                    }
                }
            });
        }
        return this;
    }

    private void share() {
        if (!AppUtils.isAppInstalled(AppUtils.getApp(), "com.tencent.mm") && mShareMedia == SHARE_MEDIA.WEIXIN) {
            ToastUtils.showLong("没有检测到安装微信，请安装后重试。");
            return;
        }
        if (!AppUtils.isAppInstalled(AppUtils.getApp(), "com.tencent.mm") && mShareMedia == SHARE_MEDIA.WEIXIN_CIRCLE) {
            ToastUtils.showLong("没有检测到安装微信，请安装后重试。");
            return;
        }
        if (!AppUtils.isAppInstalled(AppUtils.getApp(), "com.tencent.mm") && mShareMedia == SHARE_MEDIA.WEIXIN_FAVORITE) {
            ToastUtils.showLong("没有检测到安装微信，请安装后重试。");
            return;
        }
        if (!AppUtils.isAppInstalled(AppUtils.getApp(), "com.tencent.mobileqq")
                && !AppUtils.isAppInstalled(AppUtils.getApp(), "com.tencent.tim")
                && mShareMedia == SHARE_MEDIA.QQ) {
            ToastUtils.showLong("没有检测到安装QQ，请安装后重试。");
            return;
        }
        if (null != mShareAction) {
            mShareAction.share();
        }
    }


    public void share(final IShareListener iShareListener) {
        setCallback(mShareMedia, iShareListener)
                .share();
    }

    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);
    }

    public interface IShareListener {
        void onStart(SHARE_MEDIA var1);

        void onResult(SHARE_MEDIA var1);

        void onError(SHARE_MEDIA var1, Throwable var2);

        void onCancel(SHARE_MEDIA var1);
    }

}