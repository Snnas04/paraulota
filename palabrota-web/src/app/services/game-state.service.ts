import { Injectable } from '@angular/core';

export interface GameResult {
  letter: string;
  status: number;
}

@Injectable({ providedIn: 'root' })
export class GameStateService {
  uuid: string | null = null;
  word: string | null = null;
  attemptsLeft: number | null = null;
  results: GameResult[][] = [];
  inputValue: string[] = [];
  currentAttempt: number = 0;
  lose:boolean = false
  win:boolean = false


  setGame(data: any) {
    this.uuid = data.uuid;
    this.word = data.word;
    this.attemptsLeft = data['attempts-left'];
    if (data.result) {
      this.results.push(data.result);
    }
  }

  clear() {
    this.uuid = null;
    this.word = null;
    this.attemptsLeft = null;
    this.results = [];
    this.inputValue = []
    this.currentAttempt = 0
    this.lose = false
    this.win = false
  }
}