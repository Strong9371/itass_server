package com.wuhanyunzhong.itass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    //3.添加定时任务
    @Scheduled(cron = "0 0 9,10,11,12,15,16,17,18,20 ? * MON-FRI")
    //或直接指定时间间隔，例如：5秒
//    @Scheduled(fixedRate=5000)
    private void configureTasks() {
        //System.out.println("fileName---->"+filePath);
        String filePath = "src/main/webapp/temporary/";
        //创建不同的文件夹目录
        File file=new File(filePath);
        //判断文件夹是否存在
        if (!file.exists())
        {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
            return;
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try
        {
            // 建立链接
            URL httpUrl=new URL("https://biapi.dgg188.cn/ReportServer?op=export&sessionID=65201&format=excel&extype=simple");
            conn=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
//            conn.setRequestMethod("post");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {

                filePath += "/";

            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath+"jtl.xlsx");
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }

    }
}
