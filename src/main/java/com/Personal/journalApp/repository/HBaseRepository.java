package com.Personal.journalApp.repository;

import com.Personal.journalApp.entity.JournalEntry;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDateTime;

@Repository
public class HBaseRepository {

    private static final String TABLE_NAME = "journal";
    private static final String CF = "cf";

    @Autowired
    private Connection connection;

    public void save(JournalEntry entry) throws IOException {

        Table table = connection.getTable(TableName.valueOf(TABLE_NAME));

        Put put = new Put(Bytes.toBytes(entry.getId()));

        put.addColumn(Bytes.toBytes(CF),
                Bytes.toBytes("title"),
                Bytes.toBytes(entry.getTitle()));

        put.addColumn(Bytes.toBytes(CF),
                Bytes.toBytes("content"),
                Bytes.toBytes(entry.getContent()));

        put.addColumn(Bytes.toBytes(CF),
                Bytes.toBytes("date"),
                Bytes.toBytes(entry.getDate().toString()));

        table.put(put);

        table.close();
    }

    public JournalEntry getById(String id) throws IOException {

        Table table = connection.getTable(TableName.valueOf(TABLE_NAME));

        Get get = new Get(Bytes.toBytes(id));

        Result result = table.get(get);

        if(result.isEmpty()) {
            return null;
        }

        JournalEntry entry = new JournalEntry();

        entry.setId(id);

        entry.setTitle(Bytes.toString(
                result.getValue(Bytes.toBytes(CF),
                        Bytes.toBytes("title"))
        ));

        entry.setContent(Bytes.toString(
                result.getValue(Bytes.toBytes(CF),
                        Bytes.toBytes("content"))
        ));

        entry.setDate(LocalDateTime.parse(
                Bytes.toString(
                        result.getValue(Bytes.toBytes(CF),
                                Bytes.toBytes("date"))
                )
        ));

        table.close();

        return entry;
    }
}