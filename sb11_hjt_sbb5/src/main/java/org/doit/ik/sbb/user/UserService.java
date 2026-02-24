package org.doit.ik.sbb.user;

import java.util.Optional;

import org.doit.ik.sbb.exception.DataNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	//[1] 회원가입 : create
	public SiteUser create(
			String username,
			String email,
			String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		
		/* [1]
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(cryptPasswordEncoder.encode(password));
		*/
		
		user.setPassword(this.passwordEncoder.encode(password));
		
		this.userRepository.save(user);
		return user;
	}
	
	public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
	
}
