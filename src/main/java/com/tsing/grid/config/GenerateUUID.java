package com.tsing.grid.config;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * UUID 作为主键
 *使用方法为
 * @KeySql(genId = GenerateUUID.class)
 *@GeneratedValue(strategy = GenerationType.IDENTITY)
 */
public class GenerateUUID implements GenId {
    public String genId(String s, String s1) {
        return UUID.randomUUID().toString().replace("-","");
    }
}
