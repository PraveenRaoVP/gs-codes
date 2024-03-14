package org.interviewpanel.services;

public interface AdminService {
    public void addAdmin();
    public void removeAdmin();
    public void updateAdmin();
    public boolean loginAdmin(String username, String password);
    public void logoutAdmin();
    public void changeInterviewStatusOfCandidate();
}
