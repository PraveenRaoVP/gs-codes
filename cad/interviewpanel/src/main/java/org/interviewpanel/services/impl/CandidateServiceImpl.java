package org.interviewpanel.services.impl;

import org.interviewpanel.models.Candidate;
import org.interviewpanel.models.helpers.InterviewStatus;
import org.interviewpanel.services.CandidateService;

import java.util.ArrayList;
import java.util.List;

public class CandidateServiceImpl implements CandidateService {

    public List<Candidate> allCandidates;

    public CandidateServiceImpl(List<Candidate> allCandidates) {
        this.allCandidates = allCandidates;
    }

    @Override
    public void addCandidate(Candidate candidate) {
        allCandidates.add(candidate);
    }

    @Override
    public void removeCandidate(int candidateId) {
        for(Candidate candidate: allCandidates) {
            if(candidate.getCandidateId() == candidateId) {
                allCandidates.remove(candidate);
                break;
            }
        }
    }

    @Override
    public boolean updateCandidate(int candidateId, Candidate candidate) {
        for(Candidate c: allCandidates) {
            if(c.getCandidateId() == candidateId) {
                c.setCandidateName(candidate.getCandidateName());
                c.setCandidateEmail(candidate.getCandidateEmail());
                c.setCandidatePhone(candidate.getCandidatePhone());
                c.setCandidateDOB(candidate.getCandidateDOB());
                c.setCandidateQualification(candidate.getCandidateQualification());
                c.setSkills(candidate.getSkills());
                c.setPositionForInterviewing(candidate.getPositionForInterviewing());
                c.setInterviewStatus(candidate.getInterviewStatus());
                return true;
            }
        }
        return false;
    }

    @Override
    public Candidate getCandidateById(int id) {
        for(Candidate c: allCandidates) {
            if(c.getCandidateId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Candidate> getCandidateByName(String name) {
        List<Candidate> candidates = new ArrayList<>();
        for(Candidate c: allCandidates) {
            if(c.getCandidateName().equals(name)) {
                candidates.add(c);
            }
        }
        return candidates;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return allCandidates;
    }

    @Override
    public boolean setCandidateStatus(int candidateId, String status) {
        for(Candidate c: allCandidates) {
            if(c.getCandidateId() == candidateId) {
                c.setInterviewStatus(InterviewStatus.valueOf(status));
                return true;
            }
        }
        return false;
    }
}
