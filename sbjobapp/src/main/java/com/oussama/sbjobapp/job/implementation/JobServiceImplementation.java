package com.oussama.sbjobapp.job.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oussama.sbjobapp.job.Job;
import com.oussama.sbjobapp.job.JobRepository;
import com.oussama.sbjobapp.job.JobService;

@Service
public class JobServiceImplementation implements JobService {

    private JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        // Don't set ID manually - let JPA auto-generate it
        // job.setId() should NOT be called here
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
            jobRepository.save(job); // Save the updated job
            return true;
        }
        return false; // Job not found
    }
}