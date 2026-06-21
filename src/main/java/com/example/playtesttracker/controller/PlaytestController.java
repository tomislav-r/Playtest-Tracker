package com.example.playtesttracker.controller;

import com.example.playtesttracker.dto.PlaytestRequest;
import com.example.playtesttracker.entity.Playtest;
import com.example.playtesttracker.service.PlatformService;
import com.example.playtesttracker.service.PlaytestService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/playtests")
public class PlaytestController {

    private final PlaytestService playtestService;
    private final PlatformService platformService;

    public PlaytestController(PlaytestService playtestService, PlatformService platformService) {
        this.playtestService = playtestService;
        this.platformService = platformService;
    }

    @GetMapping
    public String listPlaytests(@RequestParam(required = false) String keyword,
                                @RequestParam(defaultValue = "testDate") String sortBy,
                                @RequestParam(defaultValue = "desc") String direction,
                                Model model) {

        if (keyword != null && !keyword.isBlank()) {
            model.addAttribute("playtests",
                    playtestService.searchByGameTitleSorted(keyword, sortBy, direction));
        } else {
            model.addAttribute("playtests",
                    playtestService.findAllSorted(sortBy, direction));
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "playtests/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("playtestRequest", new PlaytestRequest());
        model.addAttribute("platforms", platformService.findAll());
        return "playtests/form";
    }

    @PostMapping
    public String createPlaytest(@Valid @ModelAttribute("playtestRequest") PlaytestRequest playtestRequest,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("platforms", platformService.findAll());
            return "playtests/form";
        }

        playtestService.create(playtestRequest);
        return "redirect:/playtests";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        model.addAttribute("playtest", playtestService.findById(id));
        return "playtests/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Playtest playtest = playtestService.findById(id);

        PlaytestRequest playtestRequest = new PlaytestRequest();
        playtestRequest.setGameTitle(playtest.getGameTitle());
        playtestRequest.setTestDate(playtest.getTestDate());
        playtestRequest.setTestType(playtest.getTestType());
        playtestRequest.setStatus(playtest.getStatus());
        playtestRequest.setRating(playtest.getRating());
        playtestRequest.setNotes(playtest.getNotes());
        playtestRequest.setPlatformId(playtest.getPlatform().getId());

        model.addAttribute("playtestRequest", playtestRequest);
        model.addAttribute("playtestId", id);
        model.addAttribute("platforms", platformService.findAll());

        return "playtests/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePlaytest(@PathVariable Long id,
                                 @Valid @ModelAttribute("playtestRequest") PlaytestRequest playtestRequest,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("playtestId", id);
            model.addAttribute("platforms", platformService.findAll());
            return "playtests/edit";
        }

        playtestService.update(id, playtestRequest);
        return "redirect:/playtests";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Playtest playtest = playtestService.findById(id);
        model.addAttribute("playtest", playtest);
        return "playtests/delete";
    }

    @PostMapping("/{id}/delete")
    public String deletePlaytest(@PathVariable Long id) {
        playtestService.delete(id);
        return "redirect:/playtests";
    }
}