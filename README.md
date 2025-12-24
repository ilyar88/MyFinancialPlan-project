# MyFinancialPlan Selenium project

## About

MyFinancePlan is a SaaS website for creating and tracking personal financial plans. It allows users to build and manage their own plans by tracking income and expenses, view other users’ plans (if sharing access is enabled), access median financial data based on their status for comparison, and receive actionable steps alerts to help implement and monitor plans according to their path to the goals and dreams.

## Project Overview

![Architecture Diagram](Architecture.png)
<br>


| Features                      | Description                    |
|-------------------------------------|--------------------------|
| Login page                          | Includes the following:<br>1. Link to create an account<br>2. Email address<br>3. Password <br>4. Login button<br>5. Quick connect button<br>6. Forgot password link |
| Registration page                   | Includes the following:<br>1. Login link<br>2. Email address<br>3. Password<br>4. Password verification<br>5. Account creation button<br>6. Quick registration button |
| OTP verification                    | Include email Verification |
| Profile page                        | Includes the following:<br>1. Email Address<br>2. Submit Request Button<br>3. Return to Login Page Link                       |
| Layout                              | Includes the following:<br>1. Financial plan<br>2. Monthly goal<br>3.My financial plan<br>4. Summary of actions<br>5. Home page<br>6. Other people's plans<br>7. Profile page |
| The financial planning process      | Includes the following:<br>1. Expenses and incomes<br>2. Financial goals<br>3. Multi-year recurring expenses<br>4. Emergency fund                           |
| Purchase offer page                 | Includes the following:<br>1. Basic service path<br>2. Standard service path<br>3. Prime service path                          |
| Clearing page                       | Includes the following:<br>1. Choosing a provider<br>2. Payment details<br>3. Purchase button redirects to the home page                                 |
| View/Edit Financial Plan Page       | Includes the following:<br>1. expenses and incomes<br>2. Financial goals<br>3. Emergency  Fund                                  |
| Profile page                        | Includes the following:<br>1. First name<br>2. Last name<br>3. Email<br>4. Cell phone<br>5. Age<br>6. Marital status<br>7. Number of children                 |

## Sanity test

| Test cases                           | Description              |
|--------------------------------------|--------------------------|
| New User register to the website     | Register to the website with username and password. |
| Login to Homepage                    | Login to the website with username and password. |
| Create a profile                  | Create profile with the following details:<br>1. firstName<br>2. lastName<br>3. age<br>4. Marital status<br>5. Gender<br>6. Single breadwinner<br>7. Residential area, etc. |
| Introduction page                    | Verify expanding of 3 goals in the page. |
| Add several incomes                  | Add incomes with amonut like:<br>1. First job<br>2. Second job<br>3. Allowance |
| Add several expenses                 | Add expenses with amount like:<br>1. Apartment<br>2. Internet and media<br>3. Car |
| Congratulation page                  | Choose financial path Prime service |
| Order summary page                   | Fill in the following details and redirect to  the Payment page:<br>1. Full name<br>2. Email address                          |
| Credit checkout page                 | Fill in the payment information  |
| Join success page                    | Enable "I want to watch other people's financial plans." checkbox and redirect to the Home page                                   |
| Home page                            | Navigate to the following links:<br>1. Home page<br>2. Profile<br>3. Path to goals and dreams<br>4. My plan<br>5. Other people’s plans<br>6. Report a problem<br>7. Suggestions for improvement<br>8. Contact us |
| Notification icon                    | Click on the notifications icon and verify that each update you made appears in the notifications list.                    |
| Actions (Deposit and withdrawal)     | — |

## Validation test

| Test cases                           | Description              |
|--------------------------------------|--------------------------|
| Reset password page                  | — |
| Registration page                    | — |

