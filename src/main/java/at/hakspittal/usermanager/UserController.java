package at.hakspittal.usermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable(name="id") Long id) {

        //Die übergebene Id nutzen, um den entsprechenden User
        // in der DB zu löschen
        service.delete(id);

        return "redirect:/all_users";
    }
    

    @RequestMapping("/edit_user/{id}")
    public ModelAndView showEditUserForm(@PathVariable(name="id") Long id) {
        ModelAndView mav = new ModelAndView("edit_user");
        
        //Den gewünschten User aus der DB holen
        User user = service.get(id);

        //Die Daten des Users im View anzeigen
        mav.addObject("user", user);

        return mav;
    }

    //Neuen User über Formular anlegen.
    //Dazu benötigen wir:
    //  1. eine Route zum Formular (/new_user)
    //  2. eine Route um den neuen User zu speichern (/save_user)
    @GetMapping("/new_user")
    public String showUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    //2. Speichern
    //Die Route /save_user kann sowohl für ein INSERT eines neuen Users 
    //als auch für ein UPDATE eines bestehenden Users verwendet werden.
    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute User user, Model model) {
        // Logik zum Speichern des Benutzers (z.B. in einer Datenbank)
        // Service-Methode save vorbereiten und hier anwenden

        try{
            service.save(user);
        
            // Vor- und Nachnamen dem Model hinzufügen, um sie im View anzuzeigen
            model.addAttribute("firstname", user.getFirstname()); 
            model.addAttribute("lastname", user.getLastname());

            return "user_success";
        }catch(Exception e){
            return "error";
        }
    }


    @GetMapping("/all_users")
    public String showAllUsers(Model model) {

        //Liste aller User aus der DB laden
        List<User> listUsers = service.listAll();

        //Liste der Users an den View weiterleiten
        model.addAttribute("listUsers", listUsers);

        //Sprung zum View (HTML)
        return "all_users";
    }
    
}
