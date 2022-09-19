package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/notes")
public class NotesController {

    static final List<Note> NOTES = List.of(
            new Note("1", "note 1", "note 1 content"),
            new Note("2", "note 2", "note 2 content"),
            new Note("3", "note 3", "note 3 content"),
            new Note("4", "note 4", "note 4 content")
    );

    @GetMapping
    public NotesDto getAllNotesById() {
        return new NotesDto(NOTES);
    }

    @GetMapping("/{noteId}")
    public Note getNoteById(
            @PathVariable String noteId
    ) {
        return NOTES.stream().filter(note -> note.id.equals(noteId)).collect(Collectors.toList()).get(0);
    }
}

class NotesDto {
    private final List<Note> notes;
    NotesDto(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return this.notes;
    }
}

class Note {
    final String id;
    final String title;
    final String content;

    Note(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
