package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template"; //viewResponser에 던짐.
    }

    @GetMapping("hello-string")
    @ResponseBody // http body부에 직접넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring"
    }

    @GetMapping("hello-api")  // API방식은 객체로 반환.
    @ResponseBody   //JSON을 기본으로 반환. -> HttpMessageConverter [JsonCoverter]/[StringConverter] 동작.
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체가 오면 default가 JSON방식으로 만들어서 응답하겠다.
    }

    static class Hello {
        private String name;

        // property 접근 방식 - GetterSetter 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
 }
