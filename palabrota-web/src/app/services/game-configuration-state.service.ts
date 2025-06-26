import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GameConfigurationStateService {
  uuid: string | null = null;
  word: string | null = null;
  settings: { letters: number; attempts: number } | null = null;

  setGame(data: any) {
    this.uuid = data.uuid;
    this.word = data.word;
    this.settings = data['game-settings'];
  }

  clear() {
    this.uuid = null;
    this.word = null;
    this.settings = null;
  }
}
