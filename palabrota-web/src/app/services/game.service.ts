import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private readonly baseUrl = '/palabrota/v1'

  constructor(private readonly http: HttpClient) {}

  newGame(word: number, attempts: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/new-game`, { word, attempts })
  }

  dailyGame(): Observable<any> {
    return this.http.get(`${this.baseUrl}/daily`)
  }

  attempt(uuid: string, word: string, attempt: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/attempt/`, { uuid, word, attempt })
  }

  searchWord(word: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search-word/${word}`)
  }
}
