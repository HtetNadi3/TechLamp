package entity;

import java.util.Date;

import dto.UserDTO;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Date created_date;
    private String phone_number;
    private String bio;
    private String occupation;
    private String profile_img;

    public User(int id, String username, String password, String email, String role, Date created_date,
			String phone_number, String bio, String occupation, String profile_img) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.created_date = created_date;
		this.phone_number = phone_number;
		this.bio = bio;
		this.occupation = occupation;
		this.profile_img = profile_img;
	}

	public User() {}

    public User(int id, String username, String password,String email, String role, Date created_date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.created_date = created_date;
    }
    
    public User(UserDTO userDto) {
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.role = userDto.getRole();
        this.created_date = userDto.getCreated_date();
    }


    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    

    public void setRole(String role) {
        this.role = role;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
}