package com.interviewpanel.repository;


public class CacheMemory {
    private static CacheMemory instance;
    private int currentAdminId;
    private CacheMemory() {}

    public static CacheMemory getInstance() {
        if(instance == null) {
            instance = new CacheMemory();
        }
        return instance;
    }

    public int getCurrentAdmin() {
        return currentAdminId;
    }

    public void setCurrentAdmin(int currentAdminId) {
        this.currentAdminId = currentAdminId;
    }

    public void pushAllDataToTheirJSON() {
        AdminRepository.getInstance().pushAdminsToJSON();
        AdminToCredentialsRepository.getInstance().pushAdminToCredentialsToJSON();
        AdminToInterviewPanelRepository.getInstance().pushAdminToInterviewPanelToJSON();
        CandidatesRepository.getInstance().pushCandidatesToJSON();
        CredentialsRepository.getInstance().pushCredentialsToJSON();
        InterviewerRepository.getInstance().pushInterviewersToJSON();
        InterviewPanelRepository.getInstance().pushInterviewPanelToJSON();
        InterviewRepository.getInstance().pushInterviewsToJSON();
    }
}
