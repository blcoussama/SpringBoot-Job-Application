package com.oussama.sbjobapp.company.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oussama.sbjobapp.company.CompanyService;

@Service
public class CompanyServiceImplementation implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
