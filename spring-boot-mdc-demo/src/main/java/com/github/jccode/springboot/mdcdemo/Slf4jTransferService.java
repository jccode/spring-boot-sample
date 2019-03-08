package com.github.jccode.springboot.mdcdemo;

import lombok.extern.slf4j.Slf4j;

/**
 * Slf4jTransferService
 *
 * @author 01372461
 */
@Slf4j
public class Slf4jTransferService extends TransferService {

    @Override
    protected void beforeTransfer(long amount) {
        log.info("Preparing to transfer {}$.", amount);
    }

    @Override
    protected void afterTransfer(long amount, boolean outcome) {
        log.info("Has transfer of {}$ completed successfully ? {}.", amount, outcome);
    }

}
