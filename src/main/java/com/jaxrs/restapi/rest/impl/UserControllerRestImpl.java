package com.jaxrs.restapi.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.jaxrs.restapi.rest.model.EmailType;
import com.jaxrs.restapi.rest.model.EntityLifeCycle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.jaxrs.restapi.rest.UserControllerRest;
import com.jaxrs.restapi.rest.model.ContactInfo;
import com.jaxrs.restapi.rest.model.Person;
import com.jaxrs.restapi.rest.model.impl.PersonImpl;

public class UserControllerRestImpl implements UserControllerRest {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	private List<ContactInfo> sample = new ArrayList<ContactInfo>();

	public UserControllerRestImpl() {
		ContactInfo p = new PersonImpl("John Doe", "john@doe.com", EmailType.BUSINESS, Person.STATUS_SINGLE,
				EntityLifeCycle.ACTIVE);
		sample.add(p);
		ContactInfo m = new PersonImpl("Mickey Mouse", "mickex@mouse.com", EmailType.PRIVATE, Person.STATUS_SINGLE,
				EntityLifeCycle.ACTIVE);
		sample.add(m);
	}

	@Override
	public List<Person> getUsers(@RequestParam(value = "name") String name) {
		List<Person> personList = new ArrayList<Person>();

		if (StringUtils.isEmpty(name)) {
			personList.addAll(sample);
		} else {
			for (Person person : sample) {
				if (person.getName().startsWith(name)) {
					personList.add(person);
				}
			}
		}

		return personList;
	}

	@Override
	public List<ContactInfo> getContactInfo(ContactsRequest request) {
		List<ContactInfo> personList = new ArrayList<ContactInfo>();

		if (StringUtils.isEmpty(request.name)) {
			personList.addAll(sample);
		} else {
			for (ContactInfo person : sample) {
				if (person.getName().startsWith(request.name)) {
					personList.add(person);
				}
			}
		}

		return personList;
	}

}
