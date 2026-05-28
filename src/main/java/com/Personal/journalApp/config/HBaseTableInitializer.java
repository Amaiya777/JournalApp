package com.Personal.journalApp.config;

import jakarta.annotation.PostConstruct;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HBaseTableInitializer {

    private static final String TABLE_NAME = "journal";
    private static final String COLUMN_FAMILY = "cf";

    @Autowired
    private Connection connection;

    @PostConstruct
    public void init() {

        try {

            Admin admin = connection.getAdmin();

            TableName tableName = TableName.valueOf(TABLE_NAME);

            if (!admin.tableExists(tableName)) {

                HTableDescriptor tableDescriptor =
                        new HTableDescriptor(tableName);

                tableDescriptor.addFamily(
                        new HColumnDescriptor(COLUMN_FAMILY)
                );

                admin.createTable(tableDescriptor);

                System.out.println("Journal table created.");
            }
            else {

                System.out.println("Journal table already exists.");
            }

            admin.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}