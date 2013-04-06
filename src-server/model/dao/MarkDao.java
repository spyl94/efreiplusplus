package model.dao;

import java.sql.Connection;
import java.util.Set;

import model.Mark;

public class MarkDao extends Dao<Mark> {

	public MarkDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Mark obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Mark obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Mark obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Mark find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Mark> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
