package com.model2.mvc.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vote/*")
public class voteController {

	
	@RequestMapping(value="vote")
	public String vote(@RequestParam("result")String result,HttpServletRequest req){
		System.out.println(result);
		Map<String,Integer> inputMap=new HashMap<String, Integer>();
	
		String[] l1=result.split("/");
		for(String s: l1) {
			String[] ss=s.split(":");
			inputMap.put(ss[0], Integer.parseInt(ss[1]) );
		}
		
		for(Map.Entry<String, Integer> elem: inputMap.entrySet()) {
			System.out.println(elem.getKey()+" : "+elem.getValue());
		}
		
		Map<String,Integer> map=(Map<String,Integer>)req.getServletContext().getAttribute("map");
		
		if(map==null) {
			req.getServletContext().setAttribute("map", inputMap);
		}
		else {
			for(Map.Entry<String, Integer> elem :inputMap.entrySet()  ) {
				map.put(elem.getKey(), map.get(elem.getKey()).intValue()+elem.getValue().intValue());
			}
			req.getServletContext().setAttribute("map", map);
		
			System.out.println("현재 상태");
			for(Map.Entry<String, Integer> elem: map.entrySet()) {
				System.out.println(elem.getKey()+" : "+elem.getValue());
			}
		}
		
		return "/index.jsp";
	}
	
	@RequestMapping(value="voteResult")
	public String voteResult(HttpServletRequest req,Model model) {
		Map<String,Integer> map=(Map<String,Integer>)req.getServletContext().getAttribute("map");

		class Vote{
			int i;
			String s;
			Vote(String s,int i){
				this.i=i;
				this.s=s;
			}
		}
		List<Vote> list=new ArrayList<Vote>();
		
		for(Map.Entry<String, Integer> elem :map.entrySet()  ) {
			list.add(new Vote(elem.getKey(),elem.getValue()));
		}
		for(Vote v:list) {
			System.out.println(v.s+ v.i);
		}
		///////sort//////
		for(int i=0; i<list.size();i++) {
			int index=i;
			
			for(int j=i+1;j<list.size();j++) {
				if(list.get(index).i<list.get(j).i) {
					index=j;
				}
			}
			Vote temp=list.get(index);
			list.set(index, list.get(i));
			list.set(i, temp);
		}
		System.out.println("=====after sort======");
		for(Vote v:list) {
			System.out.println(v.s+" : "+ v.i);
		}
		////////////////////////////////////////////////////
		List<List<String>> groupList=new ArrayList<List<String>>();
		
		for(int i=0;i<5;i++) {
			List<String> subList=new ArrayList<String>();
			for(int j=0;j<3;j++) {
				subList.add(list.get(i*3+j).s);
				System.out.print((i*3+j)+",");
			}
			System.out.println();
			groupList.add(subList);
		}
		
		////////////////////////////////////////////
		Random rand=new Random();
		List<List<String>> resultList=new ArrayList<List<String>>();
		for(int i=0;i<3;i++) {
			List<String> subList=new ArrayList<String>();
			for(int j=0;j<5;j++) {
				List<String> tempList=groupList.get(j);
				int pick=rand.nextInt(tempList.size());
				subList.add(tempList.get(pick));
				tempList.remove(pick);
			}
			resultList.add(subList);
		}
		////////////////semifinal//////////////
		for(int i=0;i<resultList.size();i++) {
			List<String> li=resultList.get(i);
			for(int j=0;j<li.size();j++) {
				System.out.print(li.get(j)+",");
			}
			System.out.println();
				
		}
		/////////////////final shake////////////
		for(int i=0;i<resultList.size();i++) {
			List<String> li=resultList.get(i);
			for(int j=0;j<50;j++) {
				int num1=rand.nextInt(li.size());
				int num2=rand.nextInt(li.size());
				String temp=li.get(num1);
				li.set(num1, li.get(num2));
				li.set(num2, temp);
				
			}	
		}
		///////////////////final print///////////////////
		System.out.println("========final==============");
		for(int i=0;i<resultList.size();i++) {
			List<String> li=resultList.get(i);
			for(int j=0;j<li.size();j++) {
				System.out.print(li.get(j)+",");
			}
			System.out.println();
				
		}
		model.addAttribute("resultList", resultList);
		return "/vote/result.jsp";
	}
	
	
}
