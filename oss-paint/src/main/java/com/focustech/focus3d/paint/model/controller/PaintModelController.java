package com.focustech.focus3d.paint.model.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.extend.spring.argresolver.RedirectAttributes;
import com.focustech.focus3d.paint.model.model.PaintModel;
import com.focustech.focus3d.paint.model.service.PaintModelService;
import com.focustech.oss2008.web.AbstractController;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/paintModel.do")
public class PaintModelController extends AbstractController{
    @Autowired
    private PaintModelService<PaintModel> paintModelService;
    /**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=new", method = RequestMethod.GET)
	public String _new(ModelMap modelMap){
		modelMap.addAttribute("paintModel", new PaintModel());
		return "/focus3d/paintModel/new";
	}
	/**
	 *
	 * *
	 * @param paintModel
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=create", method = RequestMethod.POST)
	public String create(PaintModel paintModel, BindingResult result,ModelMap modelMap, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
        	return "/focus3d/paintModel/new";
        }
        paintModelService.insertOrUpdate(paintModel);
    	redirectAttributes.addFlashAttribute("保存成功");
    	return redirectTo("/paintModel.do?method=edit&sn=" + paintModel.getSn());
	}
	/**
	 *
	 * *
	 * @param paintModel
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.GET)
	public String edit(Long sn, ModelMap modelMap){
		PaintModel paintModel = paintModelService.select(sn);
		modelMap.addAttribute("paintModel", paintModel);
		return "/focus3d/paintModel/edit";
	}
	/**
	 *
	 * *
	 * @param paintModel
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.POST)
	public String edit(PaintModel paintModel, BindingResult result,ModelMap modelMap, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
        	return "/focus3d/paintModel/edit";
        }
        paintModelService.insertOrUpdate(paintModel);
        redirectAttributes.addFlashAttribute("保存成功");
    	return redirectTo("/paintModel.do?method=edit&sn=" + paintModel.getSn());
	}
	/**
	 *
	 * *
	 * @param paintModel
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(Long sn, ModelMap modelMap){
		paintModelService.delete(sn);
		return redirectTo("/uitoolList.ui?funcID=1080329");
	}

}