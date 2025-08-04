package com.naveen.ExpenseTracker.Controller;


import com.naveen.ExpenseTracker.DTO.EarningDTO;
import com.naveen.ExpenseTracker.DTO.ExpenseDTO;
import com.naveen.ExpenseTracker.Service.ServiceImpl.EarningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/earning")
public class EarningController {

    @Autowired
    private EarningServiceImpl earningService;

    @PostMapping("/create-earning")
    public EarningDTO createEarning(@RequestBody EarningDTO earningDTO) {
        return earningService.createEarning(earningDTO);
    }

    @GetMapping("/get-earning/{id}")
    public ResponseEntity<EarningDTO> getEarningById(@PathVariable Long id) {
        EarningDTO earningDTO = earningService.getEarningById(id);
        return ResponseEntity.ok(earningDTO);
    }

    @GetMapping("/get-allearnings")
    public List<EarningDTO> getAllEarnings() {
        return earningService.getAllEarnings();
    }

    @PutMapping("/update-earning/{id}")
    public ResponseEntity<EarningDTO> updateEarning(@RequestBody EarningDTO earningDTO, @PathVariable Long id) {
        EarningDTO updatedEarningDTO = earningService.updateEarning(earningDTO, id);
        return ResponseEntity.ok(updatedEarningDTO);
    }

    @DeleteMapping("/delete-earning/{id}")
    public ResponseEntity<String> deleteEarning(@PathVariable Long id) {
        earningService.deleteEarning(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/earningbyuserid/{userId}")
    public ResponseEntity<List<EarningDTO>> getExpensesByUserId(@PathVariable Long userId) {
        List<EarningDTO> earnings= earningService.getEarningByUserId(userId);
        return ResponseEntity.ok(earnings);
    }
}

