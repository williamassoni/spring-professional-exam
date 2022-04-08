package com.spring.professional.exam.jdbc.springprofessional.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

class MyLocalException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
}

class CheckedException extends Exception {
	private static final long serialVersionUID = 1L;
	
}

@Service
@AllArgsConstructor
class Local {
	private final CompanyRepository companyRepository;
	
//	@Transactional(noRollbackFor = {MyLocalException.class})
	//@Transactional(rollbackForClassName =   = {MyLocalException.class})
	//@Transactional(noRollbackForClassName = {"MyLocalException"} )
	@Transactional(rollbackFor = CheckedException.class)
	@SneakyThrows
	public void execute() {
		companyRepository.save(new Company(3L, "ok.."));
		
		if(1==1) {
			throw new MyLocalException();
//			throw new CheckedException();
		}
	}
}

@Service
@AllArgsConstructor
public class CompanyService {

	private final CompanyRepository companyRepository;
	private final TransactionTemplate template;
	private final PlatformTransactionManager manager;
	private final Local local;
	
	public void saveUsingTransactionTemplate() {
		template.execute(action -> {
			final Company com = companyRepository.save(new Company(1L, "check"));
			
			//action.setRollbackOnly();
			return com.getId(); 
		});
	}
	
	public void saveUsingPlatformTransactionTemplate() {
		final DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		definition.setTimeout(3);
		
		final TransactionStatus status = manager.getTransaction(definition);
		
		companyRepository.save(new Company(2L, "check"));
		manager.commit(status);
	}
	
	public void run() {
		//saveUsingTransactionTemplate();
		//saveUsingPlatformTransactionTemplate();
		
		try {
			local.execute();	
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		companyRepository.findAll();
	}
}