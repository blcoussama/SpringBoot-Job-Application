package com.oussama.sbjobapp.company.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oussama.sbjobapp.company.Company;
import com.oussama.sbjobapp.company.CompanyRepository;
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

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompany(Long id) {
        try {
            Company company = companyRepository.findById(id).orElse(null);
            if (company == null) {
                return false; // Company not found
            }
            companyRepository.delete(company);
            return true;
        } catch (Exception e) {
            return false; // Error occurred while deleting
        }
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false; // Company not found
    }
}