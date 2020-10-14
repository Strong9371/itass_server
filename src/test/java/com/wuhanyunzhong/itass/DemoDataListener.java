package com.wuhanyunzhong.itass;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wuhanyunzhong.itass.config.SaticScheduleTask;
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
    //    List<DepartDate> list = new ArrayList<DepartDate>();
    List<Map> list = new ArrayList<Map>();

    String fd = "合计";
    int pid = 1 ;
    int id = 1 ;


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DepartDate data, AnalysisContext context) {
//        list.add(data);

        Map mapTemp = new HashMap();



        if(! data.getFname().equals(fd)){
            fd = data.getFname();
            mapTemp.put("pid",1);
            mapTemp.put("name",data.getFname());
            pid = id;


            list.add(mapTemp);
            id ++ ;


            Map seMap = new HashMap();
            seMap.put("pid",pid);
            seMap.put("name",data.getSname());
            list.add(seMap);
            id ++ ;

        }else{
            mapTemp.put("pid",pid);
            mapTemp.put("name",data.getSname());
            list.add(mapTemp);
            id ++ ;

        }
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
//        SaticScheduleTask.firstData = list;
        testController.allDepart = list;

    }
}

