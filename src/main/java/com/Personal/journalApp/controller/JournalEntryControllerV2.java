package com.Personal.journalApp.controller;

import com.Personal.journalApp.entity.JournalEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    private List<JournalEntry> journalEntries = new ArrayList<>();


    // GET ALL
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getALL() {
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }


    // CREATE
    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody JournalEntry myEntry) {

        myEntry.setDate(LocalDateTime.now());
        journalEntries.add(myEntry);

        return new ResponseEntity<>("Entry Created Successfully", HttpStatus.CREATED);
    }


    // GET BY ID
    @GetMapping("/id/{index}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable int index) {

        if(index >= 0 && index < journalEntries.size()) {
            return new ResponseEntity<>(journalEntries.get(index), HttpStatus.OK);
        }

        return new ResponseEntity<>("Entry Not Found", HttpStatus.NOT_FOUND);
    }


    // DELETE
    @DeleteMapping("/id/{index}")
    public ResponseEntity<String> deleteJournalEntryById(@PathVariable int index) {

        if(index >= 0 && index < journalEntries.size()) {
            journalEntries.remove(index);
            return new ResponseEntity<>("Entry Deleted", HttpStatus.OK);
        }

        return new ResponseEntity<>("Entry Not Found", HttpStatus.NOT_FOUND);
    }


    // UPDATE
    @PutMapping("/id/{index}")
    public ResponseEntity<?> updateJournalById(@PathVariable int index,
                                               @RequestBody JournalEntry newEntry) {

        if(index >= 0 && index < journalEntries.size()) {

            JournalEntry old = journalEntries.get(index);

            old.setTitle(
                    newEntry.getTitle() != null &&
                            !newEntry.getTitle().equals(" ")
                            ? newEntry.getTitle()
                            : old.getTitle()
            );

            old.setContent(
                    newEntry.getContent() != null &&
                            !newEntry.getContent().equals(" ")
                            ? newEntry.getContent()
                            : old.getContent()
            );

            old.setDate(LocalDateTime.now());

            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>("Entry Not Found", HttpStatus.NOT_FOUND);
    }
}