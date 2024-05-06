package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.model.User;

import java.io.IOException;
import java.util.List;

public interface IRepository <T>  {
    public List<Object> getRepoList ();
    public void  updateFile() throws IOException ;
}
