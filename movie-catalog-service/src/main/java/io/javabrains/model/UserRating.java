package io.javabrains.model;

import java.util.List;

public class UserRating {

	private List<Rating> usersRating;
	
	public UserRating() {
		// TODO Auto-generated constructor stub
	}

	public List<Rating> getUsersRating() {
		return usersRating;
	}

	public void setUsersRating(List<Rating> usersRating) {
		this.usersRating = usersRating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usersRating == null) ? 0 : usersRating.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRating other = (UserRating) obj;
		if (usersRating == null) {
			if (other.usersRating != null)
				return false;
		} else if (!usersRating.equals(other.usersRating))
			return false;
		return true;
	}

	public UserRating(List<Rating> usersRating) {
		super();
		this.usersRating = usersRating;
	}

	@Override
	public String toString() {
		return "UserRating [usersRating=" + usersRating + "]";
	}
	
	
	
}
