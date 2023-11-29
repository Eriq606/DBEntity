package voiture;
import java.time.LocalDate;
import java.time.LocalDateTime;

import veda.godao.annotations.Table;
import veda.godao.annotations.Column;

@Table("tokens")
public class Tokens {
	@Column("id_token")
	private int id_token;
	@Column("expiration_token")
	private LocalDateTime expiration_token;
	@Column("value_token")
	private String value_token;
	@Column("id_user_token")
	private int id_user_token;

	public int getId_token() {
    	return this.id_token;
	}

	public LocalDateTime getExpiration_token() {
    	return this.expiration_token;
	}

	public String getValue_token() {
    	return this.value_token;
	}

	public int getId_user_token() {
    	return this.id_user_token;
	}

	public void setId_token(int id_token) {
    	this.id_token = id_token;
	}

	public void setExpiration_token(LocalDateTime expiration_token) {
    	this.expiration_token = expiration_token;
	}

	public void setValue_token(String value_token) {
    	this.value_token = value_token;
	}

	public void setId_user_token(int id_user_token) {
    	this.id_user_token = id_user_token;
	}
}