import { Component, ViewChild } from '@angular/core'
import { CommonModule } from '@angular/common'
import { InputComponent } from '@components/input/input.component'
import { GameData } from '@models/game.model'
import { GameStateService } from '@services/game-state.service'
import { GameService } from '@services/game.service'
import { GameConfigurationStateService } from '@services/game-configuration-state.service'
import { Router } from '@angular/router'
import { CustomButtonComponent } from '@components/custom-button/custom-button.component'
import { NavigationService } from '@services/navigation.service'
import { LinkButtonComponent } from '@components/link-button/link-button.component'
import { CreateGameService } from '@services/create-game.service'

@Component({
  selector: 'app-game',
  imports: [
    CustomButtonComponent,
    CommonModule,
    InputComponent,
    LinkButtonComponent,
  ],
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent {
  gameData: GameData | null = null
  currentInput: string = ''
  inputFocused: boolean = false
  inputCursorPos: number = 0
  public daily: boolean = false

  fieldValues: { [key: string]: any } = {}

  @ViewChild('hiddenInput') hiddenInput!: any

  constructor(
    private readonly gameService: GameService,
    public createGame: CreateGameService,
    public gameConfigurationState: GameConfigurationStateService,
    public gameState: GameStateService,
    public router: Router,
    public navigation: NavigationService
  ) {}

  ngOnInit() {
    this.gameData = {
      uuid: this.gameConfigurationState.uuid!,
      word: this.gameConfigurationState.word!,
      'game-settings': {
        letters: this.gameConfigurationState.settings!.letters,
        attempts: this.gameConfigurationState.settings!.attempts,
      },
    }
    this.daily = this.navigation.getDaily()
    this.inputCursorPos = 0
  }

  onInputValueChange(value: string) {
    this.currentInput = value
    this.gameState.inputValue[this.gameState.currentAttempt] = value

    // Maximum allowed input length
    const maxLetters = this.gameConfigurationState.settings!.letters

    // Clamp input length to maxLetters
    if (value.length === 0) this.inputCursorPos = 0
    else if (value.length >= maxLetters) this.inputCursorPos = maxLetters
    else this.inputCursorPos = value.length
  }

  onInputSelectionChange(pos: number) {
    const max = this.gameConfigurationState.settings!.letters - 1
    if (pos < 0) this.inputCursorPos = 0
    else if (pos > max) this.inputCursorPos = max
    else this.inputCursorPos = pos
  }

  onInputFocus() {
    this.inputFocused = true
  }

  onInputBlur() {
    this.inputFocused = false
  }

  onInputKeyDown(event: KeyboardEvent) {
    const max = this.gameConfigurationState.settings!.letters - 1
    if (event.key === 'ArrowLeft') {
      if (this.inputCursorPos > 0) {
        this.inputCursorPos--
        this.setInputCursorPos(this.inputCursorPos)
      }
      event.preventDefault()
    } else if (event.key === 'ArrowRight') {
      if (this.inputCursorPos < max) {
        this.inputCursorPos++
        this.setInputCursorPos(this.inputCursorPos)
      }
      event.preventDefault()
    }
  }

  setInputCursorPos(pos: number) {
    if (this.hiddenInput?.setSelection) this.hiddenInput.setSelection(pos)
  }

  sendAttempt() {
    const wordAttempt: string = this.currentInput.toLocaleLowerCase()
    if (this.daily && this.isDailyWordValid(wordAttempt.trim()))
      this.doAttempt(wordAttempt)
    else if (this.isWordValid(wordAttempt.trim())) this.doAttempt(wordAttempt)
  }

  doAttempt(wordAttempt: string) {
    this.gameState.inputValue[this.gameState.currentAttempt] = wordAttempt
    this.gameState.currentAttempt++
    this.currentInput = ''
    this.onInputSelectionChange(0)
    this.gameService
      .attempt(
        this.gameConfigurationState.uuid!,
        wordAttempt,
        this.gameState.currentAttempt
      )
      .subscribe({
        next: (response) => {
          this.gameState.setGame(response)
          this.checkResult(this.gameState)
        },
        error: (err) => {
          alert('Error al enviar la palabra')
        },
      })
  }

  isWordValid(word: string): boolean {
    const letters = this.gameConfigurationState.settings!.letters
    const regex = new RegExp(`^[a-záéíóúüñ]{${letters}}$`, 'i')
    return regex.test(word)
  }

  isDailyWordValid(word: string): boolean {
    return word.length >= this.gameConfigurationState.settings!.letters
  }

  checkResult(attemptResult: GameStateService) {
    if (this.rightWord(attemptResult)) this.gameState.win = true
    if (attemptResult.attemptsLeft == 0 && !this.gameState.win)
      this.gameState.lose = true
  }

  rightWord(attemptResult: GameStateService): boolean {
    const lastAttempt = attemptResult.results[attemptResult.results.length - 1]
    return !lastAttempt
      ? false
      : lastAttempt.every((letter) => letter.status === 2)
  }

  getCellClass(attemptIndex: number, letterIndex: number): string {
    const attemptResults = this.gameState.results[attemptIndex]
    let classes = ''
    if (attemptResults) {
      const result = attemptResults[letterIndex]
      if (result) {
        switch (result.status) {
          case 2:
            classes = 'cell-correct'
            break
          case 1:
            classes = 'cell-present'
            break
          case 0:
            classes = 'cell-absent'
            break
        }
      }
    }
    // Resalta la celda activa
    if (
      this.inputFocused &&
      attemptIndex === this.gameState.currentAttempt &&
      letterIndex === this.inputCursorPos
    ) {
      classes += ' cell-active'
    }
    return classes.trim()
  }

  focusHiddenInput() {
    this.hiddenInput?.focusInput()
  }
}
