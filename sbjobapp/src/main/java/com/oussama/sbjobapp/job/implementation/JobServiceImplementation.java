package com.oussama.sbjobapp.job.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oussama.sbjobapp.job.Job;
import com.oussama.sbjobapp.job.JobRepository;
import com.oussama.sbjobapp.job.JobService;

@Service
public class JobServiceImplementation implements JobService {

    // private List<Job> jobs = new ArrayList<>();

    JobRepository jobRepository;

    private Long nextId = 1L; // To manage IDs for new jobs

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
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
        } catch (Exception e) {
            return false; // Error occurred while deleting
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }

}