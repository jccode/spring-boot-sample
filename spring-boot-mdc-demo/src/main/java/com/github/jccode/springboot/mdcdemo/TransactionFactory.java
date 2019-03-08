package com.github.jccode.springboot.mdcdemo;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TransactionFactory
 *
 * @author 01372461
 */
public class TransactionFactory {

    private AtomicInteger id = new AtomicInteger(0);

    private String[] users = "Iris,Lucia,Danny,Dante,Drake,Malik,Ellis,Hanna,Phoenix,Kira,Alina,Alison,Jameson,Jakob,Dallas,Abbie,Lorenzo,Camille,Dustin,Erika,Allie,Fiona,Kingston Luna,Hugo,Justice,Carmen,Graham,Jaime,Miriam".split(",");

    private Random r = new Random();

    public Transfer newInstance() {
        return new Transfer(String.valueOf(id.incrementAndGet()), users[r.nextInt(users.length)], Integer.valueOf(r.nextInt(1000)).longValue());
    }
}
