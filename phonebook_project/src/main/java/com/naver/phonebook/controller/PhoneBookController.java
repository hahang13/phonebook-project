package com.naver.phonebook.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.phonebook.dao.PhoneBookDao;
import com.naver.phonebook.pdto.PhoneBookPDto;
import com.naver.phonebook.sdto.PhoneBookSDto;
import com.naver.phonebook.service.phonebookservice;

@Controller
@RequestMapping("/phonebooks")
public class PhoneBookController {
	PhoneBookDao phonebookDao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public PhoneBookController(PhoneBookDao phonedao) {
		this.phonebookDao = phonedao;
	}
	
	@GetMapping("/main")
	public String main() {
		return "phoneBookView";
	}
	// 로그인 select -> get method
	   @PostMapping("/login")
	   public String chkecklogin(@RequestParam(value = "email", required = false) String id
	      ,@RequestParam(value = "password", required = false) String pw
	      , HttpServletRequest request) {
	      phonebookservice service = new phonebookservice();
	      int check;
	       try {
	         check = service.equalcheck(id, pw);
	         
	         if(check == 1) {
	         HttpSession session = request.getSession();
	         session.setAttribute("id", id);
	         
	            
	         }else {
	            return "redirect:/phonebooks/main";
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return "redirect:/phonebooks/list";
	   }

// 가입 insert -> post method 
//	PhonebooSkDto
	@PostMapping("/addjoin")
	public String addjoin(@ModelAttribute PhoneBookSDto phoneBookSDto ,Model model
							) {
		
		try {
			phonebookDao.addjoin(phoneBookSDto);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("가입정보 추가 문제 발생하였습니다");
			model.addAttribute("error", "회원가입이 정상적으로 진행되지 않았습니다.");
		}
		
		return "redirect:/phonebooks/main";
	}
	
//	아아디 중복 검사
	@ResponseBody
	@RequestMapping(value="/checkid",method=RequestMethod.POST)
	public int checkid(@RequestParam("email") String id) {
		
		System.out.print(id);
		
		int check = 0;
		ArrayList<PhoneBookSDto> checklist;
		try {
			checklist = phonebookDao.findid(id);
			if (checklist.size() > 0) {
				check = 1;
			}else {
				check = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return check;
	}
	
	
	
	
//	리스트 조회
	@GetMapping("/list")
	public String listMember(Model model) {
		List<PhoneBookPDto> list;
	try{
		list = phonebookDao.getAlls();
		model.addAttribute("memlist", list);
	} catch (Exception e) {
		e.printStackTrace();
		logger.warn("회원 정보 추가중 문제 발생");
		model.addAttribute("error", "회원 목록이 정상적으로 추가되지 않음");
	}
	return "PhoneBooklist";
	}
	
	
//	리스트 추가
	@PostMapping("/addmem")
	public String addmem (@ModelAttribute PhoneBookPDto pdto, Model model) {
		try {
			phonebookDao.addmem(pdto);
			
			System.out.println(pdto + "controller");
//			HttpSession session = req.getSession();
//			session.getAttribute("id", id);
		}
		catch (Exception e) {
		logger.warn("추가 과정에서 문제 발생");
		model.addAttribute("error","뉴스 정상 추가 실패");
	}
		return "redirect:/phonebooks/list";
}
//					--------------------------삭제--------------------------
	@PostMapping("/delmem/{phoneNumber}")
	public String delmem(@PathVariable String phoneNumber, Model model) {
		try {
			phonebookDao.delmem(phoneNumber);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("삭제 과정에서 문제 발생!");
			model.addAttribute("error","정보가 정상적으로 삭제되지 않음.");
		}
		return "redirect:/phonebooks/list";
	}
//	-------------------------------수정----------------------------
	@PostMapping("/modify")
	public String modmem (@ModelAttribute PhoneBookPDto pdto, @RequestParam(value="phoneNumber") String phoneNumber, Model model) {
		if (pdto.getGubunnum().equals("10")) {
			pdto.setGubun("가족");
		} else if (pdto.getGubunnum().equals("20")) {
			pdto.setGubun("친구");
		} else if (pdto.getGubunnum().equals("30")) {
			pdto.setGubun("기타");
		} 
		try {
			phonebookDao.modmem(pdto);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("수정 과정에서 문제");
			model.addAttribute("error"," 수정 실패");
		}
		return "redirect:/phonebooks/list";
		
	}
	
	
	
	
	


//로그아웃 
@GetMapping("/removesession")
public String logout(HttpServletRequest request) {
   HttpSession session = request.getSession();
   session.removeAttribute("id");
   return "redirect:/phonebooks/main";
}

}




