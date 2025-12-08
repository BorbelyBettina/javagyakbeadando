package com.example.javabeadando.controller;

import com.example.javabeadando.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("/adatbazis")
    public String showTablePage(Model model) {
        model.addAttribute("tables", List.of("varos", "megye", "lelekszam"));
        return "adatbazis/adatbazis";
    }

    @PostMapping("/adatbazis")
    public String getTableData(@RequestParam("table") String tableName, Model model) {
        List<Map<String, Object>> data = tableService.getTableData(tableName);

        List<String> fieldNames = data.isEmpty()
                ? List.of()
                : new ArrayList<>(data.get(0).keySet()); // ← csak a saját kulcsok!

        List<List<Object>> rowValues = data.stream()
                .map(map -> new ArrayList<>(map.values()))
                .collect(Collectors.toList());

        model.addAttribute("tables", List.of("varos", "megye", "lelekszam"));
        model.addAttribute("selectedTable", tableName);
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("rowValues", rowValues);

        return "adatbazis/adatbazis";
    }
}

