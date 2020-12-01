package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    public String hello(Model model){ //Controller의 메서드는 Model이라는 타입의 객체를 파라미터로 받을 수 있다.
        model.addAttribute("data" ,"hello!!!"); //name:data value:hello!!!
        return "hello";  //resources/templates 의 view name -> hello.html이 실행됨(스프링부트가 함)
    }

}
