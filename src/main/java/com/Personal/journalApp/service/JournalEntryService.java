package com.Personal.journalApp.service;

import com.Personal.journalApp.entity.JournalEntry;
import com.Personal.journalApp.repository.HBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class JournalEntryService {

    @Autowired
    private HBaseRepository repository;

    @CachePut(value = "journalCache", key = "#result.id")
    public JournalEntry createEntry(JournalEntry entry) throws IOException {

        entry.setId(UUID.randomUUID().toString());

        entry.setDate(LocalDateTime.now());

        repository.save(entry);

        return entry;
    }

    @Cacheable(value = "journalCache", key = "#id")
    public JournalEntry getById(String id) throws IOException {

        System.out.println("Fetching from HBase...");

        return repository.getById(id);
    }

    @CacheEvict(value = "journalCache", key = "#id")
    public void deleteById(String id) {

        // Later implement delete in HBase
    }
}