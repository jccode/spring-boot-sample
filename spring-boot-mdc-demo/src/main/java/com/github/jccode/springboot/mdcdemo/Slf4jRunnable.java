package com.github.jccode.springboot.mdcdemo;

import org.slf4j.MDC;

/**
 * Slf4jRunnable
 *
 * @author 01372461
 */
public class Slf4jRunnable implements Runnable {

    private final Transfer tx;

    public Slf4jRunnable(Transfer tx) {
        this.tx = tx;
    }

    @Override
    public void run() {
        MDC.put("transaction.id", tx.getTrasactionId());
        MDC.put("transaction.owner", tx.getSender());
        new Slf4jTransferService().transfer(tx.getAmount());
        MDC.clear();
    }
}
