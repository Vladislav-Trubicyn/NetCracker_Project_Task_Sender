package com.dreamsearcher.sender;

import com.dreamsearcher.sender.model.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SenderApplicationTestsMockito
{
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void startSendNotification() throws Exception
	{
		List<Notification> notifications = new ArrayList<>();
		notifications.add(buildNotif());

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("http://localhost:8080/api/v1/sender").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(notifications)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String answer = result.getResponse().getContentAsString();
		Assertions.assertEquals("", answer);
	}

	private Notification buildNotif()
	{
		Notification notif = new Notification();
		notif.setNotificationText("Тут текст много текста");
		notif.setUsername("VladislavMockito");
		notif.setUserEmail("vlad.trubicyn@mail.ru");
		notif.setSend(false);
		return notif;
	}

}
