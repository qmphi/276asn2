package com.cmpt276.rectangles.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List <Rectangle> rectangles = rectangleRepo.findAll(Sort.by(Sort.Direction.ASC, "rid"));
        model.addAttribute("recs", rectangles);
        return "rectangles/showAll";
    }

    // Add new rectangle
    @PostMapping("/rectangles/add")
    public String addRectangle(Model model, @RequestParam Map<String, String> newrectangle, HttpServletResponse response) {
        System.out.println("Add Rectangle");
        String newName = newrectangle.get("name");
        String newColor = newrectangle.get("color");
        int newWidth = Integer.parseInt(newrectangle.get("width"));
        int newHeight = Integer.parseInt(newrectangle.get("height"));
        Rectangle newRectangle = new Rectangle(newName,newColor,newWidth,newHeight);
        rectangleRepo.save(newRectangle);

        model.addAttribute("recs", newRectangle);
        response.setStatus(201);
        return "rectangles/addedRectangle";
    }

    // Get rectangle to update
    @GetMapping("/rectangles/view/{rid}")
    public String getRectangle(Model model, @PathVariable int rid){
        
        System.out.println("GET Rectangle " + rid);
        Rectangle getRectangle = rectangleRepo.findByRid(rid);

        model.addAttribute("recs", getRectangle);
        return "rectangles/detailRectangle";
    }

    // Update rectangle's name
    @PostMapping("/rectangles/view/{rid}/updatename") 
    public String updateRectangleName(Model model, @PathVariable int rid, @RequestParam Map<String, String> recDetails, HttpServletResponse response) { 
        
        System.out.println("Update rectangle's name");
        Rectangle rectangleToUpdate = rectangleRepo.findByRid(rid);
        rectangleToUpdate.setName(recDetails.get("name"));
        rectangleRepo.save(rectangleToUpdate);

        model.addAttribute("recs", rectangleToUpdate);
        response.setStatus(201);
        return "rectangles/updatedRectangle";
    }

    // Update rectangle's color
    @PostMapping("/rectangles/view/{rid}/updatecolor") 
    public String updateRectangleColor(Model model, @PathVariable int rid, @RequestParam Map<String, String> recDetails, HttpServletResponse response) { 
        
        System.out.println("Update rectangle's color");
        Rectangle rectangleToUpdate = rectangleRepo.findByRid(rid);
        rectangleToUpdate.setColor(recDetails.get("color"));
        rectangleRepo.save(rectangleToUpdate);

        model.addAttribute("recs", rectangleToUpdate);
        response.setStatus(201);
        return "rectangles/updatedRectangle";
    }

    // Update rectangle's width
    @PostMapping("/rectangles/view/{rid}/updatewidth") 
    public String updateRectangleWidth(Model model, @PathVariable int rid, @RequestParam Map<String, String> recDetails, HttpServletResponse response) { 
        
        System.out.println("Update rectangle's width");
        Rectangle rectangleToUpdate = rectangleRepo.findByRid(rid);
        rectangleToUpdate.setWidth(Integer.parseInt(recDetails.get("width")));
        rectangleRepo.save(rectangleToUpdate);

        model.addAttribute("recs", rectangleToUpdate);
        response.setStatus(201);
        return "rectangles/updatedRectangle";
    }

    // Update rectangle's height
    @PostMapping("/rectangles/view/{rid}/updateheight") 
    public String updateRecHeight(Model model, @PathVariable int rid, @RequestParam Map<String, String> recDetails, HttpServletResponse response) { 
        
        System.out.println("Update rectangle's height");
        Rectangle rectangleToUpdate = rectangleRepo.findByRid(rid);
        rectangleToUpdate.setHeight(Integer.parseInt(recDetails.get("height")));
        rectangleRepo.save(rectangleToUpdate);

        model.addAttribute("recs", rectangleToUpdate);
        response.setStatus(201);
        return "rectangles/updatedRectangle";
    }

    // Delete rectangle
    @PostMapping("/delete/{rid}")
    public String deleteRec(@PathVariable int rid) {
        System.out.println("Delete rectangle");
        Rectangle rectangletoDelete = rectangleRepo.findByRid(rid);
        rectangleRepo.delete(rectangletoDelete);
        return "rectangles/deletedRectangle";
    }


}
