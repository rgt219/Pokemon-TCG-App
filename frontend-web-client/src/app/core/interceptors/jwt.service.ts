import { HttpInterceptorFn, HttpRequest, HttpHandlerFn } from "@angular/common/http";
import { inject } from '@angular/core';
import { AuthService } from "../auth/auth.service";

export const jwtInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {

  // 1. Inject our AuthService to grab the token from localStorage
  const authService = inject(AuthService);
  const token = authService.getToken();

  // 2. If a token exists, we clone the request and staple the Authorization header to it
  if (token) {
    const clonedRequest = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`) //HTTP requests in Angular are strictly immutable (they cannot be changed once created) to prevent accidental data corruption.
                                                                   //To add a header, you must create a cloned copy of the request with the new data attached.
    });
    // 3. Send the modified request on its way to the backend
    return next(clonedRequest);

  }

  // 4. If no token exists (e.g., they aren't logged in yet), just send the original request
  return next(req);
}
