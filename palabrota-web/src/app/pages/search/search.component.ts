import { CommonModule } from '@angular/common'
import { Component } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { ButtonComponent } from '@components/button/button.component'
import { InputComponent } from '@components/input/input.component'
import { GameService } from '@services/game.service'

@Component({
  selector: 'app-search',
  imports: [InputComponent, ButtonComponent, CommonModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css',
})
export class SearchComponent {
  value: string = ''
  wordData: any = null

  constructor(
    private readonly gameService: GameService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      if (params['word']) {
        this.value = params['word']
        this.showWordMeaning(this.value)
      }
    })
  }

  showWordMeaning(word: string) {
    this.wordData = null
    this.gameService.searchWord(word).subscribe({
      next: (response) => {
        console.log(response)
        this.wordData = response
      },
      error: (err) => {
        alert('Error al buscar la palabra')
      },
    })
  }
}
