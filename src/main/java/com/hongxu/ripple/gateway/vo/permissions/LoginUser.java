package com.hongxu.ripple.gateway.vo.permissions;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import com.hongxu.ripplermb.domain.core.po.PerResources;
import com.hongxu.ripplermb.domain.core.po.PerUsers;
import com.hongxu.ripplermb.domain.core.po.PerUsersRoles;

/**
* PerUsers
* table:per_users
* 
* @author 王欣
* @version v1.0
* @copy 鸿旭图码
* @date 2013-05-06 11:53:19
*/
public class LoginUser extends PerUsers implements CustomUserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
	//该用户所负责的子系统
	private String subSystem;
  	
  	//一个用户具有多个角色。
  	private Set<PerUsersRoles> perUsersRoles = new HashSet<PerUsersRoles>(0);
  	
  	//用户可以访问的资源列表
  	//HashMap<String, List<PerResources>> res;
  	
  	HashMap<String, PerResources> res;
  	
  	
  	//实现了UserDetails之后的相关变量
      private  String password;
      private  String username;
      private  Set<GrantedAuthority> authorities;
      //private List<String> authoritiesDesc;
      private  boolean accountNonExpired;
      private  boolean accountNonLocked;
      private  boolean credentialsNonExpired;

      public LoginUser(
    		  String userId, 
    		  String userAccount, 
    		  String userName,
    		  String userPassword,
    		  String userDesc, 
    		  boolean enabled,
    		  boolean issys, 
    		  String userDuty, 
    		  String userDept, 
    		  String subSystem, 
    		  Set<PerUsersRoles> perUsersRoles,
    		  boolean accountNonExpired,
              boolean credentialsNonExpired, 
              boolean accountNonLocked, 
              Collection<GrantedAuthority> authorities,
              //List<String> authoritiesDesc,
              //HashMap<String, List<PerResources>> res,
              HashMap<String, PerResources> res
              ) {

          if (((userAccount == null) || "".equals(userAccount)) || (userPassword == null)) {
              throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
          }
          setUserId(userId);
          setUserAccount(userAccount);
          setUserName(userName);
          setUserPassword(userPassword);
          setUserDesc(userDesc);
          setIssys(issys);
          setUserDuty(userDuty);
          setUserDept(userDept);
          this.subSystem = subSystem;
          this.perUsersRoles = perUsersRoles;
          this.username = userAccount;
          this.password = userPassword;
          setEnabled(enabled);
          this.accountNonExpired = accountNonExpired;
          this.credentialsNonExpired = credentialsNonExpired;
          this.accountNonLocked = accountNonLocked;
          this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
          //this.authoritiesDesc = authoritiesDesc;
          //this.res = res;
          this.res = res;
      }

    public HashMap<String, PerResources> getRes() {
		return res;
	}

	public void setRes(HashMap<String, PerResources> res) {
		this.res = res;
	}

	public LoginUser() {
		// TODO Auto-generated constructor stub
	}

    public String getSubSystem() {
		return this.subSystem;
	}

	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
	}
	
	public Set<PerUsersRoles> getPerUsersRoles() {
		return this.perUsersRoles;
	}

	public void setPerUsersRoleses(Set<PerUsersRoles> perUsersRoles) {
		this.perUsersRoles = perUsersRoles;
	}
	
	 public Collection<GrantedAuthority> getAuthorities() {
	        return authorities;
	    }
	    
	    public void setAuthorities( Collection<GrantedAuthority> authorities ){
	    	this.authorities = (Set<GrantedAuthority>) authorities;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getUsername() {
	        return username;
	    }
	    

	    public boolean isAccountNonExpired() {
	        return accountNonExpired;
	    }

	    public boolean isAccountNonLocked() {
	        return this.accountNonLocked;
	    }

	    public boolean isCredentialsNonExpired() {
	        return credentialsNonExpired;
	    }


	    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<GrantedAuthority> authorities) {
	        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
	        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
	        SortedSet<GrantedAuthority> sortedAuthorities =
	            new TreeSet<GrantedAuthority>(new AuthorityComparator());

	        for (GrantedAuthority grantedAuthority : authorities) {
	            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
	            sortedAuthorities.add(grantedAuthority);
	        }

	        return sortedAuthorities;
	    }

	    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public int compare(GrantedAuthority g1, GrantedAuthority g2) {
	            // Neither should ever be null as each entry is checked before adding it to the set.
	            // If the authority is null, it is a custom authority and should precede others.
	            if (g2.getAuthority() == null) {
	                return -1;
	            }

	            if (g1.getAuthority() == null) {
	                return 1;
	            }

	            return g1.getAuthority().compareTo(g2.getAuthority());
	        }
	    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LoginUser other = (LoginUser) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserAccount() == null ? other.getUserAccount() == null : this.getUserAccount().equals(other.getUserAccount()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserDesc() == null ? other.getUserDesc() == null : this.getUserDesc().equals(other.getUserDesc()))
            && (this.getUserDept() == null ? other.getUserDept() == null : this.getUserDept().equals(other.getUserDept()))
            && (this.getUserDuty() == null ? other.getUserDuty() == null : this.getUserDuty().equals(other.getUserDuty()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
            && (this.getIssys() == null ? other.getIssys() == null : this.getIssys().equals(other.getIssys()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserAccount() == null) ? 0 : getUserAccount().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserDesc() == null) ? 0 : getUserDesc().hashCode());
        result = prime * result + ((getUserDept() == null) ? 0 : getUserDept().hashCode());
        result = prime * result + ((getUserDuty() == null) ? 0 : getUserDuty().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        result = prime * result + ((getIssys() == null) ? 0 : getIssys().hashCode());
        return result;
    }

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
//    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("UserAccount: ").append(getUserAccount()).append("; ");
        sb.append("UserDept: ").append(getUserDept()).append("; ");
        sb.append("UserDuty: ").append(getUserDuty()).append("; ");
        sb.append("UserDesc: ").append(getUserDesc()).append("; ");
        sb.append("UserSubSystem: ").append(this.subSystem).append("; ");
        sb.append("UserIsSys: ").append(getIssys()).append("; ");
        sb.append("Enabled: ").append(isEnabled()).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

        if ( null !=authorities  && !authorities.isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : authorities) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

//	public HashMap<String, List<PerResources>> getRes() {
//		return res;
//	}
//
//	public void setRes(HashMap<String, List<PerResources>> res) {
//		this.res = res;
//	}

//	public List<String> getAuthoritiesDesc() {
//		return authoritiesDesc;
//	}
//
//	public void setAuthoritiesDesc(List<String> authoritiesDesc) {
//		this.authoritiesDesc = authoritiesDesc;
//	}

	@Override
	public boolean isEnabled() {
		return getEnabled();
	}
    
    
}