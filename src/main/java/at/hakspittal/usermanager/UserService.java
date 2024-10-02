package at.hakspittal.usermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    /*
     * Liste aller User erzeugen.
     * (SELECT * FROM User)
     */
    public List<User> listAll(){
        return repo.findAll();
    }

    /*
     * Hinzufügen eines neuen Users
     * oder aktualisieren eines bestehenden Users
     * INSERT INTO oder UPDATE .. SET
     */
    public void save(User user){
        repo.save(user);
    }

    /*
     * Einen bestimmten User aus der DB laden
     * SELECT * FROM User WHERE id = {id}
     */
    public User get(Long id){
        return repo.findById(id).get();
    }

    /* 
     * einen bestimmten User aus der DB entfernen
     * DELETE FROM User WHERE id = {id}
     */
    public void delete(Long id){
        repo.deleteById(id);
    }
}
