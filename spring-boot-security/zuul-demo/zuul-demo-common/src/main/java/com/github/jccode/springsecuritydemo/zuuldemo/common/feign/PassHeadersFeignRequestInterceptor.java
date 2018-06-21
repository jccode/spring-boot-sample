package com.github.jccode.springsecuritydemo.zuuldemo.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Pass all request headers to feign client.
 *
 */
@Slf4j
public class PassHeadersFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        if (log.isDebugEnabled()) {
            log.debug("[PassHeadersFeignRequestInterceptor]");
        }
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                Enumeration<String> headers = request.getHeaders(name);
                template.header(name, new EnumIterable<>(headers));
                if (log.isDebugEnabled()) {
                    log.debug(name + ":" + headers);
                }
            }
        }
    }


    /**
     * Enumeration to Iterable.
     * @param <T>
     */
    static class EnumIterable<T> implements Iterable<T> {

        private Enumeration<T> enumeration;

        EnumIterable(Enumeration<T> enumeration) {
            this.enumeration = enumeration;
        }

        @Override
        public Iterator<T> iterator() {
            return new IteratorAdapter<T>(enumeration);
        }
    }

    static class IteratorAdapter<T> implements Iterator<T> {

        private Enumeration<T> enumeration;

        IteratorAdapter(Enumeration<T> enumeration) {
            this.enumeration = enumeration;
        }

        @Override
        public boolean hasNext() {
            return enumeration.hasMoreElements();
        }

        @Override
        public T next() {
            return enumeration.nextElement();
        }
    }
}
