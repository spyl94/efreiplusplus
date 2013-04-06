package model.dao;

import java.sql.Connection;
import java.util.Set;

import model.Pair;
import model.Teacher;
import model.Course;

public class TeachesDao extends Dao<Pair<Teacher,Course>> {

	public TeachesDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Pair<Teacher, Course> obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Pair<Teacher, Course> obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pair<Teacher, Course> obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pair<Teacher, Course> find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Pair<Teacher,Course>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
