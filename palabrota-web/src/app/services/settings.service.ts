import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class SettingsService {
  private _letters: number;
  private _attempts: number;

  constructor() {
    // Recupera de localStorage o usa valores por defecto
    this._letters = Number(localStorage.getItem('letters')) || 2;
    this._attempts = Number(localStorage.getItem('attempts')) || 2;
  }

  get letters() {
    return this._letters;
  }
  set letters(val: number) {
    this._letters = val;
    localStorage.setItem('letters', String(val));
  }

  get attempts() {
    return this._attempts;
  }
  set attempts(val: number) {
    this._attempts = val;
    localStorage.setItem('attempts', String(val));
  }
}