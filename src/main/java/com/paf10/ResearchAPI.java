package com.paf10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ResearchAPI")
public class ResearchAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Research itemObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResearchAPI() {
		super();
		itemObj = new Research();
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
		String output = itemObj.insertResearch(
				request.getParameter("userName"),
				request.getParameter("email"),
				request.getParameter("title"),
				request.getParameter("descrip"),
				request.getParameter("price"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = itemObj.updateResearch(
				Integer.parseInt(paras.get("hiduserIDSave").toString()),
				paras.get("userName"),
				paras.get("email"),
				paras.get("title"),
				paras.get("descrip"),
				paras.get("price"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = itemObj.removeResearch(Integer.parseInt(paras.get("userID").toString()));
		response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map<String,String> getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], java.net.URLDecoder.decode(p[1], StandardCharsets.UTF_8.name()));
			}
		}
		catch (Exception e)
		{
		}
		return map;
	}
}