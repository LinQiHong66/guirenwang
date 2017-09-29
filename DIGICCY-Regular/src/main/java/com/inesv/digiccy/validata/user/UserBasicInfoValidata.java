package com.inesv.digiccy.validata.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.UserBasicCommand;

@Component
public class UserBasicInfoValidata extends AbstractAnnotatedAggregateRoot {

	@Autowired
	private CommandGateway commandGateway;

	public void addUserInfo(int userNo, String nationality, String sex, String job, String birthday, String userName)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(birthday);
		UserBasicCommand command = new UserBasicCommand();
		command.setBirthday(date);
		command.setJob(job);
		command.setNationality(nationality);
		command.setSex(sex);
		command.setUserNo(userNo);
		command.setUserName(userName);
		command.setOpration("insert");
		commandGateway.send(command);
	}
}
