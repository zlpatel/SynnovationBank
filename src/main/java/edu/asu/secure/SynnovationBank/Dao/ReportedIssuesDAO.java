package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.ReportedIssues;

public interface ReportedIssuesDAO {

	public long insertIssue(String userId, ReportedIssues issue);
	
	public boolean updateIssueResolved(Long issueId);
}
