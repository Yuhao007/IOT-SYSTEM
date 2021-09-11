package com.learn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.learn.dao.DataDao;
import com.learn.entity.DataEntity;
import com.learn.service.DataService;
import com.learn.service.SysConfigService;
import com.learn.task.Task;
import com.learn.utils.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The API interface provided by the front-end server,
 * All interfaces are defined in Controller,
 * The front end interacts with the server through an API interface
 */

@RestController

@RequestMapping("data")
public class DataController extends AbstractController implements ApplicationListener<ContextRefreshedEvent> {

    public static String DATA = null;
    public static String CMD = null;

    @Autowired
    private DataService dataService;

    /**
     * list
     */
    //
    @RequestMapping("/list")
    //
    public R list(@RequestParam Map<String, Object> params) {


        //query list
        Query query = new Query(params);
        /**
         * Service is an interface that calls this interface because Spring automatically calls the implementation class of this interface
         * Because there is only one implementation class for the interface in this project, it can be uniquely positioned
         */
        List<DataEntity> dataList = dataService.queryList(query);
        //The value returned by the interface, usually the value needed by the front end, is the method inside the package
        int total = dataService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(dataList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     *API
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<DataEntity> dataList = dataService.queryList(query);
        return R.ok().put("list", dataList);
    }

    @Autowired
    DataDao dataDao;
    @Autowired
    private Task task;


    @RequestMapping("/last")
    public R last(@RequestParam Map<String, Object> params) {
        return R.ok().put("data", this.dataDao.last(params));
    }

    @Autowired
    SysConfigService sysConfigService;

    @RequestMapping("/now")
    public R last() {
        return R.ok().put("warn",this.task.maxTest()).put("minwarn",this.task.minTest()) ;
    }


    /**
     *
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        DataEntity data = dataService.queryObject(id);

        return R.ok().put("data", data);
    }



    public static int STATE = 0;


    @RequestMapping("/control/{state}")
    public R info(@PathVariable("state") Integer state) throws InterruptedException {
        STATE = state;
        send(state+"ed");
        return R.ok() ;
    }

    public static int MODE = 0;
    @RequestMapping("/mode/{state}")
    public R mode(@PathVariable("state") Integer state) {
        MODE = state;
        return R.ok() ;
    }

    public static String WARN;


    @RequestMapping("/warn")
    public R warn( ) {
        return R.ok() .put("data",WARN);
    }


    public static void send(String cmd) throws InterruptedException {
        for(int i =0;i<5;i++){
            HttpClient.doGet("http://120.27.235.176/data/send?code=1011&cmd="+cmd);
            Thread.sleep(50);
        }
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                DataService dataService = (DataService) SpringContextUtils.getBean("dataService");
                Task task = (Task) SpringContextUtils.getBean("testTask");
                while (true){

                    try {
                        String bb =   HttpClient.doGet("http://120.27.235.176/data/now?code=1011");
                        JSONObject jsonObject = (JSONObject) JSON.parse(bb);
                        String s = jsonObject.getString("data");

                        if (StringUtils.isNotEmpty(s)) {


                            for (String b : s.split("#")) {
                                for (String a : b.split(" ")) {
                                    System.out.println(a);
                                    if (StringUtils.isNotEmpty(a)) {
                                        if (a.contains("temp")) {
                                            DataEntity data = new DataEntity();
                                            data.setType("1");
                                            data.setV(a.split("=")[1]);
                                            dataService.save(data);
                                        } else if (a.contains("humi")) {
                                            DataEntity data = new DataEntity();
                                            data.setType("2");
                                            data.setV(a.split("=")[1]);
                                            dataService.save(data);
                                        } else if (a.contains("aacx")) {
                                            DataEntity data = new DataEntity();
                                            data.setType("3");
                                            data.setV(a.split("=")[1]);
                                            dataService.save(data);
                                        } else if (a.contains("eco2")) {
                                            DataEntity data = new DataEntity();
                                            data.setType("4");
                                            data.setV(a.split("=")[1]);
                                            dataService.save(data);
                                        } else if (a.contains("aacy")) {
                                            DataEntity data = new DataEntity();
                                            data.setType("5");
                                            data.setV(a.split("=")[1]);
                                            dataService.save(data);
                                        } else if (a.contains("aacz")) {
                                            DataEntity data = new DataEntity();
                                            data.setType("6");
                                            data.setV(a.split("=")[1]);
                                            dataService.save(data);
                                        }
                                    }

                                }
                            }


                            Map<String,Boolean> min = task.minTest();
                            Map<String,Boolean> max = task.maxTest();
                            if(max.get("1")  || max.get("2")|| max.get("3")|| max.get("4")|| max.get("5")||max.get("6")  ||
                            min.get("1")  || min.get("2")|| min.get("3")|| min.get("4")|| min.get("5")||min.get("6")


                                    ){

                                DataController.send("11ed");

                            }else{
                                DataController.send("10ed");
                            }

                        }
                        Thread.sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        t.start();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        dataService.deleteBatch(ids);

        return R.ok();
    }

}

