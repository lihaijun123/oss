package org.springside.components.acegi.service;

import java.util.List;

import org.acegisecurity.userdetails.User;
import org.springside.components.acegi.resource.Resource;

/**
 * 為緩存提供 User 和 Resource 實例
 *
 * @author cac
 */
public interface AuthenticationService {

    public List<User> getUsers();

    public List<Resource> getResources();
}
