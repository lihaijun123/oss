package com.focustech.focus3d.paint.product.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.TCUtil;
import com.focustech.extend.spring.argresolver.RedirectAttributes;
import com.focustech.focus3d.paint.category.model.PaintCatgory;
import com.focustech.focus3d.paint.category.service.PaintCatgoryService;
import com.focustech.focus3d.paint.color.model.PaintColor;
import com.focustech.focus3d.paint.color.service.PaintColorService;
import com.focustech.focus3d.paint.model.model.PaintModel;
import com.focustech.focus3d.paint.product.model.PaintProduct;
import com.focustech.focus3d.paint.product.service.PaintProductService;
import com.focustech.oss2008.web.AbstractController;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/paintProduct.do")
public class PaintProductController extends AbstractController{
    @Autowired
    private PaintProductService<PaintProduct> paintProductService;
    @Autowired
    private PaintCatgoryService<PaintCatgory> paintCatgoryService;
    @Autowired
    private PaintColorService<PaintColor> paintColorService;
    
    @ModelAttribute
    public void initList(ModelMap modelMap){
    	List<PaintCatgory> cateList = paintCatgoryService.list();
		modelMap.addAttribute("cateList", cateList);
		List<PaintColor> colorList = paintColorService.list();
		modelMap.addAttribute("colorList", colorList);
    }
    /**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=new", method = RequestMethod.GET)
	public String _new(ModelMap modelMap){
		modelMap.addAttribute("paintProduct", new PaintProduct());
		return "/focus3d/paintProduct/new";
	}
	/**
	 *
	 * *
	 * @param paintProduct
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=create", method = RequestMethod.POST)
	public String create(PaintProduct paintProduct, BindingResult result,ModelMap modelMap, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
        	return "/focus3d/paintProduct/new";
        }
        paintProduct.setVersionNum(1);
        paintProductService.insertOrUpdate(paintProduct);
        redirectAttributes.addFlashAttribute("保存成功");
        return redirectTo("/paintProduct.do?method=edit&sn=" + paintProduct.getSn());
	}
	/**
	 *
	 * *
	 * @param paintProduct
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.GET)
	public String edit(Long sn, ModelMap modelMap){
		PaintProduct paintProduct = paintProductService.select(sn);
		modelMap.addAttribute("paintProduct", paintProduct);
		return "/focus3d/paintProduct/edit";
	}
	/**
	 *
	 * *
	 * @param paintProduct
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.POST)
	public String edit(PaintProduct paintProduct, BindingResult result,ModelMap modelMap, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
        	return "/focus3d/paintProduct/edit";
        }
        PaintProduct dbObj = paintProductService.select(paintProduct.getSn());
        paintProduct.setVersionNum(dbObj.getVersionNum());
		if(dbObj.getModelFileSn() != null && !dbObj.getModelFileSn().equals(paintProduct.getModelFileSn())){
			Integer version = TCUtil.iv(paintProduct.getVersionNum());
			paintProduct.setVersionNum(++ version);
		}
        paintProductService.update(paintProduct);
        redirectAttributes.addFlashAttribute("保存成功");
        return redirectTo("/paintProduct.do?method=edit&sn=" + paintProduct.getSn());
	}
	/**
	 *
	 * *
	 * @param paintProduct
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(Long sn, ModelMap modelMap){
		paintProductService.delete(sn);
		return redirectTo("/uitoolList.ui?funcID=1080332");
	}

}