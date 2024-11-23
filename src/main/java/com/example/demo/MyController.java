package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
    private List<Petition> petitions = new ArrayList<>();

    public MyController() {
        // Adding three dummy petitions with dummy signatures
        Petition petition1 = new Petition("Save the Park", "Protect our local park from development.", "Niamh O'Sullivan");
        petition1.addSignature(new Signature("Conor Fitzpatrick", "conor.fitzpatrick@ucd.ie"));
        petition1.addSignature(new Signature("Róisín Murphy", "roisin.murphy@tcd.ie"));
        petition1.addSignature(new Signature("Darragh O'Connor", "darragh.oconnor@nuigalway.ie"));
        petition1.addSignature(new Signature("Aoife Kelly", "aoife.kelly@ul.ie"));
        petition1.addSignature(new Signature("Jack Byrne", "jack.byrne@dcu.ie"));
        petition1.addSignature(new Signature("Emma Ryan", "emma.ryan@mu.ie"));
        petition1.addSignature(new Signature("Cillian Gallagher", "cillian.gallagher@itcarlow.ie"));
        petition1.addSignature(new Signature("Saoirse Reilly", "saoirse.reilly@ait.ie"));
        petition1.addSignature(new Signature("Fionn Keane", "fionn.keane@dkit.ie"));
        petition1.addSignature(new Signature("Ciara Walsh", "ciara.walsh@wit.ie"));

        Petition petition2 = new Petition("Improve Public Transport", "More buses and better schedules for public transport.", "Ciarán Murphy");
        petition2.addSignature(new Signature("Oisín Redmond", "oisin.redmond@ucd.ie"));
        petition2.addSignature(new Signature("Siobhán Brennan", "siobhan.brennan@tcd.ie"));
        petition2.addSignature(new Signature("Seán O'Donnell", "sean.odonnell@nuigalway.ie"));
        petition2.addSignature(new Signature("Aisling McGrath", "aisling.mcgrath@ul.ie"));
        petition2.addSignature(new Signature("Rory Collins", "rory.collins@dcu.ie"));
        petition2.addSignature(new Signature("Megan Doyle", "megan.doyle@mu.ie"));
        petition2.addSignature(new Signature("Eoin Buckley", "eoin.buckley@itcarlow.ie"));
        petition2.addSignature(new Signature("Nuala Hayes", "nuala.hayes@ait.ie"));
        petition2.addSignature(new Signature("Caoimhe Flynn", "caoimhe.flynn@dkit.ie"));
        petition2.addSignature(new Signature("Padraig Nolan", "padraig.nolan@wit.ie"));

        Petition petition3 = new Petition("Reduce Plastic Usage", "Promote reusable alternatives to reduce plastic waste.", "Aoife McCarthy");
        petition3.addSignature(new Signature("Liam Kavanagh", "liam.kavanagh@ucd.ie"));
        petition3.addSignature(new Signature("Orlaith Ní Chonaill", "orlaith.nichonaill@tcd.ie"));
        petition3.addSignature(new Signature("Cathal Murphy", "cathal.murphy@nuigalway.ie"));
        petition3.addSignature(new Signature("Eimear FitzGerald", "eimear.fitzgerald@ul.ie"));
        petition3.addSignature(new Signature("Tadhg Casey", "tadhg.casey@dcu.ie"));
        petition3.addSignature(new Signature("Aoibhín O'Brien", "aoibhin.obrien@mu.ie"));
        petition3.addSignature(new Signature("Declan Foley", "declan.foley@itcarlow.ie"));
        petition3.addSignature(new Signature("Maeve Hogan", "maeve.hogan@ait.ie"));
        petition3.addSignature(new Signature("Cian Sweeney", "cian.sweeney@dkit.ie"));
        petition3.addSignature(new Signature("Gráinne Kearney", "grainne.kearney@wit.ie"));

        petitions.add(petition1);
        petitions.add(petition2);
        petitions.add(petition3);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("petitions", petitions);
        return "index";
    }

    @GetMapping("/create")
    public String showCreatePetitionForm() {
        return "create";
    }

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description, @RequestParam String author) {
        petitions.add(new Petition(title, description, author));
        return "redirect:/";
    }

    @GetMapping("/view")
    public String viewPetition(@RequestParam int index, Model model) {
        if (index >= 0 && index < petitions.size()) {
            Petition petition = petitions.get(index);
            model.addAttribute("petition", petition);
            model.addAttribute("index", index);
            return "view";
        } else {
            model.addAttribute("errorMessage", "Petition not found.");
            return "error";
        }
    }

    @GetMapping("/search")
    public String searchPetitions(@RequestParam(value = "query", required = false) String query, Model model) {
        if (query == null || query.trim().isEmpty()) {
            model.addAttribute("searchResults", new ArrayList<>());
        } else {
            List<Petition> searchResults = new ArrayList<>();
            for (Petition petition : petitions) {
                if (petition.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    searchResults.add(petition);
                }
            }
            model.addAttribute("searchResults", searchResults);
        }
        return "search";
    }

    @GetMapping("/sign")
    public String signPetitionForm(@RequestParam int index, Model model) {
        if (index >= 0 && index < petitions.size()) {
            model.addAttribute("index", index);
            return "sign";
        } else {
            model.addAttribute("errorMessage", "Petition not found.");
            return "error";
        }
    }

    @PostMapping("/sign")
    public String signPetition(@RequestParam int index, @RequestParam String name, @RequestParam String email) {
        if (index >= 0 && index < petitions.size()) {
            Petition petition = petitions.get(index);
            petition.addSignature(new Signature(name, email));
            return "redirect:/view?index=" + index;
        } else {
            return "redirect:/error";
        }
    }
}
