package in.ashokit.contoller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.model.Citizen;
import in.ashokit.model.SearchRequest;
import in.ashokit.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		//System.out.println("-----------indexPage---------------");
		
		SearchRequest searchRequest = new SearchRequest();
		model.addAttribute("search", searchRequest);
		init(model);
		
	return "index";
	}


    @PostMapping(path="/search")
    public String getSearchResults(@ModelAttribute("search") SearchRequest  searchRequest, Model model) {

    	//System.out.println("-----------getSearchResults---------1------="+searchRequest);
    	init(model);
    	List<CitizenPlan> citizensList = service.getSearchResults(searchRequest);
    	model.addAttribute("citizenslst", citizensList);
    	//System.out.println("-----------getSearchResults-------2--------=");
    	return "index";
    }
    
    
	@GetMapping("/addcitizen")
	public String addCitizen(Model model) {
		//System.out.println("-----------addCitizen---------------");
		
		Citizen citizen = new Citizen();
		model.addAttribute("citizen", citizen);
		init(model);
		
		return "addcitizen";
	}
	
    @PostMapping(path="/savecitizen")
    public String saveCitizen(@ModelAttribute("citizen") Citizen citizen, Model model) {

    	//System.out.println("-----------saveCitizen---------A------="+citizen);
    	Integer citizenId = service.saveCitizen(citizen);
    	//System.out.println("-----------saveCitizen---------citizenId="+citizenId);
		model.addAttribute("citizen", new Citizen());
		init(model);
		
		getAll(model);
		
    	//System.out.println("-----------saveCitizen-------Z--------=");
    	return "allcitizens";
    }	

    
	@GetMapping("/all")
	public String getAll(Model model) {
		//System.out.println("-----------getAll---------A------=");
		
    	List<CitizenPlan> citizensList = service.getAll();
    	model.addAttribute("citizenslst", citizensList);
    	
    	//System.out.println("-----------getAll-------Z--------=");
		
	return "allcitizens";
	}    
	
	@RequestMapping(value = "/remove/{id}")
	public String deleteCitizen(@PathVariable Integer id, Model model) {
		//System.out.println("-----------deleteCitizen---------A------id===="+id);
		service.deleteCitizen(id);
		getAll(model);
		//System.out.println("-----------deleteCitizen---------Z------=");
		return "allcitizens";
	}
    
	@RequestMapping(value = "/get/{id}")
	public String getCitizen(@PathVariable Integer id, Model model) {
		//System.out.println("-----------getCitizen---------A------id===="+id);

		init(model);
		CitizenPlan citizen = service.findById(id);
		model.addAttribute("citizen", citizen);
		
		//System.out.println("-----------getCitizen---------Z------=");
		return "editcitizen";
	}
	

	
    @PostMapping(value = "/get/upadecitizen")
	public String updateCitizen(Citizen citizen, Model model) {
		System.out.println("-----------updateCitizen---------A------citizen=="+citizen);
    	Integer citizenId = service.saveCitizen(citizen);
    	System.out.println("-----------saveCitizen---------citizenId="+citizenId);
		//model.addAttribute("citizen", new Citizen());
		
		getAll(model);
		System.out.println("-----------updateCitizen---------Z------=");
		return "allcitizens";
	}
	
    
    @RequestMapping(value = "/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		System.out.println("-----------generateExcel---------A------");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=plans.xls");

		boolean exportExcel = service.exportExcel(response);
		System.out.println("-----------generateExcel---------Z------exportExcel="+exportExcel);
	}
    
    @RequestMapping(value = "/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception {
		System.out.println("-----------pdfExport---------A------");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=plans.pdf");

		boolean exportPdf = service.exportPdf(response);
		System.out.println("-----------pdfExport---------Z------exportPdf="+exportPdf);
	}
	
	private void init(Model model) {
		model.addAttribute("plannames", service.getPlans());
		model.addAttribute("statuses", service.getStatus());
	}

}
