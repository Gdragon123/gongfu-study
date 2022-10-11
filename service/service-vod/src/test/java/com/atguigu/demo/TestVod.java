package com.atguigu.demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/12 0012
 * @描述
 **/
public class TestVod {

    @Test
    public void getPlayInfo() {
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tN8AaoSkXAaBKMsJTki", "ACX0RoQXeqE5MBmpI3zjKBI7oyOU7r");
        //创建获取是视频地址的response
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    @Test
    public void testGetPlayAuth() {
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tN8AaoSkXAaBKMsJTki", "ACX0RoQXeqE5MBmpI3zjKBI7oyOU7r");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = getVideoPlayAuth(client);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        //创建获取是视频地址的request
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        //设置视频ID
        request.setVideoId("25e97669234640ea9d377283eb178559");
        //获取数据
        return client.getAcsResponse(request);
    }

    /*获取播放凭证函数*/
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("25e97669234640ea9d377283eb178559");
        return client.getAcsResponse(request);
    }


}
