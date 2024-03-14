package org.interviewpanel.services;

import org.interviewpanel.models.Candidate;

import java.util.List;

public interface CandidateService {
    public void addCandidate(Candidate candidate);

    void removeCandidate(int candidateId);

    public boolean updateCandidate(int candidateId, Candidate candidate);
    public Candidate getCandidateById(int id);
    public List<Candidate> getCandidateByName(String name);
    public List<Candidate> getAllCandidates();
    public boolean setCandidateStatus(int candidateId, String status);
}
