package com.inesv.digiccy.dto.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class UserDto{
    private long id;
    
    private String name;
    
    private String password;
    
    private String disabled;

    private Integer roleId;
    /**
     * 所拥有的权限
     * @return
     */
    private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    
    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getDisabled() {
      return disabled;
    }

    public void setDisabled(String disabled) {
      this.disabled = disabled;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
