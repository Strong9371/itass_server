package com.wuhanyunzhong.itass.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wuhanyunzhong.itass.controller.testController;
import com.wuhanyunzhong.itass.util.DepartDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HourDataListener extends AnalysisEventListener<DepartDate> {



    private static final int BATCH_COUNT = 5;
    List<Map> list = new ArrayList<Map>();


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
    }
}

