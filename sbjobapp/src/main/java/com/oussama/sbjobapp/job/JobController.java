package com.oussama.sbjobapp.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class JobController {

    private List<Job> jobs = new ArrayList<>();

    @GetMapping("/jobs")
    // This method returns a list of jobs
    public List<Job> findAll() {
        return jobs;
    }

    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job) {
        jobs.add(job);
        return "Job created successfully with ID: " + job.getId();
    }
}
