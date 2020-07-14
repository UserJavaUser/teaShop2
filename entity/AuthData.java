package by.htp.ishop.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "auth_data")
public class AuthData implements Serializable{

	private static final long serialVersionUID = 1620663981288950789L;

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="login")
	private String login;
	@Column(name="salt")
	private byte[] salt;
	@Column(name="password")
	private byte[] hash;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	public AuthData() {
		
	}

	public AuthData(String login, byte[] salt, byte[] hash) {
		super();
		this.login = login;
		this.salt = salt;
		this.hash = hash;
	}

	public AuthData(int id, String login, byte[] salt, byte[] hash) {
		super();
		this.id = id;
		this.login = login;
		this.salt = salt;
		this.hash = hash;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(hash);
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + Arrays.hashCode(salt);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		AuthData other = (AuthData) obj;
		if (!Arrays.equals(hash, other.hash))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (!Arrays.equals(salt, other.salt))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
