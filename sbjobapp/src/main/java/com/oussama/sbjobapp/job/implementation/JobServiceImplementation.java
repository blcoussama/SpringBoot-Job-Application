package com.oussama.sbjobapp.job.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oussama.sbjobapp.job.Job;
import com.oussama.sbjobapp.job.JobService;

@Service
public class JobServiceImplementation implements JobService {

    private List<Job> jobs = new ArrayList<>();

    private Long nextId = 1L; // To manage IDs for new jobs

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;

    }

}