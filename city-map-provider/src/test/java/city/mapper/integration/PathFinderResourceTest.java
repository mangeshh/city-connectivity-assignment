package city.mapper.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mc.main.Application;
import com.mc.resource.PathFinderResource;

@RunWith(SpringRunner.class)
@WebMvcTest(PathFinderResource.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource(locations="classpath:application.test.properties")
public class PathFinderResourceTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testIsConnectedAdjMatrix() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-adj-matrix?origin=Boston&destination=Newark").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("yes", result.getResponse().getContentAsString());
	}
	
	
	@Test
	public void testIsConnectedAdjMatrix1() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-disjoint?origin=L&destination=M").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("yes", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testIsConnectedDisJoint() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-disjoint?origin=L&destination=M1").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("no", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testIsConnectedFastAdjMatrix() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-fast-adj-matrix?origin=L&destination=M").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("yes", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testIsConnectedFastAdjMatrix1() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-fast-adj-matrix?origin=L&destination=M1").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("no", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testIsConnectedFastAdjMatrix2() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-fast-adj-matrix?origin=L&destination=M").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("yes", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testIsConnectedBadReqByDisJointCall() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-disjoint?origin=L").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(400, result.getResponse().getStatus());
 	}
	
	@Test
	public void testIsConnectedBadReqByAdjMatrixCall() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-adj-matrix?origin=L").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(400, result.getResponse().getStatus());
 	}
	
	@Test
	public void testIsConnectedBadReqByFastAdjMatrixCall() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-fast-adj-matrix?origin=L&destination=M1").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("no", result.getResponse().getContentAsString());
 	}
	
	@Test
	public void testIsConnectedBaseDisjoint1() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-disjoint?origin=Boston&destination=Newark").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("yes", result.getResponse().getContentAsString());
 	}
	
	@Test
	public void testIsConnectedBaseDisjoint2() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-disjoint?origin=Boston&destination=Philadelphia").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("yes", result.getResponse().getContentAsString());
 	}
	
	@Test
	public void testIsConnectedBaseDisjoint3() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-disjoint?origin=Philadelphia&destination=Albany").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("no", result.getResponse().getContentAsString());
 	}
	
	@Test
	public void testIsConnectedBaseDisjoint4() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-disjoint?origin=L&destination=M1").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("no", result.getResponse().getContentAsString());
 	}
	
	@Test
	public void testIsConnectedBaseDisjoint5() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected-by-disjoint?origin=M1&destination=L").accept(MediaType.ALL);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("no", result.getResponse().getContentAsString());
 	}
	
}
