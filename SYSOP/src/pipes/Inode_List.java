package pipes;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class Inode_List {
	
	BufferedReader odczytywanie = null;
	
	List<Pipe_Inode> InodeList;

	public Inode_List()
	{
		InodeList = new LinkedList<Pipe_Inode>();
	}
	
	void Add(Pipe_Inode inode)
	{
		InodeList.add( inode );
	}
}
