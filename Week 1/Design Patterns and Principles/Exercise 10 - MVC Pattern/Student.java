package stud;

public class Student 
{
	private String name,id,grade;
	
	public Student(String id,String name,String grade)
	{
		this.id=id;
		this.name=name;
		this.grade=grade;
	}
	
	void setName(String name) {
		this.name=name;
	}
	
	String getName() {
		return name;
	}
	
	void setId(String id) {
		this.id=id;
	}
	
	String getId() {
		return id;
	}
	
	void setGrade(String grade) {
		this.grade=grade;
	}
	
	String getGrade() {
		return grade;
	}
}
