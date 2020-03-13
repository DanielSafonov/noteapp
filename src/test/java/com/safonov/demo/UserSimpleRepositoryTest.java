package com.safonov.demo;

import com.safonov.demo.application.model.entity.User;
import com.safonov.demo.application.model.repository.UserSimpleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Set;

class UserSimpleRepositoryTest extends GenericTest {
	@Autowired
	private UserSimpleRepository userSimpleRepository;

	@Test
	void save() {
		User user = userSimpleRepository.save(
			new User().setUsername("user72").setPassword("password")
		);
		System.out.println(user.toString());
		assert(user.getId() != null);
	}

	@Test
	void getCount() {
		Long count = userSimpleRepository.getCount();
		System.out.println(count);
		assert(count != null);
	}

	@Test
	void findById() {
		User user = userSimpleRepository.findById(8L);
		System.out.println(user.toString());
		assert(user != null);
	}

	@Test
	void delete() {
		Long beforeCount = userSimpleRepository.getCount();
		userSimpleRepository.delete(
				userSimpleRepository.findById(7L)
		);
		assert(userSimpleRepository.getCount() == beforeCount - 1);
	}

	@Test
	void findAll(){
		Long count = userSimpleRepository.getCount();
		Set<User> users;
		users = userSimpleRepository.findAll();
		System.out.println(Arrays.toString(users.toArray()));
		assert(users.size() == count);
	}
}
