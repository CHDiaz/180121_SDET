package dao;

import java.sql.Date;
import java.util.List;

import bean.Reimbursement;

public interface ReimbursementDao {
	public void insertR(String username, String supername, Date submitDate, Date startDate, int isUrgent, int adjustedAmount);
	public int isUrgent(String submitDate, String startDate);
	public List<Reimbursement> getR(String username, int level);
	public int getUniqueId();
	public Reimbursement getR(int id);
}
