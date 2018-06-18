package com.niepengfei.langjitianya.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jack
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * filter type总共有pre,routing,post,error四种类型
     */
    private static final String PRE_TYPE = "pre";

    private static final String ROUTING_TYPE = "routing";

    private static final String POST_TYPE = "post";

    private static final String ERROR_TYPE = "error";

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 值越小，越早执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器,如果是true,就执行run方法;如果是false,就不执行run方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                currentContext.getResponse().getWriter().write("token is empty");
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
