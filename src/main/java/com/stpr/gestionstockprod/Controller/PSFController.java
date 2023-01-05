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
import org.springframework.data.domain.Pageable;
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
import java.util.List;

@Controller
@RequestMapping(value = "/psfs")
public class PSFController {
    @Autowired
    private PSFRepository psfRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private PSFLinkMPRepository psfLinkMPRepository;

    @Autowired
    private PSFLinkPSFRepository psfLinkPSFRepository;

    @Autowired
    private MPRepository mpRepository;

  //  @Secured(value={"ROLE_ADMIN","ROLE_USER"})
    @GetMapping(value = "/all")
    public String getAllPF(Model model, @RequestParam(name="page" , defaultValue ="0") int p){
        Page<PSF> pg = psfRepository.findAllByOrderByIdAsc( PageRequest.of(p, 6));
        int nbrePages =pg.getTotalPages();
        int [] pages = new int[nbrePages];
        for(int i= 0 ; i< nbrePages; i++)
        {
            pages[i]=i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("pagePSF", pg);
        model.addAttribute("pageCourante",p);
        return "psfs";
    }
  //  @Secured(value={"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping (value = "/index")
    public String index (Model model,
// paramètre pour le numero de la page (0 par défaut)
                         @RequestParam (name="page" , defaultValue ="0") int p,
// paramètre "motCle"
                         @RequestParam (name="motCle", defaultValue="") String mc)

    {
//déclarer une page de produits
        Page <PSF> pg =null;


// récupérer la page numero "p" (de taille 6)
        pg = psfRepository.findByDesignationLikeOrderByIdAsc("%"+mc+"%", PageRequest.of(p, 6));
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
        model.addAttribute("pagePSF", pg);
// placer la page des produits dans le "Model" comme un attribut"
        //   model.addAttribute("pageProduits", pg);
// retourner le numéro de la page courante
        model.addAttribute("pageCourante",p);
// retourner la valeur du mot clé
        model.addAttribute("motCle", mc);
//retourner le nom de la vue WEB à afficher
        return "psfs";
    }
  //  @Secured(value={"ROLE_ADMIN"})

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formPSFs(Model model){
        model.addAttribute("psf", new PSF());
        model.addAttribute("categories", categorieRepository.findAll());
        return "formPSFs";
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save (Model model, @Valid PSF psf, BindingResult bindingResult){
        System.out.println("ddddddddddddddddddddddddddddddd");
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "formPSFs";
        }
        psfRepository.save(psf);

        return "confirmation";
    }
 //   @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    public String delete (Long id, int page, String motCle)
    {
        psfRepository.deleteById(id);
        return "redirect:index?page="+page+"&motCle="+motCle;
    }
 //   @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit (Model model, @RequestParam (name="id")Long id)
    {
// récupérer l'objet ayany l'id spécifié
        PSF p =(PSF) psfRepository.findById(id).orElse(null);
        model.addAttribute("categories", categorieRepository.findAll());
// placer le produit trouvé dans le modèle
        model.addAttribute("psf", p);
// rediriger l'affichage vers la vue "editProduit"
        return "editPSF";
    }
 //   @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update (Model model, @Valid PSF psf ,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
// en cas d'erreurs de validation
            return "editPSF";
//enregistrer le produit dans la BD
        psfRepository.save(psf);
//Afficher une page pour confirmer l'enregistrement
        return "confirmation";

    }
  //  @Secured(value={"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/recipe", method = RequestMethod.GET)
    public String recipe(Model model, @RequestParam(name = "id") long id){
        PSF current= psfRepository.findOneById(id);
        Collection<PSF> psfs = new ArrayList<PSF>();
        Collection<MP> mps= new ArrayList<MP>();
        Collection<PSFLinkPSF> cPsfs=  psfLinkPSFRepository.findByPsf1(psfRepository.findOneById(id));
        System.out.println(cPsfs.size());
        Collection<PSFLinkMP> cMps= psfLinkMPRepository.findByPsf(psfRepository.findOneById(id));

        for (PSFLinkPSF cPsf: cPsfs){
            psfs.add(psfRepository.findOneById(cPsf.psf2.getId()));
        }
        for (PSFLinkMP cMp: cMps)
            mps.add(mpRepository.findOneById(cMp.mp.getId()));
        model.addAttribute("current", current);
        model.addAttribute("psfs", psfs);
        model.addAttribute("mps", mps);
        return "recipePSF";
    }
  //  @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value = "/addPSF", method = RequestMethod.GET)
    public String addPSF(Model model, @RequestParam(name = "id") long id){
        model.addAttribute("psfLinkPsf",new PSFLinkPSF());
        model.addAttribute("psfs", psfRepository.findAll());
        model.addAttribute("idpsf", id);
        return "addPSFLinkPSF";
    }
 //   @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value = "/saveLinkPSF", method = RequestMethod.POST)
    public String saveLinkPSF(Model model, @Valid PSFLinkPSF psfLink, BindingResult bindingResult){

        return "confirmation";
    }
}
