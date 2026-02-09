package org.doit.ik.dept;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@RequiredArgsConstructor
public class DeptController {
   
   private final DeptService deptService;
   
   // [1] 페이징 처리 X 부서 목록 조회
   /*
   @GetMapping("/dept/list")
   public ModelAndView deptList() {
      log.info("😘😘 DeptController.deptList()...");
      ModelAndView mv = new ModelAndView();
      
      List<Dept> list = this.deptService.getDepts();
      mv.addObject("list", list);
      mv.setViewName("dept/list"); // templates/dept/list.html
      
      return mv;
   }
   */
   // [2] 페이징처리 O + 부서목록 조회
   @GetMapping("/dept/list")
   public ModelAndView deptList(@RequestParam(name="pageNum",defaultValue="1") int pageNum
		   						,@RequestParam(name="pageSize",defaultValue="2") int pageSize) {
      log.info("😘😘 DeptController.deptList()...");
      ModelAndView mv = new ModelAndView();
      
      Page<Dept> deptPage = this.deptService.getDeptsByPage(pageNum,pageSize);
      List<Dept> list=deptPage.getContent();
      mv.addObject("list", list);
      mv.addObject("deptPage", deptPage);
      mv.setViewName("dept/list"); // templates/dept/list.html
      
      return mv;
   }
   
   // 
   @GetMapping("/dept/insert")
   public ModelAndView deptInsert(Dept dept) {   // 중요!! - Dept 엔티티 클래스
      log.info("😘😘 DeptController.deptInsert()...");
      ModelAndView mv= new ModelAndView();
      mv.setViewName("redirect:/dept/list"); // 리다이렉트 
      Dept saveDept = this.deptService.saveDept(dept);
      mv.addObject("saveDept", saveDept);
      return mv;
   }
   
   // (deptno=1, dname='XXX', loc='YYY')
   @GetMapping("/dept/update")
   public ModelAndView deptUpdate(Dept dept) {
      log.info("😘😘 DeptController.deptUpdate()...");      
      ModelAndView mv= new ModelAndView();
      mv.setViewName("redirect:/dept/list"); // 리다이렉트 
      this.deptService.updateDept(dept); 
      return mv;
   }
   
   @GetMapping("/dept/delete")
   public ModelAndView deptDelete(@RequestParam("deptno")Integer deptno) {
      log.info("😘😘 DeptController.deptDelete()...");      
      ModelAndView mv= new ModelAndView();
      mv.setViewName("redirect:/dept/list"); // 리다이렉트 
      this.deptService.deleteDept(deptno); 
      return mv;
   }
   

}






