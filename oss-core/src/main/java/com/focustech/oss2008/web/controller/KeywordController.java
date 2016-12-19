package com.focustech.oss2008.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.oss2008.service.KeywordService;
import com.focustech.oss2008.web.AbstractController;

/**
 * <li>關鍵詞查詢Controller</li>
 *
 * @author jibin
 */
@Controller
@RequestMapping("/keyword.do")
public class KeywordController extends AbstractController {
    // @Autowired
    // @Qualifier("keywordXmlRpcService")
    // private OssXmlRpcService xmlRpcService;
    @Autowired
    private KeywordService keywordService;

    /**
     * 跳轉到查詢頁面
     *
     * @param req
     * @return
     */
    @RequestMapping(params = "method=suggest", method = RequestMethod.GET)
    public String suggest(HttpServletRequest req) {
        return "/keyword/suggest";
    }

    /**
     * 關鍵詞建議
     *
     * @param word
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "method=queryKeyword", method = RequestMethod.POST)
    public void queryKeyword(String word, HttpServletResponse response) throws IOException {
        //
        // try {
        // String suggestWord = (String) xmlRpcService.call("KeywordSuggest.getSuggest", new Object[]{word});
        // ajaxOutput(response, suggestWord);
        // }
        // catch (Exception e) {
        // log.error("關鍵詞建議rpc調用失敗！", e);
        // ajaxOutput(response, "[\"errMsg\"]");
        // }
        ajaxOutput(response, "");
    }

    /**
     * 關鍵詞搜索排行
     *
     * @param req
     * @return
     */
    @RequestMapping(params = "method=order", method = RequestMethod.GET)
    public String order(HttpServletRequest req) {
        List<Map<String, Object>> lst = keywordService.search(10005L);
        req.setAttribute("lst", lst);
        return "/keyword/order";
    }
}
