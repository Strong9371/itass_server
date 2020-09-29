package com.wuhanyunzhong.itass.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wuhanyunzhong.itass.controller.testController;
import com.wuhanyunzhong.itass.service.DggService;
import com.wuhanyunzhong.itass.service.impl.DggServiceImpl;
import com.wuhanyunzhong.itass.util.DataInterface;
import com.wuhanyunzhong.itass.util.DepartDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DemoDataListener extends AnalysisEventListener<DepartDate> {

//    @Autowired
//    DggService dggService;

    private static final int BATCH_COUNT = 5;
    List<Map> list = new ArrayList<Map>();

    String fd = "顶呱呱集团";
    int pid = 1 ;
    int id = 2 ;
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param demoDAO
     */

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DepartDate data, AnalysisContext context) {
        System.err.println(data);

        Map mapTemp = new HashMap();

        if(! data.getFirstDepart().equals(fd)){
            fd = data.getFirstDepart();
            mapTemp.put("pid",1);
            mapTemp.put("name",data.getFirstDepart());
            pid = id;


            list.add(mapTemp);
            id ++ ;


            Map seMap = new HashMap();
            seMap.put("pid",pid);
            seMap.put("name",data.getSecond());
            list.add(seMap);
            id ++ ;

//
        }else{
            mapTemp.put("pid",pid);
            mapTemp.put("name",data.getSecond());
            list.add(mapTemp);
            id ++ ;

        }
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();

    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        testController.allDepart = list;
//        System.err.println(list);
//        DggService dgs = new DggServiceImpl();
//        Integer integer = dgs.insertDepart(list);
//        System.err.println(integer);
    }
}

