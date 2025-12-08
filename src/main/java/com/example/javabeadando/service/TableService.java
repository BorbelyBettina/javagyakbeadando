package com.example.javabeadando.service;

import com.example.javabeadando.entity.Lelekszam;
import com.example.javabeadando.entity.Megye;
import com.example.javabeadando.entity.Varos;
import com.example.javabeadando.repository.LelekszamRepository;
import com.example.javabeadando.repository.MegyeRepository;
import com.example.javabeadando.repository.VarosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class TableService {

    @Autowired
    private VarosRepository varosRepo;

    @Autowired
    private MegyeRepository megyeRepo;

    @Autowired
    private LelekszamRepository lelekszamRepo;

    public List<Map<String, Object>> getTableData(String tableName) {
        List<?> data = switch (tableName) {
            case "varos" -> varosRepo.findAll();
            case "megye" -> megyeRepo.findAll();
            case "lelekszam" -> lelekszamRepo.findAll();
            default -> throw new IllegalArgumentException("Érvénytelen tábla név");
        };

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object obj : data) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    map.put(field.getName(), field.get(obj));
                } catch (IllegalAccessException e) {
                    map.put(field.getName(), null);
                }
            }
            result.add(map);
        }

        return result;
    }
}
