package org.opentides.example.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.opentides.bean.Comment;
import org.opentides.bean.SearchResults;
import org.opentides.bean.SystemCodes;
import org.opentides.example.bean.Ninja;
import org.opentides.example.bean.TutorialModel;
import org.opentides.example.service.NinjaService;
import org.opentides.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A spring controller in charge for the whole tutorial module. 
 * @author Ronielson
 *
 */
@Controller
public class TutorialController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NinjaService ninjaService;
	
	@RequestMapping(method = RequestMethod.GET, value="/overview")
	public String overview(ModelMap map){

		return "app/tutorial-overview";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/opentides")
	public String opentides(ModelMap map){
		return "app/tutorial-opentides";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getting-started")
	public String gettingStarted(ModelMap map){
		
		return "app/tutorial-getting-started";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/creating-project")
	public String creatingProject(ModelMap map){

		return "app/tutorial-creating-project";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/customize")
	public String customize(ModelMap map){

		return "app/tutorial-customize";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/user-management")
	public String userManagement(ModelMap map){
		return "app/tutorial-user-management";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/entities-and-attributes")
	public String entitiesAndAttributes(ModelMap map){

		return "app/tutorial-entities-and-attributes";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/dao-and-service")
	public String daoAndService(ModelMap map){

		return "app/tutorial-dao-and-service";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/crud-controller")
	public String crudController(ModelMap map){

		return "app/tutorial-crud-controller";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/crud-view")
	public String crudView(ModelMap map){

		return "app/tutorial-crud-view";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/crud-form")
	public String crudForm(ModelMap map){

		return "app/tutorial-crud-form";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/validation")
	public String validation(ModelMap map){

		return "app/tutorial-validation";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/form-tags")
	public String formTags(ModelMap map){
		
		map.addAttribute("elementList", elementList());
		map.addAttribute("tutorialModel", tutorialModel());
		map.addAttribute("results", results());
		map.addAttribute("genderList", genderList());
		map.addAttribute("brandList", brandList());
		map.addAttribute("formCommand", modelAttribute());
		
		return "app/tutorial-form-tags";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/review")
	public String review(ModelMap map){
		return "app/tutorial-review";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/adding-tags")
	public String taggable(ModelMap map){
		map.addAttribute("formCommand", ninjaModelAttribute());
		return "app/tutorial-taggable";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/attaching-images")
	public String imageUploadable(ModelMap map){
		map.addAttribute("formCommand", ninjaModelAttribute());
		return "app/tutorial-imageuploadable";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/dbevolve")
	public String dbEvolve(ModelMap map){
		return "app/tutorial-dbevolve";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/using-modals")
	public String usingModals(ModelMap map){
		return "app/tutorial-using-modals";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/using-modals-2")
	public String usingModals2(ModelMap map){
		return "app/tutorial-using-modals-2";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/spring-controllers")
	public String springControllers(ModelMap map){
		return "app/tutorial-spring-controllers";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/adding-images")
	public String addingImages(ModelMap map){
		return "app/tutorial-adding-images";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/advanced-overview")
	public String advancedOverview(ModelMap map){
		return "app/tutorial-advanced-overview";
	}
	
	private TutorialModel modelAttribute(){
		return new TutorialModel();
	}
	
	private Ninja ninjaModelAttribute(){
		return new Ninja();
	}
	
	public List<SystemCodes> brandList() {
		List<SystemCodes> brands = new ArrayList<SystemCodes>();
		brands.add(new SystemCodes("MODEL","APPLE","Apple"));
		brands.add(new SystemCodes("MODEL","LG","LG"));
		brands.add(new SystemCodes("MODEL","HTC","HTC"));
		brands.add(new SystemCodes("MODEL","NOKIA","Nokia"));
		brands.add(new SystemCodes("MODEL","SAMSUNG","Samsung"));
		brands.add(new SystemCodes("MODEL","SONY","Sony"));
		return brands;
	}
	
	
	public List<SystemCodes> elementList() {
		List<SystemCodes> elementList = new ArrayList<SystemCodes>();
		elementList.add(new SystemCodes("Fire","",""));
		elementList.add(new SystemCodes("Water","",""));
		elementList.add(new SystemCodes("Wind","",""));
		elementList.add(new SystemCodes("Rock","",""));
		return elementList;
	}
	
	public List<SystemCodes> genderList() {
		List<SystemCodes> genderList = new ArrayList<SystemCodes>();
		genderList.add(new SystemCodes("GENDER","FEMALE","Female"));
		genderList.add(new SystemCodes("GENDER","MALE","Male"));
		genderList.add(new SystemCodes("GENDER","OTHES","Other"));
		return genderList;
	}
	
	public SearchResults<Ninja> results(){
		SearchResults<Ninja> results = new SearchResults<Ninja>(10, 10);
		for (int i = 0; i < 10; i++) {
			results.getResults().add(new Ninja());
		}
		results.setTotalResults(50L);
		
		return results;
	}
	
	public TutorialModel tutorialModel(){
		TutorialModel model = new TutorialModel();
		Comment c = new Comment();
		c.setAuthor(userService.getCurrentUser());
		c.setText("This is an example comment.");
		model.getComments().add(c);
		
		return model;
	}
	
	
	
}
