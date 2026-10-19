package com.softeck.test.respository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softeck.test.model.Client;

//@Repository
public class ClientRepository  {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	@Override
//	public List<Client> findAll() {
//		return jdbcTemplate.query("select id,first_name,last_name,surname,phone from clients", BeanPropertyRowMapper.newInstance(Client.class));
//	}
//
//	@Override
//	public save(Client client) {
//		return jdbcTemplate.update("insert into clients values (?, ?, ?, ?)", new Object[] {client.getFirstName(), client.getLastName(), client.getSurname(), client.getPhone()});
//	}
//
//	@Override
//	public int delete(Long id) {
//		return jdbcTemplate.update("delete from clients where id = ?", id);
//	}

}
