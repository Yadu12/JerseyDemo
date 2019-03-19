package com.jerseydemo.demo;


import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {
	
	AlienRepository repo=new AlienRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Alien> getAliens() {
		System.out.println("getAliens method called...");
		
		return repo.getAliens();
	}
	
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id") int id) {
		System.out.println("getAliens method called...");
		return repo.getAlien(id);
	}
	
	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a)
	{
		System.out.println("createAlien method called...");
		System.out.println(a);
		repo.create(a);
		return a;
	}
	
	@PUT
	@Path("alien")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien updateAlien(Alien a)
	{
		System.out.println("updateAlien method called...");
		System.out.println(a);
		if(repo.getAlien(a.getId()).getId()==0)
		{
			repo.create(a);
		}
		else
			repo.update(a);
		return a;
	}
	
	@DELETE
	@Path("alien/{id}")
	public Alien killAlien(@PathParam("id") int id)
	{
		System.out.println("killAlien method called...");
		Alien a=repo.getAlien(id);
		if(a.getId()!=0)
			repo.delete(id);
		System.out.println("Removed Alien :"+a);
		return a;
	}
	

}