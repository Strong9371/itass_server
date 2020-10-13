package com.wuhanyunzhong.itass.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wuhanyunzhong.itass.config.SaticScheduleTask;
import com.wuhanyunzhong.itass.controller.testController;
import com.wuhanyunzhong.itass.util.DepartDate;
import com.wuhanyunzhong.itass.util.JtlDate;

import java.util.*;


public class HourDataListener extends AnalysisEventListener<JtlDate> {



    private static final int BATCH_COUNT = 5;
    List<JtlDate> list = new ArrayList<JtlDate>();


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(JtlDate data, AnalysisContext context) {
        Calendar calendar = Calendar.getInstance();
        int h = SaticScheduleTask.isHourAddFirst;
        if(data.getFname().equals("合计")){
            data.setSname("合计");
        }

        switch(h) {
            case 10:
                data.setTime10((int)data.getPhoneDone());

                break;
            case 11:
                data.setTime11((int)data.getPhoneDone());
                break;
            case 12:
                data.setTime12((int)data.getPhoneDone());
                break;
            case 15:
                data.setTime15((int)data.getPhoneDone());
                break;
            case 16:
                data.setTime16((int)data.getPhoneDone());
                break;
            case 17:
                data.setTime17((int)data.getPhoneDone());
                break;
            case 18:
                data.setTime18((int)data.getPhoneDone());
                break;
            case 20:
                data.setTime20((int)data.getPhoneDone());
                break;
            default:
                break;
        }
        data.setUpType(h);
        list.add(data);
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
        SaticScheduleTask.jtlDates = list;
        testController.jtlDates = list;
    }
}

