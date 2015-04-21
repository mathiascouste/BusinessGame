package bg.connexion.interfaces;

import javax.ejb.Local;

@Local
public interface Connexion {
	public Long connectToGame(Long ident, String password);
	public Long connectToCompany(Long gameIdent, String companyName, String password);
	public Long connectToCompany(Long companyIdent, String password);
}
