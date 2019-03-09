package city.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mc.cache.CityConnectivityMap;
import com.mc.cache.DisjointSet;
import com.mc.cache.DisjointSet.Node;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CityConnectivityMap.class)
public class CityConnectivityMapTest {

	@Test
	public void testConnectivitySymentric() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("A", "A");
		assertEquals("yes", ans);
		String ans1 = cityConnectivityMap.isConnectedByFastAdjMatrix("B", "B");
		assertEquals("yes", ans);
		assertEquals("yes", ans1);
	}

	@Test
	public void testConnectivityTransitivity1() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		cityConnectivityMap.put("C", "D");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("A", "D");
		System.out.println(ans);
		assertEquals("yes", ans);
	}
	
	@Test
	public void testConnectivityTransitivityAdjMtx1() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		cityConnectivityMap.put("C", "D");
		String ans = cityConnectivityMap.isConnectedByAdjMatrix("A", "D");
		System.out.println(ans);
		assertEquals("yes", ans);
	}

	@Test
	public void testConnectivityTransitivity2() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		cityConnectivityMap.put("C", "D");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("D", "A");
		assertEquals("yes", ans);
	}
	
	
	@Test
	public void testConnectivityTransitivityAdjMtx2() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		cityConnectivityMap.put("C", "D");
		String ans = cityConnectivityMap.isConnectedByAdjMatrix("D", "A");
		assertEquals("yes", ans);
	}
	
	@Test
	public void testConnectivityTransitivity3() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();

		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		cityConnectivityMap.put("D", "C");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("D", "A");
		assertEquals("yes", ans);
	}
	
	@Test
	public void testConnectivityTransitivityAdjMatx3() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();

		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		cityConnectivityMap.put("D", "C");
		String ans = cityConnectivityMap.isConnectedByAdjMatrix("D", "A");
		assertEquals("yes", ans);
	}

	@Test
	public void testConnectivityTransitivity4() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		cityConnectivityMap.put("D", "C");
		cityConnectivityMap.put("E", "D");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("A", "E");
		assertEquals("yes", ans);
	}

	@Test
	public void testConnectivityReflective() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("B", "A");
		assertEquals("yes", ans);
	}

	@Test
	public void testConnectivityReflective1() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("B", "A1");
		assertEquals("no", ans);
	}

	@Test
	public void testConnectivityCircle1() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("C", "A");
		assertEquals("yes", ans);
	}
	

	@Test
	public void testConnectivityCircleAdjMtx1() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "C");
		String ans = cityConnectivityMap.isConnectedByAdjMatrix("C", "A");
		assertEquals("yes", ans);
	}

	@Test
	public void testConnectivitySimple1() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		String ans = cityConnectivityMap.isConnectedByFastAdjMatrix("A", "B");
		assertEquals("yes", ans);
		String ans1 = cityConnectivityMap.isConnectedByFastAdjMatrix("B", "A");
		assertEquals("yes", ans1);
	}

	@Test
	public void testConnectivityDifferentCombination() {
		CityConnectivityMap cityConnectivityMap = null;
		cityConnectivityMap = new CityConnectivityMap();
		
		cityConnectivityMap.put("A", "B");
		cityConnectivityMap.put("B", "F");
		cityConnectivityMap.put("C", "D");
		cityConnectivityMap.put("D", "E");
		cityConnectivityMap.put("E", "F");
		cityConnectivityMap.put("F", "G");
		cityConnectivityMap.put("G", "F");
		cityConnectivityMap.put("G", "I");

		cityConnectivityMap.put("Boston", "New York");
		cityConnectivityMap.put("Philadelphia", "Newark");
		cityConnectivityMap.put("Newark", "Boston");
		cityConnectivityMap.put("Trenton", "Albany");

		assertEquals("yes", cityConnectivityMap.isConnectedByFastAdjMatrix("G", "I"));
		assertEquals("yes", cityConnectivityMap.isConnectedByFastAdjMatrix("A", "I"));
		assertEquals("yes", cityConnectivityMap.isConnectedByFastAdjMatrix("A", "D"));
		assertEquals("yes", cityConnectivityMap.isConnectedByAdjMatrix("A", "D"));
		assertEquals("yes", cityConnectivityMap.isConnectedByFastAdjMatrix("D", "A"));
		assertEquals("yes", cityConnectivityMap.isConnectedByAdjMatrix("D", "A"));
		assertEquals("yes", cityConnectivityMap.isConnectedByFastAdjMatrix("I", "A"));
		assertEquals("no", cityConnectivityMap.isConnectedByFastAdjMatrix("New York1", "Newark"));
		assertEquals("yes", cityConnectivityMap.isConnectedByFastAdjMatrix("New York", "Newark"));
		assertEquals("yes", cityConnectivityMap.isConnectedByFastAdjMatrix("Boston", "Newark"));
	}

	@Test
	public void testConnectivityUsingBasicDisjointSetReflect1() {
		DisjointSet disjoinSet = new DisjointSet();
		
		disjoinSet.makeSet("A");
		disjoinSet.makeSet("B");
		disjoinSet.union("A", "B");
		 
        Node n1 = disjoinSet.findSet("A");
        Node n2 = disjoinSet.findSet("B");
        Boolean isConnected = null;
        if(n1 == n2){
           isConnected = true;
        } else{
           isConnected = false;
        }
        assertEquals(true, isConnected);
	}
	
	@Test
	public void testConnectivityUsingBasicDisjointSetReflect2() {
		DisjointSet disjoinSet = new DisjointSet();
		
		disjoinSet.makeSet("A");
		disjoinSet.makeSet("B");
		disjoinSet.union("A", "B");
		 
        Node n1 = disjoinSet.findSet("B");
        Node n2 = disjoinSet.findSet("A");
        Boolean isConnected = null;
        if(n1 == n2){
           isConnected = true;
        } else{
           isConnected = false;
        }
        assertEquals(true, isConnected);
	}
	
	@Test
	public void testConnectivityUsingBasicDisjointSetCircle1() {
		DisjointSet disjoinSet = new DisjointSet();
		
		disjoinSet.makeSet("A");
		disjoinSet.makeSet("B");
		disjoinSet.makeSet("C");
		disjoinSet.union("A", "B");
		disjoinSet.union("B", "A");

        Node n1 = disjoinSet.findSet("A");
        Node n2 = disjoinSet.findSet("B");
        Boolean isConnected = null;
        if(n1 == n2){
           isConnected = true;
        } else{
           isConnected = false;
        }
        assertEquals(true, isConnected);
	}
	
	@Test
	public void testConnectivityUsingBasicDisjointSetConnectivity3() {
		DisjointSet disjoinSet = new DisjointSet();
		
		disjoinSet.makeSet("A");
		disjoinSet.makeSet("B");
		disjoinSet.makeSet("C");
		disjoinSet.union("A", "B");
		disjoinSet.union("B", "C");

        Node n1 = disjoinSet.findSet("C");
        Node n2 = disjoinSet.findSet("A");
        Boolean isConnected = null;
        if(n1 == n2){
           isConnected = true;
        } else{
           isConnected = false;
        }
        assertEquals(true, isConnected);
	}
	
	@Test
	public void testConnectivityUsingBasicDisjointSet4() {
		DisjointSet disjoinSet = new DisjointSet();
		
		disjoinSet.makeSet("A");
		disjoinSet.makeSet("B");
		disjoinSet.makeSet("C");
		disjoinSet.makeSet("D");

		disjoinSet.union("A", "B");
		disjoinSet.union("C", "B");
		disjoinSet.union("D", "C");

        Node n1 = disjoinSet.findSet("D");
        Node n2 = disjoinSet.findSet("A");
        Boolean isConnected = null;
        if(n1 == n2){
           isConnected = true;
        } else{
           isConnected = false;
        }
        assertEquals(true, isConnected);
	}
	
	@Test
	public void testConnectivityUsingBasicDisjointSetTransitivity1() {
		DisjointSet disjoinSet = new DisjointSet();
		
		disjoinSet.makeSet("A");
		disjoinSet.makeSet("B");
		disjoinSet.makeSet("C");
		disjoinSet.makeSet("D");

		disjoinSet.union("A", "B");
		disjoinSet.union("C", "B");
		disjoinSet.union("D", "C");

        Node n1 = disjoinSet.findSet("A");
        Node n2 = disjoinSet.findSet("D");
        Boolean isConnected = null;
        if(n1 == n2){
           isConnected = true;
        } else{
           isConnected = false;
        }
        assertEquals(true, isConnected);
	}
	
	@Test
	public void testConnectivityUsingBasicDisjointSetTransitivity2() {
		DisjointSet disjoinSet = new DisjointSet();
		
		disjoinSet.makeSet("A");
		disjoinSet.makeSet("B");
		disjoinSet.makeSet("C");
		disjoinSet.makeSet("D");

		disjoinSet.union("A", "B");
		disjoinSet.union("C", "B");
		disjoinSet.union("D", "C");

        Node n1 = disjoinSet.findSet("D1");
        Node n2 = disjoinSet.findSet("A");
        Boolean isConnected = null;
        if(n1 == n2){
           isConnected = true;
        } else{
           isConnected = false;
        }
        assertEquals(false, isConnected);
	}
	
	@Test
	public void testConnectivityUsingDisjointSet() {
		DisjointSet disjoinSet = new DisjointSet();
		
		disjoinSet.makeSet("A");
		disjoinSet.makeSet("B");
		disjoinSet.makeSet("C");
		disjoinSet.makeSet("D");
		disjoinSet.makeSet("E");
		disjoinSet.makeSet("F");
		disjoinSet.makeSet("G");
		disjoinSet.makeSet("X");


		disjoinSet.union("A", "B");
		disjoinSet.union("B", "C");
		disjoinSet.union("C", "D");
		disjoinSet.union("D", "E");
		disjoinSet.union("E", "F");
		disjoinSet.union("F", "G");

        Node n1 = disjoinSet.findSet("A");
        Node n2 = disjoinSet.findSet("G");
        Boolean isConnected = null;
        if(n1 == n2){
           isConnected = true;
        } else{
           isConnected = false;
        }
        assertEquals(true, isConnected);
	}
}
