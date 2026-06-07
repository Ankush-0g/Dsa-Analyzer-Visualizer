package com.example.algoviz.model;

public class CodeLine {
    private String line;
    private String explain;

    public CodeLine() {
    }

    public CodeLine(String line, String explain) {
        this.line = line;
        this.explain = explain;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
