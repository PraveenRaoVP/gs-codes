package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.Candidate;
import com.interviewpanel.models.helpers.InterviewStatus;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

public class CandidatesRepository {
    private final HashMap<Integer, Candidate> candidateMap = new HashMap<>();
    private String fileNamePath = "./src/main/resources/candidates.json"; // "candidates.json
    private static CandidatesRepository candidatesRepository;

    private CandidatesRepository() {
    }

    public static CandidatesRepository getInstance() {
        if (candidatesRepository == null) {
            candidatesRepository = new CandidatesRepository();
        }
        return candidatesRepository;
    }

    public void pushCandidatesToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileNamePath), candidateMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullCandidatesFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                candidateMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Candidate>>() {}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void pushCandidates(Candidate candidate) {
        candidateMap.put(candidate.getCandidateId(), candidate);
    }

    public Candidate getCandidateById(int candidateId) {
        return candidateMap.get(candidateId);
    }

    public List<Candidate> getCandidates() {
        return new ArrayList<>(candidateMap.values());
    }

    public InterviewStatus getInterviewStatusByCandidateId(int candidateId) {
        pullCandidatesFromJSON();
        return InterviewRepository.getInstance().getInterviewByCandidateId(candidateId).getStatus();
    }
}
