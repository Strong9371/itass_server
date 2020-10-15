package com.wuhanyunzhong.itass.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuhanyunzhong.itass.service.DggService;
import com.wuhanyunzhong.itass.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("dgg")
public class DggController extends BaseController{

    public static List allFirstDepart = new ArrayList();
    public static List allDepart = new ArrayList();

    PasswordEncoder bp = new BCryptPasswordEncoder();

    @Autowired
    DggService dggService;

    @GetMapping("login")
    @ResponseBody
    public JsonResult login(@RequestParam String formdata){
        JSONObject dggObject = JSONObject.parseObject(formdata);
        JsonResult jr = new JsonResult();

        Map byname = dggService.findByname(dggObject);
        boolean matches = bp.matches(dggObject.getString("password"), (String) byname.get("password"));
        if(matches){
//            登录成功
            String token = "Authorization:" + Math.random();

            Map result = new HashMap();
            result.put("user",byname);
            result.put("token",token);

//            添加token
            dggObject.put("token",token);
            dggService.upToken(dggObject);

            jr.setState(SUCCESS);
            jr.setMessage("登录成功");
            jr.setData(result);


        }else{
            jr.setState(ERROR);
            jr.setMessage("账户名或密码错误");
            jr.setMessage("账户名或密码错误");
        }
        return jr;
    }


    @GetMapping("getAll")
    @ResponseBody
    public JsonResult getAll(@RequestParam String formdata){
        JSONObject dggObject = JSONObject.parseObject(formdata);

        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        dggObject.put("h",h);

        JsonResult jr = new JsonResult();

//        获取一级部门的信息
        List allde = dggService.findAllde(dggObject);

        if(allFirstDepart.isEmpty()){
            allFirstDepart.clear();
            getChildNodes(0, allde);
        }

        Map allData = dggService.getAllData(dggObject);

        allData.put("firstDepart",allFirstDepart);

        jr.setState(SUCCESS);
        jr.setMessage("查询成功");
        jr.setData(allData);
        return jr;
    }

    @GetMapping("getMini")
    @ResponseBody
    public JsonResult getMini(@RequestParam String formdata){
        JSONObject dggObject = JSONObject.parseObject(formdata);
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        dggObject.put("h",h);
        JsonResult jr = new JsonResult();


        Map miniData = dggService.getMini(dggObject);

        jr.setState(SUCCESS);
        jr.setMessage("查询成功");
        jr.setData(miniData);
        return jr;

    }

    @GetMapping("getBig")
    @ResponseBody
    public JsonResult getBig(@RequestParam String formdata){
        JSONObject dggObject = JSONObject.parseObject(formdata);
//        Calendar calendar = Calendar.getInstance();
//        int h = calendar.get(Calendar.HOUR_OF_DAY);
//        dggObject.put("h",h);
        JsonResult jr = new JsonResult();

        Map bigData = dggService.getBig(dggObject);
        jr.setState(SUCCESS);
        jr.setMessage("查询成功");
        jr.setData(bigData);
        return jr;

    }

    @GetMapping("getDayCompare")
    @ResponseBody
    public JsonResult getDayCompare(@RequestParam String formdata){
        JSONObject dggObject = JSONObject.parseObject(formdata);

        JsonResult jr = new JsonResult();

        Map dayCompare = dggService.getDayCompare(dggObject);
        jr.setState(SUCCESS);
        jr.setMessage("查询成功");
        jr.setData(dayCompare);
        return jr;
    }

    @GetMapping("getWeekCompare")
    @ResponseBody
    public JsonResult getWeekCompare(@RequestParam String formdata){
        JSONObject dggObject = JSONObject.parseObject(formdata);

        JsonResult jr = new JsonResult();

        Map weekCompare = dggService.getWeekCompare(dggObject);
        jr.setState(SUCCESS);
        jr.setMessage("查询成功");
        jr.setData(weekCompare);
        return jr;
    }

    @GetMapping("getPartCompareInfo")
    @ResponseBody
    public JsonResult getPartCompareInfo(@RequestParam String formdata){
        JSONObject dggObject = JSONObject.parseObject(formdata);

        JsonResult jr = new JsonResult();

        Map partCompare = dggService.findPartCompareInfo(dggObject);
        jr.setState(SUCCESS);
        jr.setMessage("查询成功");
        jr.setData(partCompare);
        return jr;
    }

    /**
     * 递归处理   数据库树结构数据->树形json
     * @param nodeId
     * @param nodes
     * @return
     */
    public List getNodeJson(Integer nodeId, List<Map> nodes){

        //当前层级当前node对象
//        Node cur = nodes.get(nodeId);
//        Map cur = nodes.get(nodeId);
        //当前层级当前点下的所有子节点（实战中不要慢慢去查,一次加载到集合然后慢慢处理）
        List<Map> childList = getChildNodes(nodeId,nodes);
        List childTree = new ArrayList();
        for(Map node:childList){
            Map o = new HashMap();
            o.put("value",node.get("pid")+"-"+node.get("id"));
            o.put("key",node.get("pid")+"-"+node.get("id"));
            o.put("title",node.get("pname"));
            Integer did = (Integer) node.get("id");
            List childs = getNodeJson(did,nodes);
            if(!childs.isEmpty()){
                o.put("children",childs);
            }
            childTree.add(o);
        }

        return childTree;
    }

    /**
     * 获取当前节点的所有子节点
     * @param nodeId
     * @param nodes
     * @return
     */
    public List<Map> getChildNodes(Integer nodeId, List<Map> nodes) {
        List list = new ArrayList<>();
        for (Map cm : nodes) {
            if (((int) cm.get("pid")) <= 1) {
                allFirstDepart.add(cm);
            }
        }
        return list;
    }

}
