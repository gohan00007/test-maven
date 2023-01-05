package com.stpr.gestionstockprod.Controller;

import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkMP;
import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkPSF;
import com.stpr.gestionstockprod.Model.ProductType.MP;
import com.stpr.gestionstockprod.Model.ProductType.PF;
import com.stpr.gestionstockprod.Model.ProductType.PSF;
import com.stpr.gestionstockprod.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping(value = "/pfs")
public class PFController {
    @Autowired
    private PFRepository pfRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private PFLinkMPRepository pfLinkMPRepository;

    @Autowired
    private PFLinkPSFRepository pfLinkPSFRepository;

    @Autowired
    private MPRepository mpRepository;

    @Autowired
    private PSFRepository psfRepository;
 //   @Secured(value={"ROLE_ADMIN","ROLE_USER"})
    @GetMapping(value = "/all")
    public String getAllPF(Model model, @RequestParam(name="page" , defaultValue ="0") int p){
        Page<PF> pg = pfRepository.findAllByOrderByIdAsc( PageRequest.of(p, 6));
        int nbrePages =pg.getTotalPages();
        int [] pages = new int[nbrePages];
        for(int i= 0 ; i< nbrePages; i++)
        {
            pages[i]=i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("pagePF", pg);
        model.addAttribute("pageCourante",p);
        return "pfs";
    }
 //   @Secured(value={"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping (value = "/index")
    public String index (Model model,
// paramètre pour le numero de la page (0 par défaut)
                         @RequestParam (name="page" , defaultValue ="0") int p,
// paramètre "motCle"
                         @RequestParam (name="motCle", defaultValue="") String mc)

    {
//déclarer une page de produits
        Page <PF> pg =null;


// récupérer la page numero "p" (de taille 6)
        pg = pfRepository.findByDesignationLikeOrderByIdAsc("%"+mc+"%", PageRequest.of(p, 6));
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
        model.addAttribute("pagePF", pg);
// placer la page des produits dans le "Model" comme un attribut"
        //   model.addAttribute("pageProduits", pg);
// retourner le numéro de la page courante
        model.addAttribute("pageCourante",p);
// retourner la valeur du mot clé
        model.addAttribute("motCle", mc);
//retourner le nom de la vue WEB à afficher
        return "pfs";
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formPFs(Model model){
        model.addAttribute("pf", new PF());
        model.addAttribute("categories", categorieRepository.findAll());
        return "formPFs";
    }
   // @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save (Model model, @Valid PF pf, BindingResult bindingResult){
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "formPFs";
        }
        System.out.println(pf.getDesignation());
        pfRepository.save(pf);

        return "confirmation";
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    public String delete (Long id, int page, String motCle)
    {
        pfRepository.deleteById(id);
        return "redirect:index?page="+page+"&motCle="+motCle;
    }
   // @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit (Model model, @RequestParam (name="id")Long id)
    {
// récupérer l'objet ayany l'id spécifié
        PF p =(PF) pfRepository.findById(id).orElse(null);
        model.addAttribute("categories", categorieRepository.findAll());
// placer le produit trouvé dans le modèle
        model.addAttribute("pf", p);
// rediriger l'affichage vers la vue "editProduit"
        return "editPF";
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update (Model model, @Valid PF pf ,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
// en cas d'erreurs de validation
            return "editPF";
//enregistrer le produit dans la BD
        pfRepository.save(pf);
//Afficher une page pour confirmer l'enregistrement
        return "confirmation";

    }
  //  @Secured(value={"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/recipe", method = RequestMethod.GET)
    public String recipe(Model model, @RequestParam(name = "id") Long id){
        PSF current= psfRepository.findOneById(id);
        Collection<PSF> psfs = new ArrayList<PSF>();
        Collection<MP> mps= new ArrayList<MP>();
        Collection<PSFLinkPSF> cPsfs=  pfLinkPSFRepository.findByPf(pfRepository.findOneById(id));
        Collection<PSFLinkMP> cMps= pfLinkMPRepository.findByPf(pfRepository.findOneById(id));

        for (PSFLinkPSF cPsf: cPsfs){
            psfs.add(psfRepository.findOneById(cPsf.psf2.getId()));

        }
        for (PSFLinkMP cMp: cMps)
            mps.add(mpRepository.findOneById(cMp.mp.getId()));
        model.addAttribute("psfs", psfs);
        model.addAttribute("mps", mps);
        model.addAttribute("current", current);
        return "recipePF";
    }
}
