# super-win-backend
This repo has the backend services for SuperWin.

SuperWin is an iGaming platform.
iGaming refers to online betting conducted through electronic means, encompassing various forms of gambling such as poker, sports betting, and online casinos. 

Available games:
1. WinGo lottery

Upcoming games:
1. Coin flip
2. Mines
3. Dice

Future games:
1. Aviator

SuperWin Microservices Flow Documentation
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
