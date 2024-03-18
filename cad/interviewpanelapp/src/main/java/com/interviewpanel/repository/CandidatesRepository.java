package com.interviewpanel.repository;

import com.interviewpanel.models.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidatesRepository {
    private final List<Candidate> candidateList = new ArrayList<>();

    private static CandidatesRepository candidatesRepository;

    private CandidatesRepository() {}

    public static CandidatesRepository getInstance() {
        if (candidatesRepository == null) {
            candidatesRepository = new CandidatesRepository();
        }
        return candidatesRepository;
    }
}
