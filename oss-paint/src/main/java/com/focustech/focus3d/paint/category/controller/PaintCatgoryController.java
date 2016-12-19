package com.focustech.focus3d.paint.category.controller;
import com.focustech.oss2008.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import com.focustech.focus3d.paint.category.model.PaintCatgory;
import com.focustech.focus3d.paint.category.service.PaintCatgoryService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/paintCatgory.do")
public class PaintCatgoryController extends AbstractController{
    @Autowired
    private PaintCatgoryService<PaintCatgory> paintCatgoryService;
    /**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=new", method = RequestMethod.GET)
	public String _new(ModelMap modelMap){
		modelMap.addAttribute("paintCatgory", new PaintCatgory());
		return "/focus3d/paintCatgory/new";
	}
	/**
	 *
	 * *
	 * @param paintCatgory
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=create", method = RequestMethod.POST)
	public String create(PaintCatgory paintCatgory, BindingResult result,ModelMap modelMap){
        if (result.hasErrors()) {
        	return "/focus3d/paintCatgory/new";
        }
        paintCatgoryService.insertOrUpdate(paintCatgory);
        modelMap.addAttribute("paintCatgory", paintCatgory);
        return "/focus3d/paintCatgory/edit";
	}
	/**
	 *
	 * *
	 * @param paintCatgory
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.GET)
	public String edit(Long sn, ModelMap modelMap){
		PaintCatgory paintCatgory = paintCatgoryService.select(sn);
		modelMap.addAttribute("paintCatgory", paintCatgory);
		return "/focus3d/paintCatgory/edit";
	}
	/**
	 *
	 * *
	 * @param paintCatgory
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.POST)
	public String edit(PaintCatgory paintCatgory, BindingResult result,ModelMap modelMap){
        if (result.hasErrors()) {
        	return "/focus3d/paintCatgory/edit";
        }
        paintCatgoryService.insertOrUpdate(paintCatgory);
        modelMap.addAttribute("paintCatgory", paintCatgory);
        return "/focus3d/paintCatgory/edit";
	}
	/**
	 *
	 * *
	 * @param paintCatgory
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(Long sn, ModelMap modelMap){
		paintCatgoryService.delete(sn);
		return redirectTo("/uitoolList.ui?funcID=1080331");
	}

}