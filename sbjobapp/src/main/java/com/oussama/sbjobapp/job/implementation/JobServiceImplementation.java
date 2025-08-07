package com.oussama.sbjobapp.job.implementation;

import java.util.List;
import java.util.Optional;

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
    // This method deletes a job by its ID
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            return true;
        }
        return false;
    }

}