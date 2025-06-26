import { Injectable } from '@angular/core'
import { GameService } from './game.service'
import { SettingsService } from './settings.service'
import { NavigationService } from './navigation.service'
import { GameConfigurationStateService } from './game-configuration-state.service'

@Injectable({
  providedIn: 'root',
})
export class CreateGameService {
  constructor(
    private readonly gameService: GameService,
    private readonly settings: SettingsService,
    private readonly gameConfigurationState: GameConfigurationStateService,
    private readonly navigation: NavigationService
  ) {}

  startGame() {
    this.gameService
      .newGame(this.settings.letters, this.settings.attempts)
      .subscribe({
        next: (response) => {
          this.gameConfigurationState.setGame(response)
          this.navigation.goToGame(false)
        },
        error: (err) => {
          alert('Error al iniciar el juego')
        },
      })
  }

  startDaily() {
    this.gameService.dailyGame().subscribe({
      next: (response) => {
        this.gameConfigurationState.setGame(response)
        this.navigation.goToGame(true)
      },
      error: (err) => {
        alert('Error al iniciar el juego')
      },
    })
  }
}
