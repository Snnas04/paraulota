import { Injectable } from '@angular/core'
import { Router } from '@angular/router'
import { GameStateService } from './game-state.service'
import { CreateGameService } from './create-game.service'

@Injectable({ providedIn: 'root' })
export class NavigationService {
  private configuration: boolean = false
  private lastRoute: string = '/home'
  private isDaily: boolean = false

  constructor(
    private readonly gameState: GameStateService,
    private readonly router: Router
  ) {}

  goToHome() {
    this.gameState.clear()
    this.configuration = false
    this.router.navigate(['/home'])
  }

  goToGame(daily: boolean) {
    this.isDaily = daily
    this.configuration = false
    this.router.navigate(['/game'])
  }

  goToNewGame(createGame: CreateGameService) {
    this.gameState.clear()
    this.configuration = false
    createGame.startGame()
  }

  goToConfiguration() {
    if (this.configuration) {
      this.configuration = false
      this.router.navigate([this.lastRoute])
    } else {
      this.lastRoute = this.router.url
      this.configuration = true
      this.router.navigate(['/configuration'])
    }
  }

  goToSearch() {
    this.router.navigate(['/search'])
  }

  goToSearchWithWord(word: string) {
    this.router.navigate(['/search'], { queryParams: { word } })
  }

  getDaily(): boolean {
    return this.isDaily
  }
}
