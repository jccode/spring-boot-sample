package com.github.jccode.springboot.mdcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TransferService {

    private Logger log = LoggerFactory.getLogger(TransferService.class);
 
    public boolean transfer(long amount) {
        beforeTransfer(amount);

        // connects to the remote service to actually transfer money
        log.info("transfer {}$ ...", amount);
        boolean result = true;

        afterTransfer(amount, result);
        return result;
    }
 
    abstract protected void beforeTransfer(long amount);
 
    abstract protected void afterTransfer(long amount, boolean outcome);
}