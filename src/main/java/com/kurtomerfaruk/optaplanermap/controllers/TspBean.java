/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kurtomerfaruk.optaplanermap.controllers;

import com.google.gson.Gson;
import com.kurtomerfaruk.optaplanermap.models.Customer;
import com.kurtomerfaruk.optaplanermap.solutions.TspSolution;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.primefaces.PrimeFaces;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2024 09:17
 */
@Named
@RequestScoped
public class TspBean {
    private List<Customer> customers;
    private Solver<TspSolution> solver;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>();
        
        customers.add(new Customer(1, "Customer 1", 39.9334, 32.8597));
        customers.add(new Customer(2, "Customer 2", 41.0082, 28.9784));
        customers.add(new Customer(3,"Customer 3",37.06434529375393, 37.37305902975972));
        customers.add(new Customer(4,"Customer 4",38.35303781936257, 38.32536092950329));
        
        SolverFactory<TspSolution> solverFactory = SolverFactory.createFromXmlResource("tspSolverConfig.xml");
        solver = solverFactory.buildSolver();
    }

    public void calculateRoute() {
        TspSolution problem = new TspSolution(customers);
        TspSolution solution = solver.solve(problem);
        customers = solution.getCustomers();
        String json = new Gson().toJson(customers );
        PrimeFaces.current().executeScript("updateMap(" + json + ")");
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
