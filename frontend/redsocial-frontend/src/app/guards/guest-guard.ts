import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth';

export const guestGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isLoggedIn()) {
    // Si el usuario ya está logueado, redirigir al home
    router.navigate(['/home']);
    return false;
  } else {
    // Si no está logueado, permitir acceso a rutas públicas
    return true;
  }
};