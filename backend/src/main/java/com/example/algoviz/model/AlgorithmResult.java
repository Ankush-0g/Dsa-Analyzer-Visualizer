package com.example.algoviz.model;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmResult {
    private boolean isValid;
    private boolean isCorrect;
    private String algorithmName;
    private String category;
    private String description;
    private String timeComplexity;
    private String spaceComplexity;
    private String explanation;
    private List<String> bugs;
    private String correctedCode;
    private List<String> howItWorks;
    private List<CodeLine> codeLines;
    private List<ExecutionStep> steps;

    public AlgorithmResult() {
        this.isValid = true;
        this.isCorrect = true;
        this.bugs = new ArrayList<>();
        this.howItWorks = new ArrayList<>();
        this.codeLines = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeComplexity() {
        return timeComplexity;
    }

    public void setTimeComplexity(String timeComplexity) {
        this.timeComplexity = timeComplexity;
    }

    public String getSpaceComplexity() {
        return spaceComplexity;
    }

    public void setSpaceComplexity(String spaceComplexity) {
        this.spaceComplexity = spaceComplexity;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<String> getBugs() {
        return bugs;
    }

    public void setBugs(List<String> bugs) {
        this.bugs = bugs;
    }

    public String getCorrectedCode() {
        return correctedCode;
    }

    public void setCorrectedCode(String correctedCode) {
        this.correctedCode = correctedCode;
    }

    public List<String> getHowItWorks() {
        return howItWorks;
    }

    public void setHowItWorks(List<String> howItWorks) {
        this.howItWorks = howItWorks;
    }

    public List<CodeLine> getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(List<CodeLine> codeLines) {
        this.codeLines = codeLines;
    }

    public List<ExecutionStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ExecutionStep> steps) {
        this.steps = steps;
    }
}
