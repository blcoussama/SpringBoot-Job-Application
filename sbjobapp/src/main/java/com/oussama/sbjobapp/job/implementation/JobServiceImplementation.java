package com.oussama.sbjobapp.job.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oussama.sbjobapp.company.Company;
import com.oussama.sbjobapp.company.CompanyService;
import com.oussama.sbjobapp.job.Job;
import com.oussama.sbjobapp.job.JobRepository;
import com.oussama.sbjobapp.job.JobService;

@Service
public class JobServiceImplementation implements JobService {

    private JobRepository jobRepository;
    private CompanyService companyService;

    public JobServiceImplementation(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        // Validate and set company if provided
        if (job.getCompany() != null && job.getCompany().getId() != null) {
            Company company = companyService.getCompanyById(job.getCompany().getId());
            if (company != null) {
                job.setCompany(company);
            } else {
                throw new RuntimeException("Company with ID " + job.getCompany().getId() + " not found");
            }
        }
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            Job job = jobRepository.findById(id).orElse(null);
            if (job == null) {
                return false; // Job not found
            }
            jobRepository.delete(job);
            return true; // ← This was missing!
        } catch (Exception e) {
            return false; // Error occurred while deleting
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            
            // Handle company update if provided
            if (updatedJob.getCompany() != null && updatedJob.getCompany().getId() != null) {
                Company company = companyService.getCompanyById(updatedJob.getCompany().getId());
                if (company != null) {
                    job.setCompany(company);
                } else {
                    throw new RuntimeException("Company with ID " + updatedJob.getCompany().getId() + " not found");
                }
            }
            
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}