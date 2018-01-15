package com.github.jccode.springcloud.integrateddemo.common.event;

import com.github.jccode.springcloud.integrateddemo.common.event.annotation.EventHandler;
import org.springframework.stereotype.Component;

public class EventHandlerTest {

    @Component
    static class EventHandlerTestBean{

        @EventHandler("foo")
        public void fooHandler() {
            System.out.println("foo handler");
        }

        @EventHandler("bar")
        public void barHandler() {
            System.out.println("bar handler");
        }
    }


}
