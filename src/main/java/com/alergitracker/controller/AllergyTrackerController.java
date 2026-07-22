package com.alergitracker.controller;

import com.alergitracker.entity.Baby;
import com.alergitracker.entity.Food;
import com.alergitracker.entity.FoodTrial;
import com.alergitracker.entity.TrialStatus;
import com.alergitracker.repository.BabyRepository;
import com.alergitracker.repository.FoodRepository;
import com.alergitracker.repository.FoodTrialRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AllergyTrackerController {

    private final BabyRepository babyRepository;
    private final FoodRepository foodRepository;
    private final FoodTrialRepository foodTrialRepository;

    public AllergyTrackerController(BabyRepository babyRepository, FoodRepository foodRepository, FoodTrialRepository foodTrialRepository) {
        this.babyRepository = babyRepository;
        this.foodRepository = foodRepository;
        this.foodTrialRepository = foodTrialRepository;
    }

    @GetMapping("/bebekler")
    public List<Baby> tumBebekler() {
        return babyRepository.findAll();
    }

    @PostMapping("/bebekler")
    public Baby bebekEkle(@RequestBody Baby baby) {
        return babyRepository.save(baby);
    }

    @DeleteMapping("/bebekler/{id}")
    public void bebekSil(@PathVariable Long id) {
        babyRepository.deleteById(id);
    }

    @PostMapping("/besinler")
    public Food besinEkle(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    @GetMapping("/bebekler/{bebekId}/reaksiyonlar")
    public List<FoodTrial> reaksiyonlariGetir(@PathVariable Long bebekId) {
        return foodTrialRepository.findByBabyId(bebekId);
    }

    @PostMapping("/reaksiyon-ekle")
    public FoodTrial reaksiyonEkle(
            @RequestParam Long bebekId,
            @RequestParam Long besinId,
            @RequestParam TrialStatus durum,
            @RequestParam String aciklama,
            @RequestParam Integer siddet) {

        Baby baby = babyRepository.findById(bebekId).orElseThrow();
        Food food = foodRepository.findById(besinId).orElseThrow();

        FoodTrial trial = new FoodTrial();
        trial.setBaby(baby);
        trial.setFood(food);
        trial.setStatus(durum);
        trial.setReactionDescription(aciklama);
        trial.setSeverity(siddet);
        trial.setTrialDate(LocalDate.now());

        return foodTrialRepository.save(trial);
    }

    @PutMapping("/reaksiyonlar/{id}")
    public FoodTrial reaksiyonGuncelle(
            @PathVariable Long id,
            @RequestParam TrialStatus durum,
            @RequestParam String aciklama,
            @RequestParam Integer siddet) {

        FoodTrial trial = foodTrialRepository.findById(id).orElseThrow();
        trial.setStatus(durum);
        trial.setReactionDescription(aciklama);
        trial.setSeverity(siddet);

        return foodTrialRepository.save(trial);
    }

    @DeleteMapping("/reaksiyonlar/{id}")
    public void reaksiyonSil(@PathVariable Long id) {
        foodTrialRepository.deleteById(id);
    }
}