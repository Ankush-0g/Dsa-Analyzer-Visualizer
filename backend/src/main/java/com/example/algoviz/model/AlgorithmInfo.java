package com.example.algoviz.model;

import java.util.List;
import java.util.Map;

public class AlgorithmInfo {
    private String id;
    private String label;
    private String description;
    private String language;
    private String category;
    private List<Integer> defaultInput;
    private String code;
    private Map<String,String> codes;
    private boolean requiresTarget;

    public AlgorithmInfo() {
    }

    public AlgorithmInfo(String id, String label, String description, String language, String category, List<Integer> defaultInput, String code, boolean requiresTarget) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.language = language;
        this.category = category;
        this.defaultInput = defaultInput;
        this.code = code;
        this.requiresTarget = requiresTarget;
    }

    public Map<String, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Integer> getDefaultInput() {
        return defaultInput;
    }

    public void setDefaultInput(List<Integer> defaultInput) {
        this.defaultInput = defaultInput;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isRequiresTarget() {
        return requiresTarget;
    }

    public void setRequiresTarget(boolean requiresTarget) {
        this.requiresTarget = requiresTarget;
    }
}
