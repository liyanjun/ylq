package com.yunquanlai.admin.system.service.impl;

import com.yunquanlai.admin.system.dao.SysMenuDao;
import com.yunquanlai.utils.Constant;
import com.yunquanlai.admin.system.entity.SysMenuEntity;
import com.yunquanlai.admin.system.service.SysMenuService;
import com.yunquanlai.admin.system.service.SysRoleMenuService;
import com.yunquanlai.admin.system.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private CacheManager cacheManager;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
       Cache.ValueWrapper temp = cacheManager.getCache("menu").get("parentId:" + parentId);
        List<SysMenuEntity> menuList = null;
        if(temp != null && temp.get() != null){
            menuList = (List<SysMenuEntity>) temp.get();
            return menuList;
        }
        if (menuIdList == null) {
            menuList = sysMenuDao.queryListParentId(parentId);
            cacheManager.getCache("menu").putIfAbsent("parentId:" + parentId, menuList);
        }

        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        Cache.ValueWrapper temp = cacheManager.getCache("menu").get("queryNotButtonList");
        List<SysMenuEntity> list;
        if(temp != null && temp.get() != null){
            list = (List<SysMenuEntity>) temp.get();
            return list;
        }
        list = sysMenuDao.queryNotButtonList();
        cacheManager.getCache("menu").putIfAbsent("queryNotButtonList", list);
        return list;
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        List<SysMenuEntity> list;
        Cache.ValueWrapper temp = cacheManager.getCache("menu").get("userId:" + userId);
        if(temp != null && temp.get() != null){
            list = (List<SysMenuEntity>) temp.get();
            return list;
        }
        //系统管理员，拥有最高权限
        if (userId == 1) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        list = getAllMenuList(menuIdList);
        cacheManager.getCache("menu").putIfAbsent("userId:" + userId, list);
        return getAllMenuList(menuIdList);
    }

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == 1) {
            List<SysMenuEntity> menuList = queryList(new HashMap<>());
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserService.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<String>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return sysMenuDao.queryObject(menuId, false);
    }

    @Override
    public List<SysMenuEntity> queryList(Map<String, Object> map) {
        return sysMenuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysMenuDao.queryTotal(map);
    }

    @Override
    public void save(SysMenuEntity menu) {
        cacheManager.getCache("menu").clear();
        sysMenuDao.save(menu);
    }

    @Override
    public void update(SysMenuEntity menu) {
        cacheManager.getCache("menu").clear();
        sysMenuDao.update(menu);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] menuIds) {
        cacheManager.getCache("menu").clear();
        sysMenuDao.deleteBatch(menuIds);
    }

    @Override
    public List<SysMenuEntity> queryUserList(Long userId) {
        return sysMenuDao.queryUserList(userId);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for (SysMenuEntity entity : menuList) {
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {//目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
