package model;

import dao.MutterDAO;

public class AddMutterListLogic {

//	public List<Mutter> execute() {
//	    MutterDAO dao = new MutterDAO();
//	    
//	    List<Mutter> insertList = dao.add();
//	    return insertList;
//	  }
	public boolean execute() {
	    MutterDAO dao = new MutterDAO();
	    
	    boolean insertList = dao.create(null);
	    return insertList;
	  }
}
