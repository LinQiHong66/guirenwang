package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CreateInesvUserCommand;
import com.inesv.digiccy.event.CreateInesvUserEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class InesvUserAggregate extends AbstractAnnotatedAggregateRoot {

	private static final long serialVersionUID = -414961613569229401L;
	
	@AggregateIdentifier
    private Long id;

    public InesvUserAggregate(){}

    @CommandHandler
    public InesvUserAggregate(CreateInesvUserCommand createInesvUserCommand){
    	apply(new CreateInesvUserEvent(
    	        createInesvUserCommand.getId(),
                createInesvUserCommand.getPassword(),
                createInesvUserCommand.getPhone(),
                createInesvUserCommand.getPhone_state(),
                createInesvUserCommand.getDeal_pwd(),
                createInesvUserCommand.getDeal_pwdstate(),
                createInesvUserCommand.getValidate_pwd(),
                createInesvUserCommand.getValidate_pwdstate(),
                createInesvUserCommand.getAlipay(),
                createInesvUserCommand.getAlipay_state(),
                createInesvUserCommand.getPhoto(),
                createInesvUserCommand.getPhoto_state(),
                createInesvUserCommand.getOperation(),
                createInesvUserCommand.getUser_no()
              ));
    }

    @EventHandler
    public void on(CreateInesvUserEvent createInesvUserEvent){
        id = createInesvUserEvent.getId();
    }

}
