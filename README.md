# SuperWin Backend
This repo has the backend services for SuperWin.

SuperWin is an iGaming platform.
iGaming refers to online betting conducted through electronic means, encompassing various forms of gambling such as poker, sports betting, and online casinos. 

Available games:
(No games available)

In-progress games:
1. WinGo lottery

Upcoming games:
1. Aviator
2. Coin flip
3. Mines
4. Dice

Future games:
1. Slots

Jump to:  
[Services Flow Documentation](https://github.com/Mukul-Howale/superwin-backend?tab=readme-ov-file#microservices-flow-documentation)  
[Services documentation](https://github.com/Mukul-Howale/superwin-backend?tab=readme-ov-file#each-service-documentation)
   
## Services Flow Documentation
1. Infrastructure Layer
  ```
  A. Service Registry (Eureka Server)
    Port: 8761
    Purpose: Service discovery and registration
    All microservices register themselves here
    Enables service-to-service communication
  
  B. Config Server
    Port: 8088
    Centralizes configuration for all services
    Services fetch their configurations on startup

  C. API Gateway
    Port: 8060
    Entry point for all external requests
    Routes:
      - /user/** → user-service
      - /profile/** → profile-service
      - /game/**, /win-go/** → game-service
      - /transaction/** → transaction-service
      - /referral/** → referral-service
  ```
    
2. Service Interaction Flows
  ```
  A. User Registration & Authentication Flow
    1. Client → API Gateway → User Service
         User registration/login request
       
    3. User Service → Profile Service
         Creates user profile after successful registration
        
    5. Profile Service → Referral Service
         Handles referral code if provided during registration
       
  B. Gaming Flow
    1. Game Initialization:
         Client → API Gateway → Game Service
         Game Service → Profile Service (validates user & balance)

    2. WinGo Game Flow:
         1. Client requests game session

         2. Game Service checks:
            - Game availability
            - Maintenance status
            - Active sessions

         3. Client places bet:
            - Validates bet parameters
            - Checks user balance
            - Creates bet record

         4. Game Service → Profile Service
            - Updates wallet balance

         5. Game completion:
            - Calculates results
            - Updates user balances
            - Records game history

  C. Transaction Flow
    1. User initiates transaction:
         Client → API Gateway → Transaction Service

    2. Transaction Processing:
        - Select payment method
        - Enter amount
        - Validate transaction limits
        - Process payment

    3. Balance Update:
        Transaction Service → Profile Service
       - Updates user's wallet balance
       - Records transaction history

  D. Profile Management Flow
    1. Profile Creation:
         User Service → Profile Service
       - Creates new profile
       - Initializes wallet
       - Generates referral code

    2. Profile Updates:
         Client → API Gateway → Profile Service
       - Updates user information
       - Manages wallet operations
    
  E. Referral System Flow
    1. Referral Creation:
        Profile Service → Referral Service
       - Generates referral code
       - Links to user profile

    2. Referral Redemption:
         User Service → Referral Service → Profile Service
       - Validates referral code
       - Awards bonuses to referrer and referee
  ```

3. Inter-Service Communication
  ```
  A. Synchronous Communication
      REST APIs between services
      WebClient configuration for service-to-service calls
      Load-balanced requests using Eureka

  B. Error Handling
      Global exception handlers in each service
      Standardized error responses
      Circuit breakers for fault tolerance
  ```
   
4. Data Flow Example (WinGo Game)
  ```
  A. Game Initialization:
       Client → API Gateway
     - Game Service: /game/win-go/session
     - Profile Service: Validate user & balance
     - Return active session & history

  B. Placing Bet:
       Client → API Gateway
     - Game Service: /win-go/bet
     - Profile Service: Check & update balance
     - Return bet confirmation

  C. Game Result:
       Game Service
     - Calculate outcome
     - Update user balances
     - Record game history
     - Notify users of results
  ```

5. Security Flow
  ```
  A. Authentication:
       Client → API Gateway
     - User Service: Validate credentials
     - Generate JWT token
     - Return token to client

  B. Authorization:
       Client → API Gateway
     - Validate JWT token
     - Route to appropriate service
     - Service-level permission check
  ```

6. Transaction Security Flow
  ```
  A. Deposit Flow:
       Client → API Gateway
     - Transaction Service
     - Validate payment method
     - Process payment
     - Update wallet balance
     - Return transaction status

  B. Withdrawal Flow:
       Client → API Gateway
     - Transaction Service
     - Validate withdrawal request
     - Check balance & limits
     - Process withdrawal
     - Update wallet balance
  ```

## Services documentation
1. User Service  
  - Purpose:  
  ```
  Handles user authentication, authorization, and user management operations.
  ```  
  - Components:
    - Models
      ```
      User: Core user entity with authentication details
      ```
      
    - Controllers
      ```
      @RestController
      public class UserController {
          // Authentication endpoints
          // User management endpoints
      }
      ```

    - Services
      ```
      
      ```
      
    - Repositories
      ```
      @Repository
      public interface UserRepository extends JpaRepository<User, UUID> {
          // User CRUD operations
      }
      ```

2. Profile Service
  - Purpose:
  ```
  Manages user profiles and wallet operations.
  ```  
  - Components:
    - Models
      ```
      Profile: User profile information
      MainWallet: Primary wallet for transactions
      SavingsWallet: Wallet for savings
      ReferralWallet: Wallet for referral earnings
      ```
      
    - Controllers
      ```
      @RestController
      @RequestMapping("/profile")
      public class ProfileController {
          @GetMapping("/{id}")
          public ResponseEntity<ProfileDTO> getById(@PathVariable UUID id)
          
          @GetMapping("/{referralCode}")
          public ResponseEntity<ProfileFilterDTO> getProfileFilterByReferralCode()
          
          @PostMapping("/create/{userId}/{referredCode}")
          public ResponseEntity<ProfileDTO> createProfile()
      }
      
      @RestController
      @RequestMapping("/main-wallet")
      public class MainWalletController {
          @PatchMapping("/update-balance")
          public ResponseEntity<Boolean> updateBalance()
      }
      ```
      
    - Services
      ```
      @Service
      public class ProfileService {
          // Profile management
          // Wallet operations
          // Referral handling
      }
      ```
      
    - Repositories
      ```
      ProfileRepository
      MainWalletRepository
      SavingsWalletRepository
      ReferralWalletRepository
      ```

3. Game Service
  - Purpose:  
  ```
  Manages game operations and betting functionality.
  ```  
  - Components:
    - Models
      ```
      Game: Base game entity
      WinGoSession: WinGo game session
      WinGoBet: Betting information
      ```
      
    - Controllers
      ```
      @RestController
      @RequestMapping("/game")
      public class GameController {
          @PostMapping("/add-game")
          public ResponseEntity<String> addGame()
      }
      
      @Controller
      @RequestMapping("/win-go")
      public class WinGoController {
          @PostMapping("/bet")
          public ResponseEntity<String> bet()
          
          @GetMapping("/session/{time}")
          public ResponseEntity<WinGoSessionResponseDTO> getSessions()
          
          @GetMapping("/bet/{profileId}")
          public ResponseEntity<List<WinGoBet>> getBets()
      }
      ```

    - Services
      ```
      @Service
      public class GameService {
          // Game management
          // Session handling
          // Bet processing
      }
      
      @Service
      public class WinGoService {
          // WinGo specific operations
          // Bet validation
          // Result calculation
      }
      ```
      
    - Repositories
      ```
      public interface GameRepository extends JpaRepository<Game, UUID>
      public interface WinGoBetRepository extends JpaRepository<WinGoBet, UUID>
      public interface WinGoSessionRepository extends JpaRepository<WinGoSession, UUID>
      ```

4. Transaction Service 
  - Purpose:  
  ```
  Handles all financial transactions and payment processing.
  ```  
  - Components:
    - Models
      ```
      
      ```
      
    - Controllers
      ```
      @RestController
      public class TransactionController {
          // Deposit endpoints
          // Withdrawal endpoints
          // Transaction history
      }
      ```

    - Services
      ```
      
      ```
      
    - Repositories
      ```
      
      ```

5. Referral Service
  - Purpose:  
  ```
  Manages referral system and rewards.
  ```  
  - Components:
    - Models
      ```
      
      ```
      
    - Controllers
      ```
      @RestController
      @RequestMapping("/referral")
      public class ReferralController {
          @PostMapping("/add")
          public ResponseEntity<String> addReferral()
      }
      ```

    - Services
      ```
      
      ```
      
    - Repositories
      ```
      @Repository
      public interface ReferralRepository extends JpaRepository<Referral, UUID> {
          Optional<Referral> getReferralDetailsByReferralCode(@Param("referredCode") Long referredCode);
      }
      ```

## Common Features Across Services
1. Exception Handling
  ```
  @RestControllerAdvice
  public class GlobalExceptionHandler {
      // Common exceptions
      // Service-specific exceptions
      // Error responses
  }
  ```

2. Configuration
  - Model Mapper
    ```
    @Configuration
    public class ModelMapperConfig {
        @Bean
        public ModelMapper getMapperObject()
    }
    ```
    
  - Web Client (for inter-service communication)
    ```
    @Configuration
    public class WebClientConfig {
        @Bean
        public WebClient webClient()
    }
    ```

3. Security
  ```
  - JWT Authentication
  - Role-based access control
  - Secure inter-service communication
  ```

4. Database
  ```
  - PostgreSQL for all services
  - JPA repositories
  - Transaction management
  ```

5. Monitoring
  ```
  - Actuator endpoints
  - Micrometer metrics
  - Zipkin tracing
  ```

6. Testing
  ```
  - JUnit Jupiter
  - TestContainers
  - Integration tests
  ```
