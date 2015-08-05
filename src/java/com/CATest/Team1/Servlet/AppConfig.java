package com.CATest.Team1.Servlet;

import com.CATest.Team1.Model.Game;
import java.util.HashMap;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class AppConfig  extends Application {
 public static final HashMap<Long, Game> games = new HashMap<>();
}
