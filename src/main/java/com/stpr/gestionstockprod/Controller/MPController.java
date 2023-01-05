package com.stpr.gestionstockprod.Controller;

import com.stpr.gestionstockprod.Model.ProductLink.PFLinkMP;
import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkMP;
import com.stpr.gestionstockprod.Model.ProductType.MP;
import com.stpr.gestionstockprod.Repository.CategorieRepository;
import com.stpr.gestionstockprod.Repository.MPRepository;
import com.stpr.gestionstockprod.Repository.PFLinkMPRepository;
import com.stpr.gestionstockprod.Repository.PSFLinkMPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
//@Secured(value={"ROLE_ADMIN","ROLE_USER"})
@RequestMapping("/mps")
public class MPController {
    @Autowired
    private MPRepository mpRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping(value = "/")
    public String acceuil(){
        return "acceuil";
    }
//    @Secured(value={"ROLE_ADMIN","ROLE_USER"})
    @GetMapping(value = "/all")
    public String getAllMP(Model model, @RequestParam (name="page" , defaultValue ="0") int p){
        Page<MP> pg = mpRepository.findAllByOrderByIdAsc( PageRequest.of(p, 6));
        int nbrePages =pg.getTotalPages();
        int [] pages = new int[nbrePages];
        for(int i= 0 ; i< nbrePages; i++)
        {
            pages[i]=i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("pageMP", pg);
        model.addAttribute("pageCourante",p);
        return "mps";
    }
    @RequestMapping (value = "/index")
    public String index (Model model,
// paramètre pour le numero de la page (0 par défaut)
                         @RequestParam (name="page" , defaultValue ="0") int p,
// paramètre "motCle"
                         @RequestParam (name="motCle", defaultValue="") String mc)

    {
//déclarer une page de produits
        Page <MP> pg =null;


// récupérer la page numero "p" (de taille 6)
        pg = mpRepository.findByDesignationLikeOrderByIdAsc("%"+mc+"%", PageRequest.of(p, 6));
// nombre total des pages
        int nbrePages =pg.getTotalPages();
// créer un tableau d'entier qui contient les numéros des pages
        int [] pages = new int[nbrePages];
        for(int i= 0 ; i< nbrePages; i++)
        {
            pages[i]=i;
        }
// placer le tableau dans le "Model"
        model.addAttribute("pages", pages);
        model.addAttribute("pageMP", pg);
// placer la page des produits dans le "Model" comme un attribut"
        //   model.addAttribute("pageProduits", pg);
// retourner le numéro de la page courante
        model.addAttribute("pageCourante",p);
// retourner la valeur du mot clé
        model.addAttribute("motCle", mc);
//retourner le nom de la vue WEB à afficher
        return "mps";
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formMPs(Model model){
        model.addAttribute("mp", new MP());
        model.addAttribute("categories", categorieRepository.findAll());
        return "formMPs";
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMP (Model model, @Valid MP mp, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "formMPs";
        mpRepository.save(mp);
        return "confirmation";
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    public String delete (Long id, int page, String motCle)
    {
        mpRepository.deleteById(id);
        return "redirect:index?page="+page+"&motCle="+motCle;
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit (Model model, @RequestParam (name="id")Long id)
    {
// récupérer l'objet ayany l'id spécifié
        MP p =(MP) mpRepository.findById(id).orElse(null);
        model.addAttribute("categories", categorieRepository.findAll());
// placer le produit trouvé dans le modèle
        model.addAttribute("mp", p);
// rediriger l'affichage vers la vue "editProduit"
        return "editMP";
    }
 //   @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update (Model model, @Valid MP mp ,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
// en cas d'erreurs de validation
            return "editMP";
//enregistrer le produit dans la BD
        mpRepository.save(mp);
//Afficher une page pour confirmer l'enregistrement
        return "confirmation";

    }
}