package com.example.common.service.Impl;


import com.example.common.entity.SysPermission;
import com.example.common.entity.User;
import com.example.common.service.PermService;
import com.example.common.service.ShiroService;
import com.example.common.service.SystemService;
import com.example.common.service.UserService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private PermService permService;

    @Autowired
    private UserService userService;
    /**
     * 初始化权限
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        List<SysPermission> authorities = permService.seePerm();
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        if (authorities.size() > 0) {
            String uris;
            String[] uriArr;
            for (SysPermission authority : authorities) {
                if (StringUtils.isEmpty(authority.getPermission())) {
                    continue;
                }
                uris = authority.getUrl();
                uriArr = uris.split(",");
                for (String uri : uriArr) {
                    filterChainDefinitionMap.put(uri, authority.getPermission());
                }
            }
        }
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/fore/**","anon");
        filterChainDefinitionMap.put("/lib/**","anon");

        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/fontimg/**","anon");
        filterChainDefinitionMap.put("/fontjs/**","anon");
        filterChainDefinitionMap.put("/fontcss/**","anon");
        filterChainDefinitionMap.put("/fontscss/**","anon");
        filterChainDefinitionMap.put("/resigter","anon");

        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/login", "anon");
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * 在对角色进行增删改操作时，需要调用此方法进行动态刷新
     * @param shiroFilterFactoryBean
     */
    public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Integer roleId, Boolean isRemoveSession) {
        synchronized (this) {
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
    }
}
