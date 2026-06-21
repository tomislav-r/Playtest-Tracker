package com.example.playtesttracker.controller;

import com.example.playtesttracker.entity.Platform;
import com.example.playtesttracker.service.PlatformService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/platforms")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public String listPlatforms(Model model) {
        model.addAttribute("platforms", platformService.findAll());
        return "platforms/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("platform", new Platform());
        return "platforms/form";
    }

    @PostMapping
    public String createPlatform(@Valid @ModelAttribute("platform") Platform platform,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "platforms/form";
        }

        platformService.create(platform);
        return "redirect:/platforms";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Platform platform = platformService.findById(id);
        model.addAttribute("platform", platform);
        return "platforms/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePlatform(@PathVariable Long id,
                                 @Valid @ModelAttribute("platform") Platform platform,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "platforms/edit";
        }

        platformService.update(id, platform);
        return "redirect:/platforms";
    }


    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Platform platform = platformService.findById(id);
        long playtestCount = platformService.countPlaytests(id);

        model.addAttribute("platform", platform);
        model.addAttribute("playtestCount", playtestCount);

        return "platforms/delete";
    }

    @PostMapping("/{id}/delete")
    public String deletePlatform(@PathVariable Long id, Model model) {
        Platform platform = platformService.findById(id);
        long playtestCount = platformService.countPlaytests(id);

        if (playtestCount > 0) {
            model.addAttribute("platform", platform);
            model.addAttribute("playtestCount", playtestCount);
            model.addAttribute("errorMessage", "Platforma se ne može obrisati jer ima povezane playtestove.");
            return "platforms/delete";
        }

        platformService.delete(id);
        return "redirect:/platforms";
    }
}