package com.interviewpanel.MainMenu;

import com.interviewpanel.CandidateManager.CandidateManagerView;
import com.interviewpanel.InterviewPanelManager.InterviewPanelView;
import com.interviewpanel.repository.CacheMemory;

class MainMenuModel {
    private final MainMenuView mainMenuView;

    MainMenuModel(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }


    public void handleInterviewPanelOptions(int choice) {
        InterviewPanelView interviewPanelView = new InterviewPanelView();
        switch(choice) {
            case 1:
                // Add Interview Panel
                interviewPanelView.addInterviewPanel();
                break;
            case 2:
                // Terminate Current Interview In Panel
                interviewPanelView.terminateCurrentInterviewInPanel();
                break;
            case 3:
                // Clear an Interview Panel
                interviewPanelView.clearInterviewPanel();
                break;
            case 4:
                // Clear all Interview Panels
                interviewPanelView.clearAllInterviewPanels();
                break;
            case 5:
                // Remove Interview Panel
                interviewPanelView.removeInterviewPanel();
                break;
            case 6:
                // View Interview Panels
                interviewPanelView.viewInterviewPanels(CacheMemory.getInstance().getCurrentAdmin());
                break;
            case 7:
                // Back to Main Menu
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void handleCandidateOptions(int choice) {
        // Candidate Options
        CandidateManagerView candidateManagerView = new CandidateManagerView();
        switch(choice) {
            case 1:
                // Add Candidate
                candidateManagerView.addCandidate();
                break;
            case 2:
                // Remove Candidate
                candidateManagerView.removeCandidate();
                break;
            case 3:
                // Change Result of Candidate
                candidateManagerView.changeResultOfCandidate();
                break;
            case 4:
                candidateManagerView.viewCandidates();
            case 5:
                // Back to Main Menu
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
