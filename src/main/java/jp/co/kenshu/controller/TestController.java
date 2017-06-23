package jp.co.kenshu.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.kenshu.dto.test.TestDto;
import jp.co.kenshu.form.TestForm;
import jp.co.kenshu.service.TestService;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    //idの値を渡してる
    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public String test(Model model, @PathVariable int id) {
        TestDto test = testService.getTest(id);
        model.addAttribute("message", "MyBatisのサンプルです");
        model.addAttribute("test", test);
        return "test";
    }

    //全件取得
    @RequestMapping(value = "/test/", method = RequestMethod.GET)
    public String testAll(Model model) {
        List<TestDto> tests = testService.getTestAll();
        model.addAttribute("message", "MyBatisの全件取得サンプルです");
        model.addAttribute("tests", tests);
        return "testAll";
    }

    //WHEREにオブジェクトを渡す
    @RequestMapping(value = "/test/dto/{id}", method = RequestMethod.GET)
    public String testDto(Model model, @PathVariable int id) {
        TestDto dto = new TestDto();
        dto.setId(id);
        TestDto test = testService.getTestByDto(dto);
        model.addAttribute("message", "MyBatisのサンプルです");
        model.addAttribute("test", test);
        return "test";
    }


    //insert
    @RequestMapping(value = "/test/insert/input/", method = RequestMethod.GET)
    public String testInsert(Model model) {
        TestForm form = new TestForm();
        model.addAttribute("testForm", form);
        model.addAttribute("message", "MyBatisのinsertサンプルです。");
        return "testInsert";
    }

    @RequestMapping(value = "/test/insert/input/", method = RequestMethod.POST)
    public String testInsert(@ModelAttribute TestForm form, Model model) {
        int count = testService.insertTest(form.getName());
        Logger.getLogger(TestController.class).log(Level.INFO, "挿入件数は" + count + "件です。");
        return "redirect:/test/";
    }

    //delete
    @RequestMapping(value = "/test/delete/input/", method = RequestMethod.GET)
    public String testDelete(Model model) {
        TestForm form = new TestForm();
        model.addAttribute("testForm", form);
        model.addAttribute("message", "MyBatisのdeleteサンプルです。");
        return "testDelete";
    }

    @RequestMapping(value = "/test/delete/input/", method = RequestMethod.POST)
    public String testDelete(@ModelAttribute TestForm form, Model model) {
        int count = testService.deleteTest(form.getId());
        Logger.getLogger(TestController.class).log(Level.INFO, "削除件数は" + count + "件です。");
        return "redirect:/test/";
    }

    //update
    @RequestMapping(value = "/test/update/input/{id}/", method = RequestMethod.GET)
    public String testUpdate(Model model, @PathVariable int id) {
        TestDto test = testService.getTest(id);
        model.addAttribute("message", "MyBatisのUpdateサンプルです");
        model.addAttribute("test", test);
        TestForm form = new TestForm();
        form.setId(test.getId());
        form.setName(test.getName());
        model.addAttribute("testForm", form);
        return "testUpdate";
    }

    @RequestMapping(value = "/test/update/input/{id}/", method = RequestMethod.POST)
    public String testUpdate(Model model, @ModelAttribute TestForm form) {
        TestDto dto = new TestDto();
        BeanUtils.copyProperties(form, dto);
        int count = testService.updateTest(dto);
        Logger.getLogger(TestController.class).log(Level.INFO, "更新件数は" + count + "件です。");
        return "redirect:/test/";
    }
}