package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.model.Portfolio;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Integer> {
  List<Portfolio> findByName(String name);
}