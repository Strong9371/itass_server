package com.wuhanyunzhong.itass;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class ItassApplicationTests {

    @Test
    void contextLoads() {
        System.err.println("ksldksl");
        String filePath = "src/main/webapp/temporary/jtl.xlsx";
        File file =  new File(filePath);
        System.err.println(file.length());

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, DemoData.class, new DemoDataListener()).sheet().doRead();
    }

}
