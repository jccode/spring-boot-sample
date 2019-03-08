package com.github.jccode.springboot.mdcdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Transfer
 *
 * @author 01372461
 */
@Getter
@AllArgsConstructor
public class Transfer {

    private String trasactionId;

    private String sender;

    private Long amount;

}
