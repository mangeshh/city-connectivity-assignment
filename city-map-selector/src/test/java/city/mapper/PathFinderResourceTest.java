/*
 * package city.mapper;
 * 
 * import java.lang.reflect.Field;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.springframework.boot.autoconfigure.ImportAutoConfiguration; import
 * org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration; import
 * org.springframework.cloud.openfeign.FeignAutoConfiguration; import
 * org.springframework.cloud.openfeign.ribbon.
 * FeignRibbonClientAutoConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders; import
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers; import
 * org.springframework.test.web.servlet.setup.MockMvcBuilders;
 * 
 * import com.mc.resource.CityMapResource;
 * 
 * @RunWith(SpringJUnit4ClassRunner.class) public class PathFinderResourceTest {
 * 
 * private MockMvc mockMvc;
 * 
 * @InjectMocks CityMapResource cityMapResource;
 * 
 * @Before public void Setup() {
 * 
 * }
 * 
 * @Test public void testIsConnected1() throws Exception { mockMvc =
 * MockMvcBuilders.standaloneSetup(cityMapResource).build();
 * mockMvc.perform(MockMvcRequestBuilders.get(
 * "/city-map-provider/connected-by-disjoint?origin=L&destination=N"))
 * .andExpect(MockMvcResultMatchers.status().isOk())
 * .andExpect(MockMvcResultMatchers.content().string("yes")); }
 * 
 * 
 * }
 */