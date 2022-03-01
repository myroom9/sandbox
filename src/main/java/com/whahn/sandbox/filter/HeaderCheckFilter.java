package com.whahn.sandbox.filter;

import com.whahn.sandbox.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(10)
public class HeaderCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isNeedCheckHeaderPath(request)) {
            log.info("응 얘는 체크해야되");
        }

        filterChain.doFilter(request, response);
    }

    private boolean isNeedCheckHeaderPath(HttpServletRequest request) {
        String url = request.getRequestURI();
        return url.startsWith(Constant.API_V1_PREFIX);
    }
}
