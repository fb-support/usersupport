package com.facebank.usersupport.online.process.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.config.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected SystemProperties systemProperties;

    protected ThreadLocal<String> requestParamLocal = new ThreadLocal<String>();
    protected ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
    protected ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ControllerTimeWatch");
    private boolean flag = false;

    @ModelAttribute
    protected void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        startTimeThreadLocal.set(System.currentTimeMillis());//线程绑定变量（该数据只有当前请求的线程可见）
        this.requestLocal.set(request);
        this.responseLocal.set(response);
    }

    //对象转换
    protected <T> T convertObject(Class<T> destClassType) {
        String jsonData = requestParamLocal.get();
        if (null != jsonData) {
            T t = null;
            try {
                t = destClassType.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return t;
        }
        return (T) JSON.parseObject((String) jsonData, destClassType);
    }


//    //日志记录
//    private void recordResponseLogs(MicRestModel micRestModel) {
//        //本地IP地址
//        String localIp = requestLocal.get().getLocalAddr();
//        //请求URL
//        String url = requestLocal.get().getRequestURL().toString();
//        //变化后的
//        String resultURL = replaceLocalIPtoReqURL(localIp, url);
//        //请求接口入参
//        String inPara = requestParamLocal.get();
//        //耗时
//        long endTime = System.currentTimeMillis();//结束时间
//        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
//        long consumeTime = endTime - beginTime;//消耗的时间
//        //响应
//        logger.info(" resposeIP:" + localIp + " url:" + resultURL + " params:" + inPara + " respone:" + JSON.toJSONString(micRestModel) + " consume(ms):" + consumeTime);
//    }


    /**
     * host替换
     *
     * @param localIP
     * @param reqURL
     * @return
     */
    private static String replaceLocalIPtoReqURL(String localIP, String reqURL) {
        if (localIP == null || reqURL == null) {
            return "";
        }
        String result = "";
        try {
            if (reqURL.indexOf("//") > -1 && reqURL.lastIndexOf(":") > -1) {
                result = reqURL.substring(0, reqURL.indexOf("//") + 2) + localIP + reqURL.substring(reqURL.lastIndexOf(":"), reqURL.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    //获取RequestDto中的公共参数
//    protected RequestDto getRequestDto() {
//        return JSON.parseObject(requestParamLocal.get(), RequestDto.class);
//    }

    //获取json对象
    protected JSONObject getJSONObject() {
        if (null != requestParamLocal.get()) {
            return JSON.parseObject(requestParamLocal.get());
        }
        return JSON.parseObject("{}");
    }



    //成功输出
    protected RestModel success(Object obj) {
        return success(MessageKeyEnum.SUCCESS,obj);
    }

    protected RestModel success(MessageKeyEnum messageKeyEnum, Object obj) {
        return new RestModel(messageKeyEnum.getCode(),messageKeyEnum.getMessage(), obj);
    }

    protected RestModel success(String code, String msg, Object obj) {
        return new RestModel(code,msg, obj);
    }

    //异常输出
    protected RestModel excpRestModel() {
        return new RestModel(MessageKeyEnum.ERROR);
    }

    protected RestModel excpRestModel(MessageKeyEnum messageKeyEnum) {
        return  excpRestModel(messageKeyEnum.getCode(),messageKeyEnum.getMessage());
    }

    protected RestModel excpRestModel(String code, String msg) {
        return excpRestModel(code,msg, null);
    }

    protected RestModel excpRestModel(String code, String msg, Object obj) {
        return new RestModel(code,msg, obj);
    }
}
