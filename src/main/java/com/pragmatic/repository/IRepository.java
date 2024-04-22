package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.model.User;

import java.util.Map;
import java.util.Objects;

public interface IRepository <T>  {
    public Map<Integer, T> getRepoList ();
}
