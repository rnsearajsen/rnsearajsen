package main;

public class Sub_Group {
   protected int subgrp_id;
   protected String fsub_group;
   protected String fgroup;   
   protected String comments;
   protected Float amtinhand;
   public Sub_Group() {   
   }
	
   public Sub_Group(int subgrp_id) {
	   this.subgrp_id = subgrp_id;
   }
   public Sub_Group(int subgrp_id, String fsub_group, String fgroup, String comments) {
       this(fsub_group, fgroup, comments);
       this.subgrp_id = subgrp_id;
   }
   
   public Sub_Group(int subgrp_id, String fsub_group, String fgroup, String comments, Float amtinhand) {
       this.subgrp_id = subgrp_id;
       this.fsub_group = fsub_group;
       this.fgroup = fgroup;
       this.comments = comments;
       this.amtinhand = amtinhand;
   }
    
   public Sub_Group(String fsub_group, String fgroup, String comments) {
	   this.fsub_group = fsub_group;
       this.fgroup = fgroup;
       this.comments = comments;
   }
   
   
public int getId() {
       return subgrp_id;
   }

   public void setId(int subgrp_id) {
       this.subgrp_id = subgrp_id;
   }

   public String getFgroup() {
		return fgroup;
	}

	public void setFgroup(String fgroup) {
		this.fgroup = fgroup;
	}
	
   public String getFsub_group() {
		return fsub_group;
	}

   public void setFsub_group(String fsub_group) {
		this.fsub_group = fsub_group;
	}

   public String getComments() {
       return comments;
   }

   public void setComments(String comments) {
       this.comments = comments;
   }

public Float getAmtinhand() {
	return amtinhand;
}

public void setAmtinhand(Float amtinhand) {
	this.amtinhand = amtinhand;
}

}
