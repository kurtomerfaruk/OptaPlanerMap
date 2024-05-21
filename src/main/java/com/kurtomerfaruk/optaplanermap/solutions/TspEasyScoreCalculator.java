/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kurtomerfaruk.optaplanermap.solutions;

import com.kurtomerfaruk.optaplanermap.models.Customer;
import java.util.List;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2024 09:17
 */
public class TspEasyScoreCalculator implements EasyScoreCalculator<TspSolution, HardSoftScore> {
    @Override
    public HardSoftScore calculateScore(TspSolution solution) {
        List<Customer> customers = solution.getCustomers();
        int softScore = 0;
        for (int i = 0; i < customers.size() - 1; i++) {
            Customer a = customers.get(i);
            Customer b = customers.get(i + 1);
            softScore -= calculateDistance(a, b);
        }
        return HardSoftScore.of(0, softScore);
    }

    private int calculateDistance(Customer a, Customer b) {
        double dx = a.getLatitude() - b.getLatitude();
        double dy = a.getLongitude() - b.getLongitude();
        return (int) (Math.sqrt(dx * dx + dy * dy) * 1000.0);
    }
}
