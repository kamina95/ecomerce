package loginApp;

import user.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
@RequestMapping("/api/public")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserProductRepository userProductRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if(!user.getUserName().isEmpty()){
            userRepository.save(user);
            return "Registration complete";
        }
        else{
            return "Missing user data";
        }
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUsername());
        if (user != null && user.getUserPass().equals(loginRequest.getPassword())) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    @PostMapping("/buyProduct")
    public String buyProduct(@RequestBody BuyProductRequest buyProductRequest) {
        User user = userRepository.findById(buyProductRequest.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Product product = productRepository.findById(buyProductRequest.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        UserProduct userProduct = new UserProduct();
        userProduct.setUser(user);
        userProduct.setProduct(product);
        userProduct.setPurchaseDate(new Date()); // Set current date as purchase date
        userProductRepository.save(userProduct);

        return "Product bought successfully";
    }

//    @PostMapping("/buyProduct")
//    public String buyProduct(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//        Product product = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
//
//        UserProduct userProduct = new UserProduct();
//        userProduct.setUser(user);
//        userProduct.setProduct(product);
//        userProduct.setPurchaseDate(new Date()); // Set current date as purchase date
//        userProductRepository.save(userProduct);
//
//        return "Product bought successfully";
//    }


}
