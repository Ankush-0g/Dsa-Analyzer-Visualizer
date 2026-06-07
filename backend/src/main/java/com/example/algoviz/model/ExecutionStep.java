package com.example.algoviz.model;

import java.util.List;
import java.util.Map;

public class ExecutionStep {
    private List<Integer> arr;
    private List<Integer> highlight;
    private List<Integer> secondary;
    private List<Integer> done;
    private List<Integer> eliminated;
    private List<Integer> swap;
    private Map<String, String> pointers;
    private Integer activeLine;
    private String msg;

    public ExecutionStep() {
    }

    public List<Integer> getArr() {
        return arr;
    }

    public void setArr(List<Integer> arr) {
        this.arr = arr;
    }

    public List<Integer> getHighlight() {
        return highlight;
    }

    public void setHighlight(List<Integer> highlight) {
        this.highlight = highlight;
    }

    public List<Integer> getSecondary() {
        return secondary;
    }

    public void setSecondary(List<Integer> secondary) {
        this.secondary = secondary;
    }

    public List<Integer> getDone() {
        return done;
    }

    public void setDone(List<Integer> done) {
        this.done = done;
    }

    public List<Integer> getEliminated() {
        return eliminated;
    }

    public void setEliminated(List<Integer> eliminated) {
        this.eliminated = eliminated;
    }

    public List<Integer> getSwap() {
        return swap;
    }

    public void setSwap(List<Integer> swap) {
        this.swap = swap;
    }

    public Map<String, String> getPointers() {
        return pointers;
    }

    public void setPointers(Map<String, String> pointers) {
        this.pointers = pointers;
    }

    public Integer getActiveLine() {
        return activeLine;
    }

    public void setActiveLine(Integer activeLine) {
        this.activeLine = activeLine;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
