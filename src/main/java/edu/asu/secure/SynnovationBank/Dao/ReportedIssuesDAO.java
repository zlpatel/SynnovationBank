package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.ReportedIssues;

public interface ReportedIssuesDAO {

	public long insertIssue(ReportedIssues issue);
	public boolean updateIssueResolved(ReportedIssues issue);
}
