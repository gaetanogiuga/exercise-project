package com.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.JoinRowSet;

import org.json.JSONException;
import org.json.JSONObject;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.model.Person;



/**
 * Servlet implementation class servletExample
 */
@WebServlet("/servletExample")
public class servletExample extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servletExample() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
        if(br != null){
            json = br.readLine();
        }
//        System.out.println(json);
//        java.lang.reflect.Type type = new TypeToken<List<Person>>(){}.getType();
//        List<Person> list = new Gson().fromJson(json, type);
//        System.out.println(list);
//        for(int i=0;i<list.size();i++){
//        	Person p1 = list.get(i);
//        	System.out.println(p1.getFirstName());
//        }
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonObject()){
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            System.out.println(jsonObject);
        }
        if(jsonTree.isJsonArray()){
        	JsonArray jsonArray = jsonTree.getAsJsonArray();
        	for(int i=0;i<jsonArray.size();i++){
        		JsonElement p = jsonArray.get(i);
        		JsonObject obj = p.getAsJsonObject();
        		String firstName = obj.get("firstName").getAsString();
        		System.out.println(firstName);
        	}
        }
        Person outPerson = new Person();
        outPerson.setFirstName("Caio");
        outPerson.setLastName("Sempronio");
        outPerson.setId(3);
		JsonObject jobj = new JsonObject();
		jobj.add("Person", gson.toJsonTree(outPerson));
		response.getWriter().print(gson.toJson(jobj));
		//doGet(request, response);
	}

}
