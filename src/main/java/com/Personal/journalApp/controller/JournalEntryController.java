package com.Personal.journalApp.controller;

import com.Personal.journalApp.entity.JournalEntry;
import com.Personal.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

        public static int app_id = new Random().nextInt(9999);


    @Autowired
    private JournalEntryService service;

    @PostMapping
    public ResponseEntity<JournalEntry> create(
            @RequestBody JournalEntry entry
    ) throws IOException {

        return ResponseEntity.ok(
                service.createEntry(entry)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable String id
    ) throws IOException {

        JournalEntry entry = service.getById(id);
        System.out.println(app_id + "app id is printed here");
        if(entry == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(entry);
    }
}