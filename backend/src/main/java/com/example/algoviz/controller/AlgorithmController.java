package com.example.algoviz.controller;

import com.example.algoviz.model.AlgorithmResult;
import com.example.algoviz.service.AlgorithmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AlgorithmController {

    private final AlgorithmService algorithmService;

    public AlgorithmController(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @GetMapping("/algorithms")
    public ResponseEntity<Object> listAlgorithms() {
        return ResponseEntity.ok(algorithmService.listAlgorithms());
    }

    @PostMapping("/algorithms/{id}/run")
    public ResponseEntity<AlgorithmResult> runAlgorithm(@PathVariable String id, @RequestBody Map<String,Object> body) {
        Object input = body.get("input");
        Object target = body.get("target");
        AlgorithmResult res = algorithmService.runById(id, input, target);
        return ResponseEntity.ok(res);
    }
}
