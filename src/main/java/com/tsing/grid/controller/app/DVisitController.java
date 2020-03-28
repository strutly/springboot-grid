package com.tsing.grid.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author lijing
 *
 */
@Controller
@RequestMapping(value = "/manager/apps/harness/dVisit")
public class DVisitController{
	
	/*@Autowired
	private DVisitService dVisitService;
	
	@Autowired
	private DrugerService drugerService;
	
	 //回访记录列表
    @RequestMapping(value = "/page/{did}")
    public String list(@PathVariable String did,@RequestParam(value = "page", defaultValue = "1") int page,
            Model model,ServletRequest request) {
    	Admin admin = getUser();
    	
    	Druger druger = drugerService.get(did);
		if(druger==null) {
			return "auth";
		}
		//网格鉴权
		if(admin.getRoleId().equals(Constants.GRID_ADMIN)) {
			if(!druger.getGrid().getId().equals(admin.getUnionId())) {
				return "auth";
			}
		}
		//社区鉴权
		if(admin.getRoleId().equals(Constants.COMMUNITY_ADMIN)) {
			if(!druger.getGrid().getCommunity().getId().equals(admin.getUnionId())) {
				return "auth";
			}
		}
        // 分页查询item
        Page<DVisit> dVisits = new Page<DVisit>();
        dVisits.setPageSize(10);
        dVisits.setPageNo(page);
        dVisits.setOrderBy("cdt");
        dVisits.setOrder("desc");
        Map<String, Object> searchParams = Maps.newHashMap();
        searchParams.put("did_EQ", did);
        searchParams.put("state_EQ", Constants.INITIAL);
        dVisits = dVisitService.searchPage(dVisits, PropertyFilter.parse(searchParams));
        model.addAttribute("page", dVisits);
        model.addAttribute("did", did);
        return "manager/apps/harness/dVisit/page";
    }
    
    @RequestMapping(value = "/{did}")
    @ResponseBody
    public ResponseEntity<Object> page(@PathVariable String did,@RequestParam(value = "page", defaultValue = "1") int page,
            Model model,ServletRequest request) {
        // 分页查询item
        Page<DVisit> dVisits = new Page<DVisit>();
        dVisits.setPageSize(5);
        dVisits.setPageNo(page);
        dVisits.setOrderBy("vTime");
        dVisits.setOrder("desc");
        Map<String, Object> searchParams = Maps.newHashMap();
        searchParams.put("did_EQ", did);
        dVisits = dVisitService.searchPage(dVisits, PropertyFilter.parse(searchParams));
        return new ResponseEntity<Object>(dVisits,HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/create/{did}", method = RequestMethod.GET)
    public String createForm(@PathVariable String did, Model model) {
    	Admin admin = getUser();
    	Druger druger = drugerService.get(did);
		if(druger==null) {
			return "auth";
		}
		//网格鉴权
		if(!admin.getRoleId().equals(Constants.GRID_ADMIN) && !admin.getRoleId().equals(Constants.COMMUNITY_ADMIN)) {
			return "auth";
		}
		if(admin.getRoleId().equals(Constants.GRID_ADMIN)){
			if(!druger.getGrid().getId().equals(admin.getUnionId())) {
				return "auth";
			}
		}
		if(admin.getRoleId().equals(Constants.COMMUNITY_ADMIN)){
			if(!druger.getGrid().getCommunity().getId().equals(admin.getUnionId())) {
				return "auth";
			}
		}
    	model.addAttribute("dVisit", new DVisit());
        model.addAttribute("did", did);
        model.addAttribute("action", "create");
        return "manager/apps/harness/dVisit/form";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> create(@Valid DVisit dVisit,ServletRequest request) {
        Map<String,Object> map = Maps.newHashMap();
    	try {
        	dVisitService.save(dVisit);
        	map.put("errorCode", 0);
        	map.put("errorMsg", "保存成功");
		} catch (Exception e) {
			map.put("errorCode", -1);
        	map.put("errorMsg", "保存失败，请稍后再试！");
		}
    	return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, Model model, 
            ServletRequest request) {
        DVisit dVisit = dVisitService.get(id);
        if(dVisit==null) {
			return "auth";
		}
        Admin admin = getUser();
    	
    	Druger druger = drugerService.get(dVisit.getDid());
		
    	if(druger==null) {
			return "auth";
		}
		//网格鉴权
		if(admin.getRoleId().equals(Constants.GRID_ADMIN)) {
			if(!druger.getGrid().getId().equals(admin.getUnionId())) {
				return "auth";
			}
		}
		//社区鉴权
		if(admin.getRoleId().equals(Constants.COMMUNITY_ADMIN)) {
			if(!druger.getGrid().getCommunity().getId().equals(admin.getUnionId())) {
				return "auth";
			}
		}
        
        model.addAttribute("dVisit", dVisit);
        model.addAttribute("did", dVisit.getDid());
        model.addAttribute("action", "update");
        model.addAttribute("page", request.getParameter("page"));
        return "manager/apps/harness/dVisit/form";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> update(@Valid @ModelAttribute("dVisit") DVisit dVisit,ServletRequest request) {
    	Map<String,Object> map = Maps.newHashMap();
    	try {
        	dVisitService.update(dVisit);
        	map.put("errorCode", 0);
        	map.put("errorMsg", "更新成功");
		} catch (Exception e) {
			map.put("errorCode", -1);
        	map.put("errorMsg", "更新失败，请稍后再试！");
		}
    	return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
    
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id,ServletRequest request) {
    	Map<String,Object> map = Maps.newHashMap();
    	DVisit dVisit = dVisitService.get(id);
        if(dVisit==null) {
        	map.put("errorCode", -1);
    		map.put("errorMsg", "内容不存在");
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}
        Admin admin = getUser();
    	
    	Druger druger = drugerService.get(dVisit.getDid());
		
    	if(druger==null) {
    		map.put("errorCode", -1);
    		map.put("errorMsg", "内容不存在");
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}
		//网格鉴权
		if(admin.getRoleId().equals(Constants.GRID_ADMIN)) {
			if(!druger.getGrid().getId().equals(admin.getUnionId())) {
				map.put("errorCode", -1);
				map.put("errorMsg", "您没有权限进行此操作");
				return new ResponseEntity<Object>(map,HttpStatus.OK);
			}
		}
		//社区鉴权
		if(admin.getRoleId().equals(Constants.COMMUNITY_ADMIN)) {
			if(!druger.getGrid().getCommunity().getId().equals(admin.getUnionId())) {
				map.put("errorCode", -1);
				map.put("errorMsg", "您没有权限进行此操作");
				return new ResponseEntity<Object>(map,HttpStatus.OK);
			}
		}
    	dVisitService.delete(id);
    	map.put("errorCode", 0);
		map.put("errorMsg", "操作成功");
    	return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
    
    @RequestMapping(value = "detail/{id}")
    @ResponseBody
    public ResponseEntity<Object> detail(@PathVariable("id") String id,ServletRequest request) {
    	Map<String,Object> map = Maps.newHashMap();
    	DVisit dVisit = dVisitService.get(id);
        if(dVisit==null) {
        	map.put("errorCode", -1);
    		map.put("errorMsg", "内容不存在");
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}
        Admin admin = getUser();
    	
    	Druger druger = drugerService.get(dVisit.getDid());
		
    	if(druger==null) {
    		map.put("errorCode", -1);
    		map.put("errorMsg", "内容不存在");
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}
		//网格鉴权
		if(admin.getRoleId().equals(Constants.GRID_ADMIN)) {
			if(!druger.getGrid().getId().equals(admin.getUnionId())) {
				map.put("errorCode", -1);
				map.put("errorMsg", "您没有权限进行此操作");
				return new ResponseEntity<Object>(map,HttpStatus.OK);
			}
		}
		//社区鉴权
		if(admin.getRoleId().equals(Constants.COMMUNITY_ADMIN)) {
			if(!druger.getGrid().getCommunity().getId().equals(admin.getUnionId())) {
				map.put("errorCode", -1);
				map.put("errorMsg", "您没有权限进行此操作");
				return new ResponseEntity<Object>(map,HttpStatus.OK);
			}
		}
		map.put("errorCode", 0);
		map.put("errorMsg", "操作成功");
		map.put("data", dVisit);
    	return new ResponseEntity<Object>(dVisitService.get(id),HttpStatus.OK);
    }
    
    @ModelAttribute
    public void getDVisit(@RequestParam(value = "id", required = false) String id, Model model) {
        if(StringUtils.isNotBlank(id)){
            model.addAttribute("dVisit", dVisitService.get(id));
        }
    }*/
}
