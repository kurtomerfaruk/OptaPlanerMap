/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kurtomerfaruk.optaplanermap.solutions;

import com.kurtomerfaruk.optaplanermap.models.Customer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2024 09:17
 */
@PlanningSolution
public class TspSolution {
    private List<Customer> customers;
    private HardSoftScore score;

    // Default constructor needed for OptaPlanner
    public TspSolution() {}

    public TspSolution(List<Customer> customers) {
        this.customers = customers;
    }

    @PlanningEntityCollectionProperty
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @ValueRangeProvider(id = "sequenceRange")
    public List<Integer> getSequenceRange() {
        return IntStream.rangeClosed(0, customers.size() - 1).boxed().collect(Collectors.toList());
    }
}
