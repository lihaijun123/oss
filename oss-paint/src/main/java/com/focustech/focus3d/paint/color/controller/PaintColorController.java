package com.focustech.focus3d.paint.color.controller;
import com.focustech.oss2008.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import com.focustech.focus3d.paint.color.model.PaintColor;
import com.focustech.focus3d.paint.color.service.PaintColorService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/paintColor.do")
public class PaintColorController extends AbstractController{
    @Autowired
    private PaintColorService<PaintColor> paintColorService;
    /**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=new", method = RequestMethod.GET)
	public String _new(ModelMap modelMap){
		modelMap.addAttribute("paintColor", new PaintColor());
		return "/focus3d/paintColor/new";
	}
	/**
	 *
	 * *
	 * @param paintColor
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=create", method = RequestMethod.POST)
	public String create(PaintColor paintColor, BindingResult result,ModelMap modelMap){
        if (result.hasErrors()) {
        	return "/focus3d/paintColor/new";
        }
        paintColorService.insertOrUpdate(paintColor);
        modelMap.addAttribute("paintColor", paintColor);
        return "/focus3d/paintColor/edit";
	}
	/**
	 *
	 * *
	 * @param paintColor
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.GET)
	public String edit(Long sn, ModelMap modelMap){
		PaintColor paintColor = paintColorService.select(sn);
		modelMap.addAttribute("paintColor", paintColor);
		return "/focus3d/paintColor/edit";
	}
	/**
	 *
	 * *
	 * @param paintColor
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.POST)
	public String edit(PaintColor paintColor, BindingResult result,ModelMap modelMap){
        if (result.hasErrors()) {
        	return "/focus3d/paintColor/edit";
        }
        paintColorService.insertOrUpdate(paintColor);
        modelMap.addAttribute("paintColor", paintColor);
        return "/focus3d/paintColor/edit";
	}
	/**
	 *
	 * *
	 * @param paintColor
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(Long sn, ModelMap modelMap){
		paintColorService.delete(sn);
		return redirectTo("/uitoolList.ui?funcID=1080331");
	}

}