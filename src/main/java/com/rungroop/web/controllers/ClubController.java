package com.rungroop.web.controllers;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.services.IClubService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private final IClubService _clubService;

    public ClubController(IClubService clubService) {
        this._clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = _clubService.findAllClubs();
        model.addAttribute("clubs", clubs);

        return "club-list";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetails(@PathVariable("clubId") long clubId, Model model) {
        ClubDto clubDto = _clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);

        return "club-detail";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long cludId) {
        _clubService.delete(cludId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return  "club-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "club-create";
        }
        _clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model) {
        ClubDto club = _clubService.findClubById(clubId);
        model.addAttribute("club",club);

        return "club-edit";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = _clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "club-list";
    }

    @PostMapping("clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("club", club);
            return "club-edit";
        }
        club.setId(clubId);
        _clubService.updateClub(club);

        return "redirect:/clubs";
    }
}
