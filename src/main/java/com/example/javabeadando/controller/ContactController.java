package com.example.javabeadando.controller;

import com.example.javabeadando.model.Message;
import com.example.javabeadando.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final MessageService messageService;

    public ContactController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Kapcsolat űrlap megjelenítése
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("message", new Message());
        return "contact/form";
    }

    // Űrlap elküldése
    @PostMapping
    public String submitForm(@ModelAttribute Message message, Model model) {
        messageService.save(message);
        model.addAttribute("success", "Üzenet sikeresen elküldve!");
        model.addAttribute("message", new Message()); // ürítjük az űrlapot
        return "contact/form";
    }

    @GetMapping("/all")
    public String listMessages(Model model) {
        List<Message> messages = messageService.findAllMessagesOrdered();
        model.addAttribute("messages", messages);
        return "contact/list";
    }



}
