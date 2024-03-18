package com.interviewpanel.repository;

import com.interviewpanel.models.Candidate;

import java.util.ArrayList;
import java.util.Collection;
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

    public void pushCandidates(Candidate candidate) {
        candidateList.add(candidate);
    }

    public Candidate getCandidateById(int candidateId) {
        for (Candidate candidate : candidatesRepository.candidateList) {
            if (candidate.getCandidateId() == candidateId) {
                return candidate;
            }
        }
        return null;
    }

    public List<Candidate> getCandidates() {
        return candidateList;
    }
}
