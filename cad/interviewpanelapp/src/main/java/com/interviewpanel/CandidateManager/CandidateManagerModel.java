package com.interviewpanel.CandidateManager;

import com.interviewpanel.models.Candidate;
import com.interviewpanel.repository.CandidatesRepository;

class CandidateManagerModel {
    private final CandidateManagerView candidateManagerView;

    public CandidateManagerModel(CandidateManagerView candidateManagerView) {
        this.candidateManagerView = candidateManagerView;
    }

    public void addCandidate(String name, String email, String phone, String position, String skills, String address) {
        int candidateId = CandidatesRepository.getInstance().getCandidates().size() + 1;
        Candidate candidate = new Candidate(candidateId, name, email, phone, position, skills, address);
        CandidatesRepository.getInstance().pushCandidates(candidate);
        // TODO: Add candidate to queue with less number of candidates
        
    }
}
