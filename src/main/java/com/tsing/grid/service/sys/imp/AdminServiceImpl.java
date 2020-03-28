package com.tsing.grid.service.sys.imp;

import com.github.pagehelper.PageHelper;
import com.tsing.grid.entity.sys.Admin;
import com.tsing.grid.entity.sys.Role;
import com.tsing.grid.exception.BusinessException;
import com.tsing.grid.exception.code.BaseExceptionType;
import com.tsing.grid.mapper.AdminMapper;
import com.tsing.grid.service.sys.AdminRoleService;
import com.tsing.grid.service.sys.AdminService;
import com.tsing.grid.service.sys.RoleService;
import com.tsing.grid.util.PageUtils;
import com.tsing.grid.vo.req.*;
import com.tsing.grid.vo.resp.AdminRoleRespVO;
import com.tsing.grid.vo.resp.PageRespVO;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl  implements AdminService {

	public static final String HASH_ALGORITHM = "SHA-256";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private RoleService roleService;
	@Autowired
	private AdminRoleService adminRoleService;


	public static String generateSalt() {
		int byteLen = SALT_SIZE >> 1;
		SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
		return secureRandom.nextBytes(byteLen).toHex();
	}

	/**
	 * 获取加密后的密码，需要指定 hash迭代的次数
	 * @param password       需要加密的密码
	 * @param salt           盐
	 * @return 加密后的密码
	 */
	public static String encryptPassword(String password, String salt) {
		SimpleHash hash = new SimpleHash(HASH_ALGORITHM, password, salt, HASH_INTERATIONS);
		return hash.toString();
	}

	@Override
	public void insert(AdminAddReqVO vo) {
		Admin admin = findByLoginName(vo.getLoginName());
		if(admin!=null){
			throw new BusinessException(BaseExceptionType.USER_ERROR,"该登录账号已存在,请修改后再保存");
		}
		admin = new Admin();
		String salt = generateSalt();
		String password = encryptPassword(vo.getPassword(),salt);
		admin.setSalt(salt);
		admin.setName(vo.getName());
		admin.setLoginName(vo.getLoginName());
		admin.setPassword(password);
		admin.setStatus(vo.getStatus());
		admin.setCreateTime(new Date());
		admin.setUpdateTime(new Date());
		adminMapper.insert(admin);
	}

	@Override
	public void update(AdminUpdateReqVO vo) {
		Admin admin = findByLoginName(vo.getLoginName());
		//不是本身这个账号
		if(admin!=null && !admin.getId().equals(vo.getId())){
			throw new BusinessException(BaseExceptionType.USER_ERROR,"该登录账号已存在,请修改后再保存");
		}
		admin = adminMapper.selectByPrimaryKey(vo.getId());
		String salt = generateSalt();
		String password = encryptPassword(vo.getPassword(),salt);
		admin.setSalt(salt);
		admin.setName(vo.getName());
		admin.setLoginName(vo.getLoginName());
		admin.setPassword(password);
		admin.setStatus(vo.getStatus());
		admin.setUpdateTime(new Date());
		adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void deleteAdmins(List<Integer> ids) {
		Example example = new Example(Admin.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("id", ids);
		adminMapper.deleteByExample(example);
	}

	/**
	 * 密码修改
	 * @param id
	 * @param vo
	 */
	@Override
	public void updatePwd(Integer id, AdminPwdReqVO vo) {
		Admin admin = adminMapper.selectByPrimaryKey(id);
		String salt = admin.getSalt();
		if(admin.getPassword().equals(encryptPassword(vo.getOldPwd(),salt))){
			String password = encryptPassword(vo.getNewPwd(),salt);
			admin.setPassword(password);
			if(!StringUtils.isEmpty(vo.getName())){
				admin.setName(vo.getName());
			}
			int count = adminMapper.updateByPrimaryKeySelective(admin);
			if (count!=1){
				throw new BusinessException(BaseExceptionType.SYSTEM_ERROR,"更新失败");
			}
			return;
		}
		throw new BusinessException(BaseExceptionType.USER_ERROR,"老密码不对");
	}

	@Override
	public List<Admin> findAll() {
		return adminMapper.selectAll();
	}

	@Override
	public PageRespVO<Admin> pageInfo(AdminPageReqVO vo) {
		PageHelper.startPage(vo.getPageNum(),vo.getPageSize());

		Example example = new Example(Admin.class);
		Example.Criteria criteria = example.createCriteria();

		if(!StringUtils.isEmpty(vo.getName())){
			criteria.andLike("name", "%"+vo.getName()+"%");
		}

		if(!StringUtils.isEmpty(vo.getLoginname())){
			criteria.andLike("loginName", "%"+vo.getLoginname()+"%");
		}

		if(!StringUtils.isEmpty(vo.getStatus())){
			criteria.andEqualTo("status", vo.getStatus());
		}

		List<Admin> list = adminMapper.selectByExample(example);

		return PageUtils.getPageVO(list);
	}

	@Override
	public AdminRoleRespVO getAdminRole(Integer aid) {
		List<Role> roles = roleService.selectAllRoles();
		List<Integer> rids = adminRoleService.getRidsByAid(aid);
		AdminRoleRespVO adminRoleRespVO = new AdminRoleRespVO();
		adminRoleRespVO.setAllRole(roles);
		adminRoleRespVO.setOwnRoles(rids);
		return adminRoleRespVO;
	}

	@Override
	public void setAdminRole(Integer aid, List<Integer> roleIds) {
		if(null!=roleIds){
			AdminRoleOperationReqVO reqVO = new AdminRoleOperationReqVO();
			reqVO.setAid(aid);
			reqVO.setRids(roleIds);
			adminRoleService.setAdminRole(reqVO);
		}
	}

	@Override
	public Admin findByLoginName(String loginName) {
		Example example = new Example(Admin.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("loginName", loginName);
		return adminMapper.selectOneByExample(example);
	}

}
