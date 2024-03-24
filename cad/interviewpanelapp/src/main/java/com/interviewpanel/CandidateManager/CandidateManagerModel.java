package com.interviewpanel.CandidateManager;

import com.interviewpanel.models.Candidate;
import com.interviewpanel.models.Interview;
import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.models.helpers.InterviewStatus;
import com.interviewpanel.models.helpers.PrintersAndFormatters;
import com.interviewpanel.repository.CacheMemory;
import com.interviewpanel.repository.CandidatesRepository;
import com.interviewpanel.repository.InterviewPanelRepository;
import com.interviewpanel.repository.InterviewRepository;

import java.util.List;

class CandidateManagerModel {
    private final CandidateManagerView candidateManagerView;

    public CandidateManagerModel(CandidateManagerView candidateManagerView) {
        this.candidateManagerView = candidateManagerView;
    }

    public void addCandidate(String name, String email, String phone, String position, String skills, String address, int interviewerId) {
        int candidateId = CandidatesRepository.getInstance().getCandidates().size() + 1;
        Candidate candidate = new Candidate(candidateId, name, email, phone, position, skills, address);
        CandidatesRepository.getInstance().pushCandidates(candidate);
        InterviewPanelRepository.getInstance().pullInterviewPanelFromJSON();
        InterviewRepository.getInstance().pushInterviewsToJSON();
        // Add candidate to queue with less number of candidates
        List<InterviewPanel> interviewPanels = InterviewPanelRepository.getInstance().getInterviewPanelsByListOfInterviewPanelIds(CacheMemory.getInstance().getCurrentAdmin());
        InterviewPanel interviewPanel;
        if(interviewerId == -1) { // Add candidate to the queue with less number of candidates
            interviewPanel = interviewPanels.get(0);
            for(InterviewPanel panel: interviewPanels) {
                if(panel.getInterviews().size() < interviewPanel.getInterviews().size()) {
                    interviewPanel = panel;
                }
            }
            interviewerId = interviewPanel.getInterviewerId();
            int interviewId = InterviewRepository.getInstance().getInterviews().size() + 1;
            Interview interview = new Interview(interviewId, interviewerId, candidateId, null, null, InterviewStatus.WAITING);
            interviewPanel.getInterviews().add(interview);
            PrintersAndFormatters.showMessage("Adding Candidate...");
            InterviewRepository.getInstance().pushInterviewsToJSON();
            InterviewPanelRepository.getInstance().pushInterviewPanelToJSON();
        } else { // Add candidate to the interviewer's queue
            interviewPanel = InterviewPanelRepository.getInstance().getInterviewPanelById(interviewerId);
            int interviewId = InterviewRepository.getInstance().getInterviews().size() + 1;
            Interview interview = new Interview(interviewId, interviewerId, candidateId, null, null, InterviewStatus.WAITING);
            interviewPanel.getInterviews().add(interview);
            PrintersAndFormatters.showMessage("Adding Candidate...");
            InterviewPanelRepository.getInstance().pushInterviewPanelToJSON();
            InterviewRepository.getInstance().pushInterviewsToJSON();
        }
        PrintersAndFormatters.showMessage("Candidate added successfully");
        CandidatesRepository.getInstance().pushCandidatesToJSON();
    }

    public Candidate viewCandidate(int candidateId) {
        CandidatesRepository.getInstance().pullCandidatesFromJSON();
        System.out.println("Candidate Details:-");
        if(CandidatesRepository.getInstance().getCandidateById(candidateId) != null) {
            return CandidatesRepository.getInstance().getCandidateById(candidateId);
        } else {
            System.out.println("No candidate found with the given id");
        }
        return null;
    }

    public void changeResultOfCandidate(int candidateId, InterviewStatus result) {
        CandidatesRepository.getInstance().pullCandidatesFromJSON();

        InterviewRepository.getInstance().pullInterviewsFromJSON();
        Interview interview = InterviewRepository.getInstance().getInterviewByCandidateId(candidateId);
        if(interview!=null) {
            interview.setStatus(result);
            InterviewRepository.getInstance().pushInterviewsToJSON();
        } else {
            System.out.println("No interview found for the candidate");
        }
        InterviewRepository.getInstance().pushInterviewsToJSON();
    }

    public void removeCandidate(int candidateId) {
        CandidatesRepository.getInstance().pullCandidatesFromJSON();
        InterviewRepository.getInstance().pullInterviewsFromJSON();
        Candidate candidate = CandidatesRepository.getInstance().getCandidateById(candidateId);
        if(candidate != null) {
            CandidatesRepository.getInstance().getCandidates().remove(candidate);
            InterviewPanelRepository.getInstance().removeCandidateFromInterviewPanel(candidateId);
        } else {
            System.out.println("No candidate found with the given id");
        }
        PrintersAndFormatters.showMessage("Removing Candidate...");
        CandidatesRepository.getInstance().pushCandidatesToJSON();
        InterviewPanelRepository.getInstance().pushInterviewPanelToJSON();
        InterviewRepository.getInstance().pushInterviewsToJSON();
        PrintersAndFormatters.showMessage("Candidate removed successfully");
    }
}
