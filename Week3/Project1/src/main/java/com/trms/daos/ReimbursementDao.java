package com.trms.daos;

import java.io.File;
import java.util.List;

import com.trms.beans.AddedInfo;
import com.trms.beans.AttachedFile;
import com.trms.beans.Reimbursement;

public interface ReimbursementDao {
	// Attachment related methods
	public int getNumberAttachments(int rId);
	public int insertReimbursement(Reimbursement r);
	public int insertAttachment(File f, int r_id);
	public int insertAttachmentWithType(File f, int rId, String type);
	public List<AttachedFile> getAttachmentsBy(int rId);
	
	// Info related methods
	public int insertAddedInfo(AddedInfo ai);
	public List<AddedInfo> getAddedInfoBy(int rId);
	public int updateReqInfo(int rId, int empId);
	
	// Get
	public int getReimburseByEmpId(int empId);
	public Reimbursement getReimbBy(int rId);
	public List<Reimbursement> getAllReimburse();
	public List<Reimbursement> getPersonalReimb(int empId);
	public List<Reimbursement> getReimburse(int empId);
	public int getEmpIdByReimburse(int rId);
	
	// Set
	public int updateGrade(int rId, float grade);
	public int updateProjReimb(int rId, float projReimb);
	
	// Approval related methods
	public int updateApproved(int rId, int response);
	public int setApproveId(int rId, int empId);
	public int setApproveLvl(int rId, int newLvl);
	public int awardReimburse(int rId, int response);
}
