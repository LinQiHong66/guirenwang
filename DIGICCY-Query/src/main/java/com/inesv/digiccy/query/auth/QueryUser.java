package com.inesv.digiccy.query.auth;

import com.inesv.digiccy.dto.auth.AuthRoleDto;
import com.inesv.digiccy.dto.auth.ResourceDto;
import com.inesv.digiccy.dto.auth.UserDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QueryUser implements UserDetailsService {

    //private static Logger log = LoggerFactory.getLogger(QueryUserInfo.class);

    @Autowired
    private QueryRunner queryRunner;

//    public UserDetails loginByUsernamePassWord(String username,String password){
//        String sql = "select * from user where name = ? and password = ?";
//        queryResourceURL();
//        Object params[] = {username,password};
//        User user = null;
//        try {
//            UserDto userdto  =  (UserDto)queryRunner.query(sql,new BeanHandler<UserDto>(UserDto.class),params);
//            Set<GrantedAuthority> grantedAuths = getGrantedAuthorities(userdto);
//            //封装成spring security的user
//         return new User(userdto.getName(),userdto.getPassword(),true,true,true,true,grantedAuths);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }

    public List<AuthRoleDto> queryUserRole(Long userid){
      String sql = "select id,name,description from t_role where id IN (SELECT role_id FROM t_user_role WHERE user_id=?)";
      Object params[] = {userid};
      
      List<AuthRoleDto> role = null;
      try {
       role = (List<AuthRoleDto>) queryRunner.query(sql,new BeanListHandler(AuthRoleDto.class),params);
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return role;
    }
    
    /**
     * 根据用户获取该用户拥有的角色
     * @param user
     * @return
    */

    private Set<GrantedAuthority> getGrantedAuthorities(UserDto user) {
      Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();  
      List<AuthRoleDto> roles = queryUserRole(user.getId());
      if(roles != null) {
          for(AuthRoleDto role : roles) {
        	  System.out.println("=================用户："+user.getName()+"拥有"+role.getDescription());
              grantedAuthorities.add(new GrantedAuthorityImpl(role.getName()));
          }
      }
        return grantedAuthorities;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      String sql = "select * from t_user where name = ?";
      queryResourceURL();
      Object params[] = {s};
      User user = null;
      try {
          UserDto userdto  =  (UserDto) queryRunner.query(sql,new BeanHandler(UserDto.class),params);
          if(userdto != null){
            //获取用户对应的角色集合
            Set<GrantedAuthority> grantedAuths = getGrantedAuthorities(userdto);  
            //封装成spring security的user
            return new User(userdto.getName(),userdto.getPassword(),true,true,true,true,grantedAuths);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return user;
    }

    /**
     * 查询访问url
     */
    public HashMap<String, Object> queryResourceURL(){
      String sql = "select id,type,value from t_resource";
      
      HashMap<String, Object> resource = null;
      try {
        resource = (HashMap<String, Object>) queryRunner.query(sql,new MapHandler());
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return resource;
    }

    /**
     * 查询所有角色
     */
    public List<AuthRoleDto> queryRole(){
      String sql = "select id,name,description from t_role";
      
      List<AuthRoleDto> roleList = null;
      try {
         roleList = (List<AuthRoleDto>) queryRunner.query(sql,new BeanListHandler(AuthRoleDto.class));
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return roleList;
    }

    /**
     * 查询角色对应的url
     */
    public List<ResourceDto> queryRoleResource(Integer roleid){
      String sql = "select id,type,value from t_resource where id in (select resource_id from t_role_resource where role_id = ?)";
      Object params[] = {roleid};
      List<ResourceDto> roleList = null;
      try {
        roleList = (List<ResourceDto>) queryRunner.query(sql,new BeanListHandler(ResourceDto.class),params);
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return roleList;
    }


}
