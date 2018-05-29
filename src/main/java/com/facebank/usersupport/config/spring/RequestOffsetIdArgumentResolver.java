package com.facebank.usersupport.config.spring;

import com.facebank.usersupport.exception.PlateformException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 分页Id 自动装填工具
 **/
public class RequestOffsetIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterName().equals("pageId");
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String result = webRequest.getParameter(parameter.getParameterName());
        if (result == null) {
            throw new PlateformException();
        }
        return Long.valueOf(result);
    }

}
