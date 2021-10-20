package main;

public class Group {
   protected int group_id;
   protected String name;
   protected String comments;
	
   public Group() {   
   }
	
   public Group(int group_id) {
	   this.group_id = group_id;
   }
   public Group(int group_id, String name, String comments) {
       this(name, comments);
       this.group_id = group_id;
   }
    
   public Group(String name, String comments) {
       this.name = name;
       this.comments = comments;
   }

   public int getId() {
       return group_id;
   }

   public void setId(int group_id) {
       this.group_id = group_id;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getComments() {
       return comments;
   }

   public void setComments(String comments) {
       this.comments = comments;
   }

}
