Integrating Razorpay Payment Gateway with Java Spring Boot and JavaScript
Introduction
In this project, we’ll walk through the process of integrating the Razorpay payment gateway into a Java Spring Boot application, with JavaScript handling the frontend interactions. Razorpay is a popular choice due to its comprehensive API, which supports multiple payment methods and offers robust features like webhooks and detailed transaction logs.

Prerequisites
Before diving in, make sure you have the following:

1. Basic knowledge of Java, Spring Boot, and JavaScript.
2. A Razorpay account with API keys.
3. An IDE (like IntelliJ IDEA) with Spring Boot setup.
4. Maven or Gradle for dependency management.

Step 1: Setting Up the Spring Boot Application
Create a Spring Boot Project:
Use Spring Initializr or your IDE to create a new Spring Boot project. Include dependencies for:

Spring Web
Spring Security
Spring Boot DevTools
Configure Dependencies:
Add the Razorpay Java SDK to your pom.xml or build.gradle file:

    <dependency>
        <groupId>com.razorpay</groupId>
        <artifactId>razorpay-java</artifactId>
        <version>1.3.0</version>
    </dependency>
    
Set Up Application Properties:
Configure your application’s properties to include Razorpay API keys:

    razorpay.key.id=your_api_key_id    
    razorpay.key.secret=your_api_secret_key
    
Create a Payment Controller:
Implement a controller to handle payment requests:

    @RestController
    @RequestMapping("/api/payment")  
    public class PaymentController {
    
      @Value("${razorpay.key.id}")
      private String razorpayKeyId;
    
      @Value("${razorpay.key.secret}")
      private String razorpayKeySecret;

      @PostMapping("/createOrder")
      public ResponseEntity<String> createOrder(@RequestBody PaymentRequest request) {
          try {
              RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

              JSONObject orderRequest = new JSONObject();
              orderRequest.put("amount", request.getAmount() * 100); // Amount in paise
              orderRequest.put("currency", "INR");
              orderRequest.put("receipt", UUID.randomUUID().toString());

            Order order = razorpayClient.Orders.create(orderRequest);
            return ResponseEntity.ok(order.toString());
          } catch (RazorpayException e) {
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
          }
      }
    }
    
Step 2: JavaScript Integration on the Frontend
Razorpay Checkout Integration:

    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    
Trigger Payment:
Use JavaScript to trigger the Razorpay Checkout:

    function makePayment(orderId, amount) {
        var options = {
            "key": "your_api_key_id",
            "amount": amount,
            "currency": "INR",
            "name": "Your Company Name",
            "description": "Test Transaction",
            "order_id": orderId,
            "handler": function (response){
                alert("Payment successful. Payment ID: " + response.razorpay_payment_id);
                // Implement your logic to verify the payment at the backend
            },
            "prefill": {
                "name": "John Doe",
                "email": "john.doe@example.com"
            },
        "theme": {
            "color": "#3399cc"
        }
    };
    var rzp1 = new Razorpay(options);
    rzp1.open();
    }
    
Create a Payment Button:
Add a button in your HTML to initiate the payment:

    <button onclick="makePayment(orderId, amount)">Pay Now</button>


Go to your Razorpay dashboard.
Navigate to the Webhooks section and register your webhook endpoint.

Step 4: Testing and Deployment
Testing: Test the integration in Razorpay’s test mode. Simulate various payment outcomes to ensure your application handles all scenarios (success, failure, etc.).
Deployment: Deploy your application to a cloud platform (like AWS or Heroku) and switch Razorpay to live mode.

Conclusion
Integrating Razorpay with Java Spring Boot and JavaScript enables a seamless payment experience in your application. This setup provides a secure and efficient way to handle transactions, enhancing user satisfaction and trust.
