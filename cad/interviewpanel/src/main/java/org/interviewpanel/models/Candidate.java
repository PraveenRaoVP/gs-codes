package org.interviewpanel.models;

import org.interviewpanel.models.helpers.InterviewStatus;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

public class Candidate {
    private int candidateId;
    private String candidateName;
    private Date candidateDOB;
    private String candidateQualification;
    private List<String> skills;
    private String positionForInterviewing;
    private InterviewStatus interviewStatus;
    private String candidateEmail;

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidatePhone() {
        return candidatePhone;
    }

    public void setCandidatePhone(String candidatePhone) {
        this.candidatePhone = candidatePhone;
    }

    private String candidatePhone;


    public Candidate(int candidateId, String candidateName, Date candidateDOB, String candidateQualification, List<String> skills, String positionForInterviewing, InterviewStatus interviewStatus) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateDOB = candidateDOB;
        this.candidateQualification = candidateQualification;
        this.skills = skills;
        this.positionForInterviewing = positionForInterviewing;
        this.interviewStatus = interviewStatus;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Date getCandidateDOB() {
        return candidateDOB;
    }

    public void setCandidateDOB(Date candidateDOB) {
        this.candidateDOB = candidateDOB;
    }

    public String getCandidateQualification() {
        return candidateQualification;
    }

    public void setCandidateQualification(String candidateQualification) {
        this.candidateQualification = candidateQualification;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getPositionForInterviewing() {
        return positionForInterviewing;
    }

    public void setPositionForInterviewing(String positionForInterviewing) {
        this.positionForInterviewing = positionForInterviewing;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(InterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId=" + candidateId +
                ", candidateName='" + candidateName + '\'' +
                ", candidateDOB=" + candidateDOB +
                ", candidateQualification='" + candidateQualification + '\'' +
                ", skills=" + skills +
                ", positionForInterviewing='" + positionForInterviewing + '\'' +
                ", interviewStatus=" + interviewStatus +
                '}';
    }
}
