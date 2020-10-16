package com.wuhanyunzhong.itass.config;

import com.alibaba.excel.EasyExcel;
import com.wuhanyunzhong.itass.listener.DemoDataListener;
import com.wuhanyunzhong.itass.listener.HourDataListener;
import com.wuhanyunzhong.itass.service.DggService;
import com.wuhanyunzhong.itass.util.DepartDate;
import com.wuhanyunzhong.itass.util.JtlDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static jodd.util.ThreadUtil.sleep;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ServeScheduleTask {

    @Autowired
    DggService dggService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    public static String downLordUrl = "20326";

//    @Scheduled(cron = "*/30 * * * * ?")
    private void serveTasks(){
        System.err.println(new Date().getTime());
        stringRedisTemplate.opsForValue().get("isaddFirst");
        String time = String.valueOf(new Date().getTime());


        HttpURLConnection conn02 = null;
        InputStream inputStream = null;
        try {
            // 建立链接
            URL serveUrl=new URL("https://biapi.dgg188.cn/ReportServer?sessionID="+downLordUrl+"&_="+time);
            conn02=(HttpURLConnection) serveUrl.openConnection();

            conn02.setDoInput(true);
            conn02.setDoOutput(true);

            //连接指定的资源
            conn02.connect();
            inputStream=conn02.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                length = bis.read(buf);
            }
            bis.close();
            inputStream.close();
            sleep(2000);
            conn02.disconnect();


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
