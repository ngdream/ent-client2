
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.RestTemplate;

// import com.isj.gestiondenote.ClientWeb.utils.test.URL;

// @ControllerAdvice
// public class AnnotationAdvice {

// @ModelAttribute("categories")
// public Object[] getCategories(Model model) {

// RestTemplate restTemplate = new RestTemplate();

// // we're getting all categories from the library service
// Object[] categories = restTemplate.getForObject(URL.BASE_URL_BIB +
// "/categories", Object[].class);
// model.addAttribute("categories", categories);
// return categories;
// }
// }