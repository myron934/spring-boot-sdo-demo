package interceptor;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.sdo.annotation.RequestLog;

public class RequestLogInterceptor extends HandlerInterceptorAdapter {
	
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("threadLocal beginTime"); 
	private final static Logger logger = LoggerFactory.getLogger("request");
//	private final static Logger rootLogger = LoggerFactory.getLogger("request");
	/**
	 * 执行前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod h = (HandlerMethod) handler;  
        RequestLog requestLog = findAnnotation(h, RequestLog.class);
        //没有声明需要权限,或者声明不验证权限
        if(requestLog!=null&&"true".equals(requestLog.forbidden())){
        	return true;
        }
        long beginTime = System.currentTimeMillis();  
        request.setAttribute("beginTime", beginTime);  
        startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
		return true;
	}
	/**
	 * 接口执行成功后调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return;
		}
		System.out.println("charset="+response.getContentType());
		HandlerMethod h = (HandlerMethod) handler;  
        RequestLog requestLog = findAnnotation(h, RequestLog.class);
        //没有声明需要权限,或者声明不验证权限
        if(requestLog!=null&&"true".equals(requestLog.forbidden())){
        	return;
        }
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
        long endTime = System.currentTimeMillis();  //结束时间  
		Gson gson = new Gson();  
		Map<String,Object> logInfo=new HashMap<String,Object>();
		logInfo.put("time", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(beginTime));
		logInfo.put("url", request.getRequestURI());
		logInfo.put("cost", ""+(endTime-beginTime)+" ms");
		logInfo.put("request", request.getParameterMap());
        logger.info(gson.toJson(logInfo));
	}

	/**
	 * 不管成功失败, 执行结束就调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
        startTimeThreadLocal.remove();  
	}
	
	private <T extends Annotation> T findAnnotation(HandlerMethod handler, Class<T> annotationType) {
        T annotation = handler.getBeanType().getAnnotation(annotationType);
        if (annotation != null) return annotation;
        return handler.getMethodAnnotation(annotationType);
	}
       
}
