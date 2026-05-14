package com.Personal.journalApp.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@org.springframework.context.annotation.Configuration
public class HBaseConfig {

    @Bean
    public Connection hbaseConnection() throws IOException {

        Configuration config = HBaseConfiguration.create();

        config.set("hbase.zookeeper.quorum", "hbase");
        config.set("hbase.zookeeper.property.clientPort", "2181");

        config.set("hbase.master", "hbase:16000");
        config.set("hbase.rootdir", "file:///tmp/hbase");

        config.set("hbase.cluster.distributed", "false");

        return ConnectionFactory.createConnection(config);
    }
}