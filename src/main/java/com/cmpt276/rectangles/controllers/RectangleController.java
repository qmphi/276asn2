package com.cmpt276.rectangles.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt276.rectangles.models.Rectangle;
import com.cmpt276.rectangles.models.RectangleRepository;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class RectangleController {

    @Autowired
    private RectangleRepository rectangleRepo;

    @GetMapping("rectangles/view")
    public String getAllRectangles(Model model) {
        System.out.println("Getting all rectangles");
        List<Rectangle> rectangles = rectangleRepo.findAll();
        model.addAttribute("recs", rectangles);
        return "rectangles/showAll";
    }

    @PostMapping("/rectangles/add")
    public String addRectangle(@RequestParam Map<String, String> newrectangle, HttpServletResponse response) {
        System.out.println("Add Rectangle");
        String newName = newrectangle.get("name");
        String newColor = newrectangle.get("color");
        int newWidth = Integer.parseInt(newrectangle.get("width"));
        int newHeight = Integer.parseInt(newrectangle.get("height"));
        rectangleRepo.save(new Rectangle(newName, newColor, newWidth, newHeight));
        response.setStatus(201); // 201 status means created new obj
        return "rectangles/addedRectangle";
    }
    
}
