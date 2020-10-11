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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static jodd.util.ThreadUtil.sleep;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    @Autowired
    DggService dggService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    public static String downLordUrl = "54753";
    public static List<DepartDate> firstData;
    public static List<JtlDate> jtlDates;
    public String isAddFirst = "";

    //3.添加定时任务
    @Scheduled(cron = "0 0 9,10,11,12,15,16,17,18,19,20 * * ?")
    private void configureTasks() {
        Calendar calendar = Calendar.getInstance();

        //获取年
        int year = calendar.get(Calendar.YEAR);
        //获取月 月从0开始，0表示1月，1表示2月
        int month = calendar.get(Calendar.MONTH)+1;
        /*
         * 获取日
         * 与"天"相关的时间分量:
         * DAY_OF_MONTH:月中的天
         * DAY_OF_WEEK:周中的天
         * DAY_OF_YEAR:年中的天
         *
         * DATE:月中的天。与DAY_OF_MONTH一致
         */
        int day = calendar.get(Calendar.DATE);

        int h = calendar.get(Calendar.HOUR_OF_DAY);

        if(null == firstData){
            String filePath = "src/main/webapp/temporary/depart.xlsx";
            EasyExcel.read(filePath, DepartDate.class, new DemoDataListener()).sheet().headRowNumber(0).doRead();
        }

        String daySt =year+"-"+month+"-"+day;

        if( ! daySt.equals(stringRedisTemplate.opsForValue().get("isaddFirst"))){

            Integer integer = dggService.addFirst(firstData);
            stringRedisTemplate.opsForValue().set("isaddFirst",daySt);
            if(h == 9){
                return;

            }
        }


        String jtlTime = year +"-" + month+"-"+ day +"-"+ h ;

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
        HttpURLConnection conn01 = null;
        InputStream inputStream = null;
        try {
            // 建立链接
            URL reflushUrl=new URL("https://biapi.dgg188.cn/ReportServer?op=fr_dialog&cmd=parameters_d&sessionID="+ downLordUrl);
            conn=(HttpURLConnection) reflushUrl.openConnection();
            //以Post方式提交表单，默认get方式
//            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
//            // post方式不能使用缓存
//            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            System.err.println(length);
            //保存文件
            while(length != -1)
            {
                length = bis.read(buf);
            }
            bis.close();
            inputStream.close();
            conn.disconnect();


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        sleep(5000);

        try
        {
            // 建立链接
            URL httpUrl=new URL("https://biapi.dgg188.cn/ReportServer?op=export&sessionID="+downLordUrl+"&format=excel&extype=simple");
            conn01=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
//            conn.setRequestMethod("post");
            conn01.setDoInput(true);
            conn01.setDoOutput(true);
            // post方式不能使用缓存
            conn01.setUseCaches(false);
            //连接指定的资源
            conn01.connect();
            //获取网络输入流
            inputStream=conn01.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {

                filePath += "/";

            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath+ jtlTime +".xlsx");
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            System.err.println(length);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            inputStream.close();
            bos.close();
            bis.close();
            conn01.disconnect();
            Integer isAdd = 0;
            do {
                System.err.println(isAdd);
                Integer addjtl = addjtl(filePath + jtlTime + ".xlsx");
                isAdd = addjtl;
            }while (isAdd < 1);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

//        addjtl(filePath+ "jtl01.xlsx");
    }

    Integer addjtl(String fileName){
        File file =  new File(fileName);
        EasyExcel.read(fileName, JtlDate.class, new HourDataListener()).sheet().headRowNumber(3).doRead();
        Integer integer = dggService.upData(jtlDates);
        return integer;
    }
}
