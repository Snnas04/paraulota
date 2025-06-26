export interface GameSettings {
  letters: number;
  attempts: number;
}

export interface GameData {
  uuid: string;
  word: string;
  'game-settings': GameSettings;
}