import { Component } from '@angular/core'
import { CreateGameService } from '@services/create-game.service'
import { NavigationService } from '@services/navigation.service'

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  constructor(
    private readonly navigation: NavigationService,
    private readonly createGame: CreateGameService
  ) {}

  startGame() {
    this.createGame.startGame()
  }

  startDaily() {
    this.createGame.startDaily()
  }

  searchWord() {
    this.navigation.goToSearch()
  }
}
