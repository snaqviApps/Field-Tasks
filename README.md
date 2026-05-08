#  This application implements Login Service 
- takes user input: username and password
- makes POST service call. Upon receiving 'access token' successfully, passes it to the subsequent GET service
- if successful, user detailed information that is received as a result, passed via Navigation to detailed screen
  for display. 
  
 
# Tools: 
 - Compose
 - sealed interface based state management
 - retrofit library for HTTP service calls
 - Nav3 library for Navigation, that takes screens as serializable keys, hence each screen is a data class / object. 
 - 

<table>
 <tr>
  <td>
   <img width="1080" height="2340" alt="preEntry_login" src="https://github.com/user-attachments/assets/5a540dbb-d1b2-4d12-b139-8ce01ce8b0f9" />
  </td>
  <tr>
 <tr>
  <td>
   <img width="1080" height="2340" alt="Screenshot_20260506_153225" src="https://github.com/user-attachments/assets/3522f3da-b1af-4c32-84ba-2bd60a4b95c9" />
  </td>
  <tr>

   ## 
  <tr>
   <td>
    <img width="1080" height="2340" alt="Screenshot_20260506_150802" src="https://github.com/user-attachments/assets/1365ad68-7091-471b-ad79-70ebdb4c0d9f" />

  </td>
  </tr>
</table>

