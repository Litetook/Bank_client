package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.model.User;

import java.util.List;

public interface IRepository <T>  {
    public List<Object> getRepoList ();
}
