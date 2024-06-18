package com.cdut.tiktok.common.config;


import com.cdut.tiktok.common.annotation.UserId;
import com.cdut.tiktok.common.exception.ExceptionCode;
import com.cdut.tiktok.common.exception.MyException;
import com.cdut.tiktok.common.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.MissingFormatArgumentException;

@Component
@Slf4j
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(String.class) && parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = null;

        // 对于GET请求，尝试从URL参数获取token
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            token = request.getParameter("token");
        } else {
            // 对于非GET请求，尝试从请求体或表单中获取token
            if ("application/json".equals(request.getContentType())) {
                // 处理JSON请求体
                token = extractTokenFromJsonBody(request);
            } else {
                // 处理表单提交
                token = request.getParameter("token");
            }
        }

        if (token == null || token.trim().isEmpty()) {
            throw new MyException(ExceptionCode.INVALID_ARGUMENT);
        }

        return getUserIdFromToken(token);
    }

    private String extractTokenFromJsonBody(HttpServletRequest request){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> requestBody = mapper.readValue(request.getInputStream(), Map.class);
            return (String) requestBody.get("token");
        } catch (IOException e) {
            throw new MyException(ExceptionCode.INVALID_ARGUMENT);
        }
    }

    private Long getUserIdFromToken(String token) {
        // 解析Token并返回UserId
        return jwtUtils.getUserIdFromToken(token);
    }
}


