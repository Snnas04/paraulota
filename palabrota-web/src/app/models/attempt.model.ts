export interface AttemptResult {
  letter: string;
  status: number;
}

export interface AttemptResponse {
  uuid: string;
  word: string;
  'attempts-left': number;
  result: AttemptResult[];
}